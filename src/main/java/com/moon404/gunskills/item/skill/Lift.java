package com.moon404.gunskills.item.skill;

import com.moon404.gunskills.entity.LiftEntity;
import com.moon404.gunskills.init.GunSkillsEntities;
import com.moon404.gunskills.struct.ClassType;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.item.ItemEntity;

public class Lift extends SkillItem
{
    public static final int DURATION = 5;

    public Lift(Properties properties)
    {
        super(properties, 3, ClassType.ROGUE);
        tooltips.add(Component.translatable("item.gunskills.lift.tooltip.1", DURATION));
        tooltips.add(Component.translatable("item.gunskills.lift.tooltip.2"));
    }

    @Override
    public boolean onLand(ItemEntity entity)
    {
        LiftEntity lift = new LiftEntity(GunSkillsEntities.LIFT.get(), entity.level());
        lift.setPos(entity.position());
        entity.level().addFreshEntity(lift);
        return true;
    }
}
