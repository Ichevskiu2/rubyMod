package com.rubymod

import net.fabricmc.api.ModInitializer
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
    val ruby: Item = Item(
        Item.Settings()
            .maxCount(64) // максимум в стаке (по умолчанию 64)
            //.maxDamage(250) // «прочность», если предмет ломается
            //.fireproof() // не сгорит в лаве
            //.rarity(Rarity.RARE) // редкость (обычная, редкая, эпическая)
            //.recipeRemainder(Item.ruby) // возвращает предмет после крафта, как ведро, но предмет, который возвращается уже должен быть ранее добавлен, в данному случае нужно работать с рецептами крафта
    )

    override fun onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		logger.info("Hello Fabric world!")
        // Регистрируем блок
        Registry.register(Registries.BLOCK, Identifier(MOD_ID, "ruby_block"), ruby_block)
        Registry.register(Registries.BLOCK, Identifier(MOD_ID, "ruby_ore"), ruby_ore)
        Registry.register(Registries.ITEM, Identifier(MOD_ID, "ruby"), ruby)
        // Регистрируем BlockItem, чтобы можно было поставить блок в инвентарь
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
	}
}