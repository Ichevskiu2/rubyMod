package com.rubymod

import com.rubymod.entities.CrusherBlockEntity
import net.minecraft.block.entity.BlockEntityType
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.util.Identifier

object ModBlockEntities {
    val CRUSHER: BlockEntityType<CrusherBlockEntity> = Registry.register(
        Registries.BLOCK_ENTITY_TYPE,
        Identifier(MOD_ID, "crusher"),
        BlockEntityType.Builder.create(::CrusherBlockEntity, crusher).build(null)
    )

    fun registerAll() {
        // вызвать в onInitialize()
    }
}
