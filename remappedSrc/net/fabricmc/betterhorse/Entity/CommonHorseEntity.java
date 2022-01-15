package net.fabricmc.betterhorse.Entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.passive.HorseEntity;
import net.minecraft.world.World;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class CommonHorseEntity extends HorseEntity implements IAnimatable {
    private static final String CONTROLLERNAME = "controller";
    private AnimationFactory factory = new AnimationFactory(this);

    public CommonHorseEntity(EntityType<? extends HorseEntity> entityType, World world)
    {
        super(entityType, world);
    }

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
