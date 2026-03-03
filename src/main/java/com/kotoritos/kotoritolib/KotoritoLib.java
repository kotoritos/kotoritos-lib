package com.kotoritos.kotoritolib;

import com.kotoritos.kotoritolib.api.lifecycle.ServerStartedCallback;
import com.kotoritos.kotoritolib.api.lifecycle.ServerStoppingCallback;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Main entrypoint for Kotorito Lib.
 *
 * <p>This mod is designed as a reusable library for the Kotoritos mod ecosystem.
 * Keep this initializer small and expose reusable features through the public API packages.</p>
 */
public final class KotoritoLib implements ModInitializer {
    public static final String MOD_ID = "kotorito_lib";
    public static final String MOD_NAME = "Kotorito Lib";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_NAME);

    @Override
    public void onInitialize() {
        registerLifecycleBridges();
        LOGGER.info("{} initialized: lifecycle bridge and API utilities are available.", MOD_NAME);
    }

    private static void registerLifecycleBridges() {
        ServerLifecycleEvents.SERVER_STARTED.register(server -> {
            LOGGER.info("Dispatching Kotorito Lib SERVER_STARTED callbacks.");
            ServerStartedCallback.EVENT.invoker().onServerStarted(server);
        });

        ServerLifecycleEvents.SERVER_STOPPING.register(server -> {
            LOGGER.info("Dispatching Kotorito Lib SERVER_STOPPING callbacks.");
            ServerStoppingCallback.EVENT.invoker().onServerStopping(server);
        });
    }
}
