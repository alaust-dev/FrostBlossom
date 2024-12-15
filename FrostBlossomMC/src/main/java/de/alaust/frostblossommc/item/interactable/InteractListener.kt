package de.alaust.frostblossommc.item.interactable

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerInteractEvent

class InteractListener : Listener {
    companion object {
        private val interactHandlers = ArrayList<Interactable>()

        fun registerInteractHandler(interactable: Interactable) = interactHandlers.add(interactable)
    }

    @EventHandler
    fun onInteraction(event: PlayerInteractEvent) {
        for (interactable in interactHandlers) {
            interactable.onInteract(event)
        }
    }
}