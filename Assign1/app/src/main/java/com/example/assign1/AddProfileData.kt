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
import com.example.assign1.ProfileSearchAddress.Companion.ADDRESS_REQUEST_CODE
import com.example.assign1.databinding.FragmentAddProfileDataBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.fragment_add_profile_data.*
import java.security.Key
import java.util.ArrayList

class AddProfileData : Fragment() {

    private lateinit var binding: FragmentAddProfileDataBinding
    val imm = App.context().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentAddProfileDataBinding.inflate(layoutInflater)

        binding.addPhoneFinishButton.setOnClickListener{

            val name = binding.addNameEditText.text.toString()
            val phone = binding.addPhoneEditText.text.toString()
            val add = binding.addAddressText.text.toString()
            val addDetail = binding.addDetailAddressText.text.toString()

            if (name == "" || phone == ""|| add == "" || addDetail=="")
            {
                val builder = AlertDialog.Builder(this.context)
                builder.setTitle("경고").setMessage("모든 정보를 채워주세요").setPositiveButton("예",null)
                builder.create()
                builder.show()
            }
            else
            {
                addContactData(name,phone,add+" "+addDetail)
                val transaction = requireActivity().supportFragmentManager.beginTransaction() // use requireActivity instead of activity!!
                transaction.replace(R.id.frameLayout,Tab1())
                transaction.commit()
            }

        }
        binding.searchButton.setOnClickListener {
            Intent(this.context,ProfileSearchAddress().javaClass).apply {
                startActivityForResult(this,ADDRESS_REQUEST_CODE)
            }
        }



        binding.addNameEditText.setOnKeyListener{ v, keyCode, event ->
            println("@@@@@@@@in setonKeyListener")
            if ((keyCode == KeyEvent.KEYCODE_ENTER) && (event.action == KeyEvent.ACTION_DOWN))
            {
                imm.hideSoftInputFromWindow(binding.addNameEditText.windowToken,0)
                Toast.makeText(App.context(),"enter clicked",Toast.LENGTH_SHORT).show()

            }
            true
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
    fun addContactData(name: String,phone: String,address: String ){

        val gson = Gson()

        var jsonString = loadFromInnerStorge("profiles.json")
        val arrayProfileDataType = object : TypeToken<ArrayList<ProfileData>>() {}.type
        var profiles: ArrayList<ProfileData> = ArrayList()
        val newData = ProfileData(name,phone,address) // 새로운 data object 생성

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