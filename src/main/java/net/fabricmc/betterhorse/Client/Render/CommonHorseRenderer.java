package net.fabricmc.betterhorse.Client.Render;

import net.fabricmc.betterhorse.Client.Model.CommonHorseModel;
import net.fabricmc.betterhorse.Entity.CommonHorseEntity;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import software.bernie.geckolib3.renderer.geo.GeoEntityRenderer;

public class CommonHorseRenderer extends GeoEntityRenderer<CommonHorseEntity> {
    public CommonHorseRenderer(EntityRenderDispatcher entityRenderDispatcher)
    {
        super(entityRenderDispatcher,new CommonHorseModel());
        this.shadowRadius=1.0f;
    }
}
