package net.fabricmc.betterhorse.StatusEffect;

import net.minecraft.client.particle.Particle;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectType;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.Util;
import net.minecraft.util.math.Box;

import javax.swing.plaf.PanelUI;
import java.util.List;
import java.util.function.Predicate;

public class FlameBarrierEffect extends StatusEffect {
    public FlameBarrierEffect()
    {
        super(StatusEffectType.BENEFICIAL,0xf78b1d);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }

    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        float radius = 8.00f;
        if (entity.world.isClient) {

            for (int i = 1; i <= 20; i++) {
                float dtx = (float) (radius * Math.cos(Math.toRadians(18 * i)));
                float dtz = (float) (radius * Math.sin(Math.toRadians(18 * i)));
                entity.world.addParticle(ParticleTypes.FLAME, entity.getX() + dtx, entity.getY() - 0.5, entity.getZ() + dtz,
                        0.00, 0.2, 0.00);
            }
        }
        Box box = new Box(entity.getX() + radius, entity.getY() + radius, entity.getZ() + radius,
                entity.getX() - radius, entity.getY() - radius, entity.getZ() - radius);
        Predicate<Entity> predicate = new Predicate<Entity>() {
            @Override
            public boolean test(Entity entity) {
                return entity instanceof HostileEntity;
            }
        };
        List<Entity> entityList = entity.world.getEntitiesByType(null, box, predicate);
        for (Entity en:entityList) {
            if(entity.getPos().subtract(en.getPos()).length() <= radius)
                if(en.getFireTicks() <= 0)
                    en.setFireTicks(100);
        }
    }
}
