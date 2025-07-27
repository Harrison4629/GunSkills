package com.moon404.gunskills.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

import net.minecraft.server.network.ServerGamePacketListenerImpl;

@Mixin(ServerGamePacketListenerImpl.class)
public class MoveCheckMixin
{
    @ModifyConstant(method = "handleMovePlayer", constant = @Constant(doubleValue = 0.0625D))
    public double playerMovedWrongly(double value)
    {
        return 1;
    }
}
