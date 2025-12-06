package net.nyanlabs.fluffnstuff.registry

import net.minecraft.core.Registry
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.core.registries.Registries
import net.minecraft.resources.ResourceKey
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.item.BlockItem
import net.minecraft.world.item.Item
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.SoundType
import net.minecraft.world.level.block.state.BlockBehaviour
import net.minecraft.world.level.material.MapColor
import net.minecraft.world.level.material.PushReaction
import net.nyanlabs.fluffnstuff.FluffnStuff
import net.nyanlabs.fluffnstuff.block.BeansCropBlock


object BlockRegistry {
  val BEANS = register("beans", ::BeansCropBlock, BlockBehaviour.Properties.of()
    .mapColor(MapColor.PLANT)
    .noCollision()
    .randomTicks()
    .instabreak()
    .sound(SoundType.CROP)
    .pushReaction(PushReaction.DESTROY),
    false)

  private fun register(
    name: String,
    blockFactory: (BlockBehaviour.Properties) -> Block?,
    settings: BlockBehaviour.Properties,
    shouldRegisterItem: Boolean
  ): Block {
    // Create a registry key for the block
    val blockKey = keyOfBlock(name)
    // Create the block instance
    val block: Block = blockFactory(settings.setId(blockKey))!!

    // Sometimes, you may not want to register an item for the block.
    // Eg: if it's a technical block like `minecraft:moving_piston` or `minecraft:end_gateway`
    if (shouldRegisterItem) {
      // Items need to be registered with a different type of registry key, but the ID
      // can be the same.
      val itemKey = keyOfItem(name)

      val blockItem = BlockItem(block, Item.Properties().setId(itemKey).useBlockDescriptionPrefix())
      Registry.register<Item?, BlockItem?>(BuiltInRegistries.ITEM, itemKey, blockItem)
    }

    return Registry.register(BuiltInRegistries.BLOCK, blockKey, block)
  }

  private fun keyOfBlock(name: String): ResourceKey<Block?> {
    return ResourceKey.create<Block?>(Registries.BLOCK, FluffnStuff.of(name))
  }

  private fun keyOfItem(name: String): ResourceKey<Item?> {
    return ResourceKey.create<Item?>(Registries.ITEM, FluffnStuff.of(name))
  }

  fun init() {}
}