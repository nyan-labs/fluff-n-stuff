package net.nyanlabs.fluffnstuff.registry

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup
import net.minecraft.core.Registry
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.network.chat.Component
import net.minecraft.resources.ResourceKey
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.item.CreativeModeTab
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.ItemLike
import net.nyanlabs.fluffnstuff.FluffnStuff
import java.util.function.Supplier


object TabRegistry {
  val STUFF_GROUP_KEY: ResourceKey<CreativeModeTab?> = ResourceKey.create<CreativeModeTab?>(
    BuiltInRegistries.CREATIVE_MODE_TAB.key(),
    ResourceLocation.fromNamespaceAndPath(FluffnStuff.MOD_ID, "stuff")
  )

  val STUFF_GROUP: CreativeModeTab = FabricItemGroup.builder()
    .icon(Supplier { ItemStack(ItemRegistry.PAW) })
    .title(Component.translatable("stuff.fluffnstuff"))
    .build()

  fun init() {
    Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, STUFF_GROUP_KEY, STUFF_GROUP)
  }
}