package com.example.assign1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var tab1: Tab1
    lateinit var tab2: Tab2
    lateinit var tab3: Tab3

    var ticketDataList = mutableListOf<TicketData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tab1 = Tab1()
        tab2 = Tab2()
        tab3 = Tab3()

        supportFragmentManager.beginTransaction().add(R.id.frameLayout, tab1).commit()

        tab_layout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when(tab?.position) {
                    0 -> {
                        replaceView(tab1)
                    }
                    1 -> {
                        replaceView(tab2)
                    }
                    2 -> {
                        replaceView(tab3)
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })
    }

    private fun replaceView(tab: Fragment) {
        var selectedFragment: Fragment? = null
        selectedFragment = tab
        selectedFragment?.let {
            supportFragmentManager.beginTransaction()
                .replace(R.id.frameLayout, it).commit()
        }
    }
}

