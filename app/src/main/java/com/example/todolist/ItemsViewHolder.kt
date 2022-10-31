package com.example.todolist

import android.graphics.Color
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.zip.Inflater

class ItemsViewHolder(inflater: LayoutInflater,
                        parent:ViewGroup):RecyclerView.ViewHolder(inflater.inflate(R.layout.items_row,
    parent,
    false))
{
    private var itemNameTextView:TextView?=null
    private var itemCheckBox:CheckBox?=null
    init {
        itemNameTextView=itemView.findViewById(R.id.itemnameTextView)
        itemCheckBox=itemView.findViewById(R.id.itemCheckBox)
    }
    fun bind(item: Item)
    {
//        there are two things here 1st is checkbox and another is a textview
        itemNameTextView!!.text=item.name
        itemCheckBox!!.isChecked=item.completed
        if(item.completed)
        {
            itemNameTextView!!.paintFlags=itemNameTextView!!.paintFlags or
                    Paint.STRIKE_THRU_TEXT_FLAG
            itemView.setBackgroundColor(Color.LTGRAY)
        }
        else
        {
            itemNameTextView!!.paintFlags=itemNameTextView!!.paintFlags and
                    Paint.STRIKE_THRU_TEXT_FLAG.inv()
            itemView.setBackgroundColor(Color.TRANSPARENT)
        }
    }
}