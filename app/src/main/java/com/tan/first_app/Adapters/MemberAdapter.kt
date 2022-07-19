package com.tan.first_app.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tan.first_app.Interfaces.IClickBtnMem
import com.tan.first_app.Models.Member
import com.tan.first_app.R

class MemberAdapter(var iClickBtnMem: IClickBtnMem):
    RecyclerView.Adapter<MemberAdapter.ViewHolder>() {
    private var data: ArrayList<Member>? = ArrayList()

    fun setData(mMembers: ArrayList<Member>){
        this.data = mMembers
        notifyDataSetChanged()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var id: TextView = view.findViewById(R.id.textViewId)
        var name: TextView = view.findViewById(R.id.textViewName)
        var age: TextView = view.findViewById(R.id.textViewAge)
        var btnEdit:ImageButton = view.findViewById(R.id.btnEditMem)
        var btnDelete:ImageButton = view.findViewById(R.id.btnDeleteMem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_member,parent,false);
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var member: Member = data!!.get(position)
        holder.id.text = member.id.toString()
        holder.name.text = member.name
        holder.age.text = member.age.toString()
        holder.btnEdit.setOnClickListener(View.OnClickListener {
            iClickBtnMem.upDateMem(member)
        })

        holder.btnDelete.setOnClickListener(View.OnClickListener {
            iClickBtnMem.deleteMem(member)
        })
    }

    override fun getItemCount(): Int {
        return data!!.size
    }
}