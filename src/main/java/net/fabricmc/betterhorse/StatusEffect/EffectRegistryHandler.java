package net.fabricmc.betterhorse.StatusEffect;

import net.fabricmc.betterhorse.BetterHorse;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.lwjgl.system.CallbackI;

public class EffectRegistryHandler {
    public static final StatusEffect FLAME_BARRIER_EFFECT = new FlameBarrierEffect();

    public static void register()
    {
        Registry.register(Registry.STATUS_EFFECT,new Identifier(BetterHorse.MODID,"flame_barrier"),FLAME_BARRIER_EFFECT);
        //TODO add a texture for this effect
    }

}
