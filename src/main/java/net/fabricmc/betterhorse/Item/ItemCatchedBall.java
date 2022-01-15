package net.fabricmc.betterhorse.Item;

import net.fabricmc.betterhorse.ItemGroup.ItemGroupHorseGroup;
import net.fabricmc.fabric.api.renderer.v1.RendererAccess;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.mob.ZombifiedPiglinEntity;
import net.minecraft.entity.passive.HorseEntity;
import net.minecraft.entity.passive.PigEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.Items;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.LiteralText;

import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;

import java.util.Objects;

public class ItemCatchedBall extends Item {
    public ItemCatchedBall(Settings settings) {
        super(settings.group(ItemGroupHorseGroup.HORSEGROUP).maxCount(1));
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {

        BlockPos blockPos = context.getBlockPos();
        World world = context.getWorld();
        ItemStack itemStack = context.getPlayer().getStackInHand(Hand.MAIN_HAND);
        if(!world.isClient)
        {
            HorseEntity horseEntity = EntityType.HORSE.create(world);
            horseEntity.refreshPositionAndAngles(blockPos.getX(),blockPos.getY()+1,blockPos.getZ(),0,0);
            horseEntity.initialize((ServerWorldAccess) world, world.getLocalDifficulty(horseEntity.getBlockPos()),SpawnReason.SPAWN_EGG,
                    null, null );
            horseEntity.readCustomDataFromNbt(itemStack.getTag().getCompound("horse"));
            if (itemStack.getTag().getCompound("horse").contains("CustomName"))
            {
                //context.getPlayer().sendMessage(new LiteralText(itemStack.getTag().getCompound("horse").getString("CustomName")),true);
                horseEntity.setCustomName(new LiteralText(itemStack.getTag().getCompound("horse").getString("CustomName")));
            }
            horseEntity.setPersistent();
            world.spawnEntity(horseEntity);
            itemStack.decrement(1);
            context.getPlayer().giveItemStack(new ItemStack(ItemRegistryHandler.CATCHBALL));
        }
        return ActionResult.CONSUME;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        return super.use(world, user, hand);
    }
}
