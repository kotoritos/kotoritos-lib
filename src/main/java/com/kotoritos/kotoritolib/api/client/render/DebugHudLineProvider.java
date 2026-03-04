package com.kotoritos.kotoritolib.api.client.render;

import com.kotoritos.kotoritolib.api.client.PerformanceSnapshot;

import java.util.ArrayList;
import java.util.List;

/**
 * Provides preformatted lines suitable for custom debug overlays.
 */
public final class DebugHudLineProvider {
    private DebugHudLineProvider() {
    }

    /**
     * Builds a small set of lines with quick performance diagnostics.
     */
    public static List<String> defaultPerformanceLines() {
        List<String> lines = new ArrayList<>();
        lines.add("Kotorito Lib Diagnostics");
        lines.add("FPS (current): " + PerformanceSnapshot.currentFps());
        lines.add("FPS (average): " + PerformanceSnapshot.averageFps());
        lines.add("FPS limit: " + PerformanceSnapshot.maxFramerateLimit());
        return lines;
    }
}
