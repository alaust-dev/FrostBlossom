package de.alaust.frostblossommc.item

import de.alaust.frostblossommc.item.consumable.Consumable
import de.alaust.frostblossommc.item.consumable.ConsumeListener
import de.alaust.frostblossommc.item.craftable.Craftable
import de.alaust.frostblossommc.item.droppable.block.BlockBreakListener
import de.alaust.frostblossommc.item.droppable.block.BlockDroppable
import de.alaust.frostblossommc.item.droppable.entity.EntityDeathListener
import de.alaust.frostblossommc.item.droppable.entity.EntityDroppable
import de.alaust.frostblossommc.item.interactable.InteractListener
import de.alaust.frostblossommc.item.interactable.Interactable
import org.bukkit.Bukkit

class ItemManager {
    private val itemInstances = HashMap<Class<CustomItem>, CustomItem>()

    fun registerCustomItem(customItem: CustomItem) {
        itemInstances[customItem.javaClass] = customItem

        if (customItem is BlockDroppable) {
            BlockBreakListener.registerBlockDropItem(customItem)
        }
        if (customItem is EntityDroppable) {
            EntityDeathListener.registerEntityDropItem(customItem)
        }
        if (customItem is Craftable) {
            for (recipe in customItem.getRecipes()) {
                Bukkit.getServer().addRecipe(recipe)
            }
        }
        if (customItem is Interactable) {
            InteractListener.registerInteractHandler {
                if (it.item != null && customItem.isSimilarTo(it.item!!))
                    customItem.onInteract(it)
            }
        }
        if (customItem is Consumable) {
            ConsumeListener.registerConsumeHandler {
                if (customItem.isSimilarTo(it.item))
                    customItem.onConsume(it)
            }
        }
    }

    fun instanceOf(itemClass: Class<CustomItem>): CustomItem? {
        return itemInstances[itemClass]
    }
}