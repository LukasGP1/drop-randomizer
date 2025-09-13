package de.lulkas_.drop_randomizer;

import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DropRandomizer implements ModInitializer {
	public static final String MOD_ID = "drop-randomizer";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("Loaded Drop Randomizer Mod");
	}
}