package com.example.practica2.Vista

import InscripcionPresentador
import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.practica2.Contrato.ContratoInscripcion
import com.example.practica2.R

class MainActivity : AppCompatActivity(), ContratoInscripcion.vista {

    private lateinit var matricula: EditText
    private lateinit var nombre: EditText
    private lateinit var carrera: Spinner
    private lateinit var promedio: EditText
    private lateinit var calcular: Button
    private lateinit var presentador: ContratoInscripcion.presentador

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        inicializarVistas()

        presentador = InscripcionPresentador(this)
        presentador.cargarCarreras()

        calcular.setOnClickListener {
            val nom = nombre.text.toString()
            val mat = matricula.text.toString()
            val car = carrera.selectedItem?.toString() ?: ""
            val promText = promedio.text.toString()

            if (promText.isEmpty()) {
                mostrarError("Ingresa el promedio")
                return@setOnClickListener
            }

            try {
                val prom = promText.toFloat()
                presentador.calcularCostoInscripcion(nom, mat, car, prom)
            } catch (e: NumberFormatException) {
                mostrarError("Formato de promedio inválido")
            }
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun inicializarVistas() {
        matricula = findViewById(R.id.matricula)
        nombre = findViewById(R.id.nombre)
        carrera = findViewById(R.id.carrera)
        promedio = findViewById(R.id.promedio)
        calcular = findViewById(R.id.calcular)
    }

    override fun mostrarError(mensaje: String) {
        Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show()
    }

    override fun cargarCarreras(carreras: List<String>) {
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, carreras)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        carrera.adapter = adapter
    }

    override fun navegarADetalle(nombre: String, carrera: String, promedio: Float, costo: Float) {
        val intent = Intent(this, DetalleActivity::class.java).apply {
            putExtra("NOMBRE", nombre)
            putExtra("CARRERA", carrera)
            putExtra("PROMEDIO", promedio)
            putExtra("COSTO", costo)
        }
        startActivity(intent)
    }
}