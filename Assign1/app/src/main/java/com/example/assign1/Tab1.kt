package com.example.assign1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.assign1.databinding.FragmentTab1Binding

class Tab1 : Fragment() {

    private lateinit var binding: FragmentTab1Binding
    private lateinit var adapter: RecyclerViewAdapter

    val mDatas = mutableListOf<ProfileData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentTab1Binding.inflate(layoutInflater)
        initializelist()
        initProfileRecyclerView()
        return binding.root
    }

    fun initProfileRecyclerView(){
        val adapter = RecyclerViewAdapter() //어댑터 객체 만듦
        adapter.datalist = mDatas //데이터 넣어줌
        binding.recyclerView.adapter = adapter //리사이클러뷰에 어댑터 연결
        binding.recyclerView.layoutManager = LinearLayoutManager(activity) //레이아웃 매니저 연결
    }

    fun initializelist(){ //임의로 데이터 넣어서 만들어봄
        with(mDatas){
            add(ProfileData("","Test1","010-1111-1111"))
            add(ProfileData("","Test2","010-2222-2222"))
            add(ProfileData("","Test3","010-3333-3333"))
            add(ProfileData("","Test4","010-4444-4444"))
            add(ProfileData("","Test5","010-5555-5555"))
            add(ProfileData("","Test6","010-6666-6666"))
            add(ProfileData("","Test7","010-7777-7777"))
            add(ProfileData("","Test8","010-8888-8888"))
            add(ProfileData("","Test9","010-9999-9999"))
            add(ProfileData("","Test10","010-1010-1010"))
        }
    }
}