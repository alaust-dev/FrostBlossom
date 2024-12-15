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
import kotlin.reflect.KClass

class ItemManager(
    private val blockBreakListener: BlockBreakListener,
    private val entityDeathListener: EntityDeathListener
) {
    private val itemInstances = HashMap<KClass<out CustomItem>, CustomItem>()

    fun registerCustomItem(customItem: CustomItem) {
        itemInstances[customItem::class] = customItem

        if (customItem is BlockDroppable) {
            blockBreakListener.registerBlockDropItem(customItem)
        }
        if (customItem is EntityDroppable) {
            entityDeathListener.registerEntityDropItem(customItem)
        }
        if (customItem is Craftable) {
            for (recipe in customItem.getRecipes()) {
                Bukkit.getServer().addRecipe(recipe)
            }
        }
        if (customItem is Interactable) {
            InteractListener.registerInteractHandler {
                val item = it.item
                if (item != null && customItem.isSimilarTo(item))
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

    fun instanceOf(itemClass: KClass<CustomItem>): CustomItem? {
        return itemInstances[itemClass]
    }

    fun isRegistered(itemClass: CustomItem): Boolean {
        return itemInstances.containsKey(itemClass::class)
    }
}