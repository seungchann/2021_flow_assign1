package com.example.assign1

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.core.view.MotionEventCompat
import androidx.viewpager.widget.ViewPager

class FormViewPager : ViewPager {
    private var enable = true

    constructor(context: Context):super(context)
    constructor(context: Context, attributeSet: AttributeSet?):super(context,attributeSet)

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        return if (enable) {
            super.onInterceptTouchEvent(ev);
        } else {
            if (MotionEventCompat.getActionMasked(ev) == MotionEvent.ACTION_MOVE) {

            } else {
                if (super.onInterceptTouchEvent(ev)) {
                    super.onTouchEvent(ev);
                }
            }
            false;
        }
    }

    override fun onTouchEvent(ev: MotionEvent?): Boolean {
        return if(enable) {
            super.onTouchEvent(ev)
        } else {
            MotionEventCompat.getActionMasked(ev) != MotionEvent.ACTION_MOVE && super.onTouchEvent(ev)
        }
    }

    fun setPagingEnabled(enable: Boolean){
        this.enable = enable
    }
}