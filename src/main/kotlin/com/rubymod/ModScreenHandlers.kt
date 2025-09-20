package com.rubymod

import com.rubymod.screenhandler.CrusherScreenHandler
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.screen.ScreenHandlerType
import net.minecraft.util.Identifier
import net.minecraft.resource.featuretoggle.FeatureFlags

object ModScreenHandlers {
    val CRUSHER: ScreenHandlerType<CrusherScreenHandler> = Registry.register(
        Registries.SCREEN_HANDLER,
        Identifier(MOD_ID, "crusher"),
        ScreenHandlerType(::CrusherScreenHandler, FeatureFlags.VANILLA_FEATURES) // 👈 тут только конструктор
    )

    fun registerAll() {
        // вызвать в onInitialize()
    }
}
