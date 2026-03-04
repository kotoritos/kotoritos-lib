package com.kotoritos.kotoritolib.api.client;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

/**
 * Lightweight client-side scheduler for delayed and repeating tasks.
 */
public final class ClientTickScheduler {
    private static final List<Task> TASKS = new ArrayList<>();
    private static long tickCounter;
    private static boolean bootstrapped;

    private ClientTickScheduler() {
    }

    /**
     * Registers internal client tick hooks. Safe to call multiple times.
     */
    public static synchronized void bootstrap() {
        if (bootstrapped) {
            return;
        }

        ClientTickEvents.END_CLIENT_TICK.register(ClientTickScheduler::tick);
        bootstrapped = true;
    }

    /**
     * Schedules a one-time task.
     */
    public static UUID schedule(long delayTicks, Runnable action) {
        return scheduleRepeating(delayTicks, 0L, action);
    }

    /**
     * Schedules a repeating task.
     */
    public static UUID scheduleRepeating(long delayTicks, long intervalTicks, Runnable action) {
        Task task = new Task(tickCounter + Math.max(0L, delayTicks), Math.max(0L, intervalTicks), action);
        synchronized (TASKS) {
            TASKS.add(task);
        }
        return task.id();
    }

    /**
     * Cancels a scheduled task.
     */
    public static boolean cancel(UUID taskId) {
        synchronized (TASKS) {
            return TASKS.removeIf(task -> task.id().equals(taskId));
        }
    }

    private static void tick(MinecraftClient client) {
        tickCounter++;

        synchronized (TASKS) {
            Iterator<Task> iterator = TASKS.iterator();
            while (iterator.hasNext()) {
                Task task = iterator.next();
                if (task.nextRunTick > tickCounter) {
                    continue;
                }

                task.action.run();

                if (task.intervalTicks <= 0L) {
                    iterator.remove();
                    continue;
                }

                task.nextRunTick = tickCounter + task.intervalTicks;
            }
        }
    }

    private static final class Task {
        private final UUID id = UUID.randomUUID();
        private final long intervalTicks;
        private final Runnable action;
        private long nextRunTick;

        private Task(long nextRunTick, long intervalTicks, Runnable action) {
            this.nextRunTick = nextRunTick;
            this.intervalTicks = intervalTicks;
            this.action = action;
        }

        private UUID id() {
            return id;
        }
    }
}
