package com.moon404.gunskills.item.skill;

import com.moon404.gunskills.init.GunSkillsEffects;
import com.moon404.gunskills.struct.ClassType;

import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;

public class Charge extends SkillItem
{
    public static final int AMOUNT = 4;
    public static final int DURATION = 10;

    public Charge(Properties properties)
    {
        super(properties, 40, 2, ClassType.SUPPORT);
        tooltips.add(Component.translatable("item.gunskills.charge.tooltip", AMOUNT, DURATION));
    }

    @Override
    public void active(Player player)
    {
        for (Player target : player.level().players())
        {
            if (target.getTeam() == player.getTeam())
            {
                target.addEffect(new MobEffectInstance(GunSkillsEffects.CHARGE.get(), DURATION * 20, 0, false, false, true));
            }
        }
    }
}
