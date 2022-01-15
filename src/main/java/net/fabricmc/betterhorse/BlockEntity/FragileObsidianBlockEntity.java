package net.fabricmc.betterhorse.BlockEntity;


import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Tickable;

public class FragileObsidianBlockEntity extends BlockEntity implements Tickable {
    public int remain = 80;

    public FragileObsidianBlockEntity()
    {
        super(BlockEntityRegistryHandler.FRAGILE_OBSIDIAN_BLOCK_ENTITY);
    }

    @Override
    public NbtCompound writeNbt(NbtCompound nbt) {
        NbtCompound tag = super.writeNbt(nbt);
        tag.putInt("remain",remain);
        return tag;
    }

    @Override
    public void fromTag(BlockState state, NbtCompound tag) {
        super.fromTag(state, tag);
        this.remain = tag.getInt("remain");
    }

    @Override
    public void tick() {
        this.remain--;
        if(this.remain == 0)
        {
            world.removeBlockEntity(this.getPos());
            world.setBlockState(this.getPos(), Blocks.LAVA.getDefaultState());
        }
    }
}
