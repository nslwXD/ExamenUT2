package com.isengard.fruegas

import android.os.Bundle
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageButton
import android.widget.RadioGroup
import android.widget.Spinner
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var etIdentificador: EditText
    private lateinit var spinnerTipo: Spinner
    private lateinit var radioProteccion: RadioGroup
    private lateinit var checkAntorcha: CheckBox
    private lateinit var btnEnviar: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etIdentificador = findViewById(R.id.identificador)
        spinnerTipo = findViewById(R.id.spinnerTipo)
        radioProteccion = findViewById(R.id.radioProteccion)
        checkAntorcha = findViewById(R.id.checkAntorcha)
        btnEnviar = findViewById(R.id.btnEnviar)

        }
    }
}