package com.example.practica2.Modelo

import com.example.practica2.Contrato.ContratoInscripcion

class InscripcionModelo : ContratoInscripcion.modelo {
    private val listaCarreras = listOf(
        "Ingenieria en Sistemas",
        "Ingenieria en Mecatronica",
        "Ingenieria Civil",
        "Contaduria",
        "Ingenieria Mecanica",
        "Mercadotecnia",
        "Gastronomia"
    )

    override fun calcularDescuento(promedio: Float): Float {
        if(promedio >= 9.5f) return 40f
        if(promedio >= 8.5f) return 20f
        return 0f
    }

    override fun calcularCostoInscripcion(descuento: Float): Float {
        return 1500f - (1500*descuento/100)
    }

    override fun validarPromedio(promedio: Float): Boolean {
        return promedio >= 0f && promedio <= 10f
    }

    override fun obtenerCarreras(): List<String> {
        return listaCarreras
    }
}