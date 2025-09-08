package com.rubymod

import net.minecraft.item.Item
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.util.Identifier

val ruby: Item = Item(
    Item.Settings()
        .maxCount(64) // максимум в стаке (по умолчанию 64)
    //.maxDamage(250) // «прочность», если предмет ломается
    //.fireproof() // не сгорит в лаве
    //.rarity(Rarity.RARE) // редкость (обычная, редкая, эпическая)
    //recipeRemainder(Item.ruby) // возвращает предмет после крафта, как ведро, но предмет, который возвращается уже должен быть ранее добавлен, в данному случае нужно работать с рецептами крафта
)
// функция регистрации предметов
fun registerItems() {
    Registry.register(Registries.ITEM, Identifier(MOD_ID, "ruby"), ruby)
}