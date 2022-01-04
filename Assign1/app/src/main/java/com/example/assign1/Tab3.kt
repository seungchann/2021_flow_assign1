package com.example.assign1

import android.animation.ObjectAnimator
import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateInterpolator
import androidx.activity.OnBackPressedCallback
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.ViewPager
import kotlin.math.floor

class Tab3 : Fragment() {
    private lateinit var callback: OnBackPressedCallback
    private lateinit var mPager: FormViewPager
    private lateinit var background: ConstraintLayout
    private lateinit var baseline: View

    // Fragment간 공유를 위한 Shared Variable
    lateinit var mainActivity: MainActivity

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }


    val posY = arrayListOf(360, 360, -130, -320, -320, -320, -320)

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
        background = view.findViewById(R.id.whole_thing)
        baseline = view.findViewById(R.id.hole_front)

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
                    mainActivity.sharedTicketData = TicketData(R.drawable.ticket_layer_black,"","","","","",
                        4,4,4,4,"","","","")
                    mPager.setPagingEnabled(false)

                    ObjectAnimator.ofFloat(ticket, "translationY", -3000f).apply {
                        interpolator = AccelerateInterpolator(2F)
                        duration = 1500
                        start()
                    }
                }
            }
        }  )

        mainActivity.sharedTicketData = TicketData(R.drawable.ticket_layer_black,"","","","","",
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

    fun updateBGColor() {
        background.setBackgroundColor(Color.parseColor((activity as MainActivity).getBGHexCode((activity as MainActivity).sharedTicketData.layerColorResource)))
        baseline.setBackgroundColor(Color.parseColor((activity as MainActivity).getBGHexCode((activity as MainActivity).sharedTicketData.layerColorResource)))
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