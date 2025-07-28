package com.moon404.gunskills.entity;

import java.util.HashMap;
import java.util.UUID;

import org.joml.Vector3f;

import com.moon404.gunskills.Utils;
import com.moon404.gunskills.item.skill.HealthBottle;

import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Marker;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class HealthBottleEntity extends Marker
{
    private static final HashMap<UUID, Long> playerHealCooldown = new HashMap<>();

    public HealthBottleEntity(EntityType<?> p_147250_, Level p_147251_)
    {
        super(p_147250_, p_147251_);
    }
    
    @Override
    public void tick()
    {
        if (this.tickCount > HealthBottle.DURATION * 20)
        {
            this.kill();
            return;
        }
        if (this.level() instanceof ServerLevel level)
        {
            Vector3f color = new Vector3f(1.00F, 0.33F, 0.33F);
            DustParticleOptions options = new DustParticleOptions(color, 2.0F);
            level.sendParticles(options, this.getX(), this.getY() + 0.2, this.getZ(), 1, 1, 0, 1, 0.5);
            int interval = 20 / HealthBottle.AMOUNT;
            if (this.tickCount % interval == 0)
            {
                long currentGameTime = level.getGameTime();
                for (Player player : level.players())
                {
                    if (this.distanceTo(player) > 4) continue;
                    UUID playerId = player.getUUID();
                    long nextAvailableTime = playerHealCooldown.getOrDefault(playerId, 0L);
                    if (currentGameTime >= nextAvailableTime)
                    {
                        float amount = player.getHealth() + 1;
                        float shield = player.getAbsorptionAmount();
                        if (amount > player.getMaxHealth())
                        {
                            shield += amount - player.getMaxHealth();
                            amount = player.getMaxHealth();
                        }
                        if (shield > Utils.getMaxShield(player))
                        {
                            shield = Utils.getMaxShield(player);
                        }
                        player.setHealth(amount);
                        player.setAbsorptionAmount(shield);
                        playerHealCooldown.put(playerId, currentGameTime + interval);
                    }
                }
            }
        }
    }
}
