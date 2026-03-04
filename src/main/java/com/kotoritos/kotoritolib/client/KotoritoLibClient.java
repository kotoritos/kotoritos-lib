package com.kotoritos.kotoritolib.client;

import com.kotoritos.kotoritolib.KotoritoLib;
import com.kotoritos.kotoritolib.api.client.ClientTickScheduler;
import com.kotoritos.kotoritolib.api.client.PerformanceSnapshot;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;

/**
 * Client entrypoint that initializes client-side library services.
 */
public final class KotoritoLibClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ClientTickScheduler.bootstrap();
        ClientTickEvents.END_CLIENT_TICK.register(client -> PerformanceSnapshot.captureSample());
        KotoritoLib.LOGGER.info("Kotorito Lib client services initialized.");
    }
}
