package com.example.assign1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.example.assign1.databinding.FragmentTab1Binding
import com.example.assign1.databinding.FragmentTab2Binding
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator
import kotlinx.android.synthetic.main.gallery_list.view.*

class Tab2 : Fragment() {
    private lateinit var binding: FragmentTab2Binding
    private lateinit var adapter: ContactViewAdapter

    private val MIN_SCALE = 0.85f // 뷰가 몇퍼센트로 줄어들 것인지
    private val MIN_ALPHA = 0.5f // 어두워지는 정도를 나타낸 듯 하다.

    var mDatas = mutableListOf<GalleryData>()

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
        val adapter = GalleryViewAdapter() //어댑터 객체 만듦
        binding.photoCardViewPager.adapter = adapter
        binding.photoCardViewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL // 방향을 가로로
        binding.photoCardViewPager.setPageTransformer(ZoomOutPageTransformer())
        binding.dotsIndicator.setViewPager2(binding.photoCardViewPager)
        adapter.datalist = mDatas //데이터 넣어줌
    }

    fun initializelist(){ //임의로 데이터 넣어서 만들어봄
        mDatas = mutableListOf<GalleryData>()
        with(mDatas){
            add(GalleryData(R.drawable.spiderman,"9.6","스파이더맨-노 웨이 홈", "#액션", "#어드벤처"))
            add(GalleryData(R.drawable.happynewyear,"9.0","해피 뉴 이어", "#로맨스", "#멜로"))
            add(GalleryData(R.drawable.kingsman,"8.7","킹스맨-퍼스트 에이전트", "#액션", "#시리즈"))
            add(GalleryData(R.drawable.matrix,"6.8","매트릭스-리저렉션", "#액션", "#SF"))
            add(GalleryData(R.drawable.drivemycar,"9.4","드라이브 마이 카", "#드라마", "#감동"))
            add(GalleryData(R.drawable.lamb,"8.7","램", "#호러", "#스릴러"))
            add(GalleryData(R.drawable.amelie,"9.4","아멜리에", "#코미디", "#환타지"))
        }
    }

    inner class ZoomOutPageTransformer : ViewPager2.PageTransformer {
        override fun transformPage(view: View, position: Float) {
            view.apply {
                val pageWidth = width
                val pageHeight = height
                when {
                    position < -1 -> { // [-Infinity,-1)
                        // This page is way off-screen to the left.
                        alpha = 0f
                    }
                    position <= 1 -> { // [-1,1]
                        // Modify the default slide transition to shrink the page as well
                        val scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position))
                        val vertMargin = pageHeight * (1 - scaleFactor) / 2
                        val horzMargin = pageWidth * (1 - scaleFactor) / 2
                        translationX = if (position < 0) {
                            horzMargin - vertMargin / 2
                        } else {
                            horzMargin + vertMargin / 2
                        }

                        // Scale the page down (between MIN_SCALE and 1)
                        scaleX = scaleFactor
                        scaleY = scaleFactor

                        // Fade the page relative to its size.
                        alpha = (MIN_ALPHA +
                                (((scaleFactor - MIN_SCALE) / (1 - MIN_SCALE)) * (1 - MIN_ALPHA)))
                    }
                    else -> { // (1,+Infinity]
                        // This page is way off-screen to the right.
                        alpha = 0f
                    }
                }
            }
        }
    }
}