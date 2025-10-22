import com.example.practica2.Contrato.ContratoInscripcion
import com.example.practica2.Modelo.InscripcionModelo

class InscripcionPresentador(private val vista: ContratoInscripcion.vista) : ContratoInscripcion.presentador {
    private val modelo: ContratoInscripcion.modelo = InscripcionModelo()

    override fun calcularCostoInscripcion(nombre: String, matricula: String, carrera: String, promedio: Float) {
        if (nombre.isEmpty() || matricula.isEmpty() || carrera.isEmpty()) {
            vista.mostrarError("Todos los campos son obligatorios")
            return
        }

        if(modelo.validarPromedio(promedio)) {
            val descuento = modelo.calcularDescuento(promedio)
            val costo = modelo.calcularCostoInscripcion(descuento)
            vista.navegarADetalle(nombre, carrera, promedio, costo)
        }
        else{
            vista.mostrarError("Promedio invalido. Debe estar entre 0 y 10")
        }
    }

    override fun cargarCarreras() {
        val carreras = modelo.obtenerCarreras()
        vista.cargarCarreras(carreras)
    }
}