package de.alaust.frostblossommc.item.craftable

import org.bukkit.inventory.Recipe

fun interface Craftable {
    fun getRecipes(): Array<Recipe>
}