package com.rubymod

import net.fabricmc.api.ModInitializer
import org.slf4j.LoggerFactory
import net.minecraft.block.AbstractBlock
import net.minecraft.block.Block
import net.minecraft.block.MapColor
import net.minecraft.item.ArmorItem
import net.minecraft.item.ArmorMaterial
import net.minecraft.item.BlockItem
import net.minecraft.item.Item
import net.minecraft.item.PickaxeItem
import net.minecraft.item.AxeItem
import net.minecraft.item.ShovelItem
import net.minecraft.item.HoeItem
import net.minecraft.item.SwordItem
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.sound.BlockSoundGroup
import net.minecraft.util.Identifier
import net.minecraft.item.ToolMaterial
import net.minecraft.recipe.Ingredient
import net.minecraft.sound.SoundEvents

const val MOD_ID = "rubymod"  // <-- объявляем здесь, на уровне пакета

object RubyToolMaterial : ToolMaterial {
    override fun getDurability(): Int = 1024              // прочность (железо = 250, алмаз = 1561)
    override fun getMiningSpeedMultiplier(): Float = 7.0f // скорость копания
    override fun getAttackDamage(): Float = 2.5f          // базовый урон
    override fun getMiningLevel(): Int = 3                // уровень добычи (0-древо, 1-камень, 2-железо, 3-алмаз, 4-незерит)
    override fun getEnchantability(): Int = 15            // зачаровываемость (чем больше, тем лучше)
    override fun getRepairIngredient(): Ingredient =
        Ingredient.ofItems(RubyMod.ruby)                  // предмет для починки (наш ruby item)
}

object RubyArmorMaterial : ArmorMaterial {
    override fun getDurability(type: ArmorItem.Type): Int {
        val base = when (type) {
            ArmorItem.Type.BOOTS -> 13
            ArmorItem.Type.LEGGINGS -> 15
            ArmorItem.Type.CHESTPLATE -> 16
            ArmorItem.Type.HELMET -> 11
        }
        val multiplier = 30 // множитель прочности (как у алмаза)
        return base * multiplier
    }

    override fun getProtection(type: ArmorItem.Type): Int = when (type) {
        ArmorItem.Type.BOOTS -> 2
        ArmorItem.Type.LEGGINGS -> 5
        ArmorItem.Type.CHESTPLATE -> 7
        ArmorItem.Type.HELMET -> 2
    }

    override fun getEnchantability(): Int = 9

    override fun getEquipSound() = SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND

    override fun getRepairIngredient(): Ingredient =
        Ingredient.ofItems(RubyMod.ruby) // твой ruby item

    // ВАЖНО: имя влияет на путь к текстурам брони:
    // assets/rubymod/textures/models/armor/ruby_layer_1.png
    // assets/rubymod/textures/models/armor/ruby_layer_2.png
    override fun getName(): String = "ruby"

    override fun getToughness(): Float = 1.0f  // броня более "стойкая" (как у алмаза)

    override fun getKnockbackResistance(): Float = 0.0f
}
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
            //recipeRemainder(Item.ruby) // возвращает предмет после крафта, как ведро, но предмет, который возвращается уже должен быть ранее добавлен, в данному случае нужно работать с рецептами крафта
    )
    // Инструмент — кирка
    val ruby_pickaxe: Item = PickaxeItem(
        RubyToolMaterial,   // наш материал
        1,                  // добавочный урон (к базовому)
        -2.8f,              // скорость атаки
        Item.Settings()
    )
    val ruby_axe: Item = AxeItem(
        RubyToolMaterial,   // наш материал
        8f,                  // добавочный урон (к базовому)
        -3.5f,              // скорость атаки
        Item.Settings()
    )
    val ruby_sword: Item = SwordItem(
        RubyToolMaterial,   // наш материал
        6,                  // добавочный урон (к базовому)
        -1.7f,              // скорость атаки
        Item.Settings()
    )
    val ruby_hoe: Item = HoeItem(
        RubyToolMaterial,   // наш материал
        1,                  // добавочный урон (к базовому)
        -2.8f,              // скорость атаки
        Item.Settings()
    )
    val ruby_shovel: Item = ShovelItem(
        RubyToolMaterial,   // наш материал
        8f,                  // добавочный урон (к базовому)
        -2.8f,              // скорость атаки
        Item.Settings()
    )
    val ruby_helmet = ArmorItem(RubyArmorMaterial, ArmorItem.Type.HELMET, Item.Settings())
    val ruby_chestplate = ArmorItem(RubyArmorMaterial, ArmorItem.Type.CHESTPLATE, Item.Settings())
    val ruby_leggings = ArmorItem(RubyArmorMaterial, ArmorItem.Type.LEGGINGS, Item.Settings())
    val ruby_boots = ArmorItem(RubyArmorMaterial, ArmorItem.Type.BOOTS, Item.Settings())

    override fun onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		logger.info("Hello Fabric world!")
        // Регистрируем блок
        Registry.register(Registries.BLOCK, Identifier(MOD_ID, "ruby_block"), ruby_block)
        Registry.register(Registries.BLOCK, Identifier(MOD_ID, "ruby_ore"), ruby_ore)
        Registry.register(Registries.ITEM, Identifier(MOD_ID, "ruby"), ruby)
        Registry.register(Registries.ITEM, Identifier(MOD_ID, "ruby_pickaxe"),ruby_pickaxe )
        Registry.register(Registries.ITEM, Identifier(MOD_ID, "ruby_axe"), ruby_axe)
        Registry.register(Registries.ITEM, Identifier(MOD_ID, "ruby_sword"), ruby_sword)
        Registry.register(Registries.ITEM, Identifier(MOD_ID, "ruby_hoe"), ruby_hoe)
        Registry.register(Registries.ITEM, Identifier(MOD_ID, "ruby_shovel"), ruby_shovel)

        Registry.register(Registries.ITEM, Identifier(MOD_ID, "ruby_helmet"), ruby_helmet)
        Registry.register(Registries.ITEM, Identifier(MOD_ID, "ruby_chestplate"), ruby_chestplate)
        Registry.register(Registries.ITEM, Identifier(MOD_ID, "ruby_leggings"), ruby_leggings)
        Registry.register(Registries.ITEM, Identifier(MOD_ID, "ruby_boots"), ruby_boots)


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