package com.example.assign1

import androidx.recyclerview.widget.RecyclerView

interface ItemDragListener {
    fun onStartDrag(viewHolder: RecyclerView.ViewHolder)
}

interface ItemActionListner {
    fun onItemMoved(from: Int, to: Int)
    fun onItemSwiped(position: Int)
}