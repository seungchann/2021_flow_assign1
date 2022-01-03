package com.example.assign1

import android.content.Intent
import android.graphics.*
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.assign1.databinding.FragmentTab1Binding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.File
import java.util.ArrayList

class Tab1 : Fragment() {

    private lateinit var binding: FragmentTab1Binding
    private lateinit var adapter: ContactViewAdapter

    var mDatas = mutableListOf<ProfileData>()
    var jsonString: String = loadFromInnerStorge("profiles.json") // jsonfile에서 가져온 string 저장


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    // action bar에 메뉴를 넣어줌
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_tab1, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    // 메뉴 선택시 profile add fragment로 이
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menuAdd -> {
                val transaction = requireActivity().supportFragmentManager.beginTransaction() // use requireActivity instead of activity!!
                transaction.replace(R.id.frameLayout, AddProfileData())
                transaction.addToBackStack("tab1")
//                transaction.disallowAddToBackStack()
                transaction.commit()
//                val nextIntent = Intent (App.context(), ProfileAddDataActivity().javaClass)
//                nextIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
//                nextIntent.run { App.context().startActivity(this) }

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
        adapter = ContactViewAdapter()
        binding = FragmentTab1Binding.inflate(layoutInflater)
        initProfileRecyclerView()

        //swipe delete 기능 구현
        val itemTouchCallback = object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN, ItemTouchHelper.LEFT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
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

                if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
                    val itemView = viewHolder.itemView
                    val height = (itemView.bottom - itemView.top).toFloat()
                    val width = height / 4
                    val paint = Paint()
                    if (dX < 0) {
                        paint.color = Color.parseColor("#ff0000")
                        val background = RectF(
                            itemView.right.toFloat() + dX,
                            itemView.top.toFloat(),
                            itemView.right.toFloat(),
                            itemView.bottom.toFloat()
                        )
                        c.drawRect(background, paint)

//                        icon = BitmapFactory.decodeResource(resources, R.drawable.ic_menu_delete)
//                        val iconDst = RectF(itemView.right.toFloat() - 3 - width, itemView.top.toFloat() + width, itemView.right.toFloat()-width, itemView.bottom.toFloat() - width)
//                        c.drawBitmap(icon,null,iconDst,null)
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

        ItemTouchHelper(itemTouchCallback).attachToRecyclerView(binding.contactRecyclerView)
        return binding.root
    }

    override fun onDestroyView() {
        binding = FragmentTab1Binding.inflate(layoutInflater)
        super.onDestroyView()
    }

    fun initProfileRecyclerView() {
        adapter.datalist = (activity as MainActivity).profileDataList //데이터 넣어줌
        binding.contactRecyclerView.adapter = adapter //리사이클러뷰에 어댑터 연결
        binding.contactRecyclerView.layoutManager = LinearLayoutManager(activity) //레이아웃 매니저 연결
    }
}

// 내부 저장소에서 jsonString 가져오는 method
fun loadFromInnerStorge(filename: String): String {
    val file = File(App.context().filesDir, filename)
    if (!file.exists()) {
        file.createNewFile()
        file.writeText("")
    }
    val fileInputStream = App.context().openFileInput(filename)
    return fileInputStream.reader().readText()
}

// 내부 저장소에 새로운 jsonString 저장하는 method
fun saveToInnerStorage(filename: String, newString: String) {
    val file = File(App.context().filesDir, filename)
    if (file.exists()) {
        file.createNewFile()
        file.writeText(newString)
    }

}

// 해당 position의 profile을 삭제하는 method
fun removeContactData(position: Int) {
    val gson = Gson()

    var jsonString = loadFromInnerStorge("profiles.json")
    val arrayProfileDataType = object : TypeToken<ArrayList<ProfileData>>() {}.type
    var profiles: ArrayList<ProfileData> = ArrayList()

    if (jsonString != "") {
        profiles = gson.fromJson(jsonString, arrayProfileDataType)
    }

    profiles.removeAt(position)

    val newJsonString: String = gson.toJson(profiles) // 새로운 연락처가 추가된 jsonString

    saveToInnerStorage("profiles.json", newJsonString)
}