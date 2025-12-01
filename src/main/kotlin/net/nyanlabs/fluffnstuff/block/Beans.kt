package net.nyanlabs.fluffnstuff.block

import net.minecraft.core.BlockPos
import net.minecraft.world.item.Item
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.block.CropBlock
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.properties.BlockStateProperties
import net.minecraft.world.level.block.state.properties.IntegerProperty
import net.minecraft.world.phys.shapes.CollisionContext
import net.minecraft.world.phys.shapes.VoxelShape
import net.nyanlabs.fluffnstuff.registry.ItemRegistry
import java.util.function.IntFunction


class Beans(properties: Properties): CropBlock(properties) {
  protected override fun getBaseSeedId(): Item? {
    return ItemRegistry.BEANS
  }
}

// possible issues: loot_table, lack of model, idk