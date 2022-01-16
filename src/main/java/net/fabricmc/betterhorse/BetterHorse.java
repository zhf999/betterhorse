package net.fabricmc.betterhorse;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.betterhorse.Biome.BiomeHandler;
import net.fabricmc.betterhorse.Block.BlockRegistryHandler;
import net.fabricmc.betterhorse.BlockEntity.BlockEntityRegistryHandler;
import net.fabricmc.betterhorse.Client.GUI.ManaGUI;
import net.fabricmc.betterhorse.Network.NetworkRegisterHandler;
import net.fabricmc.betterhorse.Entity.EntityRegistryHandler;
import net.fabricmc.betterhorse.Item.ItemRegistryHandler;

import net.fabricmc.betterhorse.StatusEffect.EffectRegistryHandler;
import net.fabricmc.fabric.api.biome.v1.BiomeModificationContext;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectionContext;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import software.bernie.geckolib3.GeckoLib;

import java.util.function.Predicate;

import static net.fabricmc.betterhorse.Entity.EntityRegistryHandler.COMMONHORSE;

public class BetterHorse implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final String MODID = "betterhorse";
	public static final Logger LOGGER = LogManager.getLogger("betterhorse");



	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		LOGGER.info("Hello Fabric world!");
		ItemRegistryHandler.registeritem();
		BlockRegistryHandler.register();
		BlockEntityRegistryHandler.register();
		EntityRegistryHandler.register();
		EffectRegistryHandler.register();
		BiomeHandler.biome_config();
		//key binding Events:
		NetworkRegisterHandler.register();
		GeckoLib.initialize();

	}


}
