package de.alaust.frostblossommc.item.droppable.block

import de.alaust.frostblossommc.item.droppable.DropAmountRange
import org.bukkit.Material
import org.bukkit.inventory.ItemStack

class BlockDropData private constructor(
    val sourceMaterial: Material,
    val dropPercentChance: Float,
    val dropItemStack: ItemStack,
    val dropAmountRange: DropAmountRange
) {
    class Builder(
        private var sourceMaterial: Material = Material.AIR,
        private var dropPercentChance: Float = 1f,
        private var dropItemStack: ItemStack = ItemStack(Material.AIR),
        private var dropAmountRange: DropAmountRange = DropAmountRange(1)
    ) {
        fun sourceMaterial(sourceMaterial: Material) = apply { this.sourceMaterial = sourceMaterial }

        fun dropPercentChance(dropPercentChance: Float) = apply { this.sourceMaterial }

        fun dropItemStack(dropItemStack: ItemStack) = apply { this.dropItemStack = dropItemStack }

        fun dropItemStackAmount(dropAmountRange: DropAmountRange) = apply { this.dropAmountRange = dropAmountRange }

        fun build(): BlockDropData = BlockDropData(sourceMaterial, dropPercentChance, dropItemStack, dropAmountRange)
    }
}