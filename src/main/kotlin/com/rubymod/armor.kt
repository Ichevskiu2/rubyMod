package com.rubymod

import net.minecraft.item.ArmorItem
import net.minecraft.item.ArmorMaterial
import net.minecraft.item.Item
import net.minecraft.recipe.Ingredient
import net.minecraft.registry.Registry
import net.minecraft.registry.Registries
import net.minecraft.util.Identifier
import net.minecraft.sound.SoundEvents

// материал брони
object RubyArmorMaterial : ArmorMaterial {
    override fun getDurability(type: ArmorItem.Type): Int {
        val base = when (type) {
            ArmorItem.Type.BOOTS -> 13
            ArmorItem.Type.LEGGINGS -> 15
            ArmorItem.Type.CHESTPLATE -> 16
            ArmorItem.Type.HELMET -> 11
        }
        val multiplier = 30
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

    // ЗДЕСЬ: используем top-level `ruby` из items.kt
    override fun getRepairIngredient(): Ingredient = Ingredient.ofItems(ruby)

    override fun getName(): String = "ruby" // для путей к текстурам: textures/models/armor/ruby_layer_*.png
    override fun getToughness(): Float = 1.0f
    override fun getKnockbackResistance(): Float = 0.0f
}

// top-level предметы брони — видимы в других файлах
val ruby_helmet = ArmorItem(RubyArmorMaterial, ArmorItem.Type.HELMET, Item.Settings())
val ruby_chestplate = ArmorItem(RubyArmorMaterial, ArmorItem.Type.CHESTPLATE, Item.Settings())
val ruby_leggings = ArmorItem(RubyArmorMaterial, ArmorItem.Type.LEGGINGS, Item.Settings())
val ruby_boots = ArmorItem(RubyArmorMaterial, ArmorItem.Type.BOOTS, Item.Settings())

// регистрация — вызывай registerArmor() из RubyMod.onInitialize() после registerItems()
fun registerArmor() {
    Registry.register(Registries.ITEM, Identifier(MOD_ID, "ruby_helmet"), ruby_helmet)
    Registry.register(Registries.ITEM, Identifier(MOD_ID, "ruby_chestplate"), ruby_chestplate)
    Registry.register(Registries.ITEM, Identifier(MOD_ID, "ruby_leggings"), ruby_leggings)
    Registry.register(Registries.ITEM, Identifier(MOD_ID, "ruby_boots"), ruby_boots)
}
