package com.moon404.gunskills.entity;

import org.joml.Vector3f;
import com.moon404.gunskills.init.GunSkillsItems;
import com.moon404.gunskills.item.skill.Exhibit;
import com.moon404.gunskills.message.GlowMessage;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

public class ExhibitEntity extends ThrowSkillEntity
{
    public ExhibitEntity(EntityType<? extends ThrowableItemProjectile> pEntityType, Level pLevel)
    {
        super(pEntityType, pLevel, Exhibit.RADIUS);
        color = new Vector3f(0F, 0.67F, 0F);
    }

    @Override
    protected Item getDefaultItem()
    {
        return GunSkillsItems.EXHIBIT.get();
    }

    @Override
    protected void onPurify()
    {
        this.user.displayClientMessage(Component.translatable("skill.gunskills.exhibit.purify"), true);
    }

    @Override
    protected void onEffect()
    {
        for (Player player : lastTickPlayers)
        {
            int duration = Exhibit.DURATION * 20;
            player.addEffect(new MobEffectInstance(MobEffects.GLOWING, duration, 0, false, false, true));
            GlowMessage.sendToTeam(this.user.getTeam(), player, duration);
        }
        this.user.displayClientMessage(Component.translatable("skill.gunskills.exhibit.effect", lastTickPlayers.size()), true);
    }
}
