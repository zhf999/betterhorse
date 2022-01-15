package net.fabricmc.betterhorse.BlockEntity;

import net.fabricmc.fabric.api.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import static net.fabricmc.betterhorse.Block.BlockRegistryHandler.FRAGILE_OBSIDIAN_BLOCK;

public class BlockEntityRegistryHandler {
    public static FragileObsidianBlockEntity FRAGILE_OBSIDIAN_BLOCK_ENTITY ;

    public static final void register()
    {
        FRAGILE_OBSIDIAN_BLOCK_ENTITY = Registry.register(
                Registry.BLOCK_ENTITY_TYPE,
                new Identifier("betterhorse","fragile_obsidian_blockentity"),
                FabricBlockEntityTypeBuilder.create(FragileObsidianBlockEntity::new, FRAGILE_OBSIDIAN_BLOCK).build(null)
                );
    }
}
