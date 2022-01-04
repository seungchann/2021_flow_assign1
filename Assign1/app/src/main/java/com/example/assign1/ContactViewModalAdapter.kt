package com.example.assign1

import android.app.PendingIntent.getActivity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.example.assign1.databinding.ProfileListBinding
import com.example.assign1.databinding.ProfileModalListBinding


class ContactViewModalAdapter: RecyclerView.Adapter<ContactViewModalAdapter.MyViewHolder>() {
    var datalist = mutableListOf<ProfileData>()

    interface OnItemClickListener {
        fun onItemClick(v: View?, pos:Int)
    }

    private var mListener: OnItemClickListener? = null

    fun setOnItemClickListener(listener: OnItemClickListener){
        this.mListener = listener
    }

    inner class MyViewHolder(private val binding: ProfileModalListBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(profileData: ProfileData){
            binding.profileNameTvModal.text = profileData.profileName

            if(profileData.profileNumber == "") {
                binding.profileAddressTvModal.text = "삭제하기"
            } else {
                binding.profileAddressTvModal.text = profileData.profileAddress.split(",")[0]
            }
            when (profileData.profileIcon) {
                0 -> binding.profilePhotoImgModal.setImageResource(R.drawable.icon_black)
                1 -> binding.profilePhotoImgModal.setImageResource(R.drawable.icon_blue)
                2 -> binding.profilePhotoImgModal.setImageResource(R.drawable.icon_green)
                3 -> binding.profilePhotoImgModal.setImageResource(R.drawable.icon_pink)
                4 -> binding.profilePhotoImgModal.setImageResource(R.drawable.icon_delete)
            }
        }
    }

    //만들어진 뷰홀더 없을때 뷰홀더(레이아웃) 생성하는 함수
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ProfileModalListBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int = datalist.size

    //recyclerview가 viewholder를 가져와 데이터 연결할때 호출
    //적절한 데이터를 가져와서 그 데이터를 사용하여 뷰홀더의 레이아웃 채움
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(datalist[position])

        holder.itemView.setOnClickListener(
            object : View.OnClickListener {
                override fun onClick(v: View?) {
                    val pos = holder.adapterPosition
                    if( pos != RecyclerView.NO_POSITION ){
                        mListener?.onItemClick(v, pos)
                    }
                }
            }
        )
    }
}
