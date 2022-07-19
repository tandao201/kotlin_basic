package com.tan.first_app.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tan.first_app.Models.Member
import com.tan.first_app.R
import kotlinx.android.synthetic.main.item_member.view.*

class SearchAdapter: RecyclerView.Adapter<SearchAdapter.ViewHolder>() {

    private var data: ArrayList<Member>? = ArrayList()

    fun setData(mMembers: ArrayList<Member>){
        this.data = mMembers
        notifyDataSetChanged()
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        var id: TextView = view.findViewById(R.id.textViewId)
        var name: TextView = view.findViewById(R.id.textViewName)
        var age: TextView = view.findViewById(R.id.textViewAge)
        var btnEdit = view.btnEditMem
        var btnDelete = view.btnDeleteMem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_member,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var member = data!!.get(position)
        holder.name.text = member.name
        holder.age.text = member.age.toString()
        holder.id.text = member.id.toString()
        holder.btnEdit.visibility = View.GONE
        holder.btnDelete.visibility = View.GONE
    }

    override fun getItemCount(): Int {
        return data!!.size
    }
}