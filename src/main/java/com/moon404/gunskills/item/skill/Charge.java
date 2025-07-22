package com.moon404.gunskills.item.skill;

import com.moon404.gunskills.struct.ClassType;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;

public class Charge extends SkillItem
{
    public static final int AMOUNT = 25;

    public Charge(Properties properties)
    {
        super(properties, 480, 2, ClassType.SUPPORT);
        tooltips.add(Component.translatable("item.gunskills.charge.tooltip", AMOUNT));
    }

    @Override
    public boolean onToss(Player player)
    {
        if (!canUse(player)) return false;
        for (Player target : player.level().players())
        {
            if (target.getTeam() == player.getTeam())
            {
                int oldlevel = target.experienceLevel;
                target.giveExperiencePoints(AMOUNT * 100);
                int newlevel = target.experienceLevel;
                if (newlevel > oldlevel)
                {
                    target.setAbsorptionAmount(target.getAbsorptionAmount() + 4);
                }
            }
        }
        return true;
    }
}
