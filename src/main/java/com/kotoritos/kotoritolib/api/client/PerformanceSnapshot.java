package com.kotoritos.kotoritolib.api.client;

import net.minecraft.client.MinecraftClient;

/**
 * Utility with quick access to client performance metrics.
 */
public final class PerformanceSnapshot {
    private static final int SMOOTHING_WINDOW = 120;
    private static final int[] SAMPLES = new int[SMOOTHING_WINDOW];

    private static int currentFps;
    private static int averageFps;
    private static int sampleCount;
    private static int sampleIndex;
    private static long rollingTotal;

    private PerformanceSnapshot() {
    }

    /**
     * Captures one sample from the current client state.
     */
    public static void captureSample() {
        int fps = MinecraftClient.getCurrentFps();
        currentFps = fps;

        if (sampleCount < SMOOTHING_WINDOW) {
            sampleCount++;
        } else {
            rollingTotal -= SAMPLES[sampleIndex];
        }

        SAMPLES[sampleIndex] = fps;
        rollingTotal += fps;
        sampleIndex = (sampleIndex + 1) % SMOOTHING_WINDOW;

        averageFps = (int) Math.max(1L, rollingTotal / Math.max(1, sampleCount));
    }

    public static int currentFps() {
        return currentFps;
    }

    public static int averageFps() {
        return averageFps;
    }

    public static int maxFramerateLimit() {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client == null) {
            return 0;
        }

        return client.options.getMaxFps().getValue();
    }
}
