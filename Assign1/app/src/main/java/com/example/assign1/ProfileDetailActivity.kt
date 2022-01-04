package com.example.assign1

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_profile_detail.*
import kotlinx.android.synthetic.main.profile_list.*
import java.util.ArrayList

class ProfileDetailActivity : AppCompatActivity() {

//    var index : Int = 0
//    lateinit var name : String
//    lateinit var phone : String
//    lateinit var address : String
//    var iconData : Int = 0
//
//    lateinit var detailAddress: String
//
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_profile_detail)
//
//        index = (intent.getSerializableExtra("index")) as Int
//        name = intent.getSerializableExtra("name").toString()
//        phone = insertBarInPhoneNumber(intent.getSerializableExtra("phone").toString())
//        address = intent.getSerializableExtra("address").toString()
//        iconData = (intent.getSerializableExtra("icon")) as Int
//
//        val token = address.split(")")
//        address = token[0] + ")"
//        detailAddress = token[1]
//        detailNameShowTextView.setText(name)
//        detailPhoneShowTextView.setText(phone)
//        detailAddressShowTextView.setText(address)
//        detailDetailAddressShowTextView.setText(detailAddress)
//        when (iconData) {
//            0 -> detailImageView.setImageResource(R.drawable.icon_black)
//            1 -> detailImageView.setImageResource(R.drawable.icon_blue)
//            2 -> detailImageView.setImageResource(R.drawable.icon_green)
//            3 -> detailImageView.setImageResource(R.drawable.icon_pink)
//        }
//
//    }
//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        menuInflater.inflate(R.menu.menu_detail, menu)
//        return true
//    }
//
//    // 메뉴 선택시 profile add fragment로 이
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        when (item.itemId) {
//            R.id.menuEdit -> {
//                var nextFragment = ProfileDataEdit()
//                var bundle = Bundle()
//                bundle.putInt("index",index as Int)
//                bundle.putString("name",name)
//                bundle.putString("phone",phone)
//                bundle.putString("address",address)
//                bundle.putString("detailAddress",detailAddress)
//                bundle.putInt("icon",iconData as Int)
//
//                nextFragment.arguments = bundle
//                supportFragmentManager.beginTransaction().replace(R.id.frameLayout, nextFragment).commit()
//                return super.onOptionsItemSelected(item)
//            }
//            else -> return super.onOptionsItemSelected(item)
//        }
//    }

}


// profile data 추가 method
