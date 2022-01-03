package com.example.assign1

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.assign1.databinding.TicketSettingLayoutBinding
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import java.util.*

class TicketSettingAdapter(mContext: Activity): RecyclerView.Adapter<TicketSettingAdapter.MyViewHolder>() {
    var datalist = mutableListOf<TicketData>()
    val mainActivity: MainActivity = mContext as MainActivity

    inner class MyViewHolder(private val binding: TicketSettingLayoutBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(ticketData: TicketData){
            binding.settingPartyNameTextView.text = ticketData.ticketHost
            binding.settingPartyDateTextView.text = ticketData.ticketDate
            binding.settingPartyTimeTextView.text = ticketData.ticketTime

            binding.ticketSettingLayout.setOnClickListener {
                setData(layoutPosition)
            }
        }
    }

    //만들어진 뷰홀더 없을때 뷰홀더(레이아웃) 생성하는 함수
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TicketSettingAdapter.MyViewHolder {
        val binding = TicketSettingLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int = datalist.size

    //recyclerview가 viewholder를 가져와 데이터 연결할때 호출
    //적절한 데이터를 가져와서 그 데이터를 사용하여 뷰홀더의 레이아웃 채움
    override fun onBindViewHolder(holder: TicketSettingAdapter.MyViewHolder, position: Int) {
        holder.bind(datalist[position])
    }

    fun removeData(position: Int) {
        mainActivity.ticketDataList.removeAt(position)

        val gson = Gson()
        val newJsonString: String = gson.toJson(mainActivity.ticketDataList)
        mainActivity.saveToInnerStorage("tickets.json", newJsonString)

        notifyItemRemoved(position)
    }

    fun swapData(fromPos: Int, toPos: Int) {
        Collections.swap(mainActivity.ticketDataList, fromPos, toPos)
        notifyItemMoved(fromPos, toPos)
    }

    fun setData(position: Int) {
        notifyItemChanged(position)
    }
}