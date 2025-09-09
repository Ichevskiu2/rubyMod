package com.rubymod

import net.minecraft.block.AbstractBlock
import net.minecraft.block.Block
import net.minecraft.block.MapColor
import net.minecraft.item.BlockItem
import net.minecraft.item.Item
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.sound.BlockSoundGroup
import net.minecraft.util.Identifier

val ruby_block: Block = Block(
    AbstractBlock.Settings.create()
        .mapColor { MapColor.BRIGHT_RED }
        .strength( 2.0f, 6.0f)
        .requiresTool()
        .sounds(BlockSoundGroup.STONE)
)
val ruby_ore: Block = Block(
    AbstractBlock.Settings.create()
        .mapColor { MapColor.STONE_GRAY }
        .strength( 2.0f, 6.0f)
        .requiresTool()
        .sounds(BlockSoundGroup.STONE)
)
val crusher: Block = Block(
    AbstractBlock.Settings.create()
        .mapColor { MapColor.LIGHT_GRAY }
        .strength(2.0f, 6.0f)
        .requiresTool()
        .sounds(BlockSoundGroup.VINE)
)

fun registerBlocks() {
    // потом блоки
    Registry.register(Registries.BLOCK, Identifier(MOD_ID, "ruby_block"), ruby_block)
    Registry.register(Registries.BLOCK, Identifier(MOD_ID, "ruby_ore"), ruby_ore)
    Registry.register(Registries.BLOCK, Identifier(MOD_ID, "crusher"), crusher)

    // blockItems
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
