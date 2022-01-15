package net.fabricmc.betterhorse.Client.Model;

import net.fabricmc.betterhorse.BetterHorse;
import net.fabricmc.betterhorse.Entity.CommonHorseEntity;
import net.fabricmc.betterhorse.Entity.NetherHorseEntity;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class NetherHorseEntityModel extends AnimatedGeoModel<NetherHorseEntity> {
    //TODO: add specified model and texture for NetherHorseEntity
    @Override
    public Identifier getModelLocation(NetherHorseEntity object) {
        return new Identifier(BetterHorse.MODID,"geo/entity/common_horse.geo.json");
    }

    @Override
    public Identifier getTextureLocation(NetherHorseEntity object) {
        return  new Identifier(BetterHorse.MODID,"textures/entity/common_horse.png");
    }

    @Override
    public Identifier getAnimationFileLocation(NetherHorseEntity animatable) {
        return  new Identifier(BetterHorse.MODID,"animations/entity/common_horse.animation.json");
    }
}
