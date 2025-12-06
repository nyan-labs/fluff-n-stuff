package net.nyanlabs.fluffnstuff.block

import net.minecraft.world.item.Item
import net.minecraft.world.level.block.CropBlock
import net.nyanlabs.fluffnstuff.registry.ItemRegistry

class BeansCropBlock(properties: Properties): CropBlock(properties) {
  protected override fun getBaseSeedId(): Item? {
    return ItemRegistry.BEANS
  }
}