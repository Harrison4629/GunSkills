package com.moon404.gunskills.item.skill;

import java.util.List;

import com.moon404.gunskills.init.GunSkillsEffects;
import com.moon404.gunskills.init.GunSkillsItems;
import com.moon404.gunskills.struct.ClassType;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public class Purify extends SkillItem
{
    public Purify(Properties properties)
    {
        super(properties, 1, ClassType.SUPPORT);
        tooltips.add(Component.translatable("item.gunskills.purify.tooltip.1"));
        tooltips.add(Component.translatable("item.gunskills.purify.tooltip.2"));
    }

    public static boolean purified(List<Player> players)
    {
        for (Player player : players)
        {
            ItemStack offhandStack = player.getOffhandItem();
            if (offhandStack.getItem() == GunSkillsItems.PURIFY.get() && !player.hasEffect(GunSkillsEffects.SILENCE.get()) && ClassType.getClass(player) == ClassType.SUPPORT)
            {
                offhandStack.setCount(offhandStack.getCount() - 1);
                player.displayClientMessage(Component.translatable("skill.gunskills.purify.effect"), true);
                return true;
            }
        }
        return false;
    }
}
