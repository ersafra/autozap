package com.ersafra.autozap

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import java.util.*

class SaudacaoViewModel {

    private val _saudacao = MutableLiveData<String>()
    val saudacao: LiveData<String>
        get() = _saudacao

    init {
        atualizarSaudacao()
    }

    private fun atualizarSaudacao() {
        val horaAtual = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
        _saudacao.value = when (horaAtual) {
            in 0..11 -> "Bom dia!"
            in 12..17 -> "Boa tarde!"
            else -> "Boa noite!"
        }
    }
}

