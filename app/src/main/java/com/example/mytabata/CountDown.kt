package com.example.mytabata

import android.os.CountDownTimer
import android.util.Log

class CountDown(var seconds: Int, var veces: Int, var loquehacealhacertick: (Long) -> Unit) {
    private var estado: Boolean = false
    private var contador: Int = 0

    var contadorTrabajo = object : CountDownTimer((seconds * 1000L), 1000) {
        override fun onTick(millisUntilFinished: Long) {
            if (estado) loquehacealhacertick(millisUntilFinished / 1000)
            Log.i("Contador ${contador}", "Segundos restantes: ${millisUntilFinished / 1000}")
        }

        override fun onFinish() {
            estado = false
            Log.i("Contador ${contador}", "Contador ${contador} terminado. Conteo: $contador")

            if (contador < veces) {
                startContadorDescanso()
            } else {
                Log.i("Contador", "Contador completado $veces veces.")
            }
        }
    }

    var contadorDescanso = object : CountDownTimer((seconds * 500L), 1000) {
        override fun onTick(millisUntilFinished: Long) {
            loquehacealhacertick(millisUntilFinished / 1000)
            Log.i("Contador ${contador}", "Segundos restantes: ${millisUntilFinished / 1000}")
        }

        override fun onFinish() {
            Log.i("Contador ${contador}", "Contador de descanso terminado")

            if (contador < veces) {
                startContadorTrabajo()
            } else {
                Log.i("Contador", "Todos los contadores completados.")
            }
        }
    }

    private fun startContadorDescanso() {
        contador++
        estado = true
        Log.i("Contador ${contador}", "Iniciando contador de descanso")
        contadorDescanso.start()
    }

    private fun startContadorTrabajo() {
        contador++
        Log.i("Contador ${contador}", "Iniciando contador de trabajo")
        estado = true
        contadorTrabajo.start()
    }

    fun toggle() {
        if (this.estado) {
            this.cancel()
        } else {
            contador = 0
            startContadorTrabajo()
        }
    }

    fun cancel() {
        estado = false
        this.contadorTrabajo.cancel()
        this.contadorDescanso.cancel()
    }
}
