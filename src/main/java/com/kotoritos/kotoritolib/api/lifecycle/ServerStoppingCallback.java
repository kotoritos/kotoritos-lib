package com.kotoritos.kotoritolib.api.lifecycle;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.server.MinecraftServer;

/**
 * Public event for mods that want to run cleanup logic before the server stops.
 */
@FunctionalInterface
public interface ServerStoppingCallback {
    Event<ServerStoppingCallback> EVENT = EventFactory.createArrayBacked(ServerStoppingCallback.class,
            listeners -> server -> {
                for (ServerStoppingCallback listener : listeners) {
                    listener.onServerStopping(server);
                }
            });

    /**
     * Called when the server is stopping.
     *
     * @param server active Minecraft server instance.
     */
    void onServerStopping(MinecraftServer server);
}
