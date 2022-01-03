package com.example.assign1

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.assign1.MainActivity
import com.example.assign1.R
import de.hdodenhof.circleimageview.CircleImageView

class MemberFormFragment: Fragment(), View.OnClickListener {

    lateinit var formNameView: Array<TextView>
    lateinit var formCircleImageView: Array<CircleImageView>
    lateinit var previewName: Array<String>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.form_member, container, false)

        view.findViewById<CircleImageView>(R.id.member_1).setOnClickListener( this )
        view.findViewById<CircleImageView>(R.id.member_2).setOnClickListener( this )
        view.findViewById<CircleImageView>(R.id.member_3).setOnClickListener( this )
        view.findViewById<CircleImageView>(R.id.member_4).setOnClickListener( this )

        formNameView = arrayOf(
            view.findViewById(R.id.member_1_name),
            view.findViewById(R.id.member_2_name),
            view.findViewById(R.id.member_3_name),
            view.findViewById(R.id.member_4_name)
        )

        formCircleImageView = arrayOf(
            view.findViewById<CircleImageView>(R.id.member_1),
            view.findViewById<CircleImageView>(R.id.member_2),
            view.findViewById<CircleImageView>(R.id.member_3) ,
            view.findViewById<CircleImageView>(R.id.member_4)
        )

        previewName = arrayOf(
            (activity as MainActivity).sharedTicketData.profileName1,
            (activity as MainActivity).sharedTicketData.profileName2,
            (activity as MainActivity).sharedTicketData.profileName3,
            (activity as MainActivity).sharedTicketData.profileName4
        )



        return view
    }

    override fun onClick(view: View){

        val idx = when(view.id) {
            R.id.member_1 -> 0
            R.id.member_2 -> 1
            R.id.member_3 -> 2
            R.id.member_4 -> 3
            else -> 0
        }

        val dialog = Dialog(activity as MainActivity)
        dialog.setContentView(R.layout.form_member_modal)

        val adapter = ContactViewModalAdapter()
        adapter.datalist = (activity as MainActivity).profileDataList //데이터 넣어줌
        val recyclerView = dialog.findViewById<RecyclerView>(R.id.contactModalRecyclerView)
        adapter.setOnItemClickListener(
            object : ContactViewModalAdapter.OnItemClickListener {
                override fun onItemClick(v: View?, pos: Int) {

                    val text = adapter.datalist[pos].profileName
                    val imgSrc = adapter.datalist[pos].profileIcon
                    val address = adapter.datalist[pos].profileAddress
                        //v?.findViewById<TextView>(R.id.profileNameTvModal)?.text.toString()
                    //val src = v?.findViewById<ImageView>(R.id.profilePhotoImgModal)?.get

                    formNameView[idx].text = text

                    formCircleImageView[idx].setImageResource(
                        when (imgSrc) {
                            0 -> R.drawable.icon_black
                            1 -> R.drawable.icon_blue
                            2 -> R.drawable.icon_green
                            3 -> R.drawable.icon_pink
                            4 -> R.drawable.icon_defult_add
                            else -> R.drawable.ic_launcher_background
                        }
                    )

                    when(idx){
                        0 -> (activity as MainActivity).sharedTicketData.profileName1 = text
                        1 -> (activity as MainActivity).sharedTicketData.profileName2 = text
                        2 -> (activity as MainActivity).sharedTicketData.profileName3 = text
                        3 -> (activity as MainActivity).sharedTicketData.profileName4 = text
                        else -> Unit
                    }

                    when(idx){
                        0 -> (activity as MainActivity).sharedTicketData.profileImageResource1 = imgSrc
                        1 -> (activity as MainActivity).sharedTicketData.profileImageResource2 = imgSrc
                        2 -> (activity as MainActivity).sharedTicketData.profileImageResource3 = imgSrc
                        3 -> (activity as MainActivity).sharedTicketData.profileImageResource4 = imgSrc
                        else -> Unit
                    }

                    if(idx == 0) {
                        (activity as MainActivity).sharedTicketData.ticketAddress = address
                    }

                    (activity as MainActivity).update()
                    dialog.dismiss()
                }
            }
        )

        recyclerView.adapter = adapter //리사이클러뷰에 어댑터 연결
        recyclerView.layoutManager = LinearLayoutManager(activity) //레이아웃 매니저 연


        dialog.show()
    }
}