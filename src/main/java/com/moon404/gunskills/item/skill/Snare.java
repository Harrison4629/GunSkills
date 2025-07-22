package com.moon404.gunskills.item.skill;

import com.moon404.gunskills.entity.SnareEntity;
import com.moon404.gunskills.init.GunSkillsEntities;
import com.moon404.gunskills.struct.ClassType;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;

public class Snare extends SkillItem
{
    public static final int RADIUS = 8;
    public static final int DAMAGE = 4;
    public static final int DURATION = 5;

    public Snare(Properties properties)
    {
        super(properties, 480, 3, ClassType.ATTACK);
        tooltips.add(Component.translatable("item.gunskills.snare.tooltip.1", RADIUS));
        tooltips.add(Component.translatable("item.gunskills.snare.tooltip.2", DAMAGE, DURATION));
    }

    @Override
    public boolean onToss(Player player)
    {
        if (!canUse(player)) return false;
        SnareEntity snare = new SnareEntity(GunSkillsEntities.SNARE.get(), player.level());
        snare.user = player;
        snare.setPos(player.getEyePosition());
        snare.setNoGravity(true);
        snare.shootFromRotation(player, player.getXRot(), player.getYRot(), 0, 3.2F, 0);
        player.level().addFreshEntity(snare);
        return true;
    }
}
