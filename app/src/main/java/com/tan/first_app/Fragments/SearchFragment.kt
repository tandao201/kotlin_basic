package com.tan.first_app.Fragments

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.tan.first_app.Adapters.SearchAdapter
import com.tan.first_app.DB.AppDatabase
import com.tan.first_app.Models.Member
import com.tan.first_app.R
import com.tan.first_app.Repositories.MemberRepo
import kotlinx.android.synthetic.main.fragment_search.view.*
import kotlinx.coroutines.launch

class SearchFragment : Fragment() {

    private var data = ArrayList<Member>()
    lateinit var memberRepo: MemberRepo
    var mSearchAdapter = SearchAdapter()
    lateinit var editText: EditText

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        memberRepo = MemberRepo(AppDatabase.getDatabase(context).memberDao())
        var view = inflater.inflate(R.layout.fragment_search, container, false)
        editText = view.editTextSearch
        var recyclerView = view.recyclerViewSearch
        data.clear()
        editText.setText("")
        editText.doOnTextChanged { text, start, before, count ->
            var name = text.toString().trim()
            if (inputCheck(name)){
                Toast.makeText(context,"Vui lòng nhập tên", Toast.LENGTH_SHORT).show()
            } else {
                var name = editText.text.toString()
                getSearchMember(name)
                recyclerView.layoutManager = LinearLayoutManager(context)
                recyclerView.adapter = mSearchAdapter
            }
        }

        return view
    }

    override fun onResume() {
        data.clear()
        mSearchAdapter.setData(data)
        editText.setText("")
        super.onResume()
    }

    private fun inputCheck(name: String): Boolean {
        return TextUtils.isEmpty(name)
    }

    private fun getSearchMember(name: String) {
        lifecycleScope.launch {
            data = memberRepo.getMemberContainName(name) as ArrayList<Member>
            mSearchAdapter.setData(data)
        }
    }
}