package com.moon404.gunskills.item.skill;

import com.moon404.gunskills.init.GunSkillsEffects;
import com.moon404.gunskills.message.GlowMessage;
import com.moon404.gunskills.struct.ClassType;

import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;

public class Scan extends SkillItem
{
    public static final int DURATION = 3;

    public Scan(Properties properties)
    {
        super(properties, 2, ClassType.SCOUT);
        tooltips.add(Component.translatable("item.gunskills.scan.tooltip", DURATION));
    }

    @Override
    public boolean onToss(Player player)
    {
        if (ClassType.getClass(player) != this.classType) return false;
        if (player.hasEffect(GunSkillsEffects.SILENCE.get())) return false;
        double mindis = Float.MAX_VALUE;
        Player nearest = null;
        for (Player target : player.level().players())
        {
            if (!target.isSpectator() && target.getTeam() != player.getTeam())
            {
                double dis = target.distanceTo(player);
                if (dis < mindis)
                {
                    mindis = dis;
                    nearest = target;
                }
            }
        }
        if (nearest == null)
        {
            Component component = Component.translatable("skill.gunskills.scan.fail");
            player.displayClientMessage(component, true);
        }
        else
        {
            Component component = Component.translatable("skill.gunskills.scan.effect", (int)mindis);
            player.displayClientMessage(component, true);
            nearest.addEffect(new MobEffectInstance(MobEffects.GLOWING, DURATION * 20, 0, false, false, false));
            GlowMessage.sendToTeam(player.getTeam(), nearest, DURATION * 20);
        }
        return true;
    }
}
