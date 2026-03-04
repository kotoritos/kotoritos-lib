package com.kotoritos.kotoritolib.api.scheduler;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.server.MinecraftServer;

import java.util.ArrayList;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Simple per-server task scheduler for delayed and repeating work.
 */
public final class ServerTaskScheduler {
    private static final Map<MinecraftServer, List<ScheduledTask>> TASKS_BY_SERVER = new IdentityHashMap<>();
    private static boolean bootstrapped;

    private ServerTaskScheduler() {
    }

    /**
     * Registers scheduler lifecycle/tick hooks. Safe to call multiple times.
     */
    public static synchronized void bootstrap() {
        if (bootstrapped) {
            return;
        }

        ServerTickEvents.END_SERVER_TICK.register(ServerTaskScheduler::tick);
        ServerLifecycleEvents.SERVER_STOPPING.register(ServerTaskScheduler::clear);
        bootstrapped = true;
    }

    /**
     * Schedules a one-shot task to execute after {@code delayTicks}.
     */
    public static UUID schedule(MinecraftServer server, long delayTicks, Runnable action) {
        return scheduleRepeating(server, delayTicks, 0L, action);
    }

    /**
     * Schedules a repeating task.
     *
     * @param server       current server instance.
     * @param delayTicks   ticks before first execution.
     * @param intervalTick repeating interval in ticks. Use {@code 0} for one-shot tasks.
     * @param action       task body.
     * @return task id that can be used with {@link #cancel(MinecraftServer, UUID)}.
     */
    public static UUID scheduleRepeating(MinecraftServer server, long delayTicks, long intervalTick, Runnable action) {
        long startTick = server.getTicks() + Math.max(0L, delayTicks);
        ScheduledTask task = new ScheduledTask(startTick, Math.max(0L, intervalTick), action);

        synchronized (TASKS_BY_SERVER) {
            TASKS_BY_SERVER.computeIfAbsent(server, ignored -> new ArrayList<>()).add(task);
        }

        return task.id();
    }

    /**
     * Cancels a scheduled task.
     */
    public static boolean cancel(MinecraftServer server, UUID taskId) {
        synchronized (TASKS_BY_SERVER) {
            List<ScheduledTask> tasks = TASKS_BY_SERVER.get(server);
            if (tasks == null) {
                return false;
            }

            return tasks.removeIf(task -> task.id().equals(taskId));
        }
    }

    /**
     * Clears all tasks for a server.
     */
    public static void clear(MinecraftServer server) {
        synchronized (TASKS_BY_SERVER) {
            TASKS_BY_SERVER.remove(server);
        }
    }

    private static void tick(MinecraftServer server) {
        synchronized (TASKS_BY_SERVER) {
            List<ScheduledTask> tasks = TASKS_BY_SERVER.get(server);
            if (tasks == null || tasks.isEmpty()) {
                return;
            }

            long now = server.getTicks();
            Iterator<ScheduledTask> iterator = tasks.iterator();

            while (iterator.hasNext()) {
                ScheduledTask task = iterator.next();
                if (task.nextRunTick > now) {
                    continue;
                }

                task.action.run();

                if (task.intervalTicks <= 0L) {
                    iterator.remove();
                    continue;
                }

                task.nextRunTick = now + task.intervalTicks;
            }
        }
    }

    private static final class ScheduledTask {
        private final UUID id = UUID.randomUUID();
        private final long intervalTicks;
        private final Runnable action;
        private long nextRunTick;

        private ScheduledTask(long nextRunTick, long intervalTicks, Runnable action) {
            this.nextRunTick = nextRunTick;
            this.intervalTicks = intervalTicks;
            this.action = action;
        }

        private UUID id() {
            return id;
        }
    }
}
