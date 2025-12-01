package net.nyanlabs.fluffnstuff.registry

import net.minecraft.core.Registry
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.core.registries.Registries
import net.minecraft.resources.ResourceKey
import net.minecraft.resources.ResourceLocation
import net.minecraft.sounds.SoundEvent
import net.minecraft.world.item.Item
import net.minecraft.world.item.Items
import net.nyanlabs.fluffnstuff.FluffnStuff

object AudioRegistry {
  val SQUEAK = register("squeak")

  fun register(name: String): SoundEvent? {
    val location = ResourceLocation.fromNamespaceAndPath(FluffnStuff.MOD_ID, "squeak");
    val audio = SoundEvent.createVariableRangeEvent(location)

    return Registry.register(BuiltInRegistries.SOUND_EVENT, location, audio);
  }

  fun init() {}
}