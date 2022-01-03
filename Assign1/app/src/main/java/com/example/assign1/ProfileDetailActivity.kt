package com.example.assign1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_profile_detail.*
import kotlinx.android.synthetic.main.profile_list.*
import java.util.ArrayList

class ProfileDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_detail)

        detailNameShowTextView.text = intent.getSerializableExtra("name").toString()
        detailPhoneShowTextView.text = insertBarInPhoneNumber(intent.getSerializableExtra("phone").toString())
        detailAddressShowTextView.text = intent.getSerializableExtra("address").toString()
        val iconData = intent.getSerializableExtra("icon")
        when (iconData) {
            0 -> detailImageView.setImageResource(R.drawable.icon_black)
            1 -> detailImageView.setImageResource(R.drawable.icon_blue)
            2 -> detailImageView.setImageResource(R.drawable.icon_green)
            3 -> detailImageView.setImageResource(R.drawable.icon_pink)
        }

    }
}


// profile data 추가 method
