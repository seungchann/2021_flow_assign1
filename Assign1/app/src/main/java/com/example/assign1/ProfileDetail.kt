package com.example.assign1

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.example.assign1.databinding.FragmentAddProfileDataBinding
import com.example.assign1.databinding.FragmentProfileDataEditBinding
import com.example.assign1.databinding.FragmentProfileDetailBinding

class ProfileDetail : Fragment() {

    private lateinit var binding: FragmentProfileDetailBinding

    var index : Int = 0
    lateinit var name : String
    lateinit var phone : String
    lateinit var address : String
    var iconData : Int = 0
    lateinit var detailAddress: String
    lateinit var tmp:String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)



    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentProfileDetailBinding.inflate(layoutInflater)

        index = arguments?.getInt("index") as Int
        name = arguments?.getString("name").toString()
        tmp = arguments?.getString("phone").toString()
        phone = insertBarInPhoneNumber(tmp)
        address = arguments?.getString("address").toString()
        iconData = arguments?.getInt("icon") as Int


        val token = address.split(", ")
        address = token[0]
        detailAddress = token[1]
        binding.detailNameShowTextView.setText(name)
        binding.detailPhoneShowTextView.setText(phone)
        binding.detailAddressShowTextView.setText(address)
        binding.detailDetailAddressShowTextView.setText(detailAddress)


        when (iconData) {
            0 -> binding.detailImageView.setImageResource(R.drawable.icon_black)
            1 -> binding.detailImageView.setImageResource(R.drawable.icon_blue)
            2 -> binding.detailImageView.setImageResource(R.drawable.icon_green)
            3 -> binding.detailImageView.setImageResource(R.drawable.icon_pink)
        }
        return binding.root
    }

    override fun onPause() {
        super.onPause()

    }



    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_detail, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }



    // 메뉴 선택시 profile add fragment로 이
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menuEdit -> {
                var nextFragment = ProfileDataEdit()
                var bundle = Bundle()
                bundle.putInt("index",index as Int)
                bundle.putString("name",name)
                bundle.putString("phone",tmp)
                bundle.putString("address",address)
                bundle.putString("detailAddress",detailAddress)
                bundle.putInt("icon",iconData as Int)

                nextFragment.arguments = bundle
                val transaction = requireActivity().supportFragmentManager.beginTransaction()
                transaction.replace(R.id.frameLayout, nextFragment)
                transaction.addToBackStack("detail")
                transaction.commit()
                return super.onOptionsItemSelected(item)
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

}