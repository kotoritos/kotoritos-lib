package com.kotoritos.kotoritolib.api.lifecycle;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.server.MinecraftServer;

/**
 * Public event for mods that want to run logic when the dedicated/integrated server is fully started.
 */
@FunctionalInterface
public interface ServerStartedCallback {
    Event<ServerStartedCallback> EVENT = EventFactory.createArrayBacked(ServerStartedCallback.class,
            listeners -> server -> {
                for (ServerStartedCallback listener : listeners) {
                    listener.onServerStarted(server);
                }
            });

    /**
     * Called when the server has started.
     *
     * @param server active Minecraft server instance.
     */
    void onServerStarted(MinecraftServer server);
}
