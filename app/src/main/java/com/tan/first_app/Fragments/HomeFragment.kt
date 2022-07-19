package com.tan.first_app.Fragments

import android.app.Dialog
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.tan.first_app.Adapters.MemberAdapter
import com.tan.first_app.DB.AppDatabase
import com.tan.first_app.Interfaces.IClickBtnMem
import com.tan.first_app.Models.Member
import com.tan.first_app.R
import com.tan.first_app.Repositories.MemberRepo
import kotlinx.android.synthetic.main.fragment_home.view.*
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {

    var data: ArrayList<Member> = ArrayList()

    lateinit var memberRepo:MemberRepo
    val iClick = object : IClickBtnMem {
        override fun upDateMem(member: Member) {
            clickUpdateMem(member)
        }

        override fun deleteMem(member: Member) {
            clickDeleteMem(member)
        }

    }
    var memberAdapter = MemberAdapter(iClick)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        memberRepo = MemberRepo(AppDatabase.getDatabase(context).memberDao())
        lifecycleScope.launch {
            data = memberRepo.getAllMember() as ArrayList<Member>
            memberAdapter.setData(data)
        }

        var view = inflater.inflate(R.layout.fragment_home, container, false)

        view.recyclerViewMember.adapter = memberAdapter
        view.recyclerViewMember.layoutManager = LinearLayoutManager(context)
        view.btnFloating.setOnClickListener(View.OnClickListener {
            clickAddNewMem()
        })
        return view
    }

    private fun clickAddNewMem() {
        var dialog = Dialog(requireContext())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(true)
        dialog.setContentView(R.layout.custom_dialog_add)
        dialog.show()
        var nameEdit = dialog.findViewById<EditText>(R.id.editNameDialog)
        var ageEdit = dialog.findViewById<EditText>(R.id.editAgeDialog)
        var addBtn = dialog.findViewById<Button>(R.id.btnAddDialog)

        addBtn.setOnClickListener(View.OnClickListener {
            var name = nameEdit.text.toString()
            var age = ageEdit.text.toString()

            if (inputCheck(name, age)) {
                Toast.makeText(requireContext(), "Vui lòng nhập thông tin!", Toast.LENGTH_SHORT).show()
            } else {
                var lastMem = data.last()
                var memberNew = Member(lastMem.id+1, name, age.toInt())
                addNewMember(memberNew)
                Toast.makeText(requireContext(),"Thêm mới thành công!", Toast.LENGTH_SHORT).show()
                data.add(memberNew)
                memberAdapter.setData(data)
                dialog.dismiss()
            }
        })
    }

    private fun clickUpdateMem(member: Member) {
        var dialog = Dialog(requireContext())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(true)
        dialog.setContentView(R.layout.custom_dialog_update)
        dialog.show()
        var nameEdit = dialog.findViewById<EditText>(R.id.editNameDialogUpdate)
        var ageEdit = dialog.findViewById<EditText>(R.id.editAgeDialogUpdate)
        var addBtn = dialog.findViewById<Button>(R.id.btnAddDialogUpdate)

        nameEdit?.setText(member.name)
        ageEdit?.setText(member.age.toString())

        addBtn.setOnClickListener(View.OnClickListener {
            var name = nameEdit.text.toString()
            var age = ageEdit.text.toString()

            if (inputCheck(name, age)) {
                Toast.makeText(requireContext(), "Vui lòng nhập thông tin!", Toast.LENGTH_SHORT).show()
            } else {
                var upDateMem=Member(member.id,name,age.toInt())
                for (i in data.indices){
                    if (data[i].id == upDateMem.id){
                        data[i] = upDateMem
                        break
                    }
                }
                Toast.makeText(requireContext(),"Cập nhật thành công!", Toast.LENGTH_SHORT).show()
                memberAdapter.setData(data)
                updateMember(upDateMem)
                dialog.dismiss()
            }
        })
    }

    private fun clickDeleteMem(member: Member) {
        var dialog = Dialog(requireContext())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(true)
        dialog.setContentView(R.layout.custom_dialog_delete)
        var btnYes = dialog.findViewById<Button>(R.id.btnYesDialog)
        var btnNo = dialog.findViewById<Button>(R.id.btnNoDialog)

        btnYes.setOnClickListener(View.OnClickListener {
            for (i in data.indices){
                if (data[i].id == member.id){
                    data.removeAt(i)
                    break
                }
            }
            dialog.dismiss()
            Toast.makeText(requireContext(), "Xóa thành công!", Toast.LENGTH_SHORT).show()
            deleteMember(member)
            memberAdapter.setData(data)
        })

        btnNo.setOnClickListener(View.OnClickListener {
            dialog.dismiss()
        })
        dialog.show()
    }

    private fun inputCheck(name: String, age: String): Boolean {
        return TextUtils.isEmpty(name) || TextUtils.isEmpty(age)
    }

    private fun addNewMember(memberNew: Member) {
        lifecycleScope.launch {
            memberRepo.addNewMem(memberNew)
        }

    }

    private fun updateMember(member: Member){
        lifecycleScope.launch{
            memberRepo.updateMember(member)
        }
    }

    private fun deleteMember(member: Member){
        lifecycleScope.launch{
            memberRepo.delete(member)
        }
    }
}