package net.fabricmc.betterhorse.Client.Model;

import net.fabricmc.betterhorse.BetterHorse;
import net.fabricmc.betterhorse.Entity.CommonHorseEntity;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class CommonHorseModel extends AnimatedGeoModel<CommonHorseEntity> {
    @Override
    public Identifier getModelLocation(CommonHorseEntity object) {
        return new Identifier(BetterHorse.MODID,"geo/entity/common_horse.geo.json");
    }

    @Override
    public Identifier getTextureLocation(CommonHorseEntity object) {
        return  new Identifier(BetterHorse.MODID,"textures/entity/common_horse.png");
    }

    @Override
    public Identifier getAnimationFileLocation(CommonHorseEntity animatable) {
        return  new Identifier(BetterHorse.MODID,"animations/entity/common_horse.animation.json");
    }
}
