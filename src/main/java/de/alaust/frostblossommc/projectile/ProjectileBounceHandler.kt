package de.alaust.frostblossommc.projectile

import org.bukkit.entity.Arrow

fun interface ProjectileBounceHandler {
    fun onBounce(arrow: Arrow)
}