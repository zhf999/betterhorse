package net.fabricmc.betterhorse.Network;

import net.fabricmc.betterhorse.BetterHorse;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.projectile.FireballEntity;
import net.minecraft.util.Identifier;

import static java.lang.Math.*;
import static java.lang.Math.cos;

public class NetworkRegisterHandler {
    public static final Identifier EXPLOSION_IDENTIFIER = new Identifier("betterhorse","explosion");
    public static final void register()
    {
        ServerPlayNetworking.registerGlobalReceiver(EXPLOSION_IDENTIFIER,(server, player, handler, buf, responseSender)->
        {
            //player.sendMessage(new LiteralText("hello"),false);
            if(player.hasVehicle()||true)
            {
                FireballEntity fireballEntity = EntityType.FIREBALL.create(player.world);
                fireballEntity.refreshPositionAndAngles(player.getX(),player.getY()+1,player.getZ(),player.yaw,player.pitch);
                double veloc = 5.0;
                System.out.println(player.yaw);
                System.out.println(player.pitch);
                double yaw = toRadians(player.yaw);
                double pitch = toRadians(player.pitch);
                fireballEntity.addVelocity(veloc * cos(pitch) * sin(-yaw),veloc * sin(-pitch)
                        , veloc * cos(-pitch) *(cos(yaw)));
                player.world.spawnEntity(fireballEntity);

            }
        });
    }
}
