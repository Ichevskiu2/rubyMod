package com.rubymod.screenhandler

import com.rubymod.ModScreenHandlers
import com.rubymod.cutter
import com.rubymod.ruby
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.inventory.Inventory
import net.minecraft.inventory.SimpleInventory
import net.minecraft.item.ItemStack
import net.minecraft.item.Items
import net.minecraft.screen.ScreenHandler
import net.minecraft.screen.slot.Slot

class CrusherScreenHandler(
    syncId: Int,
    playerInventory: PlayerInventory,
    inventory: Inventory = SimpleInventory(4)
) : ScreenHandler(ModScreenHandlers.CRUSHER, syncId) {

    class FuelSlot(inventory: Inventory, index: Int, x: Int, y: Int) : Slot(inventory, index, x, y) {
        override fun canInsert(stack: ItemStack): Boolean {
            return stack.item == Items.BLAZE_POWDER // или AbstractFurnaceBlockEntity.canUseAsFuel(stack)
        }
    }

    class CutterSlot(inventory: Inventory, index: Int, x: Int, y: Int) : Slot(inventory, index, x, y) {
        override fun canInsert(stack: ItemStack): Boolean {
            return stack.item == cutter // или AbstractFurnaceBlockEntity.canUseAsFuel(stack)
        }
    }

    class RubySlot(inventory: Inventory, index: Int, x: Int, y: Int) : Slot(inventory, index, x, y) {
        override fun canInsert(stack: ItemStack): Boolean {
            return stack.item == ruby // или AbstractFurnaceBlockEntity.canUseAsFuel(stack)
        }
    }
    class OutputSlot(inventory: Inventory, index: Int, x: Int, y: Int) : Slot(inventory, index, x, y) {
        override fun canInsert(stack: ItemStack): Boolean {
            return false // сюда руками класть нельзя
        }
    }

    init {
        // твоя схема: 2 резака + вход + топливо + выход
        this.addSlot(RubySlot(inventory, 0, 34, 17)) // рубин
        this.addSlot(CutterSlot(inventory, 1, 12, 17)) // резак 1
        this.addSlot(CutterSlot(inventory, 2, 56, 17)) // резак 2
        this.addSlot(FuelSlot(inventory, 3, 34, 53)) // топливо
        this.addSlot(OutputSlot(inventory, 4, 116, 35)) // выход

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
