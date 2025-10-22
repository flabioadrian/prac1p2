package com.example.practica2.Contrato

interface ContratoInscripcion {
    interface modelo {
        fun calcularDescuento(promedio: Float): Float
        fun calcularCostoInscripcion(descuento: Float): Float
        fun validarPromedio(promedio: Float): Boolean
        fun obtenerCarreras(): List<String>
    }

    interface vista {
        fun mostrarError(mensaje: String)
        fun cargarCarreras(carreras: List<String>)
        fun navegarADetalle(nombre: String, carrera: String, promedio: Float, costo: Float)
    }

    interface presentador {
        fun calcularCostoInscripcion(nombre: String, matricula: String, carrera: String, promedio: Float)
        fun cargarCarreras()
    }
}