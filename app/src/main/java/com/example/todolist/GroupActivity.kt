package com.example.todolist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.Groups
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewParent
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatViewInflater
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class GroupActivity : AppCompatActivity(),OnGroupClickListener {
    private var groupsAdapter:GroupsAdapter?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_group)
        val groupsRecyclerView=findViewById<RecyclerView>(R.id.groupsrecyclerview)
        groupsRecyclerView.layoutManager=LinearLayoutManager(this)
        AppData.initialize()

        val groupsAdapter=GroupsAdapter(AppData.groups,this)
        groupsRecyclerView.adapter=groupsAdapter
    }
//    OnResume fun every time see the  view and when
    fun OnResume(){
        super.onResume()
        groupsAdapter!!.notifyDataSetChanged()
    }
    fun createNewGroup(v:View){
//        when new group is to be added there is alert dialogue occurs
        val builder=AlertDialog.Builder(this)
        builder.setTitle("New Group")
        builder.setMessage("Please Enter a name for your New Group")
//        here we take some input after dialog box appear
//        we add edit text in our dialog
        val myInput=EditText(this)
        myInput.inputType=InputType.TYPE_CLASS_TEXT
        builder.setView(myInput)
//        set two button
        builder.setPositiveButton("Save"){
//            two arguments is there 1st-->dialog 2nd--> on which object we trying to work on
            dialogue,which->
//            if we click on save button this will create a new group in our groups
//            these are set by positive button
            val groupName:String=myInput.text.toString()
            val NewGroup=Group(groupName, mutableListOf())
//            when we create the new groups we add the groups to the main group that is in App data
            AppData.groups.add(NewGroup)
//            now we have to updates our group adapter
            groupsAdapter!!.notifyItemInserted(AppData.groups.count())
        }
//        set negative button
        builder.setNegativeButton("Cancel"){
            _,_->
        }
        val dialogue:AlertDialog=builder.create()
        dialogue.show()
    }

    override fun groupClicked(index: Int) {
//        go to next activity
        val intent=Intent(this,Items::class.java)
        intent.putExtra("groupIndex",index)
//        intent got the index of the group which is clicked
        startActivity(intent)
        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left)
    }

    override fun groupLongClicked(index: Int) {
//        delete a group
        AppData.groups.removeAt(index)
        groupsAdapter!!.notifyItemRemoved(index)

    }
}
//class GroupsViewHolder(inflater: LayoutInflater,
//                       parent:ViewGroup):
//    RecyclerView.ViewHolder(inflater.inflate(R.layout.group_row,parent,false))
//{
//        private var groupNameTextView:TextView?=null
//        private var groupCountTextView:TextView?=null
//    init {
//        groupNameTextView=itemView.findViewById(R.id.groupNametextView)
//        groupCountTextView=itemView.findViewById(R.id.groupcountTextView)
//    }
//    fun bind(group:Group)
//    {
//        groupNameTextView!!.text=group.name
//        groupCountTextView!!.text="${group.items.count()}items"
//
//    }
//    }
//class GroupsAdapter(private val list:List<Group>):RecyclerView.Adapter<GroupsViewHolder>()
//{
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroupsViewHolder {
//        val inflater=LayoutInflater.from(parent.context)
//        return GroupsViewHolder(inflater,parent)
//
//    }
//
//    override fun onBindViewHolder(holder: GroupsViewHolder, position: Int) {
//        val group=list[position]
//        holder.bind(group)
//    }
//
//    override fun getItemCount(): Int {
//        return list.size
//    }
//}