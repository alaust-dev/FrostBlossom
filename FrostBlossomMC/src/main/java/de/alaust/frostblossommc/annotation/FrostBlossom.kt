package de.alaust.frostblossommc.annotation

import de.alaust.frostblossommc.item.CustomItem
import kotlin.reflect.KClass

/**
 * An Annotation to mark FrostBlossom Classes.
 *
 * This annotation is used to find and register FrostBlossom related Classes.
 */
@Target(AnnotationTarget.CLASS)
annotation class FrostBlossom(
    val dependencies: Array<KClass<out CustomItem>> = []
)
