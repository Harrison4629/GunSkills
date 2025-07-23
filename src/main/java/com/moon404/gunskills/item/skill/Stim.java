package com.moon404.gunskills.item.skill;

import com.moon404.gunskills.struct.ClassType;

import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;

public class Stim extends SkillItem
{
    public static final int AMOUNT = 4;
    public static final int DURATION = 5;

    public Stim(Properties properties)
    {
        super(properties, 40, 2, ClassType.ROGUE);
        tooltips.add(Component.translatable("item.gunskills.stim.tooltip.1", AMOUNT));
        tooltips.add(Component.translatable("item.gunskills.stim.tooltip.2", DURATION));
    }

    @Override
    public boolean onToss(Player player)
    {
        if (!canUse(player)) return false;
        float hp = player.getHealth();
        hp -= AMOUNT;
        if (hp < 1) hp = 1;
        player.setHealth(hp);
        player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, DURATION * 20, 2, false, false, true));
        player.addEffect(new MobEffectInstance(MobEffects.JUMP, DURATION * 20, 1, false, false, true));
        return true;
    }
}
