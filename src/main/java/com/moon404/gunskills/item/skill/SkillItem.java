package com.moon404.gunskills.item.skill;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import com.moon404.gunskills.init.GunSkillsEffects;
import com.moon404.gunskills.struct.ClassType;

import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

public class SkillItem extends Item
{
    public final int cooldown;
    public final int useType;
    public final ClassType classType;
    protected List<Component> tooltips;

    public SkillItem(Properties properties, int cooldown, int useType, ClassType classType)
    {
        super(properties);
        this.cooldown = cooldown;
        this.useType = useType;
        this.classType = classType;
        this.tooltips = new ArrayList<>();
    }

    // 该玩家是否可以使用这个技能物品
    public boolean canUse(Player player)
    {
        if (ClassType.getClass(player) != this.classType) return false;
        if (player.hasEffect(GunSkillsEffects.SILENCE.get())) return false;
        if (player.getCooldowns().isOnCooldown(this)) return false;
        return true;
    }

    public int getCooldown(Player player)
    {
        Inventory inventory = player.getInventory();
        int count = Math.max(1, inventory.countItem(this));
        return this.cooldown / count;
    }

    public void enterCooldown(Player player)
    {
        player.getCooldowns().addCooldown(this, this.getCooldown(player));
    }
    
    // 技能物品被扔下时触发
    // player 扔物品的玩家
    // return 是否消耗
    public boolean onToss(Player player)
    {
        return false;
    }

    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced)
    {
        if (this.classType != null)
            pTooltipComponents.add(Component.translatable("skill.gunskills.class.limit").append(this.classType.getDisplay()));
        pTooltipComponents.add(Component.translatable("skill.gunskills.use." + this.useType));
        pTooltipComponents.add(Component.translatable("skill.gunskills.cooldown", this.getCooldown(Minecraft.getInstance().player) / 20));
        pTooltipComponents.addAll(this.tooltips);
    }
}
