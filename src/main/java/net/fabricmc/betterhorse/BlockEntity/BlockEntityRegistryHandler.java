package net.fabricmc.betterhorse.BlockEntity;

import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.registry.Registry;


import static net.fabricmc.betterhorse.Block.BlockRegistryHandler.FRAGILE_OBSIDIAN_BLOCK;

public class BlockEntityRegistryHandler {
    public static  BlockEntityType<FragileObsidianBlockEntity> FRAGILE_OBSIDIAN_BLOCK_ENTITY;


    public static void register()
    {
        FRAGILE_OBSIDIAN_BLOCK_ENTITY = Registry.register(
                Registry.BLOCK_ENTITY_TYPE,
                "fragile_obsidian",
                BlockEntityType.Builder.create(FragileObsidianBlockEntity::new,FRAGILE_OBSIDIAN_BLOCK).build(null)
        );
    }
}
