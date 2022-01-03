package com.example.assign1

import android.content.Context
import android.os.Bundle
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
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

        var name:String
        var phone:String
        var add:String
        binding.addPhoneFinishButton.setOnClickListener{

            name = binding.addNameEditText.text.toString()
            phone = binding.addPhoneEditText.text.toString()
            add = binding.addAddressEditText.text.toString()
            addContactData(name,phone,add)

            val transaction = requireActivity().supportFragmentManager.beginTransaction() // use requireActivity instead of activity!!
            transaction.replace(R.id.frameLayout,Tab1())
//            transaction.disallowAddToBackStack()
            transaction.commit()

        }
//        binding.addNameEditText.setOnKeyListener{ v, keyCode, event ->
////            when{((keyCode == KeyEvent.KEYCODE_ENTER) && (event.action == KeyEvent.ACTION_DOWN)) ->
////                    {
//////                        println("######in when stmt")
//////                        imm.hideSoftInputFromWindow(binding.addNameEditText.windowToken,0)
////                        Toast.makeText(App.context(),"enter clicked",Toast.LENGTH_SHORT).show()
////                    }
////
////                else -> false
////            }
//
//            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_DOWN)
//            {
//                imm.hideSoftInputFromWindow(binding.addNameEditText.windowToken,0)
//            }
////

//        binding.addNameEditText.setOnEditorActionListener { _, keyCode, event ->
//            if((event.action : -1) == KeyEvent.ACTION_DOWN)
//            {
//                imm.hideSoftInputFromWindow(binding.addNameEditText.windowToken,0)
//        }
//
//        }



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


    val newJsonString:String = gson.toJson(profiles) // 새로운 연락처가 추가된 jsonString
    saveToInnerStorage("profiles.json",newJsonString)

}