package com.example.todolist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class ItemsAdapter(private val group: Group,
            listenerContext:OnItemClickListener):RecyclerView.Adapter<ItemsViewHolder>(){
    //    here we have pass a private variable because we wnt to show that clicked group
    private var myInterface:OnItemClickListener=listenerContext
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemsViewHolder {
        val inflater=LayoutInflater.from(parent.context)
        return ItemsViewHolder(inflater,parent)
    }

    override fun onBindViewHolder(holder: ItemsViewHolder, position: Int) {
        val item:Item=group.items[position]
        holder.bind(item)
        holder.itemView.setOnClickListener{
            myInterface.ItemClicked(position)
        }
        holder.itemView.setOnLongClickListener {
            myInterface.ItemLongClicked(position)
            true
        }
    }

    override fun getItemCount(): Int {
        return group.items.size
    }

}