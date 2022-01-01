package com.example.assign1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.assign1.Tab3
import com.example.assign1.TicketPreviewFragment
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var tab1: Tab1
    lateinit var tab2: Tab2
    lateinit var tab3: Tab3

    // Tab2에서 보여줄 TicketData 리스트 (read by Tab2, written by Tab3)
    var ticketDataList = mutableListOf<TicketData>()

    // Tab3에서 Tab2로 Ticket Data를 옮길 때 사용하는 전역 변수 (Fragment -> Activity -> Fragment 통신)
    var sharedTicketData: TicketData = TicketData(0,"","","","","",0,0,0,0,"","","","")

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
                        // 작성 중에 나가면 복구되지 않도록 항상 새로 생성
                        replaceView(Tab3())
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

    public fun update(){
        // Tab3에서 input form에 값이 들어오면 ticket preview를 업데이트.
        val fm = supportFragmentManager
        val fragment1 = fm.findFragmentById(R.id.frameLayout)
        val fragment2 = fragment1?.childFragmentManager?.findFragmentById(R.id.ticket_fragment)
        (fragment2 as TicketPreviewFragment).update(sharedTicketData)
    }

    fun addTicket(){
        // Tab3에서 마지막 페이지 도달했을 때(입력 완료 했을때) 호출됨.
        // 전체 ticket data list에 append한다.

        ticketDataList.add(
            TicketData(
                sharedTicketData.layerColorResource,
                sharedTicketData.ticketDate,
                sharedTicketData.ticketTime,
                sharedTicketData.ticketEntryFee,
                sharedTicketData.ticketAddress,
                sharedTicketData.ticketHost,
                sharedTicketData.profileImageResource1,
                sharedTicketData.profileImageResource2,
                sharedTicketData.profileImageResource3,
                sharedTicketData.profileImageResource4,
                sharedTicketData.profileName1,
                sharedTicketData.profileName2,
                sharedTicketData.profileName3,
                sharedTicketData.profileName4
            ) )
    }


}

