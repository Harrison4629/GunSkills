package com.moon404.gunskills.item.skill;

import com.moon404.gunskills.entity.SmokeEntity;
import com.moon404.gunskills.init.GunSkillsEffects;
import com.moon404.gunskills.init.GunSkillsEntities;
import com.moon404.gunskills.struct.ClassType;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;

public class Smoke extends SkillItem
{
    public static final int RADIUS = 4;
    public static final int DURATION = 10;

    public Smoke(Properties properties)
    {
        super(properties, 3, ClassType.ATTACK);
        tooltips.add(Component.translatable("item.gunskills.smoke.tooltip.1"));
        tooltips.add(Component.translatable("item.gunskills.smoke.tooltip.2", RADIUS, DURATION));
    }
    
    @Override
    public boolean onToss(Player player)
    {
        if (ClassType.getClass(player) != this.classType) return false;
        if (player.hasEffect(GunSkillsEffects.SILENCE.get())) return false;
        SmokeEntity smoke = new SmokeEntity(GunSkillsEntities.SMOKE.get(), player.level());
        smoke.setPos(player.getEyePosition());
        smoke.setNoGravity(true);
        smoke.shootFromRotation(player, player.getXRot(), player.getYRot(), 0, 3.2F, 0);
        player.level().addFreshEntity(smoke);
        return true;
    }
}
