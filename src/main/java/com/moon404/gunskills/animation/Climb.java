package com.moon404.gunskills.animation;

import com.moon404.gunskills.GunSkills;

import net.minecraft.client.Minecraft;
import net.minecraft.client.Options;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent.Phase;
import net.minecraftforge.event.TickEvent.PlayerTickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@EventBusSubscriber(modid = GunSkills.MODID, value = Dist.CLIENT, bus = Bus.FORGE)
public class Climb
{
    public static int tickCount = 0;
    public static boolean jumping = false;

    @SubscribeEvent
    public static void onPlayerTick(PlayerTickEvent event)
    {
        if (event.phase != Phase.START) return;
        if (event.player instanceof LocalPlayer player)
        {
            Minecraft minecraft = Minecraft.getInstance();
            Options options = minecraft.options;
            Vec3 delta = player.getDeltaMovement();
            if (player.horizontalCollision && !player.onGround() && delta.y <= 0.1 && tickCount < 30 && options.keyUp.isDown())
            {
                tickCount++;
                Vec3 pos = player.position();
                Vec3 look = player.getLookAngle().multiply(1, 0, 1).normalize();
                BlockHitResult blockHitResult = player.level().clip(new ClipContext(pos, pos.add(look), ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, player));

                if (blockHitResult.getType() != HitResult.Type.BLOCK && (options.keyLeft.isDown() || options.keyRight.isDown()))
                {
                    float speed = player.getSpeed() * 2;
                    Vec3 movevec = new Vec3(0, 0, 0);
                    if (delta.x == 0) movevec = look.multiply(0, 0, 1).normalize().scale(speed);
                    else if (delta.z == 0) movevec = look.multiply(1, 0, 0).normalize().scale(speed);
                    player.setDeltaMovement(movevec);

                    if (!jumping && options.keyJump.isDown())
                    {
                        jumping = true;
                        Vec3 jumpvec = look.scale(speed * 2);
                        double dy = 0.3;
                        if (player.hasEffect(MobEffects.JUMP)) dy *= 1.2 + 0.2 * player.getEffect(MobEffects.JUMP).getAmplifier();
                        jumpvec = jumpvec.add(0, dy, 0);
                        player.setDeltaMovement(jumpvec);
                    }
                }
                else
                {
                    player.setDeltaMovement(delta.x, 0.1, delta.z);
                }
            }
            if (player.onGround())
            {
                tickCount = 0;
                jumping = false;
            }
        }
    }
}
