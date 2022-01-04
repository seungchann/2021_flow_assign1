package com.example.assign1

import android.graphics.*
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.assign1.databinding.FragmentTab2Binding
import com.example.assign1.databinding.FragmentTicketSettingBinding
import com.example.assign1.databinding.TicketSettingLayoutBinding


class TicketSettingFragment : Fragment() {
    private lateinit var binding: FragmentTicketSettingBinding
    private lateinit var adapter: TicketSettingAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    // action bar에 메뉴를 넣어줌
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_tab3, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    // 메뉴 선택시 profile add fragment로 이
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menuReturn -> {
                val transaction = requireActivity().supportFragmentManager.beginTransaction() // use requireActivity instead of activity!!
                transaction.setCustomAnimations(R.animator.back_animator_left_to_right, R.animator.front_animator_left_to_right)
                transaction.replace(R.id.frameLayout, Tab2())
                transaction.addToBackStack("ticket_setting")
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
        adapter = TicketSettingAdapter((activity as MainActivity))
        binding = FragmentTicketSettingBinding.inflate(layoutInflater)
        initProfileRecyclerView()

        // Swipe, Drag 기능 구현
        val itemTouchCallback = object : ItemTouchHelper.SimpleCallback (
            ItemTouchHelper.UP or ItemTouchHelper.DOWN, ItemTouchHelper.LEFT
                ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                val fromPos: Int = viewHolder.adapterPosition
                val toPos: Int = target.adapterPosition
                adapter.swapData(fromPos, toPos)
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                adapter.removeData(viewHolder.layoutPosition)
            }

            override fun onChildDraw(
                c: Canvas,
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                dX: Float,
                dY: Float,
                actionState: Int,
                isCurrentlyActive: Boolean
            ) {
                val icon: Bitmap
                // actionState가 SWIPE 동작일 때 배경을 빨간색으로 칠하는 작업을 수행하도록 함
                if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
                    val itemView = viewHolder.itemView
                    val height = (itemView.bottom - itemView.top).toFloat()
                    val width = height / 4
                    val paint = Paint()
                    if (dX < 0) {  // 왼쪽으로 스와이프하는지 확인
                        // ViewHolder의 백그라운드에 깔아줄 사각형의 크기와 색상을 지정
                        paint.color = Color.parseColor("#ff0000")
                        val background = RectF(itemView.right.toFloat() + dX, itemView.top.toFloat(), itemView.right.toFloat(), itemView.bottom.toFloat())
                        c.drawRect(background, paint)

                        // 휴지통 아이콘과 표시될 위치를 지정하고 비트맵을 그려줌
                        // 비트맵 이미지는 Image Asset 기능으로 추가하고 drawable 폴더에 위치하도록 함
//                    icon = BitmapFactory.decodeResource(resources, R.drawable.ic_menu_delete)
//                    val iconDst = RectF(itemView.right.toFloat() - 3  - width, itemView.top.toFloat() + width, itemView.right.toFloat() - width, itemView.bottom.toFloat() - width)
//                    c.drawBitmap(icon, null, iconDst, null)
                    }
                }

                super.onChildDraw(
                    c,
                    recyclerView,
                    viewHolder,
                    dX,
                    dY,
                    actionState,
                    isCurrentlyActive
                )
            }
        }

        ItemTouchHelper(itemTouchCallback).attachToRecyclerView(binding.ticketSettingRecyclerView)


        return binding.root
    }

    fun initProfileRecyclerView(){
        binding.ticketSettingRecyclerView.adapter = adapter
        adapter.datalist = (activity as MainActivity).ticketDataList
        binding.ticketSettingRecyclerView.layoutManager = LinearLayoutManager(activity) //레이아웃 매니저 연결
    }

}