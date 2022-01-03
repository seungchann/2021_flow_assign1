package com.example.assign1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.assign1.R
import com.example.assign1.TicketData

class TicketPreviewFragment: Fragment(){
    lateinit var dateTextView: TextView
    lateinit var timeTextView: TextView
    lateinit var entreeFeeTextView: TextView
    lateinit var addressTextView: TextView
    lateinit var titleTextView: TextView
    lateinit var name1TextView: TextView
    lateinit var name2TextView: TextView
    lateinit var name3TextView: TextView
    lateinit var name4TextView: TextView
    lateinit var imageView1: ImageView
    lateinit var imageView2: ImageView
    lateinit var imageView3: ImageView
    lateinit var imageView4: ImageView


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.ticket_layout, container, false)
        titleTextView = view.findViewById(R.id.partyNameTextView)
        dateTextView = view.findViewById(R.id.dateTextView)
        timeTextView = view.findViewById(R.id.timeTextView)
        entreeFeeTextView = view.findViewById(R.id.costTextView)
        addressTextView = view.findViewById(R.id.addressTextView)
        name1TextView = view.findViewById(R.id.profileTextView1)
        name2TextView = view.findViewById(R.id.profileTextView2)
        name3TextView = view.findViewById(R.id.profileTextView3)
        name4TextView = view.findViewById(R.id.profileTextView4)
        imageView1 = view.findViewById(R.id.profileImageView1)
        imageView2 = view.findViewById(R.id.profileImageView2)
        imageView3 = view.findViewById(R.id.profileImageView3)
        imageView4 = view.findViewById(R.id.profileImageView4)

        return view
    }

    fun update(data: TicketData){
        titleTextView.text = data.ticketHost
        dateTextView.text = data.ticketDate
        timeTextView.text = data.ticketTime
        entreeFeeTextView.text = data.ticketEntryFee
        addressTextView.text = data.ticketAddress
        name1TextView.text = data.profileName1
        name2TextView.text = data.profileName2
        name3TextView.text = data.profileName3
        name4TextView.text = data.profileName4
        imageView1.setImageResource(getResource(data.profileImageResource1))
        imageView2.setImageResource(getResource(data.profileImageResource2))
        imageView3.setImageResource(getResource(data.profileImageResource3))
        imageView4.setImageResource(getResource(data.profileImageResource4))
    }

    fun getResource(i: Int):Int{
        return when (i) {
            0 -> R.drawable.icon_black
            1 -> R.drawable.icon_blue
            2 -> R.drawable.icon_green
            3 -> R.drawable.icon_pink
            else -> R.drawable.ic_launcher_background
        }
    }
}