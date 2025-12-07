package net.nyanlabs.fluffnstuff

import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.event.registry.DynamicRegistrySetupCallback
import net.minecraft.resources.ResourceLocation
import net.nyanlabs.fluffnstuff.accessory.TailAccessory
import net.nyanlabs.fluffnstuff.registry.AudioRegistry
import net.nyanlabs.fluffnstuff.registry.BlockRegistry
import net.nyanlabs.fluffnstuff.registry.ItemRegistry
import net.nyanlabs.fluffnstuff.registry.TabRegistry
import org.slf4j.Logger
import org.slf4j.LoggerFactory


object FluffnStuff : ModInitializer {
  val RANDOM_MESSAGES = arrayOf(":3", "paws bro paws", "haiii")

  public const val MOD_ID = "fluffnstuff"
  val logger: Logger = LoggerFactory.getLogger("fluffnstuff")

	override fun onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
    logger.info(RANDOM_MESSAGES.random())

    ItemRegistry.init()
    AudioRegistry.init()
    BlockRegistry.init()
    TabRegistry.init()
	}

  fun of(path: String): ResourceLocation {
    return ResourceLocation.fromNamespaceAndPath(MOD_ID, path)
  }
}