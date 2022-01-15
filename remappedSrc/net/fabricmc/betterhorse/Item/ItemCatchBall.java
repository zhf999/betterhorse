package net.fabricmc.betterhorse.Item;

import net.fabricmc.betterhorse.ItemGroup.ItemGroupHorseGroup;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LightningEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;

public class ItemCatchBall extends Item {
    public ItemCatchBall(Settings settings) {
        super(settings.group(ItemGroupHorseGroup.HORSEGROUP).maxCount(1));
    }

//    @Override
//    public ActionResult useOnBlock(ItemUsageContext context) {
//        LightningEntity lightning = new LightningEntity(EntityType.LIGHTNING_BOLT,context.getWorld());
//        BlockPos blockPos = context.getBlockPos();
//        lightning.setPos(blockPos.getX(),blockPos.getY(),blockPos.getZ());
//        context.getWorld().spawnEntity(lightning);
//        return ActionResult.SUCCESS;
//    }

}
