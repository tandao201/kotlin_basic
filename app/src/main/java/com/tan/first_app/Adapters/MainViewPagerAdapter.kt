package com.tan.first_app.Adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.tan.first_app.Fragments.HomeFragment
import com.tan.first_app.Fragments.LoginFragment
import com.tan.first_app.Fragments.SearchFragment

class MainViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) : FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        when(position) {
            0 -> {
                return HomeFragment()
            }
            1 -> {
                return SearchFragment()
            }
            2 -> {
                return LoginFragment()
            }
            else -> return HomeFragment()
        }
    }

}