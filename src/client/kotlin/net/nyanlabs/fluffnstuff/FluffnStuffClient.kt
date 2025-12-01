package net.nyanlabs.fluffnstuff

import net.fabricmc.api.ClientModInitializer
import net.fabricmc.fabric.api.client.rendering.v1.BlockRenderLayerMap
import net.minecraft.client.renderer.chunk.ChunkSectionLayer
import net.nyanlabs.fluffnstuff.registry.BlockRegistry

object FluffnStuffClient : ClientModInitializer {
	override fun onInitializeClient() {
    BlockRenderLayerMap.putBlock(BlockRegistry.BEANS, ChunkSectionLayer.CUTOUT);
	}
}