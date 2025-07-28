package com.moon404.gunskills.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(targets = "net.minecraft.client.renderer.FogRenderer$BlindnessFogFunction")
public class BlindnessFogMixin
{
    @ModifyConstant(method = "setupFog", constant = @Constant(floatValue = 5.0F))
    public float modifyBlindnessFogRange(float value)
    {
        return 20.0F;
    }
}
