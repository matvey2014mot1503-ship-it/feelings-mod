package com.example.feelings;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;

public class FeelingsMod implements ModInitializer {
    public static final String MOD_ID = "feelings";
    private static final String[] NAMES = {"love", "greed", "faith", "mogging", "cruelty", "gay"};
    public static final List<BlockItem> ITEMS = new ArrayList<>();

    @Override
    public void onInitialize() {
        for (String name : NAMES) {
            Identifier id = new Identifier(MOD_ID, name + "_block");
            Block block = Registry.register(Registries.BLOCK, id,
                    new Block(FabricBlockSettings.copyOf(Blocks.STONE).strength(2.0f, 6.0f)));
            BlockItem item = Registry.register(Registries.ITEM, id,
                    new BlockItem(block, new FabricItemSettings()));
            ITEMS.add(item);
        }
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(entries -> {
            for (BlockItem i : ITEMS) entries.add(i);
        });
    }
}
