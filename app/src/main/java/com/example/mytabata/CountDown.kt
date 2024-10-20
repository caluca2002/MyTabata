package com.example.mytabata

import android.os.CountDownTimer
import android.util.Log
import androidx.compose.material3.Text

class CountDown(var seconds: Int, var loquehacealhacertick: (Long) -> Unit) {
    private var estado: Boolean = false

    // Contador 1
    var myCounter = object : CountDownTimer((seconds * 1000L), 1000) {

        override fun onTick(millisUntilFinished: Long) {
            if (estado) loquehacealhacertick(millisUntilFinished / 1000)
            Log.i("PrimerCounter", "Segundos restantes: ${millisUntilFinished / 1000}")
        }

        override fun onFinish() {
            estado = false
            Log.i("PrimerCounter", "Primer contador terminado")

            startSecondCounter()
        }
    }

    var secondCounter = object : CountDownTimer(10000L, 1000) {
        override fun onTick(millisUntilFinished: Long) {
            loquehacealhacertick(millisUntilFinished / 1000)
            Log.i("SecondCounter", "Segundos restantes: ${millisUntilFinished / 1000}")
        }

        override fun onFinish() {
            Log.i("SecondCounter", "Segundo contador terminado")
        }
    }

    private fun startSecondCounter() {
        Log.i("SecondCounter", "Iniciando segundo contador")
        secondCounter.start()
    }

    fun toggle() {
        Log.i("estado", "toggle: $estado")
        if (this.estado == true) {
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
        this.secondCounter.cancel()
    }
}