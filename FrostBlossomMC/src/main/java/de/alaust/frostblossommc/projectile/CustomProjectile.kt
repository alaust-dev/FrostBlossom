package de.alaust.frostblossommc.projectile


class CustomProjectile private constructor(
    val liveTime: Int,
    val damage: Double,
    val force: Double,
    val bounceForceReduction: Double,
    val bouncing: Boolean,
    val tickHandler: ProjectileTickHandler?,
    val bounceHandler: ProjectileBounceHandler?,
    val destroyHandler: ProjectileDestroyHandler?,
) {
    data class Builder(
        private var liveTime: Int = 200,
        private var damage: Double = 1.0,
        private var force: Double = 0.5,
        private var bounceForceReduction: Double = 0.0,
        private var bouncing: Boolean = false,
        private var tickHandler: ProjectileTickHandler? = null,
        private var bounceHandler: ProjectileBounceHandler? = null,
        private var destroyHandler: ProjectileDestroyHandler? = null
    ) {
        fun liveTime(liveTime: Int) = apply { this.liveTime = liveTime }
        fun damage(damage: Double) = apply { this.damage = damage }
        fun force(force: Double) = apply { this.force = force }
        fun bounceForceReduction(bounceForceReduction: Double) =
            apply { this.bounceForceReduction = bounceForceReduction }

        fun bouncing(bouncing: Boolean) = apply { this.bouncing = bouncing }
        fun tickHandler(tickHandler: ProjectileTickHandler) = apply { this.tickHandler = tickHandler }
        fun bounceHandler(bounceHandler: ProjectileBounceHandler) = apply { this.bounceHandler = bounceHandler }
        fun destroyHandler(destroyHandler: ProjectileDestroyHandler) = apply { this.destroyHandler = destroyHandler }
        fun build() = CustomProjectile(
            liveTime,
            damage,
            force,
            bounceForceReduction,
            bouncing,
            tickHandler,
            bounceHandler,
            destroyHandler
        )
    }
}