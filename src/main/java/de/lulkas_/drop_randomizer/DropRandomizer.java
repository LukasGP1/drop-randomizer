package de.lulkas_.drop_randomizer;

import net.fabricmc.api.ModInitializer;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Collectors;

public class DropRandomizer implements ModInitializer {
	public static final String MOD_ID = "drop-randomizer";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("Loaded Drop Randomizer Mod");
	}

	private static Map<Block, Map<Item, Item>> map = new HashMap<>();

	public static List<ItemStack> getRandomDrops(List<ItemStack> normalDrops, Block block) {
		if(!map.containsKey(block)) {
			map.put(block, new HashMap<>());
		}

		Map<Item, Item> itemMap = map.get(block);
		List<Item> allItems = Registries.ITEM.stream().toList();

		for (ItemStack normalStack : normalDrops) {
			if(!itemMap.containsKey(normalStack.getItem())) {
				itemMap.put(normalStack.getItem(), allItems.get((new Random()).nextInt(allItems.size())));
			}
		}
		map.replace(block, itemMap);

		return applyItemMap(normalDrops, map.get(block));
	}

	private static List<ItemStack> applyItemMap(List<ItemStack> in, Map<Item, Item> itemMap) {
		List<ItemStack> toReturn = new ArrayList<>();
		for(ItemStack stack : in) toReturn.add(new ItemStack(itemMap.get(stack.getItem()), stack.getCount()));
		return toReturn;
	}
}