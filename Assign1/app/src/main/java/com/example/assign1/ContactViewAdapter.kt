package com.example.assign1

import android.app.PendingIntent.getActivity
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.example.assign1.databinding.ProfileListBinding


class ContactViewAdapter: RecyclerView.Adapter<ContactViewAdapter.MyViewHolder>() {
    var datalist = mutableListOf<ProfileData>()

    inner class MyViewHolder(private val binding: ProfileListBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(profileData: ProfileData){
            binding.profileNameTv.text = profileData.profileName
            binding.profileNumberTv.text = insertBarInPhoneNumber(profileData.profileNumber)
        }
    }

    //만들어진 뷰홀더 없을때 뷰홀더(레이아웃) 생성하는 함수
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ProfileListBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int = datalist.size

    //recyclerview가 viewholder를 가져와 데이터 연결할때 호출
    //적절한 데이터를 가져와서 그 데이터를 사용하여 뷰홀더의 레이아웃 채움
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(datalist[position])

        holder.itemView.setOnClickListener {
//                Intent(App.context(),ProfileDataDetail().apply{ putExtra("data") addFlags(Intent.FLAG_ACTIVITY_NEW_TASK) }.run { App.context().startActivity(this) })
            val nextIntent = Intent (App.context(), ProfileDetailActivity().javaClass)
            nextIntent.putExtra("name",datalist[holder.adapterPosition].profileName)
            nextIntent.putExtra("phone",datalist[holder.adapterPosition].profileNumber)
            nextIntent.putExtra("address",datalist[holder.adapterPosition].profileAddress)
            nextIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            nextIntent.run { App.context().startActivity(this) }

        }
    }

    // profile data 삭제하는 함
    fun removeData(position: Int){
        datalist.removeAt(position)
        removeContactData(position)
        notifyItemRemoved(position)
    }

}

fun insertBarInPhoneNumber(initString: String) : String{
    return initString.substring(0,3) + " - " +initString.substring(3,7) + " - " + initString.substring(7,)
}