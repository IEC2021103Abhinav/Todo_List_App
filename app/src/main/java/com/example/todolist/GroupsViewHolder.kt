package com.example.todolist

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class GroupsViewHolder(inflater: LayoutInflater,
                       parent: ViewGroup
):
    RecyclerView.ViewHolder(inflater.inflate(R.layout.group_row,parent,false))
{
    private var groupNameTextView: TextView?=null
    private var groupCountTextView: TextView?=null
    init {
        groupNameTextView=itemView.findViewById(R.id.groupNametextView)
        groupCountTextView=itemView.findViewById(R.id.groupcountTextView)
    }
    fun bind(group:Group)
    {
        groupNameTextView!!.text=group.name
        groupCountTextView!!.text="${group.items.count()}items"

    }
}