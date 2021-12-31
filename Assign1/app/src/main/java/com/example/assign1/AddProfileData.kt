package com.example.assign1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.assign1.databinding.FragmentAddProfileDataBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.ArrayList

class AddProfileData : Fragment() {

    private lateinit var binding: FragmentAddProfileDataBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentAddProfileDataBinding.inflate(layoutInflater)

        var name:String
        var phone:String
        binding.addPhoneFinishButton.setOnClickListener{

            name = binding.addNameEditText.text.toString()
            phone = binding.addPhoneEditText.text.toString()
            addContactData(name,phone)

            val transaction = requireActivity().supportFragmentManager.beginTransaction() // use requireActivity instead of activity!!
            transaction.replace(R.id.frameLayout,Tab1())
            transaction.disallowAddToBackStack()
            transaction.commit()

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


}

// profile data 추가 method
fun addContactData(name: String,phone: String ){
    val gson = Gson()

    var jsonString = loadFromInnerStorge("profiles.json")
    val arrayProfileDataType = object : TypeToken<ArrayList<ProfileData>>() {}.type
    var profiles: ArrayList<ProfileData> = ArrayList()
    val newData = ProfileData(name,phone) // 새로운 data object 생성

    if (jsonString != "")
    {
        profiles = gson.fromJson(jsonString,arrayProfileDataType)
    }

    profiles.add(newData)


    val newJsonString:String = gson.toJson(profiles) // 새로운 연락처가 추가된 jsonString
    saveToInnerStorage("profiles.json",newJsonString)

}