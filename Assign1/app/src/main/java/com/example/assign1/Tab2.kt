package com.example.assign1

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.assign1.databinding.FragmentTab2Binding
import com.google.zxing.integration.android.IntentIntegrator
import kotlinx.android.synthetic.main.fragment_tab2.*

class Tab2 : Fragment() {
    private lateinit var binding: FragmentTab2Binding
    lateinit var mainActivity: MainActivity

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    // action bar에 메뉴를 넣어줌
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_tab2, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    // 메뉴 선택시 profile add fragment로 이
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menuSetting -> {
                val transaction = requireActivity().supportFragmentManager.beginTransaction() // use requireActivity instead of activity!!
                transaction.setCustomAnimations(R.animator.back_animator_right_to_left, R.animator.front_animator_right_to_left)
                transaction.replace(R.id.frameLayout, TicketSettingFragment())
                transaction.addToBackStack("tab2")
                transaction.commit()

                return super.onOptionsItemSelected(item)
            }
            else -> return super.onOptionsItemSelected(item)
        }
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

//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        binding.ticketViewPager.currentItem = mainActivity.ticketDataList.size - 1
//    }

    fun initProfileRecyclerView(){
        val adapter = TicketViewAdapter(mainActivity) //어댑터 객체 만듦
        binding.ticketViewPager.adapter = adapter
        binding.ticketViewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL // 방향을 가로로
        binding.ticketViewPager.setPageTransformer(ZoomOutPageTransformer())
        binding.dotsIndicator.setViewPager2(binding.ticketViewPager)


        adapter.datalist = mainActivity.ticketDataList//데이터 넣어줌
    }

    fun initializelist() { //임의로 데이터 넣어서 만들어봄
    }
}