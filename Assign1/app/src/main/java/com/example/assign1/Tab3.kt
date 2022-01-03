package com.example.assign1

import android.app.Activity
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.ViewPager
import kotlin.math.floor

class Tab3 : Fragment() {
    private lateinit var callback: OnBackPressedCallback
    private lateinit var mPager: FormViewPager

    // Fragment간 공유를 위한 Shared Variable
    lateinit var mainActivity: MainActivity

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }


    val posY = arrayListOf(360, 360, -130, -320, -320, -1700, -1700)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ):View {
        val view = inflater.inflate(R.layout.fragment_tab3, container, false)

        val ticket = view.findViewById<View>(R.id.mock_ticket2)
        val whiteBack = view.findViewById<View>(R.id.form_background)
        // Layout Constraint로 미리 정해진 값을 저장해둡니다.
        mPager = view.findViewById(R.id.pager)

        val pagerAdapter = ScreenSlidePagerAdapter( mainActivity.supportFragmentManager )
        mPager.adapter = pagerAdapter

        val ticketOffsetY = 300F

        mPager.addOnPageChangeListener( object: ViewPager.OnPageChangeListener{

            override fun onPageScrollStateChanged(state: Int) {

            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                val here = position + positionOffset
                val n = floor(here).toInt()
                val alpha = here - floor(here)
                val pos = (1-alpha)*posY[n] + (alpha)*posY[n+1]
                //ticket.y = ticketOffsetY - 150*(position + positionOffset)
                ticket.y = pos + 85
            }

            override fun onPageSelected(position: Int) {
                if(position==5){
                    mainActivity.addTicket()
                    mainActivity.sharedTicketData = TicketData(0,"","","","","",
                        4,4,4,4,"","","","")
                    mPager.setPagingEnabled(false)
                }
            }
        }  )

        mainActivity.sharedTicketData = TicketData(0,"","","","","",
            4,4,4,4,"","","","")
        mainActivity.update()

        return view

    }

//    override fun onBackPressed() {
//        if(mPager.currentItem == 0 || mPager.currentItem == 5) {
//            super.onBackPressed()
//        } else {
//            mPager.currentItem -= 1
//        }
//    }

    override fun onAttach(activity: Activity) {
        super.onAttach(activity)
        callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if(mPager.currentItem == 0) {
                    activity.finish()
                } else if(mPager.currentItem == 5) {
                    (activity as MainActivity).refreshTab3()
                } else {
                    mPager.currentItem -= 1
                }
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }

    private inner class ScreenSlidePagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {
        override fun getCount(): Int = 6
        override fun getItem(position: Int): Fragment {
            return when(position){
                0 -> TitleFormFragment()
                1 -> DateTimeFormFragment()
                2 -> MemberFormFragment()
                3 -> FeeFormFragment()
                4 -> ConfirmFormFragment()
                5 -> PostFormFragment()
                else -> SlideFormFragment()
            }

        }
    }

}