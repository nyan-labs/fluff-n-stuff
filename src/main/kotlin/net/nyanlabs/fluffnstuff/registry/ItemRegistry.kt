package net.nyanlabs.fluffnstuff.registry

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.core.registries.Registries
import net.minecraft.resources.ResourceKey
import net.minecraft.resources.ResourceLocation
import net.minecraft.tags.BlockTags
import net.minecraft.tags.TagKey
import net.minecraft.world.effect.MobEffectInstance
import net.minecraft.world.effect.MobEffects
import net.minecraft.world.entity.EquipmentSlot
import net.minecraft.world.food.FoodProperties
import net.minecraft.world.item.BlockItem
import net.minecraft.world.item.Item
import net.minecraft.world.item.Items
import net.minecraft.world.item.ToolMaterial
import net.minecraft.world.item.component.Consumables
import net.minecraft.world.item.consume_effects.ApplyStatusEffectsConsumeEffect
import net.nyanlabs.fluffnstuff.FluffnStuff
import net.nyanlabs.fluffnstuff.accessory.TailAccessory
import net.nyanlabs.fluffnstuff.item.BakedBeansItem
import net.nyanlabs.fluffnstuff.item.FluffItem
import net.nyanlabs.fluffnstuff.item.PawItem

object ItemRegistry {
  val FLUFF_REPAIRS: TagKey<Item?> = TagKey.create<Item?>(
    BuiltInRegistries.ITEM.key(),
    ResourceLocation.fromNamespaceAndPath(FluffnStuff.MOD_ID, "repairs_fluff")
  )
  val FLUFF_TOOL_MATERIAL = ToolMaterial(
    BlockTags.INCORRECT_FOR_WOODEN_TOOL,
    175,
    1.25f,
    0.0f,
    4,
    FLUFF_REPAIRS
  )

  val PAW_DAMAGE = ResourceKey.create(Registries.DAMAGE_TYPE, ResourceLocation.fromNamespaceAndPath(FluffnStuff.MOD_ID, "paw"));
  val PAW = register("paw", ::PawItem, Item.Properties()
    .sword(FLUFF_TOOL_MATERIAL, 1.0f, 0.0f)
  )

  val FLUFF = register("fluff", ::FluffItem, Item.Properties())

  val BEANS = register("beans", { properties -> BlockItem(BlockRegistry.BEANS, properties.useBlockDescriptionPrefix()) }, Item.Properties())
  val BAKED_BEANS = register("baked_beans", ::BakedBeansItem, Item.Properties()
    .food(
      FoodProperties.Builder()
        .nutrition(5)
        .alwaysEdible()
        .build(),
      Consumables.defaultFood()
        .onConsume(ApplyStatusEffectsConsumeEffect(MobEffectInstance(MobEffects.SPEED, 7 * 20, 1)))
        .build()
    )
  )

  val TAIL = register("tail", ::TailAccessory,
    Item.Properties()
//      .equippable(EquipmentSlot.CHEST)
  )


  fun register(name: String, factory: (Item.Properties) -> Item, properties: Item.Properties): Item {
    val item_key = ResourceKey.create<Item?>(Registries.ITEM, FluffnStuff.of(name))

    return Items.registerItem(item_key, factory, properties);
  }

  fun init() {
    ItemGroupEvents.modifyEntriesEvent(TabRegistry.STUFF_GROUP_KEY).register{ itemGroup ->
      itemGroup.accept(PAW);
      itemGroup.accept(BEANS);
      itemGroup.accept(FLUFF);
      itemGroup.accept(BAKED_BEANS);
    }
  }
}

// use architectury?
// use some sort of API to make crossplatform or versino possible