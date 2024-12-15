package de.alaust.frostblossommc.item

import org.bukkit.inventory.ItemStack

abstract class CustomItem(
    val itemStack: ItemStack
) {
    fun isSimilarTo(similarStack: ItemStack): Boolean {
        val similarItemMeta = similarStack.itemMeta
        val itemMeta = itemStack.itemMeta

        if (itemStack.type != similarStack.type)
            return false
        if ((itemMeta == null && similarItemMeta == null))
            return true
        if (!itemMeta.hasCustomModelData() && !similarItemMeta.hasCustomModelData())
            return true
        if (itemMeta.customModelData == similarItemMeta.customModelData)
            return true
        return false
    }
}