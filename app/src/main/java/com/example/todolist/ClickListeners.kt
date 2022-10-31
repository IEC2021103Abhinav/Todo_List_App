package com.example.todolist

interface OnGroupClickListener
{
    fun groupClicked(index:Int)
    fun groupLongClicked(index:Int)
}
interface OnItemClickListener
{
    fun ItemClicked(index:Int)
    fun ItemLongClicked(index:Int)
}