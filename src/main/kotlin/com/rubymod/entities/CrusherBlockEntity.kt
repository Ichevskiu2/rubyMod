package com.rubymod.entities

import com.rubymod.ModBlockEntities
import com.rubymod.screenhandler.CrusherScreenHandler
import net.minecraft.block.BlockState
import net.minecraft.block.entity.BlockEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.inventory.Inventories
import net.minecraft.inventory.SimpleInventory
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NbtCompound
import net.minecraft.screen.NamedScreenHandlerFactory
import net.minecraft.screen.ScreenHandler
import net.minecraft.text.Text
import net.minecraft.util.collection.DefaultedList
import net.minecraft.util.math.BlockPos

class CrusherBlockEntity(pos: BlockPos, state: BlockState)
    : BlockEntity(ModBlockEntities.CRUSHER, pos, state),
    NamedScreenHandlerFactory {

    private val inventory: DefaultedList<ItemStack> = DefaultedList.ofSize(4, ItemStack.EMPTY)

    override fun createMenu(syncId: Int, playerInventory: PlayerInventory, player: PlayerEntity): ScreenHandler {
        return CrusherScreenHandler(syncId, playerInventory, SimpleInventory(inventory.size).also {
            for (i in inventory.indices) it.setStack(i, inventory[i])
        })
    }

    override fun getDisplayName(): Text = Text.translatable("block.rubymod.crusher")

    override fun writeNbt(nbt: NbtCompound) {
        super.writeNbt(nbt)
        Inventories.writeNbt(nbt, inventory)
    }

    override fun readNbt(nbt: NbtCompound) {
        super.readNbt(nbt)
        Inventories.readNbt(nbt, inventory)
    }
}
