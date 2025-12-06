package net.nyanlabs.fluffnstuff.item

import net.minecraft.server.level.ServerLevel
import net.minecraft.sounds.SoundSource
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResult
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.Level
import net.nyanlabs.fluffnstuff.registry.AudioRegistry

class PawItem(properties: Properties?) : Item(properties) {
  fun squeak(level: Level, entity: LivingEntity) {
    val pitch = 0.8f + (Math.random().toFloat() / 2);

    level.playSound(null, entity.x, entity.y, entity.z, AudioRegistry.SQUEAK, SoundSource.PLAYERS, 0.85f, pitch);
  }

  override fun use(level: Level, player: Player, interactionHand: InteractionHand): InteractionResult? {
    val item = player.getItemInHand(interactionHand);
    if(player.cooldowns.isOnCooldown(item)) return InteractionResult.FAIL;
    if(level !is ServerLevel) return InteractionResult.FAIL;

    player.cooldowns.addCooldown(item, 5);
    squeak(level, player)

    return InteractionResult.SUCCESS;
  }
  override fun hurtEnemy(item: ItemStack, enemy: LivingEntity, entity: LivingEntity) {
    // todo: fix weird shit like hitting when on same plane y it hits heigher than if the entity was above you
    //       i beleive this is to do with `dist`.
    val dist = enemy.position().subtract(entity.position()).normalize()

    val delta_intensity = Math.clamp(Math.random() * 1.25, 0.95, 1.25)

    val delta_y_base = 0.48
    val delta_y_limit = 0.75

    val delta_x = dist.x() * delta_intensity
    val delta_y = Math.clamp((delta_y_base + dist.y()) * delta_intensity, -delta_y_limit, delta_y_limit)
    val delta_z = dist.z() * delta_intensity

    println("$delta_x, $delta_y, $delta_z")

    enemy.deltaMovement = enemy.deltaMovement.add(
      delta_x,
      delta_y,
      delta_z
    )

    squeak(entity.level(), entity)
  }
}

// make this craftable with "fluff", which is just a wool block turned into 9 pieces of fluff (you can use the 9 fluff to make wool)