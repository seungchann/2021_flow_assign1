package com.example.assign1

import android.app.Activity
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.example.assign1.databinding.FragmentTab2Binding

class Tab2 : Fragment() {
    private lateinit var binding: FragmentTab2Binding
    lateinit var mainActivity: MainActivity

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentTab2Binding.inflate(layoutInflater)
        initializelist()
        initProfileRecyclerView()
        return binding.root
    }

    fun initProfileRecyclerView(){
        val adapter = TicketViewAdapter() //어댑터 객체 만듦
        binding.ticketViewPager.adapter = adapter
        binding.ticketViewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL // 방향을 가로로
        binding.ticketViewPager.setPageTransformer(ZoomOutPageTransformer())
        binding.dotsIndicator.setViewPager2(binding.ticketViewPager)
        adapter.datalist = mainActivity.ticketDataList //데이터 넣어줌
    }

    fun initializelist(){ //임의로 데이터 넣어서 만들어봄
        with(mainActivity.ticketDataList){
            add(TicketData(R.drawable.ticket_layer_blue))
            add(TicketData(R.drawable.ticket_layer_green))
            add(TicketData(R.drawable.ticket_layer_pink))
            add(TicketData(R.drawable.ticket_layer_black))
        }
    }
}