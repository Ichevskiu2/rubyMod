package com.rubymod.screen

import com.rubymod.screenhandler.CrusherScreenHandler
import net.minecraft.client.gui.screen.ingame.HandledScreen
import net.minecraft.client.gui.DrawContext
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.text.Text
import net.minecraft.util.Identifier

class CrusherScreen(handler: CrusherScreenHandler, playerInventory: PlayerInventory, title: Text) :
    HandledScreen<CrusherScreenHandler>(handler, playerInventory, title) {

    companion object {
        private val TEXTURE = Identifier("rubymod", "textures/gui/crusher.png")
    }

    override fun drawBackground(context: DrawContext, delta: Float, mouseX: Int, mouseY: Int) {
        context.drawTexture(TEXTURE, this.x, this.y, 0, 0, this.backgroundWidth, this.backgroundHeight)
    }

    override fun render(context: DrawContext, mouseX: Int, mouseY: Int, delta: Float) {
        renderBackground(context)
        super.render(context, mouseX, mouseY, delta)
        drawMouseoverTooltip(context, mouseX, mouseY)
    }
}
