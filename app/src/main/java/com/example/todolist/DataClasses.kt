package com.example.todolist
//here we made two different data classes which are for Items and those items are in a Group
//kis type ka data object bana rah hai just like item which has name(string) AND ITS STATUS OF COMPLETION
//group--->name (string)  and they have items which has list of item

data class Item(val name:String,
                var completed:Boolean)

data class Group(val name: String,
                val items:MutableList<Item>)