package com.example.mytabata

import android.os.CountDownTimer
import android.util.Log

class CountDown(var seconds: Int, var veces: Int, var loquehacealhacertick: (Long) -> Unit) { // Es la clase con los dos countDown
    private var estado: Boolean = false  // Es para saber si el countDown está corriendo
    private var contador: Int = 0 // Para seguir las veces que se empieza un countDown

    var contadorTrabajo = object : CountDownTimer((seconds * 1000L), 1000) { // Es el countDown de trabajo
        override fun onTick(millisUntilFinished: Long) { // Es el contador
            if (estado) loquehacealhacertick(millisUntilFinished / 1000)
            Log.i("Contador ${contador}", "Segundos restantes: ${millisUntilFinished / 1000}")
        }

        override fun onFinish() { // Acaba un countDown de trabajo
            estado = false
            Log.i("Contador ${contador}", "Contador ${contador} terminado. Conteo: $contador")

            if (contador < veces) { // Comprueba con la variable de contador si hay que hacer otro countDown al terminar el que está corriendo
                startContadorDescanso()
            } else {
                Log.i("Contador", "Contador completado $veces veces.")
            }
        }
    }

    var contadorDescanso = object : CountDownTimer((seconds * 500L), 1000) { // Es el countDown de descanso
        override fun onTick(millisUntilFinished: Long) { // Es el countDown
            loquehacealhacertick(millisUntilFinished / 1000)
            Log.i("Contador ${contador}", "Segundos restantes: ${millisUntilFinished / 1000}")
        }

        override fun onFinish() { // Acaba un contador de descanso
            Log.i("Contador ${contador}", "Contador de descanso terminado")

            if (contador < veces) { // Comprueba con la variable de contador si hay que hacer otro countDown al terminar el que está corriendo
                startContadorTrabajo()
            } else {
                Log.i("Contador", "Todos los contadores completados.")
            }
        }
    }

    private fun startContadorDescanso() { // Empieza el countDown de descanso
        contador++
        estado = true
        Log.i("Contador ${contador}", "Iniciando contador de descanso")
        contadorDescanso.start()
    }

    private fun startContadorTrabajo() { // Empieza el countDown de trabajo
        contador++
        Log.i("Contador ${contador}", "Iniciando contador de trabajo")
        estado = true
        contadorTrabajo.start()
    }

    fun toggle() { // Empieza el countDown si no lo cancela
        if (this.estado) { // Mira si hay un contador corriendo; si no hay, empieza uno
            this.cancel()
        } else { // Si hay un countDown corriendo, lo cancela y pone el contador a 0
            contador = 0
            startContadorTrabajo()
        }
    }

    fun cancel() { // Cancela el countDown
        estado = false
        this.contadorTrabajo.cancel()
        this.contadorDescanso.cancel()
    }
}
