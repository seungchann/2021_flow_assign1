package com.example.assign1

import android.app.Activity
import android.app.Activity.RESULT_OK
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.example.assign1.ProfileIconSelect.Companion.ICON_REQUEST_CODE
import com.example.assign1.ProfileSearchAddress.Companion.ADDRESS_REQUEST_CODE
import com.example.assign1.databinding.FragmentAddProfileDataBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.fragment_add_profile_data.*
import java.security.Key
import java.util.ArrayList

class AddProfileData : Fragment() {

    private lateinit var binding: FragmentAddProfileDataBinding
    lateinit var name:String
    lateinit var phone:String
    lateinit var add:String
    lateinit var addDetail:String
    var icon = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentAddProfileDataBinding.inflate(layoutInflater)

        binding.addPhoneFinishButton.setOnClickListener{

            name = binding.addNameEditText.text.toString()
            phone = binding.addPhoneEditText.text.toString()
            add = binding.addAddressText.text.toString()
            addDetail = binding.addDetailAddressEditText.text.toString()
            val builder = AlertDialog.Builder(this.context)


            if (name == "" || phone == ""|| add == "" || addDetail=="")
            {
                builder.setTitle("잠시만요!!").setMessage("모든 정보를 채워주세요").setPositiveButton("예",null)
                builder.create()
                builder.show()
            }
            else
            {
                if(phone.length != 11)
                {
                    builder.setTitle("잠시만요!!").setMessage("정확한 번호를 입력해주세요").setPositiveButton("예",null)
                    builder.create()
                    builder.show()
                }
                else {

                    var totalAddress: String
                    if (addDetail == "")
                        totalAddress = add
                    else {
                        while (addDetail[0] == ' ') {
                            addDetail = addDetail.substring(1)
                        } // 공백 없애주기
                        totalAddress = add + ", " + addDetail
                    }

                    addContactData(name, phone, totalAddress, icon)
                    val transaction =
                        requireActivity().supportFragmentManager.beginTransaction() // use requireActivity instead of activity!!
                    transaction.replace(R.id.frameLayout, Tab1())
                    transaction.commit()
                }
            }

        }
        binding.searchButton.setOnClickListener {
            Intent(this.context,ProfileSearchAddress().javaClass).apply {
                startActivityForResult(this,ADDRESS_REQUEST_CODE)
            }
        }


//        binding.addDetailAddressEditText.setOnKeyListener{ v, keyCode, event ->
//            if ((keyCode == KeyEvent.KEYCODE_ENTER) && (event.action == KeyEvent.ACTION_DOWN))
//            {
//                imm.hideSoftInputFromWindow(binding.addNameEditText.windowToken,0)
//                Toast.makeText(App.context(),"enter clicked",Toast.LENGTH_SHORT).show()
//
//            }
//            true
//        }

//        binding.addDetailAddressEditText.setOnEditorActionListener { textView, i, keyEvent ->
//            if (i == EditorInfo.IME_ACTION_DONE){
//                 true
//            }
//             false
//        }

        binding.addImageView.setOnClickListener {
            Intent(this.context,ProfileIconSelect().javaClass).apply {
                startActivityForResult(this, ICON_REQUEST_CODE)
            }
        }


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, intent: Intent?) {
        super.onActivityResult(requestCode, resultCode, intent)
        when(requestCode){
            ADDRESS_REQUEST_CODE ->{
                if (resultCode == RESULT_OK){
                    val addressData = intent?.extras?.getString("data")
                    if(addressData != null)
                        binding.addAddressText.text = addressData
                }
            }

            ICON_REQUEST_CODE ->{
                if (resultCode == RESULT_OK){
                    val iconData = intent?.extras?.getInt("icon")
                    if(iconData != null) {
                        icon = iconData
                        when (iconData) {
                            0 -> binding.addImageView.setImageResource(R.drawable.icon_black)
                            1 -> binding.addImageView.setImageResource(R.drawable.icon_blue)
                            2 -> binding.addImageView.setImageResource(R.drawable.icon_green)
                            3 -> binding.addImageView.setImageResource(R.drawable.icon_pink)
                        }
                    }
                }
            }

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root
    }

    override fun onDestroyView() {
        binding = FragmentAddProfileDataBinding.inflate(layoutInflater)
        super.onDestroyView()
    }

    // profile data 추가 method
    fun addContactData(name: String,phone: String,address: String,icon: Int ){

        val gson = Gson()

        var jsonString = loadFromInnerStorge("profiles.json")
        val arrayProfileDataType = object : TypeToken<ArrayList<ProfileData>>() {}.type
        var profiles: ArrayList<ProfileData> = ArrayList()
        val newData = ProfileData(name,phone,address,icon) // 새로운 data object 생성

        if (jsonString != "")
        {
            profiles = gson.fromJson(jsonString,arrayProfileDataType)
        }

        profiles.add(newData)
        (activity as MainActivity).profileDataList = profiles.toMutableList()

        val newJsonString:String = gson.toJson(profiles) // 새로운 연락처가 추가된 jsonString
        saveToInnerStorage("profiles.json",newJsonString)
    }

}