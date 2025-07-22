package com.moon404.gunskills.item.skill;

import com.moon404.gunskills.entity.TotemEntity;
import com.moon404.gunskills.init.GunSkillsEntities;
import com.moon404.gunskills.struct.ClassType;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;

public class Totem extends SkillItem
{
    public static final int DURATION = 10;

    public Totem(Properties properties)
    {
        super(properties, 480, 2, ClassType.ATTACK);
        tooltips.add(Component.translatable("item.gunskills.totem.tooltip.1"));
        tooltips.add(Component.translatable("item.gunskills.totem.tooltip.2", DURATION));
        tooltips.add(Component.translatable("item.gunskills.totem.tooltip.3"));
    }

    @Override
    public boolean onToss(Player player)
    {
        if (!canUse(player)) return false;
        if (player.getAbsorptionAmount() == 0) return false;
        TotemEntity totem = new TotemEntity(GunSkillsEntities.TOTEM.get(), player.level());
        totem.setPos(player.position());
        totem.player = player;
        player.level().addFreshEntity(totem);
        return true;
    }
}
