package com.sol.glfencesyncFix.client.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.lwjgl.opengl.GL33C;

@Mixin(targets = "com.mojang.blaze3d.opengl.GlCommandEncoder")
public abstract class GlCommandEncoderMixin {

    @Redirect(
            method = "submit",
            at = @At(
                    value = "INVOKE",
                    target = "Lorg/lwjgl/opengl/GL33C;glFenceSync(II)J",
                    remap = false
            ),
            require = 1
    )
    private long fencefix$redirectFence(int condition, int flags) {
        return GL33C.glFenceSync(condition, flags);
    }
}