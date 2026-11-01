package com.sol.glfencesyncFix.client.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(targets = "com.mojang.blaze3d.opengl.GlCommandEncoder")
public abstract class GlCommandEncoderMixin {

    @Redirect(
            method = "submit",
            at = @At(
                    value = "INVOKE",
                    target = "Lorg/lwjgl/opengl/GL33C;glFenceSync(II)J"
            ),
            remap = false
    )
    private long fencefix$skipFence(int condition, int flags) {
        return 0L;
    }
}