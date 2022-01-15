package net.fabricmc.betterhorse.Item;

import net.fabricmc.betterhorse.BetterHorse;
import net.fabricmc.betterhorse.Block.BlockRegistryHandler;
import net.fabricmc.betterhorse.Block.FragileObsidianBlock;
import net.fabricmc.betterhorse.ItemGroup.ItemGroupHorseGroup;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ItemRegistryHandler {
    public static final Item CATCHBALL = new ItemCatchBall(new Item.Settings());
    public static final Item CATCHEDBALL = new ItemCatchedBall(new Item.Settings());
    public static final Item FRAGILE_OBSIDIAN = new BlockItem(BlockRegistryHandler.FRAGILE_OBSIDIAN_BLOCK,new Item.Settings().group(ItemGroupHorseGroup.HORSEGROUP));

    public static final void registeritem()
    {
        quickregister(CATCHBALL, "catchball");
        quickregister(CATCHEDBALL, "catchedball");
        quickregister(FRAGILE_OBSIDIAN, "fragile_obsidian");
    }

    public static final void quickregister(Item item, String path)
    {
        Registry.register(Registry.ITEM, new Identifier(BetterHorse.MODID, path),item);
    }
}
