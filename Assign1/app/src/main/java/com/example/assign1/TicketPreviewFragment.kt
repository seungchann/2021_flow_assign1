package com.example.assign1

import android.graphics.Color
import android.media.Image
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.assign1.R
import com.example.assign1.TicketData
import kotlinx.android.synthetic.main.ticket_layout.*

class TicketPreviewFragment: Fragment(){
    lateinit var backgroundColorImageView: ImageView
    lateinit var ticketLayerImageView: ImageView
    lateinit var dateTextView: TextView
    lateinit var timeTextView: TextView
    lateinit var entryFeeTextView: TextView
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
    lateinit var titleTextView1: TextView
    lateinit var titleTextView2: TextView
    lateinit var titleTextView3: TextView
    lateinit var titleTextView4: TextView


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.ticket_layout, container, false)
        backgroundColorImageView = view.findViewById(R.id.backgroundView)
        ticketLayerImageView = view.findViewById(R.id.ticketLayerImageView)
        titleTextView = view.findViewById(R.id.partyNameTextView)
        dateTextView = view.findViewById(R.id.dateTextView)
        timeTextView = view.findViewById(R.id.timeTextView)
        entryFeeTextView = view.findViewById(R.id.costTextView)
        addressTextView = view.findViewById(R.id.addressTextView)
        name1TextView = view.findViewById(R.id.profileTextView1)
        name2TextView = view.findViewById(R.id.profileTextView2)
        name3TextView = view.findViewById(R.id.profileTextView3)
        name4TextView = view.findViewById(R.id.profileTextView4)
        imageView1 = view.findViewById(R.id.profileImageView1)
        imageView2 = view.findViewById(R.id.profileImageView2)
        imageView3 = view.findViewById(R.id.profileImageView3)
        imageView4 = view.findViewById(R.id.profileImageView4)
        // TitleTextView1 -> Invited to
        // TitleTextView2 -> Members ...
        titleTextView1 = view.findViewById(R.id.titleTextView1)
        titleTextView2 = view.findViewById(R.id.titleTextView2)
        titleTextView3 = view.findViewById(R.id.titleTextView3)
        titleTextView4 = view.findViewById(R.id.titleTextView4)

        return view
    }

    fun update(data: TicketData){
        backgroundColorImageView.setBackgroundColor(Color.parseColor((activity as MainActivity).getBGHexCode(data.layerColorResource)))
        ticketLayerImageView.setImageResource(data.layerColorResource)
        setupColor(data.layerColorResource)
        titleTextView.text = data.ticketHost
        dateTextView.text = data.ticketDate
        timeTextView.text = data.ticketTime
        entryFeeTextView.text = data.ticketEntryFee
        addressTextView.text = data.ticketAddress.split(",")[0]
        name1TextView.text = data.profileName1
        name2TextView.text = data.profileName2
        name3TextView.text = data.profileName3
        name4TextView.text = data.profileName4
        imageView1.setImageResource(getResource(data.profileImageResource1))
        imageView2.setImageResource(getResource(data.profileImageResource2))
        imageView3.setImageResource(getResource(data.profileImageResource3))
        imageView4.setImageResource(getResource(data.profileImageResource4))
    }

    fun setupColor(color: Int) {
        val brightColorTextViewList = mutableListOf<TextView>(titleTextView1, titleTextView2, titleTextView3, titleTextView4)
        val darkColorTextViewList = mutableListOf<TextView>(dateTextView, timeTextView, name1TextView, name2TextView, name3TextView, name4TextView, titleTextView, addressTextView, entryFeeTextView)
        var hexValues = mutableListOf<String>()

        when (color) {
            R.drawable.ticket_layer_black -> hexValues = mutableListOf<String>("#242424", "#919191", "#212121")
            R.drawable.ticket_layer_pink -> hexValues = mutableListOf<String>("#ff4770", "#ff8ea7", "#db3e63")
            R.drawable.ticket_layer_blue -> hexValues = mutableListOf<String>("#3e3dae", "#5150e8", "#3e3dae")
            R.drawable.ticket_layer_green -> hexValues = mutableListOf<String>("#09685b", "#34a79d", "#026326")
            else -> return
        }
        brightColorTextViewList.forEach { it.setTextColor(Color.parseColor(hexValues[1])) }
        darkColorTextViewList.forEach { it.setTextColor(Color.parseColor(hexValues[2])) }
    }

    private fun getResource(i: Int):Int{
        return when (i) {
            0 -> R.drawable.icon_black
            1 -> R.drawable.icon_blue
            2 -> R.drawable.icon_green
            3 -> R.drawable.icon_pink
            4 -> R.drawable.icon_defult
            else -> R.drawable.ic_launcher_background
        }
    }
}