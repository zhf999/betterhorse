package net.fabricmc.betterhorse.Entity;

import net.minecraft.entity.attribute.ClampedEntityAttribute;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.util.registry.Registry;

public class AttributesRegistryHandler {
    public static final EntityAttribute HORSE_MAX_MANA = Registry.register(Registry.ATTRIBUTE,"horse_max_mana",(new ClampedEntityAttribute("attribute.name.horse.max_mana",130,0,2048)).setTracked(true));
}
