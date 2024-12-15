package de.alaust.frostblossommc.item.droppable.block

fun interface BlockDroppable {
    fun getBlockDropData(): Array<BlockDropData>
}