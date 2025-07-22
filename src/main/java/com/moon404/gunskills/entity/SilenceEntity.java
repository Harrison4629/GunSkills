package com.moon404.gunskills.entity;

import org.joml.Vector3f;
import com.moon404.gunskills.init.GunSkillsEffects;
import com.moon404.gunskills.init.GunSkillsItems;
import com.moon404.gunskills.item.skill.Silence;

import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

public class SilenceEntity extends ThrowSkillEntity
{
    public SilenceEntity(EntityType<? extends ThrowableItemProjectile> pEntityType, Level pLevel)
    {
        super(pEntityType, pLevel, Silence.RADIUS);
        color = new Vector3f(0.67F, 0, 0F);
    }

    @Override
    protected Item getDefaultItem()
    {
        return GunSkillsItems.SILENCE.get();
    }

    @Override
    protected void onPurify()
    {
        this.user.displayClientMessage(Component.translatable("skill.gunskills.silence.purify"), true);
    }

    @Override
    protected void onEffect()
    {
        for (Player player : lastTickPlayers)
        {
            player.addEffect(new MobEffectInstance(GunSkillsEffects.SILENCE.get(), Silence.DURATION * 20, 0, false, false, true));
            player.hurt(player.damageSources().playerAttack(this.user), Silence.DAMAGE);
        }
        this.user.displayClientMessage(Component.translatable("skill.gunskills.silence.effect", lastTickPlayers.size()), true);
    }
}
