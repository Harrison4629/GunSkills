package com.moon404.gunskills.item.skill;

import java.util.List;

import com.moon404.gunskills.struct.ClassType;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public class Purify extends SkillItem
{
    public Purify(Properties properties)
    {
        super(properties, 20, 1, ClassType.SUPPORT);
        tooltips.add(Component.translatable("item.gunskills.purify.tooltip.1"));
        tooltips.add(Component.translatable("item.gunskills.purify.tooltip.2"));
    }

    public static boolean purified(List<Player> players)
    {
        for (Player player : players)
        {
            ItemStack offhandStack = player.getOffhandItem();
            if (offhandStack.getItem() instanceof Purify item && item.canUse(player))
            {
                item.enterCooldown(player);
                player.displayClientMessage(Component.translatable("skill.gunskills.purify.effect"), true);
                return true;
            }
        }
        return false;
    }
}
