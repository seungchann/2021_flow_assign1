package com.example.assign1

import android.animation.ObjectAnimator
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

class ConfirmFormFragment: Fragment(), View.OnClickListener {

    private var selected = 0
    private var scaleList = mutableListOf<Float>(0.5F,0.5F,0.5F,0.5F)
    lateinit var viewList: Array<CircleImageView>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.form_confirm, container, false)

        viewList = arrayOf(
            view.findViewById<CircleImageView>(R.id.color_black),
            view.findViewById<CircleImageView>(R.id.color_blue),
            view.findViewById<CircleImageView>(R.id.color_green),
            view.findViewById<CircleImageView>(R.id.color_pink)
        )

        for(i in 0..3) {
            viewList[i].setOnClickListener(this)
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        animate()
    }

    override fun onClick(view: View){
        // 여기서 색상 변경을 감지한다.
        // (activity as MainActivity).sharedTicketData.layerColorResource를 switch 문에서 수정한 후
        // (activity as MainActivity).update를 불러서 TicketPreviewFragment가 Re-rendering을 하도록 한다.

        // TODO : TicketViewFragment의 update 함수를 color까지 변경하도록 만들어 주세요.

        when(view.id){
            R.id.color_black -> {
                selected = 0
            }
            R.id.color_blue -> {
                selected = 1
            }
            R.id.color_green -> {
                selected = 2
            }
            R.id.color_pink -> {
                selected = 3
            }
        }
        animate()
        (activity as MainActivity).update()
    }

    fun animate(){
        for(i in 0 .. 3){
            if( i == selected ) scaleList[i] = 1F
            else scaleList[i] = 0.7F

            ObjectAnimator.ofFloat(viewList[i], "scaleX", scaleList[i]).apply {
                duration = 100
                start()
            }

            ObjectAnimator.ofFloat(viewList[i], "scaleY", scaleList[i]).apply {
                duration = 100
                start()
            }
        }

    }
}