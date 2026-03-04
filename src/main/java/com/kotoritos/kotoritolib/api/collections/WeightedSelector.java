package com.kotoritos.kotoritolib.api.collections;

import net.minecraft.util.math.random.Random;

import java.util.ArrayList;
import java.util.List;

/**
 * Utility to select weighted entries (loot tables, random rewards, etc.).
 */
public final class WeightedSelector<T> {
    private final List<Entry<T>> entries = new ArrayList<>();
    private int totalWeight;

    /**
     * Adds an entry with weight.
     */
    public WeightedSelector<T> add(T value, int weight) {
        if (weight <= 0) {
            throw new IllegalArgumentException("weight must be > 0");
        }

        entries.add(new Entry<>(value, weight));
        totalWeight += weight;
        return this;
    }

    /**
     * Picks one entry according to cumulative weight.
     */
    public T pick(Random random) {
        if (entries.isEmpty()) {
            throw new IllegalStateException("No weighted entries were added.");
        }

        int target = random.nextInt(totalWeight);
        int cumulative = 0;

        for (Entry<T> entry : entries) {
            cumulative += entry.weight;
            if (target < cumulative) {
                return entry.value;
            }
        }

        return entries.get(entries.size() - 1).value;
    }

    public int size() {
        return entries.size();
    }

    private record Entry<T>(T value, int weight) {
    }
}
