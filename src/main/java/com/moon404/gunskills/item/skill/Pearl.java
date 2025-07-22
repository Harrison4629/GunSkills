package com.moon404.gunskills.item.skill;

import com.moon404.gunskills.entity.PearlEntity;
import com.moon404.gunskills.struct.ClassType;

import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;

public class Pearl extends SkillItem
{
    public Pearl(Properties properties)
    {
        super(properties, 480, 3, ClassType.ROGUE);
        tooltips.add(Component.translatable("item.gunskills.pearl.tooltip.1"));
        tooltips.add(Component.translatable("item.gunskills.pearl.tooltip.2"));
    }

    @Override
    public boolean onToss(Player player)
    {
        if (!canUse(player)) return false;
        player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 100, 4, false, false, true));
        PearlEntity pearl = new PearlEntity(player.level(), player);
        pearl.shootFromRotation(player, player.getXRot(), player.getYRot(), 0, 1, 0);
        player.level().addFreshEntity(pearl);
        return true;
    }
}
