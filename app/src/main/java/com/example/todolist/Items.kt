package com.example.todolist

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import android.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class Items : AppCompatActivity(),OnItemClickListener {
    val newItemEditText = findViewById<TextView>(R.id.NewItemEditText)
    lateinit var thisGroup: Group
    private var itemsAdapter: ItemsAdapter? = null
    override fun ItemClicked(index: Int) {
        thisGroup.items[index].completed = !thisGroup.items[index].completed
        itemsAdapter!!.notifyDataSetChanged()
    }

    override fun ItemLongClicked(index: Int) {
        thisGroup.items.removeAt(index)
        itemsAdapter!!.notifyItemRemoved(index)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_items)
        var selectedIndex = intent.getIntExtra("groupIndex", 0)
//        if nothing in the group then we can make item which we want that is denoted by 0
        var thisGroup = AppData.groups[selectedIndex]
//        by this group we can specify the selected group
        val ToolbarTitle = findViewById<TextView>(R.id.ToolbarTitle)
        ToolbarTitle.text = thisGroup.name
//        the name of group show in the textview of item activity
        val myToolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.myToolBar)
        val itemsRecyclerView = findViewById<RecyclerView>(R.id.ItemsRecyclerView)
        itemsRecyclerView.layoutManager = LinearLayoutManager(this)
        itemsAdapter = ItemsAdapter(thisGroup, this)
        itemsRecyclerView.adapter = itemsAdapter
        setSupportActionBar(myToolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
//        the title is displayed on the nav bar
        supportActionBar!!.setDisplayShowTitleEnabled(false)
        newItemEditText.setOnKeyListener { view, keycode, event ->
            if (keycode == KeyEvent.KEYCODE_ENTER) {
                if (event.action == KeyEvent.ACTION_DOWN) {
//                    when the button is pressed down
                    val name: String = newItemEditText.text.toString()
//                    item is starting
                    val item = Item(name, false)
                    thisGroup.items.add(item)
                    itemsAdapter!!.notifyItemInserted(thisGroup.items.count())
                    newItemEditText.text.clear()
//                    after writing we want that the keyboard to be hide by input manager
                    val inputManager=getSystemService(Activity.INPUT_METHOD_SERVICE)
                            as InputMethodManager
                    inputManager.hideSoftInputFromWindow(view.windowToken,0)
                }
            }
            false
        }
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
//        if we are in the item activity then there is a back button on the nav bar by nav up func
//        for going to previous activity
        return true
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right)
    }
    private fun CharSequence.clear() {
        val text=newItemEditText.text
            text.clear()
    }
}


