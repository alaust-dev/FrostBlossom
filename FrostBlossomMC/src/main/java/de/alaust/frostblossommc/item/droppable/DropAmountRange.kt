package de.alaust.frostblossommc.item.droppable

import kotlin.random.Random

class DropAmountRange(val min: Int, val max: Int) {
    constructor(amount: Int) : this(amount, amount)

    fun getRandom(): Int {
        return if (min == max) min else Random.nextInt(min, max)
    }
}