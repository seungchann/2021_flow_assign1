package com.example.assign1

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.doOnAttach
import androidx.fragment.app.Fragment
import com.example.assign1.Tab3
import com.example.assign1.TicketPreviewFragment
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.google.android.material.tabs.TabLayout
import com.google.zxing.integration.android.IntentIntegrator
import com.google.zxing.integration.android.IntentResult
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_tab2.*
import java.io.File
import java.lang.reflect.Type

class MainActivity : AppCompatActivity() {

    lateinit var tab1: Tab1
    lateinit var tab2: Tab2
    lateinit var tab3: Tab3

    // Tab2에서 보여줄 TicketData 리스트 (read by Tab2, written by Tab3)
    var ticketDataList = mutableListOf<TicketData>()
    var profileDataList = mutableListOf<ProfileData>()

    // Tab3에서 Tab2로 Ticket Data를 옮길 때 사용하는 전역 변수 (Fragment -> Activity -> Fragment 통신)
    var sharedTicketData: TicketData = TicketData(0,"","","","","",0,0,0,0,"","","","")
    lateinit var sharedTicketBarcode: Bitmap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initialLoadFromInnerStorage()
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
        (fragment1 as Tab3).updateBGColor()
    }

    fun addTicket(){
        // Tab3에서 마지막 페이지 도달했을 때(입력 완료 했을때) 호출됨.
        // 전체 ticket data list에 append한다.

        val newTicketData: TicketData = TicketData(
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
        )

        val gson = Gson()

        var jsonString = loadFromInnerStorage("tickets.json")
        val arrayTicketDataType = object : TypeToken<ArrayList<TicketData>>() {}.type
        var tickets: ArrayList<TicketData> = ArrayList()

        if (jsonString != "") {
            tickets = gson.fromJson(jsonString, arrayTicketDataType)
        }
        tickets.add(0, newTicketData)

        val newJsonString: String = gson.toJson(tickets)
        saveToInnerStorage("tickets.json", newJsonString)

        ticketDataList = tickets
    }

    fun loadFromInnerStorage(filename: String): String {
        val file = File(this.filesDir, filename)
        if (!file.exists()) {
            file.createNewFile()
            file.writeText("")
        }
        val fileInputStream = this.openFileInput(filename)
        return fileInputStream.reader().readText()
    }

    fun removeTicketData(filename: String, position: Int) {
        val gson = Gson()

        var jsonString = loadFromInnerStorage(filename)
        val arrayProfileDataType = object : TypeToken<ArrayList<TicketData>>() {}.type
        var tickets: ArrayList<TicketData> = ArrayList()

        if (jsonString != "") {
            tickets = gson.fromJson(jsonString, arrayProfileDataType)
        }

        tickets.removeAt(position)

        val newJsonString: String = gson.toJson(tickets)

        saveToInnerStorage(filename, newJsonString)
    }

    // TRANSITION BY MAINACTIVITY

    // Tab3 fragment 내에서 생성 완료 후 '처음으로' 눌렀을 때 원래 fragment를 죽이기 위해 만든 함수.
    fun refreshTab3(){
        replaceView(Tab3())
    }

    // Tab3 티켓 생성 완료 후 Tab2로 이동하는 함수. 가장 최근에 생성한 티켓으로 currentItem을 옮긴다.
    fun transitionToTab2(){
        tab2 = Tab2()
        tab_layout.getTabAt(1)?.select()
    }
    
    fun saveToInnerStorage(filename: String, newString: String) {
        val file = File(this.filesDir, filename)
        if (file.exists()) {
            file.createNewFile()
            file.writeText(newString)
        }
    }

    fun initialLoadFromInnerStorage() {
        val gson = Gson()
        var jsonString = loadFromInnerStorage("tickets.json")
        if (jsonString != "") {

            val arrayTicketDataType = object : TypeToken<Array<TicketData>>() {}.type
            var ticketDatas: Array<TicketData> = gson.fromJson(jsonString, arrayTicketDataType)
            ticketDatas.forEach { ticketData -> ticketDataList.add(ticketData) }
        }

        var jsonString2 = loadFromInnerStorage("profiles.json")
        if (jsonString2 != "") {
            val arrayProfileDataType = object : TypeToken<Array<ProfileData>>() {}.type
            var profiles: Array<ProfileData> = gson.fromJson(jsonString2, arrayProfileDataType)
            profiles.forEach { profileData -> profileDataList.add(profileData) }
        }
    }

    fun getBGHexCode(i: Int):String{
        return when (i) {
            R.drawable.ticket_layer_black -> "#242424"
            R.drawable.ticket_layer_green -> "#09685b"
            R.drawable.ticket_layer_pink -> "#ff4770"
            R.drawable.ticket_layer_blue -> "#3e3dae"
            else -> "#242424"
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        when(requestCode){
            49374 -> {

                val result: IntentResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
                if(result != null){

                    if(result.contents == null){
                        Toast.makeText(this, "바코드를 인식하지 못했습니다.", Toast.LENGTH_SHORT).show()
                    } else {
                        val ticketData = findTicketById(result.contents.toString())
                        if( ticketData == null) {
                            Toast.makeText(this, "바코드를 인식하지 못했습니다.", Toast.LENGTH_SHORT).show()
                        } else {
                            val fm = supportFragmentManager
                            val fragment = fm.findFragmentById(R.id.frameLayout)

                            sharedTicketData = ticketData
                            update()
                            (fragment as Tab3).mPager.setCurrentItem(5, false)
                        }
                    }
                }
            }
            else -> super.onActivityResult(requestCode, resultCode, data)
        }
    }

    fun findTicketById( name :String ): TicketData? {
        for (ticketData in sampleTicketDatabase) {
            if( ticketData.ticketTime == name ) return ticketData
        }
        return null
    }

    // TODO 더미 데이터 추가.

    val sampleTicketDatabase = arrayListOf<TicketData>(
        TicketData(
            R.drawable.ticket_layer_pink,
            "23 FEB 2022",
            "7:00 PM",
            "₩16000",
            "서울 성동구 왕십리로 222 (사근동)",
            "치킨 먹을 사람 모여라~!",
            3,0,1,4,
            "이수정","김승찬","박수빈",""
        )
    )


}

