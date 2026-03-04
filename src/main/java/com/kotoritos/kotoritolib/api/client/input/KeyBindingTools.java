package com.kotoritos.kotoritolib.api.client.input;

import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import org.lwjgl.glfw.GLFW;

/**
 * Convenience methods for keybinding registration.
 */
public final class KeyBindingTools {
    private KeyBindingTools() {
    }

    /**
     * Registers a keybind with a default GLFW key code.
     */
    public static KeyBinding register(String translationKey, int defaultKeyCode, String category) {
        KeyBinding keyBinding = new KeyBinding(translationKey, defaultKeyCode, category);
        return KeyBindingHelper.registerKeyBinding(keyBinding);
    }

    /**
     * Registers an unbound keybind.
     */
    public static KeyBinding registerUnbound(String translationKey, String category) {
        return register(translationKey, GLFW.GLFW_KEY_UNKNOWN, category);
    }
}
