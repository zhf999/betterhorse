package net.fabricmc.betterhorse.Network;

import net.fabricmc.betterhorse.BetterHorse;
import net.fabricmc.betterhorse.Client.GUI.ManaGUI;
import net.fabricmc.betterhorse.Entity.CommonHorseEntity;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.projectile.FireballEntity;
import net.minecraft.text.LiteralText;
import net.minecraft.util.Identifier;

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
            if(player.hasVehicle()&&player.getVehicle() instanceof CommonHorseEntity)
            {
                CommonHorseEntity horse =(CommonHorseEntity) player.getVehicle();
                if(horse.getMana() >= 20)
                {
                    horse.setMana(horse.getMana() - 20);
                    FireballEntity fireballEntity = EntityType.FIREBALL.create(player.world);
                    fireballEntity.refreshPositionAndAngles(player.getX(),player.getY()+1,player.getZ(),player.yaw,player.pitch);
                    double veloc = 5.0;
                    double yaw = toRadians(player.yaw);
                    double pitch = toRadians(player.pitch);
                    fireballEntity.addVelocity(veloc * cos(pitch) * sin(-yaw),veloc * sin(-pitch)
                            , veloc * cos(-pitch) *(cos(yaw)));
                    player.world.spawnEntity(fireballEntity);
                    String string = "Your horse has " + horse.getMana() + " left";
                    String string2 = "Max: " + horse.getAttributeValue(HORSE_MAX_MANA);
                    player.sendMessage(new LiteralText(string),false);
                    player.sendMessage(new LiteralText(string2),false);
                }
            }
        });

    }
}
