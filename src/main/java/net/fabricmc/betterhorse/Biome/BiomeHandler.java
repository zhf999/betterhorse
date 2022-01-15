package net.fabricmc.betterhorse.Biome;

import net.fabricmc.fabric.api.biome.v1.*;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.SpawnSettings;
import org.lwjgl.system.CallbackI;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Predicate;

import static net.fabricmc.betterhorse.BetterHorse.MODID;
import static net.fabricmc.betterhorse.Entity.EntityRegistryHandler.COMMONHORSE;

public class BiomeHandler {
    public static void biome_config()
    {
        Predicate<BiomeSelectionContext> biomeselector = BiomeSelectors.spawnsOneOf(EntityType.HORSE);
        BiomeModification biomeModification = BiomeModifications.create(new Identifier(MODID,"horse_sub"));
        BiConsumer<BiomeSelectionContext,BiomeModificationContext> biConsumer = (biomeSelectionContext,modificationContext)->{
            modificationContext.getSpawnSettings().removeSpawnsOfEntityType(EntityType.HORSE);
            modificationContext.getSpawnSettings().addSpawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(COMMONHORSE,10,2,6));
        };
        biomeModification.add(ModificationPhase.REPLACEMENTS,biomeselector,biConsumer);
    }
}
