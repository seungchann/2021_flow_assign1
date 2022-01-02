package com.example.assign1

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TimePicker
import androidx.fragment.app.Fragment
import com.example.assign1.R
import java.util.*

class PostFormFragment: Fragment(){

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.form_post, container, false)
        val goBackButton = view.findViewById<Button>(R.id.back_to_form)
        val goGalleryButton = view.findViewById<Button>(R.id.go_to_see)

        goBackButton.setOnClickListener(
            object: View.OnClickListener {
                override fun onClick(p0: View?) {
                    (activity as MainActivity).refreshTab3()
                }
            }
        )

        goGalleryButton.setOnClickListener(
            object: View.OnClickListener {
                override fun onClick(p0: View?) {
                    (activity as MainActivity).transitionToTab2()
                }
            }
        )



        return view
    }
}