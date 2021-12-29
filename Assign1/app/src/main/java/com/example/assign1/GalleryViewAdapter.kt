package com.example.assign1

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.assign1.databinding.GalleryListBinding

class GalleryViewAdapter: RecyclerView.Adapter<GalleryViewAdapter.MyViewHolder>() {
    var datalist = mutableListOf<GalleryData>()

    inner class MyViewHolder(private val binding: GalleryListBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(galleryData: GalleryData){
            binding.photoCardImageView.setImageResource(galleryData.photoCardImageResource)
            binding.movieNameTextView.text = galleryData.movieName
            binding.scoreTextView.text = galleryData.movieScore
            binding.genreTextView1.text = galleryData.movieGenre1
            binding.genreTextView2.text = galleryData.movieGenre2
        }
    }

    //만들어진 뷰홀더 없을때 뷰홀더(레이아웃) 생성하는 함수
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = GalleryListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int = datalist.size

    //recyclerview가 viewholder를 가져와 데이터 연결할때 호출
    //적절한 데이터를 가져와서 그 데이터를 사용하여 뷰홀더의 레이아웃 채움
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(datalist[position])
    }
}