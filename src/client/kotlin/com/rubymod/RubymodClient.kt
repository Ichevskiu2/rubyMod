package com.rubymod

import net.fabricmc.api.ClientModInitializer
import net.minecraft.client.gui.screen.ingame.HandledScreens
import com.rubymod.screen.CrusherScreen

object RubymodClient : ClientModInitializer {
    override fun onInitializeClient() {
        HandledScreens.register(ModScreenHandlers.CRUSHER, ::CrusherScreen)
    }
}