package net.fabricmc.betterhorse.Entity;

import net.fabricmc.betterhorse.Block.BlockRegistryHandler;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.particle.Particle;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.HorseEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class NetherHorseEntity extends CommonHorseEntity{
    public NetherHorseEntity(EntityType<? extends HorseEntity> entityType, World world)
    {
        super(entityType,world);
    }

    @Override
    public boolean isFireImmune() {
        return true;
    }

    @Override
    public void tickMovement() {
        super.tickMovement();
    }

    @Override
    public void tick() {
        this.world.addParticle(ParticleTypes.FLAME, this.getParticleX(1.0D), this.getY()+0.2F, this.getParticleZ(1.0D), 0.0D, 0.0D, 0.0D);
        for(int i=-1;i<=1;i++)
        {
            for(int j=-1;j<=1;j++)
            {
                BlockPos blockPos = new BlockPos(this.getPos().add(i,-1,j));
                BlockState blockState = this.getEntityWorld().getBlockState(blockPos);
                if(blockState.getBlock().equals(Blocks.LAVA))
                {
                    BlockState blockState1 = BlockRegistryHandler.FRAGILE_OBSIDIAN_BLOCK.getDefaultState();
                    this.getEntityWorld().setBlockState(blockPos,blockState1);
                }
            }
        }
        super.tick();
    }

}
