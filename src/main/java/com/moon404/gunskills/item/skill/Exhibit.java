package com.moon404.gunskills.item.skill;

import com.moon404.gunskills.entity.ExhibitEntity;
import com.moon404.gunskills.init.GunSkillsEntities;
import com.moon404.gunskills.struct.ClassType;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;

public class Exhibit extends SkillItem
{
    public static final int RADIUS = 16;
    public static final int DURATION = 3;

    public Exhibit(Properties properties)
    {
        super(properties, 40, 3, ClassType.SCOUT);
        tooltips.add(Component.translatable("item.gunskills.exhibit.tooltip.1", RADIUS));
        tooltips.add(Component.translatable("item.gunskills.exhibit.tooltip.2", DURATION));
    }

    @Override
    public void active(Player player)
    {
        ExhibitEntity exhibit = new ExhibitEntity(GunSkillsEntities.EXHHIBIT.get(), player.level());
        exhibit.user = player;
        exhibit.setPos(player.getEyePosition());
        exhibit.setNoGravity(true);
        exhibit.shootFromRotation(player, player.getXRot(), player.getYRot(), 0, 3.2F, 0);
        player.level().addFreshEntity(exhibit);
    }
}
