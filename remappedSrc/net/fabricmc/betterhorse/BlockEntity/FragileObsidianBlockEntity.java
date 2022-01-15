package net.fabricmc.betterhorse.BlockEntity;

import net.fabricmc.betterhorse.Block.BlockRegistryHandler;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;

public class FragileObsidianBlockEntity extends BlockEntity {
    public FragileObsidianBlockEntity(BlockPos blockPos, BlockState blockState)
    {
        super(BlockRegistryHandler.FRAGILE_OBSIDIAN_BLOCK,blockPos,blockState);
    }
}
