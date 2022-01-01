package com.example.assign1

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.assign1.databinding.TicketLayoutBinding
import com.google.zxing.BarcodeFormat
import com.journeyapps.barcodescanner.BarcodeEncoder

class TicketViewAdapter: RecyclerView.Adapter<TicketViewAdapter.MyViewHolder>() {
    var datalist = mutableListOf<TicketData>()

    inner class MyViewHolder(private val binding: TicketLayoutBinding): RecyclerView.ViewHolder(binding.root) {

        fun setupColor(color: String) {
            val brightColorTextViewList = mutableListOf<TextView>(binding.titleTextView1, binding.titleTextView2, binding.titleTextView3, binding.titleTextView4)
            val darkColorTextViewList = mutableListOf<TextView>(binding.dateTextView, binding.timeTextView, binding.profileTextView1, binding.profileTextView2, binding.profileTextView3, binding.profileTextView4, binding.partyNameTextView, binding.addressTextView, binding.costTextView)

            if (color == "black") {
                binding.backgroundView.setBackgroundColor(Color.parseColor("#242424"))
                binding.ticketLayerImageView.setImageResource(R.drawable.ticket_layer_black)
                brightColorTextViewList.forEach { it.setTextColor(Color.parseColor("#919191")) }
                darkColorTextViewList.forEach { it.setTextColor(Color.parseColor("#212121")) }
            } else if (color == "pink") {
                binding.backgroundView.setBackgroundColor(Color.parseColor("#ff4770"))
                binding.ticketLayerImageView.setImageResource(R.drawable.ticket_layer_pink)
                brightColorTextViewList.forEach { it.setTextColor(Color.parseColor("#ffc2d0")) }
                darkColorTextViewList.forEach { it.setTextColor(Color.parseColor("#db3e63")) }
            } else if (color == "blue") {
                binding.backgroundView.setBackgroundColor(Color.parseColor("#3e3dae"))
                binding.ticketLayerImageView.setImageResource(R.drawable.ticket_layer_blue)
                brightColorTextViewList.forEach { it.setTextColor(Color.parseColor("#5150e8")) }
                darkColorTextViewList.forEach { it.setTextColor(Color.parseColor("#3e3dae")) }
            } else if (color == "green") {
                binding.backgroundView.setBackgroundColor(Color.parseColor("#09685b"))
                binding.ticketLayerImageView.setImageResource(R.drawable.ticket_layer_green)
                brightColorTextViewList.forEach { it.setTextColor(Color.parseColor("#34a79d")) }
                darkColorTextViewList.forEach { it.setTextColor(Color.parseColor("#026326")) }
            }
        }

        fun bind(ticketData: TicketData){
//            binding.dateTextView.text = ticketData.ticketDate
//            binding.timeTextView.text = ticketData.ticketTime
//            binding.costTextView.text = ticketData.ticketEntreeFee
//            binding.MenuTextView.text = ticketData.ticketMenu

            val barcodeEncoder = BarcodeEncoder()
            val bitmap = barcodeEncoder.encodeBitmap("123456", BarcodeFormat.CODE_128, 750, 120)
            binding.BarcodeImageView.setImageBitmap(bitmap)
            setupColor(ticketData.layerColor)
//            binding.photoCardImageView.setImageResource(galleryData.photoCardImageResource)
//            binding.movieNameTextView.text = galleryData.movieName
//            binding.scoreTextView.text = galleryData.movieScore
//            binding.genreTextView1.text = galleryData.movieGenre1
//            binding.genreTextView2.text = galleryData.movieGenre2
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