package com.rubymod

import net.minecraft.item.*
import net.minecraft.recipe.Ingredient
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.util.Identifier

object RubyToolMaterial : ToolMaterial {
    override fun getDurability() = 1024
    override fun getMiningSpeedMultiplier() = 7.0f
    override fun getAttackDamage() = 2.5f
    override fun getMiningLevel() = 3
    override fun getEnchantability() = 15
    override fun getRepairIngredient() = Ingredient.ofItems(ruby)
}

val ruby_pickaxe = PickaxeItem(RubyToolMaterial, 1, -2.8f, Item.Settings())
val ruby_axe = AxeItem(RubyToolMaterial, 8f, -3.5f, Item.Settings())
val ruby_sword = SwordItem(RubyToolMaterial, 6, -1.7f, Item.Settings())
val ruby_hoe = HoeItem(RubyToolMaterial, 1, -2.8f, Item.Settings())
val ruby_shovel = ShovelItem(RubyToolMaterial, 8f, -2.8f, Item.Settings())

fun registerTools() {
    Registry.register(Registries.ITEM, Identifier(MOD_ID, "ruby_pickaxe"), ruby_pickaxe)
    Registry.register(Registries.ITEM, Identifier(MOD_ID, "ruby_axe"), ruby_axe)
    Registry.register(Registries.ITEM, Identifier(MOD_ID, "ruby_sword"), ruby_sword)
    Registry.register(Registries.ITEM, Identifier(MOD_ID, "ruby_hoe"), ruby_hoe)
    Registry.register(Registries.ITEM, Identifier(MOD_ID, "ruby_shovel"), ruby_shovel)
}
