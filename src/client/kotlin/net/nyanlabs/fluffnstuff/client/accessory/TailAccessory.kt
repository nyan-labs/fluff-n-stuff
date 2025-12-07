package net.nyanlabs.fluffnstuff.client.accessory

import com.mojang.blaze3d.vertex.PoseStack
import com.mojang.math.Axis
import io.wispforest.accessories.api.client.AccessoriesRendererRegistry
import io.wispforest.accessories.api.client.AccessoryRenderState
import io.wispforest.accessories.api.client.renderers.AccessoryRenderer
import io.wispforest.accessories.api.client.renderers.SimpleAccessoryRenderer
import io.wispforest.accessories.api.client.rendering.Side
import io.wispforest.accessories.api.core.AccessoryItem
import io.wispforest.accessories.api.slot.SlotReference
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.minecraft.client.model.EntityModel
import net.minecraft.client.model.HumanoidModel
import net.minecraft.client.renderer.SubmitNodeCollector
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState
import net.minecraft.client.renderer.item.ItemStackRenderState
import net.minecraft.client.renderer.texture.OverlayTexture
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.nyanlabs.fluffnstuff.FluffnStuff
import net.nyanlabs.fluffnstuff.registry.ItemRegistry
import java.util.function.Supplier


class TailAccessory(properties: Item.Properties) : AccessoryItem(properties) {
  companion object {
    fun clientInit() {
      AccessoriesRendererRegistry.bindItemToRenderer(
        ItemRegistry.TAIL,
        FluffnStuff.of("tail_renderer"),
        Supplier { Renderer() })
    }

    @Environment(EnvType.CLIENT)
    class Renderer : SimpleAccessoryRenderer {
      public override fun <S : LivingEntityRenderState?> renderStack(
        accessoryState: AccessoryRenderState,
        entityState: S,
        model: EntityModel<S>,
        matrices: PoseStack,
        collector: SubmitNodeCollector,
        stack: ItemStack,
        stackRenderState: ItemStackRenderState,
        light: Int
      ) {
        matrices.translate(0.0, 3.0/16.0, -8.0/16.0)
        matrices.scale(2.0f,2.0f,2.0f)
        matrices.mulPose(Axis.YP.rotationDegrees(90.0f))

        for (i in 0..<stack.count) {
          stackRenderState.submit(matrices, collector, light, OverlayTexture.NO_OVERLAY, 0)
        }
      }

      public override fun <S : LivingEntityRenderState?> align(
        accessoryState: AccessoryRenderState?,
        entityState: S?,
        model: EntityModel<S?>?,
        matrices: PoseStack
      ) {
        if (model !is HumanoidModel) return

        AccessoryRenderer.transformToFace(matrices, model.body, Side.BACK)
      }
    }
  }
}