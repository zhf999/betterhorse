package net.fabricmc.betterhorse.Network;

import net.fabricmc.betterhorse.BetterHorse;
import net.fabricmc.betterhorse.Client.GUI.ManaGUI;
import net.fabricmc.betterhorse.Entity.CommonHorseEntity;
import net.fabricmc.betterhorse.Entity.NetherHorseEntity;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.projectile.FireballEntity;
import net.minecraft.text.LiteralText;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3d;

import static java.lang.Math.*;
import static java.lang.Math.cos;
import static net.fabricmc.betterhorse.Entity.AttributesRegistryHandler.HORSE_MAX_MANA;

public class NetworkRegisterHandler {
    public static final Identifier EXPLOSION_IDENTIFIER = new Identifier(BetterHorse.MODID,"explosion");
    public static void register()
    {
        ServerPlayNetworking.registerGlobalReceiver(EXPLOSION_IDENTIFIER,(server, player, handler, buf, responseSender)->
        {
            //player.sendMessage(new LiteralText("hello"),false);
            if(player.hasVehicle()&&player.getVehicle() instanceof NetherHorseEntity)
            {
                NetherHorseEntity horse =(NetherHorseEntity) player.getVehicle();

            }
        });

    }
}
