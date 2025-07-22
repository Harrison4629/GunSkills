package com.moon404.gunskills.item.skill;

import com.moon404.gunskills.struct.ClassType;

import net.minecraft.network.chat.Component;

public class Fast extends SkillItem
{
    public static final int DURATION = 3;

    public Fast(Properties properties)
    {
        super(properties, 480, 1, ClassType.ATTACK);
        tooltips.add(Component.translatable("item.gunskills.fast.tooltip", DURATION));
    }
}
