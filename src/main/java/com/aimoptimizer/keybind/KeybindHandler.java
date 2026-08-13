package com.aimoptimizer.keybind;

import com.aimoptimizer.AimOptimizerMod;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;

public class KeybindHandler {

    private static KeyBinding toggleKey;

    public static void register() {
        toggleKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.aimoptimizer.toggle",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_RIGHT_SHIFT,
                "category.aimoptimizer"
        ));
    }

    public static void tick(MinecraftClient client) {
        while (toggleKey.wasPressed()) {
            AimOptimizerMod.CONFIG.enabled = !AimOptimizerMod.CONFIG.enabled;
            AimOptimizerMod.CONFIG.smoothedDY = 0.0;
            if (client.player != null) {
                String state = AimOptimizerMod.CONFIG.enabled ? "§aON" : "§cOFF";
                client.player.sendMessage(
                        Text.literal("§7[AimOptimizer] Y-Stabilizer: " + state),
                        true
                );
            }
        }
    }
}
