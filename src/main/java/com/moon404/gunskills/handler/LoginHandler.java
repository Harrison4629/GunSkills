package com.moon404.gunskills.handler;

import com.moon404.gunskills.Utils;
import com.moon404.gunskills.struct.ClassType;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.player.PlayerEvent.PlayerLoggedInEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class LoginHandler
{
    @SubscribeEvent
    public static void onLogin(PlayerLoggedInEvent event)
    {
        Player player = event.getEntity();
        player.sendSystemMessage(Component.translatable("message.gunskills.login.1").append(ClassType.getClass(player).getDisplay()));
        player.sendSystemMessage(Component.translatable("message.gunskills.login.2"));
        for (ClassType type : ClassType.values())
        {
            player.sendSystemMessage(type.getHelper());
        }

        if (player instanceof ServerPlayer serverPlayer)
        {
            Utils.invokeFunction(serverPlayer, "gunskills:login");
        }
    }
}
