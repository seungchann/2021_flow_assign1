package com.example.assign1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.example.assign1.databinding.FragmentTab2Binding
import com.example.assign1.databinding.FragmentTicketSettingBinding
import com.example.assign1.databinding.TicketSettingLayoutBinding


class TicketSettingFragment : Fragment() {
    private lateinit var binding: FragmentTicketSettingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentTicketSettingBinding.inflate(layoutInflater)
        initProfileRecyclerView()
        return binding.root
    }

    fun initProfileRecyclerView(){
        val adapter = TicketSettingAdapter() //어댑터 객체 만듦
        binding.ticketSettingRecyclerView.adapter = adapter
        adapter.datalist = (activity as MainActivity).ticketDataList.reversed().toMutableList()
        binding.ticketSettingRecyclerView.layoutManager = LinearLayoutManager(activity) //레이아웃 매니저 연결
    }

}