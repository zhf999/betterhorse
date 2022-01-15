package net.fabricmc.betterhorse.Block;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.MapColor;
import net.minecraft.block.Material;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class BlockRegistryHandler {
    public static final FragileObsidianBlock FRAGILE_OBSIDIAN_BLOCK = new FragileObsidianBlock(AbstractBlock.Settings.of(Material.STONE, MapColor.BLACK).requiresTool().strength(10.0F,100.0F).dropsNothing());

    public static void register()
    {
        Registry.register(Registry.BLOCK,new Identifier("betterhorse","fragile_obsidian"),FRAGILE_OBSIDIAN_BLOCK);
    }
}
