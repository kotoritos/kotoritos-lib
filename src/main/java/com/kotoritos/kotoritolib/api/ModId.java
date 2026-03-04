package com.kotoritos.kotoritolib.api;

import com.kotoritos.kotoritolib.KotoritoLib;
import net.minecraft.util.Identifier;

/**
 * Utility for creating identifiers in a safe, consistent way.
 */
public final class ModId {
    private ModId() {
    }

    /**
     * Creates an {@link Identifier} using Kotorito Lib's namespace.
     *
     * @param path path portion of the identifier.
     * @return identifier in the form {@code kotorito_lib:path}.
     */
    public static Identifier id(String path) {
        return Identifier.of(KotoritoLib.MOD_ID, path);
    }

    /**
     * Creates an identifier using a custom namespace.
     *
     * @param namespace namespace of the identifier.
     * @param path      path portion of the identifier.
     * @return identifier in the form {@code namespace:path}.
     */
    public static Identifier id(String namespace, String path) {
        return Identifier.of(namespace, path);
    }
}
