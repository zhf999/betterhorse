package net.fabricmc.betterhorse.Block;

import net.fabricmc.betterhorse.BlockEntity.FragileObsidianBlockEntity;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class FragileObsidianBlock extends BlockWithEntity {

    public FragileObsidianBlock(Settings settings)
    {
        super(settings);
    }

    @Nullable
    public BlockEntity createBlockEntity(BlockView world)
    {
        return new FragileObsidianBlockEntity();
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

}
