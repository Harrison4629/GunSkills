package com.moon404.gunskills.item.skill;

import com.moon404.gunskills.struct.ClassType;

import net.minecraft.network.chat.Component;

public class Ire extends SkillItem
{
    public static final int DURATION = 5;

    public Ire(Properties properties)
    {
        super(properties, 480, 1, ClassType.SCOUT);
        tooltips.add(Component.translatable("item.gunskills.ire.tooltip", DURATION));
    }
}
