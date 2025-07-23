package com.moon404.gunskills.item.skill;

import com.moon404.gunskills.struct.ClassType;

import net.minecraft.network.chat.Component;

public class Boot extends SkillItem
{
    public Boot(Properties properties)
    {
        super(properties, 20, 1, ClassType.ROGUE);
        tooltips.add(Component.translatable("item.gunskills.boot.tooltip"));
    }
}
