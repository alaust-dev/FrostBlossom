package de.alaust.frostblossommc

import de.alaust.frostblossommc.annotation.FrostBlossom
import de.alaust.frostblossommc.exceptions.InitializationException
import de.alaust.frostblossommc.item.CustomItem
import de.alaust.frostblossommc.item.ItemManager
import de.alaust.frostblossommc.item.droppable.block.BlockBreakListener
import de.alaust.frostblossommc.item.droppable.entity.EntityDeathListener
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin
import org.reflections.Reflections
import kotlin.reflect.KClass

class FrostBlossomMC {
    companion object {
        private val blockBreakListener = BlockBreakListener()
        private val entityDeathListener = EntityDeathListener()
        private val itemManager = ItemManager(blockBreakListener, entityDeathListener)

        fun instanceOf(clazz: KClass<CustomItem>) = itemManager.instanceOf(clazz)

        fun initialise(plugin: JavaPlugin, scanPath: String) {
            scanForFrostBlossomClasses(scanPath)

            val pluginManager = Bukkit.getPluginManager()
            pluginManager.registerEvents(blockBreakListener, plugin)
            pluginManager.registerEvents(entityDeathListener, plugin)
        }

        private fun scanForFrostBlossomClasses(scanPath: String) {
            val reflectedPackage = Reflections(scanPath)
            val annotatedClasses = reflectedPackage.getTypesAnnotatedWith(FrostBlossom::class.java)

            for (clazz in annotatedClasses) {
                if (clazz.superclass == CustomItem::class.java) {
                    loadItem(clazz, null)
                }
            }
        }

        private fun loadItem(clazz: Class<*>, origin: Class<*>?) {
            if (origin == clazz) {
                throw InitializationException("Circular dependency detected: $clazz")
            }

            val customClassInstance = clazz.getDeclaredConstructor().newInstance() as CustomItem
            if (itemManager.isRegistered(customClassInstance)) {
                return
            }

            val dependencies = clazz.getAnnotation(FrostBlossom::class.java).dependencies
            dependencies.forEach {
                dep -> loadItem(dep::class.java, origin ?: clazz)
            }

            itemManager.registerCustomItem(customClassInstance)
        }
    }
}