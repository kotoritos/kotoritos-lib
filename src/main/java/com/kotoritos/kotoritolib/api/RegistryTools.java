package com.kotoritos.kotoritolib.api;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.function.Supplier;

/**
 * Shared registry helpers for mods that depend on Kotorito Lib.
 */
public final class RegistryTools {
    private RegistryTools() {
    }

    /**
     * Registers an object in any Minecraft registry with a provided identifier.
     *
     * @param registry target registry.
     * @param id       identifier to register with.
     * @param entry    object to register.
     * @param <T>      registry entry type.
     * @return the same object that was registered.
     */
    public static <T> T register(Registry<T> registry, Identifier id, T entry) {
        return Registry.register(registry, id, entry);
    }

    /**
     * Registers a lazily-created object using namespace + path.
     */
    public static <T> T register(Registry<T> registry, String namespace, String path, Supplier<T> supplier) {
        return register(registry, Identifier.of(namespace, path), supplier.get());
    }

    /**
     * Registers a lazily-created object in Kotorito Lib namespace.
     */
    public static <T> T registerLib(Registry<T> registry, String path, Supplier<T> supplier) {
        return register(registry, ModId.id(path), supplier.get());
    }

    /**
     * Resolves whether an item id already exists in the item registry.
     *
     * @param id item identifier to test.
     * @return true when item id exists, otherwise false.
     */
    public static boolean itemExists(Identifier id) {
        return Registries.ITEM.containsId(id);
    }
}
