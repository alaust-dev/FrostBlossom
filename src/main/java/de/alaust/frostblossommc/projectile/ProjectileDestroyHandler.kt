package de.alaust.frostblossommc.projectile

import org.bukkit.entity.Arrow

fun interface ProjectileDestroyHandler {
    fun onDestroy(arrow: Arrow)
}