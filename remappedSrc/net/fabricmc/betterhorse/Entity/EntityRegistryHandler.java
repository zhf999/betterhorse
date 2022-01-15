package net.fabricmc.betterhorse.Entity;

import net.fabricmc.betterhorse.BetterHorse;
import net.fabricmc.betterhorse.Client.Render.CommonHorseRenderer;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class EntityRegistryHandler {
    public static final EntityType<CommonHorseEntity> COMMONHORSE = Registry.register(
            Registry.ENTITY_TYPE,
            new Identifier("betterhorse","common_horse"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, CommonHorseEntity::new).dimensions(EntityDimensions.fixed(1.6F, 2.2F)).build()
    );
    public static final EntityType<NetherHorseEntity> NETHER_HORSE = Registry.register(
            Registry.ENTITY_TYPE,
            new Identifier("betterhorse","nether_horse"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, NetherHorseEntity::new).dimensions(EntityDimensions.fixed(1.6F,2.2F)).build()
    );


    public static final void register()
    {
        FabricDefaultAttributeRegistry.register(COMMONHORSE, CommonHorseEntity.createBaseHorseAttributes());

        EntityRendererRegistry.INSTANCE.register(COMMONHORSE,(dispatcher, context)->{
            return new CommonHorseRenderer(dispatcher);
        });

        FabricDefaultAttributeRegistry.register(NETHER_HORSE, NetherHorseEntity.createBaseHorseAttributes());

        EntityRendererRegistry.INSTANCE.register(NETHER_HORSE,(dispatcher, context)->{
            return new CommonHorseRenderer(dispatcher);
        });
    }

}
