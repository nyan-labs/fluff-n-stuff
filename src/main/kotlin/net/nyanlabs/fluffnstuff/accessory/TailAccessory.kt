package net.nyanlabs.fluffnstuff.accessory

import io.wispforest.accessories.api.client.AccessoriesRendererRegistry
import io.wispforest.accessories.api.core.AccessoryItem
import io.wispforest.accessories.api.core.AccessoryRegistry
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.minecraft.world.item.Item
import net.nyanlabs.fluffnstuff.FluffnStuff
import net.nyanlabs.fluffnstuff.registry.ItemRegistry
import java.util.function.Supplier


class TailAccessory(properties: Item.Properties) : AccessoryItem(properties) {
  companion object {
    fun init() {
      AccessoryRegistry.register(ItemRegistry.TAIL, TailAccessory(Item.Properties()))
    }
  }
}