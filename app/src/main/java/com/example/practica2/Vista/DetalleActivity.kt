package com.example.practica2.Vista

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.practica2.R

class DetalleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detalle)

        val txtNombre = findViewById<TextView>(R.id.txtNombre)
        val txtCarrera = findViewById<TextView>(R.id.txtCarrera)
        val txtPromedio = findViewById<TextView>(R.id.txtPromedio)
        val txtCosto = findViewById<TextView>(R.id.txtCosto)

        val bundle = intent
        val nombre = bundle.getStringExtra("NOMBRE") ?: ""
        val carrera = bundle.getStringExtra("CARRERA") ?: ""
        val promedio = bundle.getFloatExtra("PROMEDIO", 0.0f)
        val costo = bundle.getFloatExtra("COSTO", 0.0f)

        txtNombre.text = "Nombre del estudiante: ${nombre}"
        txtCarrera.text = "Carrera seleccionada: ${carrera}"
        txtPromedio.text = "Promedio obtenido: ${promedio}"
        txtCosto.text = "Monto final a pagar: $${costo}"

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}