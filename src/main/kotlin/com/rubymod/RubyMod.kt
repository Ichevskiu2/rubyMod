package com.rubymod

import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.impl.networking.NetworkingImpl.MOD_ID
import org.slf4j.LoggerFactory
import net.minecraft.block.AbstractBlock
import net.minecraft.block.Block
import net.minecraft.block.MapColor
import net.minecraft.item.BlockItem
import net.minecraft.item.Item
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.sound.BlockSoundGroup
import net.minecraft.util.Identifier

const val MOD_ID = "rubymod"  // <-- объявляем здесь, на уровне пакета

object RubyMod : ModInitializer {
    private val logger = LoggerFactory.getLogger("rubymod")

    val RubyBlock: Block = Block(
        AbstractBlock.Settings.create()
            .mapColor { MapColor.BRIGHT_RED }
            .strength( 3.0f, 6.0f)
            .requiresTool()
            .sounds(BlockSoundGroup.STONE)
            //.dropsLike()
    )
    override fun onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		logger.info("Hello Fabric world!")
        // Регистрируем блок
        Registry.register(Registries.BLOCK, Identifier(MOD_ID, "ruby_block"), RubyBlock)

        // Регистрируем BlockItem, чтобы можно было поставить блок в инвентарь
        Registry.register(
            Registries.ITEM,
            Identifier(MOD_ID, "ruby_block"),
            BlockItem(RubyBlock, Item.Settings())
        )
	}
}