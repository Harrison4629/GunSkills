package com.moon404.gunskills.item.skill;

import java.util.List;
import java.util.ArrayList;

import com.moon404.gunskills.init.GunSkillsEffects;
import com.moon404.gunskills.message.GlowMessage;
import com.moon404.gunskills.struct.ClassType;

import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class Glow extends SkillItem
{
    public static final int RADIUS = 32;
    public static final int DURATION = 3;

    public Glow(Properties properties)
    {
        super(properties, 2, ClassType.SCOUT);
        tooltips.add(Component.translatable("item.gunskills.glow.tooltip", RADIUS, DURATION));
    }

    @Override
    public boolean onToss(Player player)
    {
        if (ClassType.getClass(player) != this.classType) return false;
        if (player.hasEffect(GunSkillsEffects.SILENCE.get())) return false;
        Level level = player.level();
        List<Player> players = new ArrayList<>();
        for (Player target : level.players())
        {
            if (!target.isSpectator() && player.distanceTo(target) < RADIUS && player.getTeam() != target.getTeam())
            {
                players.add(target);
            }
        }
        if (Purify.purified(players))
        {
            player.displayClientMessage(Component.translatable("skill.gunskills.glow.purify"), true);
        }
        else
        {
            for (Player target : players)
            {
                target.addEffect(new MobEffectInstance(MobEffects.GLOWING, DURATION * 20, 0, false, false, true));
                GlowMessage.sendToTeam(player.getTeam(), target, DURATION * 20);
            }
            player.displayClientMessage(Component.translatable("skill.gunskills.glow.effect", players.size()), true);
        }
        return true;
    }
}
