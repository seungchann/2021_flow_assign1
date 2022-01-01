package com.example.assign1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.assign1.R
import com.example.assign1.TicketData

class TicketPreviewFragment: Fragment(){
    lateinit var dateTextView: TextView
    lateinit var timeTextView: TextView
    lateinit var entreeFeeTextView: TextView
    lateinit var addressTextView: TextView
    lateinit var menuTextView: TextView
    lateinit var titleTextView: TextView

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

        return view
    }

    fun update(data: TicketData){
        titleTextView.text = data.ticketHost
        dateTextView.text = data.ticketDate
        timeTextView.text = data.ticketTime
        entreeFeeTextView.text = data.ticketEntryFee
        addressTextView.text = data.ticketAddress
    }
}