package com.example.assign1

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TimePicker
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.assign1.MainActivity
import com.example.assign1.R
import de.hdodenhof.circleimageview.CircleImageView

class MemberFormFragment: Fragment(), View.OnClickListener {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.form_member, container, false)

        view.findViewById<CircleImageView>(R.id.member_1).setOnClickListener( this )
        view.findViewById<CircleImageView>(R.id.member_2).setOnClickListener( this )
        view.findViewById<CircleImageView>(R.id.member_3).setOnClickListener( this )
        view.findViewById<CircleImageView>(R.id.member_4).setOnClickListener( this )

        return view
    }

    override fun onClick(view: View){
        val dialog = Dialog(activity as MainActivity)
        dialog.setContentView(R.layout.form_member_modal)

        dialog.show()
    }
}