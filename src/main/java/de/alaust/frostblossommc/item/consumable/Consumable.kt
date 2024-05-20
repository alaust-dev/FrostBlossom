package de.alaust.frostblossommc.item.consumable

import org.bukkit.event.player.PlayerItemConsumeEvent

fun interface Consumable {
    fun onConsume(event: PlayerItemConsumeEvent)
}