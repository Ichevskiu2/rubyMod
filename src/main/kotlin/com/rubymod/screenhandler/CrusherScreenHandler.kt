package com.rubymod.screenhandler

import com.rubymod.ModScreenHandlers
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.inventory.Inventory
import net.minecraft.inventory.SimpleInventory
import net.minecraft.item.ItemStack
import net.minecraft.screen.ScreenHandler
import net.minecraft.screen.slot.Slot

class CrusherScreenHandler(
    syncId: Int,
    playerInventory: PlayerInventory,
    inventory: Inventory = SimpleInventory(4)
) : ScreenHandler(ModScreenHandlers.CRUSHER, syncId) {

    init {
        // твоя схема: 2 резака + вход + топливо + выход
        addSlot(Slot(inventory, 0, 26, 17)) // резак 1
        addSlot(Slot(inventory, 1, 44, 17)) // резак 2
        addSlot(Slot(inventory, 2, 35, 53)) // топливо
        addSlot(Slot(inventory, 3, 116, 35)) // выход

        // инвентарь игрока
        for (m in 0 until 3) {
            for (l in 0 until 9) {
                addSlot(Slot(playerInventory, l + m * 9 + 9, 8 + l * 18, 84 + m * 18))
            }
        }
        for (m in 0 until 9) {
            addSlot(Slot(playerInventory, m, 8 + m * 18, 142))
        }
    }

    override fun canUse(player: PlayerEntity): Boolean = true

    override fun quickMove(player: PlayerEntity, invSlot: Int): ItemStack = ItemStack.EMPTY
}
