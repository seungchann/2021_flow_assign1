package com.example.assign1

import android.app.Application
import android.content.Context

class App:Application() {
    init{
        instance = this
    }

    companion object {
        var instance: App? = null
        fun context(): Context{
            return instance!!.applicationContext
        }
    }
}
// [ApplicationContext 가져오기] 어디서나 접근할 수 있는 context