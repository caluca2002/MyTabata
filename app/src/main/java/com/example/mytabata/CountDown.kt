package com.example.mytabata

import android.os.CountDownTimer
import android.util.Log
import androidx.compose.material3.Text

class CountDown(var seconds: Int, var loquehacealhacertick: (Long) -> Unit) {
    private var estado: Boolean = false

    var myCounter =object : CountDownTimer((seconds * 1000L), 1000) {

        override fun onTick(millisUntilFinished: Long) {
            if (estado) loquehacealhacertick(millisUntilFinished / 1000)
        }

        override fun onFinish() {
            estado = false
            Log.i("end", "Finish")
        }
    }

    fun toggle() {
        Log.i("estado", "toggle: $estado")
        if (this.estado == true){
            this.cancel()
        } else {
            Log.i("empezar", "toggle: start")
            this.start()
        }
    }


    fun start() {
        estado = true
        this.myCounter.start()
    }

    fun cancel() {
        estado = false
        this.myCounter.cancel()
    }

    fun myEstado(): Boolean {
        return this.estado
    }
}