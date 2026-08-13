package com.aimoptimizer.mixin;

import com.aimoptimizer.AimOptimizerMod;
import com.aimoptimizer.config.AimConfig;
import net.minecraft.client.Mouse;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(Mouse.class)
public class MouseHandlerMixin {

    @ModifyVariable(
            method = "updateMouse",
            at = @At(
                    value = "FIELD",
                    target = "Lnet/minecraft/client/Mouse;cursorDeltaY:D",
                    ordinal = 0,
                    shift = At.Shift.AFTER
            ),
            ordinal = 1
    )
    private double stabilizeYAxis(double rawDeltaY) {
        AimConfig cfg = AimOptimizerMod.CONFIG;

        if (!cfg.enabled) {
            cfg.smoothedDY = rawDeltaY;
            return rawDeltaY;
        }

        double abs = Math.abs(rawDeltaY);

        if (abs > cfg.bypassThreshold) {
            cfg.smoothedDY = rawDeltaY;
            return rawDeltaY;
        }

        cfg.smoothedDY = cfg.alpha * rawDeltaY + (1.0 - cfg.alpha) * cfg.smoothedDY;
        return cfg.smoothedDY;
    }
}
