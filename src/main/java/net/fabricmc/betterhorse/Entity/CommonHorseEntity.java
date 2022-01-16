package net.fabricmc.betterhorse.Entity;

import net.fabricmc.betterhorse.Client.GUI.ManaGUI;
import net.fabricmc.betterhorse.Client.GUI.ManaScreen;
import net.fabricmc.betterhorse.Item.ItemRegistryHandler;
import net.fabricmc.betterhorse.StatusEffect.EffectRegistryHandler;
import net.fabricmc.betterhorse.StatusEffect.FlameBarrierEffect;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.passive.HorseEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import static net.fabricmc.betterhorse.Entity.AttributesRegistryHandler.HORSE_MAX_MANA;
import static net.minecraft.entity.data.TrackedDataHandlerRegistry.FLOAT;

public class CommonHorseEntity extends HorseEntity implements IAnimatable {
    private static final String CONTROLLERNAME = "controller";
    private final AnimationFactory factory = new AnimationFactory(this);
    private static final TrackedData<Float> MANA = DataTracker.registerData(CommonHorseEntity.class, FLOAT);

    public CommonHorseEntity(EntityType<? extends HorseEntity> entityType, World world)
    {
        super(entityType, world);
        this.dataTracker.startTracking(MANA,(float)this.getAttributeValue(HORSE_MAX_MANA));
    }

    public static DefaultAttributeContainer.Builder createCommonHorseAttributes()
    {
        return createBaseHorseAttributes().add(HORSE_MAX_MANA);
    }

    @Override
    public float getMovementSpeed() {
        return super.getMovementSpeed();
    }

    @Override
    protected void initAttributes() {
        super.initAttributes();
        this.getAttributeInstance(HORSE_MAX_MANA).setBaseValue(this.getChildMaxManaBonus());
    }

    public void setMana(float m)
    {
        dataTracker.set(MANA,m);
    }

    public float getMana()
    {
        return dataTracker.get(MANA);
    }

    @Override
    public boolean canBeRiddenInWater() {
        return true;
    }


    @Override
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
        return super.interactMob(player, hand);
    }

    @Override
    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        nbt.putFloat("Mana",this.getMana());
    }

    @Override
    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        this.setMana(nbt.getFloat("Mana")); ;
    }

    protected float getChildMaxManaBonus()
    {
        return 80.0F + (float) random.nextInt(40);
    }

    @Override
    public void tick() {
        if(getMana() < getAttributeValue(HORSE_MAX_MANA))
            setMana(getMana() + 0.2f);
        if(this.hasPassengers())
        {
            if(this.getPrimaryPassenger() instanceof LivingEntity)
                EffectRegistryHandler.FLAME_BARRIER_EFFECT.applyUpdateEffect((LivingEntity) this.getPrimaryPassenger(),1);
        }
        super.tick();
    }

    /*
    *   below are the animation part:
    * */
    private <E extends IAnimatable> PlayState precdicate(AnimationEvent<E> event)
    {
        if(event.isMoving())
        {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("move",true));
            return PlayState.CONTINUE;
        }
        else return PlayState.STOP;
    }

    @Override
    public void registerControllers(AnimationData animationData) {
        animationData.addAnimationController(new AnimationController(this,CONTROLLERNAME,20,this::precdicate));
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }

    @Override
    public double getMountedHeightOffset() {
        return 2.10D;
    }


}
