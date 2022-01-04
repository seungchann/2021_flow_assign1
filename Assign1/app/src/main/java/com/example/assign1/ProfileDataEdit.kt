package com.example.assign1

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.assign1.databinding.FragmentAddProfileDataBinding
import com.example.assign1.databinding.FragmentProfileDataEditBinding
import com.example.assign1.databinding.FragmentProfileDetailBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.ArrayList

class ProfileDataEdit : Fragment() {

    private lateinit var binding: FragmentProfileDataEditBinding

    var index : Int = 0
    lateinit var name : String
    lateinit var phone : String
    lateinit var add : String
    lateinit var addDetail : String
    var icon : Int = 0
    var icon2 : Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = FragmentProfileDataEditBinding.inflate(layoutInflater)

        index = arguments?.getInt("index") as Int
        name = arguments?.getString("name").toString()
        phone = arguments?.getString("phone").toString()
        add = arguments?.getString("address").toString()
        addDetail = arguments?.getString("detailAddress").toString()
        icon = arguments?.getInt("icon") as Int

        binding.editNameEditText.setText(name)
        binding.editPhoneEditText.setText(phone)
        binding.editAddressText.setText(add)
        binding.editDetailAddressEditText.setText(addDetail)
        when (icon) {
            0 -> binding.editImageView.setImageResource(R.drawable.icon_black)
            1 -> binding.editImageView.setImageResource(R.drawable.icon_blue)
            2 -> binding.editImageView.setImageResource(R.drawable.icon_green)
            3 -> binding.editImageView.setImageResource(R.drawable.icon_pink)
        }
        binding.editPhoneFinishButton.setOnClickListener{

            val name2 = binding.editNameEditText.text.toString()
            val phone2 = binding.editPhoneEditText.text.toString()
            val add2 = binding.editAddressText.text.toString()
            var addDetail2 = binding.editDetailAddressEditText.text.toString()
            val builder = AlertDialog.Builder(this.context)


            if (name2 == "" || phone2 == ""|| add2 == "" || addDetail2 == "")
            {
                builder.setTitle("잠시만요!!").setMessage("모든 정보를 채워주세요").setPositiveButton("예",null)
                builder.create()
                builder.show()
            }
            else
            {
                if(phone2.length != 11)
                {
                    builder.setTitle("잠시만요!!").setMessage("정확한 번호를 입력해주세요").setPositiveButton("예",null)
                    builder.create()
                    builder.show()
                }
                else {

                    var totalAddress: String
                    if (addDetail2 == "")
                        totalAddress = add2
                    else {
                        while (addDetail2[0] == ' ') {
                            addDetail2 = addDetail2.substring(1)
                        } // 공백 없애주기
                        totalAddress = add2 + " " + addDetail2
                    }

                    editContactData(name2, phone2, totalAddress, icon2, index)
                    val nextIntent = Intent (App.context(), MainActivity().javaClass)
                    nextIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    nextIntent.run { App.context().startActivity(this) }
                }
            }

        }
        binding.searchButton.setOnClickListener {
            Intent(this.context,ProfileSearchAddress().javaClass).apply {
                startActivityForResult(this, ProfileSearchAddress.ADDRESS_REQUEST_CODE)
            }
        }

        binding.editImageView.setOnClickListener {
            Intent(this.context,ProfileIconSelect().javaClass).apply {
                startActivityForResult(this, ProfileIconSelect.ICON_REQUEST_CODE)
            }
        }


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, intent: Intent?) {
        super.onActivityResult(requestCode, resultCode, intent)
        when(requestCode){
            ProfileSearchAddress.ADDRESS_REQUEST_CODE ->{
                if (resultCode == Activity.RESULT_OK){
                    val addressData = intent?.extras?.getString("data")
                    if(addressData != null)
                        binding.editAddressText.text = addressData
                }
            }

            ProfileIconSelect.ICON_REQUEST_CODE ->{
                if (resultCode == Activity.RESULT_OK){
                    val iconData = intent?.extras?.getInt("icon")
                    if(iconData != null) {
                        icon2 = iconData
                        when (iconData) {
                            0 -> binding.editImageView.setImageResource(R.drawable.icon_black)
                            1 -> binding.editImageView.setImageResource(R.drawable.icon_blue)
                            2 -> binding.editImageView.setImageResource(R.drawable.icon_green)
                            3 -> binding.editImageView.setImageResource(R.drawable.icon_pink)
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
        // Inflate the layout for this fragment
        return binding.root
    }


    override fun onDestroyView() {
        binding = FragmentProfileDataEditBinding.inflate(layoutInflater)
        super.onDestroyView()
    }



    fun editContactData(name: String,phone: String,address: String,icon: Int, index: Int ){
        val gson = Gson()

        var jsonString = loadFromInnerStorge("profiles.json")
        val arrayProfileDataType = object : TypeToken<ArrayList<ProfileData>>() {}.type
        var profiles: ArrayList<ProfileData> = ArrayList()
        val newData = ProfileData(name,phone,address,icon) // 새로운 data object 생성

        if (jsonString != "")
        {
            profiles = gson.fromJson(jsonString,arrayProfileDataType)
        }

        profiles[index] = newData
//        (activity as MainActivity).profileDataList = profiles.toMutableList()

        val newJsonString:String = gson.toJson(profiles) // 새로운 연락처가 추가된 jsonString
        saveToInnerStorage("profiles.json",newJsonString)
    }


}