package de.alaust.frostblossommc.item.consumable

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerItemConsumeEvent

class ConsumeListener : Listener {
    companion object {
        private val consumeHandler = ArrayList<Consumable>()

        fun registerConsumeHandler(consumable: Consumable) = consumeHandler.add(consumable)
    }

    @EventHandler
    fun onConsume(event: PlayerItemConsumeEvent) {
        for (consumable in consumeHandler) {
            consumable.onConsume(event)
        }
    }
}