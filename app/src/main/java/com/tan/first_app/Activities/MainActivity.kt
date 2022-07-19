package com.tan.first_app.Activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayoutMediator
import com.tan.first_app.Adapters.MainViewPagerAdapter
import com.tan.first_app.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = MainViewPagerAdapter(supportFragmentManager, lifecycle)
        viewPagerMain.adapter = adapter
        TabLayoutMediator(tabLayout, viewPagerMain){ tab, position ->
            when (position) {
                0 -> {
                    tab.text = "Home"
                }
                1 -> {
                    tab.text = "Search"
                }
                2 -> {
                    tab.text = "Login"
                }
            }
        }.attach()

    }
}