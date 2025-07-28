package com.moon404.gunskills.item.skill;

import com.moon404.gunskills.init.GunSkillsItems;
import com.moon404.gunskills.struct.ClassType;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;

public class ShieldBottle extends SkillItem
{
    public ShieldBottle(Properties properties)
    {
        super(properties, 40, 2, ClassType.SUPPORT);
        tooltips.add(Component.translatable("item.gunskills.shield_bottle.tooltip.1"));
    }

    @Override
    public void active(Player player)
    {
        for (Player target : player.level().players())
        {
            if (!target.isSpectator() && target.getTeam() == player.getTeam())
            {
                player.addItem(GunSkillsItems.SHIELD_BATTERY.get().getDefaultInstance());
            }
        }
    }
}
