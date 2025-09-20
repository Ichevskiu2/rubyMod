package com.rubymod

import net.minecraft.block.AbstractBlock
import net.minecraft.block.Block
import net.minecraft.block.BlockEntityProvider
import net.minecraft.block.BlockRenderType
import net.minecraft.block.BlockState
import net.minecraft.block.MapColor
import net.minecraft.block.entity.BlockEntity
import net.minecraft.item.BlockItem
import net.minecraft.item.Item
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.sound.BlockSoundGroup
import net.minecraft.util.Identifier
import net.minecraft.util.math.BlockPos
import com.rubymod.entities.CrusherBlockEntity
import net.minecraft.world.World
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.util.Hand
import net.minecraft.util.ActionResult
import net.minecraft.util.hit.BlockHitResult

val ruby_block: Block = Block(
    AbstractBlock.Settings.create()
        .mapColor { MapColor.BRIGHT_RED }
        .strength(2.0f, 6.0f)
        .requiresTool()
        .sounds(BlockSoundGroup.STONE)
)

val ruby_ore: Block = Block(
    AbstractBlock.Settings.create()
        .mapColor { MapColor.STONE_GRAY }
        .strength(2.0f, 6.0f)
        .requiresTool()
        .sounds(BlockSoundGroup.STONE)
)

// 👇 ключевой момент: делаем val crusher, чтобы на него можно было ссылаться
val crusher: Block = CrusherBlock(
    AbstractBlock.Settings.create()
        .strength(4.0f)
        .requiresTool()
)

class CrusherBlock(settings: AbstractBlock.Settings) : Block(settings), BlockEntityProvider {
    override fun createBlockEntity(pos: BlockPos, state: BlockState): BlockEntity =
        CrusherBlockEntity(pos, state)

    override fun getRenderType(state: BlockState): BlockRenderType =
        BlockRenderType.MODEL

    override fun onUse(
        state: BlockState,
        world: World,
        pos: BlockPos,
        player: PlayerEntity,
        hand: Hand,
        hit: BlockHitResult
    ): ActionResult {
        if (!world.isClient) {
            val blockEntity = world.getBlockEntity(pos) as? CrusherBlockEntity
            if (blockEntity != null) {
                player.openHandledScreen(blockEntity)
            }
        }
        return ActionResult.SUCCESS
    }
}
// Объяснение:
//ActionResult.SUCCESS → означает, что клик обработан (и анимация руки отыграет).
//ActionResult.CONSUME → то же самое, но говорит клиенту «не пытайся обработать дальше».
//ActionResult.PASS → пропускает обработку (будто блока нет).
//ActionResult.FAIL → означает «действие не прошло» (например, клик невозможен).

fun registerBlocks() {
    Registry.register(Registries.BLOCK, Identifier(MOD_ID, "ruby_block"), ruby_block)
    Registry.register(Registries.BLOCK, Identifier(MOD_ID, "ruby_ore"), ruby_ore)
    Registry.register(Registries.BLOCK, Identifier(MOD_ID, "crusher"), crusher)

    Registry.register(
        Registries.ITEM,
        Identifier(MOD_ID, "ruby_block"),
        BlockItem(ruby_block, Item.Settings())
    )
    Registry.register(
        Registries.ITEM,
        Identifier(MOD_ID, "ruby_ore"),
        BlockItem(ruby_ore, Item.Settings())
    )
    Registry.register(
        Registries.ITEM,
        Identifier(MOD_ID, "crusher"),
        BlockItem(crusher, Item.Settings())
    )
}
