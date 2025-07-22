package com.moon404.gunskills.item.skill;

import com.moon404.gunskills.entity.SilenceEntity;
import com.moon404.gunskills.init.GunSkillsEntities;
import com.moon404.gunskills.struct.ClassType;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;

public class Silence extends SkillItem
{
    public static final int RADIUS = 8;
    public static final int DAMAGE = 4;
    public static final int DURATION = 5;

    public Silence(Properties properties)
    {
        super(properties, 480, 3, ClassType.ATTACK);
        tooltips.add(Component.translatable("item.gunskills.silence.tooltip.1", RADIUS));
        tooltips.add(Component.translatable("item.gunskills.silence.tooltip.2", DAMAGE, DURATION));
    }

    @Override
    public boolean onToss(Player player)
    {
        if (!canUse(player)) return false;
        SilenceEntity silence = new SilenceEntity(GunSkillsEntities.SILENCE.get(), player.level());
        silence.user = player;
        silence.setPos(player.getEyePosition());
        silence.setNoGravity(true);
        silence.shootFromRotation(player, player.getXRot(), player.getYRot(), 0, 3.2F, 0);
        player.level().addFreshEntity(silence);
        return true;
    }
}
