package de.alaust.frostblossommc.item.interactable

import org.bukkit.event.player.PlayerInteractEvent

fun interface Interactable {
    fun onInteract(event: PlayerInteractEvent)
}