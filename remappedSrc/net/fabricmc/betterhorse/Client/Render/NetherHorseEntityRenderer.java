package net.fabricmc.betterhorse.Client.Render;

import net.fabricmc.betterhorse.Client.Model.NetherHorseEntityModel;
import net.fabricmc.betterhorse.Entity.CommonHorseEntity;
import net.fabricmc.betterhorse.Entity.NetherHorseEntity;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import software.bernie.geckolib3.renderer.geo.GeoEntityRenderer;

public class NetherHorseEntityRenderer extends GeoEntityRenderer<NetherHorseEntity> {
    public NetherHorseEntityRenderer(EntityRenderDispatcher entityRenderDispatcher)
    {
        super(entityRenderDispatcher,new NetherHorseEntityModel());

    }
}
