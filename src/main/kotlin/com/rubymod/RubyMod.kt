package com.rubymod

import net.fabricmc.api.ModInitializer

const val MOD_ID = "rubymod"

object RubyMod : ModInitializer {
    override fun onInitialize() {
        // Этот код выполняется при загрузке мода
        registerItems()
        registerTools()
        registerBlocks()
        registerArmor()
    }
}