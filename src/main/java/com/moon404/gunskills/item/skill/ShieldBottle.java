package com.moon404.gunskills.item.skill;

import com.moon404.gunskills.entity.ShieldBottleEntity;
import com.moon404.gunskills.init.GunSkillsEffects;
import com.moon404.gunskills.init.GunSkillsEntities;
import com.moon404.gunskills.struct.ClassType;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;

public class ShieldBottle extends SkillItem
{
    public static final int AMOUNT = 2;
    public static final int DURATION = 2;

    public ShieldBottle(Properties properties)
    {
        super(properties, 2, ClassType.SUPPORT);
        tooltips.add(Component.translatable("item.gunskills.shield_bottle.tooltip.1"));
        tooltips.add(Component.translatable("item.gunskills.shield_bottle.tooltip.2", AMOUNT, DURATION));
    }

    @Override
    public boolean onToss(Player player)
    {
        if (ClassType.getClass(player) != this.classType) return false;
        if (player.hasEffect(GunSkillsEffects.SILENCE.get())) return false;
        for (Player target : player.level().players())
        {
            if (!target.isSpectator() && target.getTeam() == player.getTeam())
            {
                ShieldBottleEntity bottle = new ShieldBottleEntity(GunSkillsEntities.SHIELD_BOTTLE.get(), target.level());
                bottle.setPos(target.position());
                target.level().addFreshEntity(bottle);
            }
        }
        return true;
    }
}
