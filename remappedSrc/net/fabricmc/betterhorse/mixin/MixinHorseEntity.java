package net.fabricmc.betterhorse.mixin;

import net.fabricmc.betterhorse.Item.ItemCatchedBall;
import net.fabricmc.betterhorse.Item.ItemRegistryHandler;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.HorseBaseEntity;
import net.minecraft.entity.passive.HorseEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.LiteralText;
import net.minecraft.text.NbtText;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;


@Mixin(HorseEntity.class)
public abstract class MixinHorseEntity extends HorseBaseEntity {

    @Shadow
    protected abstract int getVariant();

    @Shadow public abstract void writeCustomDataToNbt(NbtCompound tag);

    protected MixinHorseEntity(EntityType<? extends HorseBaseEntity> entityType, World world)
    {
        super(entityType,world);
    }

    //make horse riddable in water
    public boolean canBeRiddenInWater() {
        return true;
    }


    @Overwrite
    public ActionResult interactMob(PlayerEntity player, Hand hand) {
        ItemStack itemStack = player.getStackInHand(hand);
        if (itemStack.getItem() == ItemRegistryHandler.CATCHBALL)
        {
            itemStack.decrement(1);
            ItemStack itemstack2 = new ItemStack(ItemRegistryHandler.CATCHEDBALL);
            NbtCompound horsetag = new NbtCompound();

            this.writeCustomDataToNbt(horsetag);
            Text name;
            if (this.hasCustomName())
            {
                name = this.getCustomName();
                player.sendMessage(new LiteralText(name.getString()),true);
                horsetag.putString("CustomName",name.getString());
            }
            itemstack2.putSubTag("horse",horsetag);
            player.giveItemStack(itemstack2);
            this.remove();
            return ActionResult.success(this.world.isClient);
        }
        if (!this.isBaby()) {
            if (this.isTame() && player.shouldCancelInteraction()) {
                this.openInventory(player);
                return ActionResult.success(this.world.isClient);
            }

            if (this.hasPassengers()) {
                return super.interactMob(player, hand);
            }
        }

        if (!itemStack.isEmpty()) {
            if (this.isBreedingItem(itemStack)) {
                return this.method_30009(player, itemStack);
            }

            ActionResult actionResult = itemStack.useOnEntity(player, this, hand);
            if (actionResult.isAccepted()) {
                return actionResult;
            }

            if (!this.isTame()) {
                this.playAngrySound();
                return ActionResult.success(this.world.isClient);
            }

            boolean bl = !this.isBaby() && !this.isSaddled() && itemStack.getItem() == Items.SADDLE;
            if (this.isHorseArmor(itemStack) || bl) {
                this.openInventory(player);
                return ActionResult.success(this.world.isClient);
            }
        }

        if (this.isBaby()) {
            return super.interactMob(player, hand);
        } else {
            this.putPlayerOnBack(player);
            return ActionResult.success(this.world.isClient);
        }
    }
}
