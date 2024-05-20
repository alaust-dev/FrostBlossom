package de.alaust.frostblossommc.projectile

import org.bukkit.entity.Arrow

fun interface ProjectileTickHandler {
    fun onTick(arrow: Arrow)
}