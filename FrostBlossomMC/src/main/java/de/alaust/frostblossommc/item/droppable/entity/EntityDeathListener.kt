package de.alaust.frostblossommc.item.droppable.entity

import org.bukkit.entity.EntityType
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDeathEvent

class EntityDeathListener : Listener {
    private val mappedTypes = HashMap<EntityType, ArrayList<EntityDropData>>()

    fun registerEntityDropItem(entityDroppable: EntityDroppable) {
        val entityDropDataArray = entityDroppable.getEntityDopData()
        for (entityDropData in entityDropDataArray) {
            if (!mappedTypes.containsKey(entityDropData.sourceEntity)) {
                mappedTypes[entityDropData.sourceEntity] = ArrayList()
            }

            mappedTypes[entityDropData.sourceEntity]?.add(entityDropData)
        }
    }

    @EventHandler
    fun onEntityDeath(event: EntityDeathEvent) {
        val diedEntity = event.entity
        val entityType = diedEntity.type
        if (!mappedTypes.containsKey(entityType)) {
            return
        }

        val mobDrops = mappedTypes[entityType] ?: return
        for (mobDrop in mobDrops) {
            if (mobDrop.dropPercentChance > Math.random()) {
                diedEntity.world.dropItem(diedEntity.location, mobDrop.dropItemStack)
            }
        }
    }
}