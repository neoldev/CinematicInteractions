package dev.dyslexic.cinematic.mixin.compat;

import dev.dyslexic.cinematic.client.Scene;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * First-person Model draws the player's whole body under the camera. A scene has hands of its own and leans the camera
 * away from the eyes, so that body would block the work: it is left out until the scene is over.
 */
@Pseudo
@Mixin(targets = "dev.tr7zw.firstperson.LogicHandler", remap = false)
public abstract class FirstPersonModelLogicMixin {
    @Inject(method = "shouldApplyThirdPerson", at = @At("HEAD"), cancellable = true, require = 0)
    private void cinematic$noBodyDuringScene(CallbackInfoReturnable<Boolean> cir) {
        if (Scene.active()) cir.setReturnValue(false);
    }
}
