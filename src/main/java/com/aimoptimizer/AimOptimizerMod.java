package com.aimoptimizer;

import com.aimoptimizer.config.AimConfig;
import com.aimoptimizer.keybind.KeybindHandler;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AimOptimizerMod implements ClientModInitializer {

    public static final String MOD_ID = "aimoptimizer";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    public static final AimConfig CONFIG = new AimConfig();

    @Override
    public void onInitializeClient() {
        KeybindHandler.register();
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            KeybindHandler.tick(client);
        });
        LOGGER.info("[AimOptimizer] Y-axis stabilizer loaded. Toggle: R-SHIFT");
    }
}
