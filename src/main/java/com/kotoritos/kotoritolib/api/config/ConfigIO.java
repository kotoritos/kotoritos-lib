package com.kotoritos.kotoritolib.api.config;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.function.Supplier;

/**
 * JSON config utilities for dependent mods.
 */
public final class ConfigIO {
    private ConfigIO() {
    }

    /**
     * Reads a JSON config file. If it does not exist, creates it with defaults.
     */
    public static <T> T readOrCreate(Path path, Gson gson, Class<T> type, Supplier<T> defaultSupplier) throws IOException {
        if (Files.notExists(path)) {
            T defaults = defaultSupplier.get();
            write(path, gson, defaults);
            return defaults;
        }

        try (Reader reader = Files.newBufferedReader(path)) {
            T loaded = gson.fromJson(reader, type);
            if (loaded != null) {
                return loaded;
            }
        }

        T defaults = defaultSupplier.get();
        write(path, gson, defaults);
        return defaults;
    }

    /**
     * Writes a JSON config file, creating parent directories if needed.
     */
    public static <T> void write(Path path, Gson gson, T data) throws IOException {
        Path parent = path.getParent();
        if (parent != null) {
            Files.createDirectories(parent);
        }

        try (Writer writer = Files.newBufferedWriter(path)) {
            gson.toJson(data, writer);
        }
    }
}
