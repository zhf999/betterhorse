package net.fabricmc.betterhorse.Client.Model;

import net.minecraft.util.Identifier;
import software.bernie.geckolib3.GeckoLib;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class ReplacedCreeperModel extends AnimatedGeoModel {
	@Override
	public Identifier getModelLocation(Object object) {
		return new Identifier(GeckoLib.ModID, "geo/creeper.geo.json");
	}

	@Override
	public Identifier getTextureLocation(Object object) {
		return new Identifier(GeckoLib.ModID, "textures/entity/creeper.png");
	}

	@Override
	public Identifier getAnimationFileLocation(Object animatable) {
		return new Identifier(GeckoLib.ModID, "animations/creeper.animation.json");
	}
}