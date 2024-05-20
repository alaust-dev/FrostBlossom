package de.alaust.frostblossommc.item.droppable.entity

import de.alaust.frostblossommc.item.droppable.DropAmountRange
import org.bukkit.Material
import org.bukkit.entity.EntityType
import org.bukkit.inventory.ItemStack

class EntityDropData private constructor(
    val sourceEntity: EntityType,
    val dropPercentChance: Float,
    val dropItemStack: ItemStack,
    val dropAmountRange: DropAmountRange
) {
    class Builder(
        private var sourceEntity: EntityType = EntityType.BAT,
        private var dropPercentChance: Float = 1f,
        private var dropItemStack: ItemStack = ItemStack(Material.AIR),
        private var dropAmountRange: DropAmountRange = DropAmountRange(1)
    ) {
        fun sourceEntity(sourceEntity: EntityType) = apply { this.sourceEntity }
        fun dropPercentChance(dropPercentChance: Float) = apply { this.dropPercentChance }
        fun dropItemStack(dropItemStack: ItemStack) = apply { this.dropItemStack = dropItemStack }
        fun dropItemStackAmount(dropAmountRange: DropAmountRange) = apply { this.dropAmountRange = dropAmountRange }
        fun build(): EntityDropData = EntityDropData(sourceEntity, dropPercentChance, dropItemStack, dropAmountRange)
    }
}