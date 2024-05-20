package de.alaust.frostblossommc

import de.alaust.frostblossommc.annotation.FrostBlossom
import de.alaust.frostblossommc.item.CustomItem
import de.alaust.frostblossommc.item.ItemManager
import de.alaust.frostblossommc.item.droppable.block.BlockBreakListener
import de.alaust.frostblossommc.item.interactable.InteractListener
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin
import org.reflections.Reflections

class FrostBlossomMC {
    companion object {
        private val itemManager = ItemManager()

        fun initialise(plugin: JavaPlugin, scanPath: String) {
            scanForFrostBlossomClasses(scanPath)

            val pluginManager = Bukkit.getPluginManager()
            pluginManager.registerEvents(BlockBreakListener(), plugin)
            pluginManager.registerEvents(InteractListener(), plugin)
        }

        fun instanceOf(clazz: Class<CustomItem>) = itemManager.instanceOf(clazz)

        private fun scanForFrostBlossomClasses(scanPath: String) {
            val reflectedPackage = Reflections(scanPath)
            val annotatedClasses = reflectedPackage.getTypesAnnotatedWith(FrostBlossom::class.java)

            for (clazz in annotatedClasses) {
                val customClassInstance = clazz.getDeclaredConstructor().newInstance()

                if (customClassInstance is CustomItem) {
                    itemManager.registerCustomItem(customClassInstance)
                }
            }
        }
    }
}