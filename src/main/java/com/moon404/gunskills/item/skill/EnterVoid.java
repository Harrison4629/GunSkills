package com.moon404.gunskills.item.skill;

import com.moon404.gunskills.init.GunSkillsEffects;
import com.moon404.gunskills.struct.ClassType;

import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public class EnterVoid extends SkillItem
{
    public static final int DURATION = 5;

    public EnterVoid(Properties properties)
    {
        super(properties, 2, ClassType.ROGUE);
        tooltips.add(Component.translatable("item.gunskills.void.tooltip.1", DURATION));
        tooltips.add(Component.translatable("item.gunskills.void.tooltip.2"));
        tooltips.add(Component.translatable("item.gunskills.void.tooltip.3"));
        tooltips.add(Component.translatable("item.gunskills.void.tooltip.4"));
        tooltips.add(Component.translatable("item.gunskills.void.tooltip.5"));
    }

    private static int getFreeSlot(Inventory inventory)
    {
        for (int i = 9; i < inventory.items.size(); i++)
        {
            if (inventory.items.get(i).isEmpty())
            {
                return i;
            }
        }
        return -1;
    }

    @Override
    public boolean onToss(Player player)
    {
        if (ClassType.getClass(player) != this.classType) return false;
        if (player.hasEffect(GunSkillsEffects.SILENCE.get())) return false;
        Inventory inventory = player.getInventory();
        int freeBagSlot = getFreeSlot(inventory);
        if (freeBagSlot >= 0)
        {
            ItemStack itemStack = player.getMainHandItem();
            if (!itemStack.isEmpty())
            {
                inventory.setItem(freeBagSlot, itemStack.copy());
                inventory.removeItem(itemStack);
            }
        }
        freeBagSlot = getFreeSlot(inventory);
        if (freeBagSlot >= 0)
        {
            ItemStack itemStack = player.getOffhandItem();
            if (!itemStack.isEmpty())
            {
                inventory.setItem(freeBagSlot, itemStack.copy());
                inventory.removeItem(itemStack);
            }
        }
        player.addEffect(new MobEffectInstance(MobEffects.INVISIBILITY, DURATION * 20, 0, false, false, true));
        player.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, DURATION * 20, 0, false, false, true));
        return true;
    }
}
