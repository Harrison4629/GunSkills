package com.moon404.gunskills.item.skill;

import com.moon404.gunskills.entity.HealthBottleEntity;
import com.moon404.gunskills.init.GunSkillsEntities;
import com.moon404.gunskills.struct.ClassType;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;

public class HealthBottle extends SkillItem
{
    public static final int AMOUNT = 2;
    public static final int DURATION = 2;

    public HealthBottle(Properties properties)
    {
        super(properties, 480, 2, ClassType.SUPPORT);
        tooltips.add(Component.translatable("item.gunskills.health_bottle.tooltip.1"));
        tooltips.add(Component.translatable("item.gunskills.health_bottle.tooltip.2", AMOUNT, DURATION));
    }

    @Override
    public boolean onToss(Player player)
    {
        if (!canUse(player)) return false;
        for (Player target : player.level().players())
        {
            if (!target.isSpectator() && target.getTeam() == player.getTeam())
            {
                HealthBottleEntity bottle = new HealthBottleEntity(GunSkillsEntities.HEALTH_BOTTLE.get(), target.level());
                bottle.setPos(target.position());
                target.level().addFreshEntity(bottle);
            }
        }
        return true;
    }
}
