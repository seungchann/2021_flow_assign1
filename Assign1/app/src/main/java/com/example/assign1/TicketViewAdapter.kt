package com.example.assign1

import android.app.Activity
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.assign1.databinding.TicketLayoutBinding
import com.google.zxing.BarcodeFormat
import com.journeyapps.barcodescanner.BarcodeEncoder

class TicketViewAdapter(mContext: Activity): RecyclerView.Adapter<TicketViewAdapter.MyViewHolder>() {
    var datalist = mutableListOf<TicketData>()
    val mainActivity: MainActivity = mContext as MainActivity

    inner class MyViewHolder(private val binding: TicketLayoutBinding): RecyclerView.ViewHolder(binding.root) {

        fun setupColor(color: Int) {
            val brightColorTextViewList = mutableListOf<TextView>(binding.titleTextView1, binding.titleTextView2, binding.titleTextView3, binding.titleTextView4)
            val darkColorTextViewList = mutableListOf<TextView>(binding.dateTextView, binding.timeTextView, binding.profileTextView1, binding.profileTextView2, binding.profileTextView3, binding.profileTextView4, binding.partyNameTextView, binding.addressTextView, binding.costTextView)
            var hexValues = mutableListOf<String>()

            when (color) {
                R.drawable.ticket_layer_black -> hexValues = mutableListOf<String>("#242424", "#919191", "#212121")
                R.drawable.ticket_layer_pink -> hexValues = mutableListOf<String>("#ff4770", "#ffc2d0", "#db3e63")
                R.drawable.ticket_layer_blue -> hexValues = mutableListOf<String>("#3e3dae", "#5150e8", "#3e3dae")
                R.drawable.ticket_layer_green -> hexValues = mutableListOf<String>("#09685b", "#34a79d", "#026326")
                else -> return
            }

            binding.backgroundView.setBackgroundColor(Color.parseColor(hexValues[0]))
            binding.ticketLayerImageView.setImageResource(color)
            brightColorTextViewList.forEach { it.setTextColor(Color.parseColor(hexValues[1])) }
            darkColorTextViewList.forEach { it.setTextColor(Color.parseColor(hexValues[2])) }
        }

        fun bind(ticketData: TicketData){
            binding.dateTextView.text = ticketData.ticketDate
            binding.timeTextView.text = ticketData.ticketTime
            binding.costTextView.text = ticketData.ticketEntryFee
            binding.partyNameTextView.text = ticketData.ticketHost
            binding.addressTextView.text = ticketData.ticketAddress

            binding.profileTextView1.text = ticketData.profileName1

//            val ticketBarcode = BarcodeEncoder().encodeBitmap(mainActivity.loadFromInnerStorage("tickets.json"), BarcodeFormat.CODE_128, 750, 120)
//            binding.BarcodeImageView.setImageBitmap(ticketBarcode)
//            setupColor(ticketData.layerColorResource)
        }
    }

    //만들어진 뷰홀더 없을때 뷰홀더(레이아웃) 생성하는 함수
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = TicketLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int = datalist.size

    //recyclerview가 viewholder를 가져와 데이터 연결할때 호출
    //적절한 데이터를 가져와서 그 데이터를 사용하여 뷰홀더의 레이아웃 채움
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(datalist[position])
    }
}