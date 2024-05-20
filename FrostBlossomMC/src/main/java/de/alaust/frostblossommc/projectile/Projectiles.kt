package de.alaust.frostblossommc.projectile

import org.bukkit.Material
import org.bukkit.entity.AbstractArrow
import org.bukkit.entity.Arrow
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.scheduler.BukkitRunnable
import org.bukkit.util.Vector
import java.util.*


class Projectiles {
    companion object {
        private val NON_SOLID: Array<Material> = arrayOf(
            Material.AIR,
            Material.TORCH
        )

        fun cast(plugin: JavaPlugin, player: Player, projectile: CustomProjectile) {
            val arrow = player.launchProjectile(Arrow::class.java)
            arrow.damage = projectile.damage
            arrow.velocity = arrow.velocity.multiply(projectile.force)
            arrow.pickupStatus = AbstractArrow.PickupStatus.DISALLOWED
            arrow.isSilent = true

            object : BukkitRunnable() {
                var ticksLived: Int = 0

                override fun run() {
                    if (projectile.tickHandler != null) {
                        projectile.tickHandler.onTick(arrow)
                    }

                    if (projectile.bouncing) {
                        val newVelocity: Vector = calculateBounceVelocity(arrow, 1 - projectile.bounceForceReduction)
                        val oldVelocity: Vector = arrow.velocity
                        if (!(Objects.equals(newVelocity, oldVelocity)) && projectile.bounceHandler != null) {
                            projectile.bounceHandler.onBounce(arrow)
                        }

                        arrow.velocity = newVelocity
                    }

                    if (arrow.isDead || arrow.isInBlock || ticksLived >= projectile.liveTime) {
                        arrow.remove()
                        if (projectile.destroyHandler != null) {
                            projectile.destroyHandler.onDestroy(arrow)
                        }
                        cancel()
                    }
                    ticksLived++
                }
            }.runTaskTimer(plugin, 0, 1)
        }


        private fun checkNonSolid(arrow: Arrow, x: Double, y: Double, z: Double): Boolean {
            return !NON_SOLID.contains(arrow.location.add(x, y, z).block.type)
        }

        private fun calculateBounceVelocity(arrow: Arrow, bounceForceReduction: Double): Vector {
            val velocity: Vector = arrow.velocity
            if (checkNonSolid(arrow, 1.0, 0.0, 0.0) && arrow.velocity.x > 0.1) {
                velocity.setX(velocity.x * -bounceForceReduction)
            } else if (checkNonSolid(arrow, -1.0, 0.0, 0.0) && arrow.velocity.x < -0.1) {
                velocity.setX(velocity.x * -bounceForceReduction)
            } else if (checkNonSolid(arrow, 0.0, 1.0, 0.0) && arrow.velocity.y > 0.1) {
                velocity.setY(velocity.y * -bounceForceReduction)
            } else if (checkNonSolid(arrow, 0.0, -1.0, 0.0) && arrow.velocity.y < -0.1) {
                velocity.setY(velocity.y * -bounceForceReduction)
            } else if (checkNonSolid(arrow, 0.0, 0.0, 1.0) && arrow.velocity.z > 0.1) {
                velocity.setZ(velocity.z * -bounceForceReduction)
            } else if (checkNonSolid(arrow, 0.0, 0.0, -1.0) && arrow.velocity.z < -0.1) {
                velocity.setZ(velocity.z * -bounceForceReduction)
            }

            return velocity
        }
    }
}