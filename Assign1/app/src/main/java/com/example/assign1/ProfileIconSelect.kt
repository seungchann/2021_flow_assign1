package com.example.assign1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_profile_icon_select.*

class ProfileIconSelect : AppCompatActivity() {
    companion object{
        const val ICON_REQUEST_CODE = 1000
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_icon_select)


        SelectIcon1.setOnClickListener {
            syncIcon(0)
        }

        SelectIcon2.setOnClickListener {
            syncIcon(1)
        }

        SelectIcon3.setOnClickListener {
            syncIcon(2)
        }

        SelectIcon4.setOnClickListener {
            syncIcon(3)
        }
    }

    fun syncIcon(icon:Int) {
        Intent().apply {
            putExtra("icon",icon)
            setResult(RESULT_OK, this)
        }
        finish()
    }
}