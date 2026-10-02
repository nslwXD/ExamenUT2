package com.isengard.fruegas

import android.os.Bundle
import android.util.Log
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageButton
import android.widget.RadioGroup
import android.widget.Spinner
import android.widget.Toast
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

    companion object {
        private const val TAG = "FraguasIsengard"
        private const val KEY_ID = "id"
        private const val KEY_TIPO = "tipo"
        private const val KEY_RADIO = "radio"
        private const val KEY_ANTORCHA = "antorcha"
        private const val KEY_ERROR = "error"
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etIdentificador = findViewById(R.id.identificador)
        spinnerTipo = findViewById(R.id.spinnerTipo)
        radioProteccion = findViewById(R.id.radioProteccion)
        checkAntorcha = findViewById(R.id.checkAntorcha)
        btnEnviar = findViewById(R.id.btnEnviar)

        etIdentificador.requestFocus()

        etIdentificador.setOnFocusChangeListener { _, tieneFoco ->
            if (!tieneFoco &&
                etIdentificador.text.toString().trim().isEmpty()
            ) {
                etIdentificador.error =
                    getString(R.string.error_id_vacio)
            }
        }

        btnEnviar.setOnClickListener {
            enviarUnidad()
        }




    }

    private fun enviarUnidad() {

        val id = etIdentificador.text.toString().trim()

        // validamos que el identificador tenga contenido
        if (id.isEmpty()) {

            etIdentificador.error =
                getString(R.string.error_id_vacio)

            etIdentificador.requestFocus()

            return
        }

        val tipo = spinnerTipo.selectedItem.toString()

        val proteccion = when (radioProteccion.checkedRadioButtonId) {

            R.id.radioArmadura ->
                getString(R.string.armadura_hierro)

            R.id.radioEscudo ->
                getString(R.string.escudo_isengard)

            else ->
                "Sin proteccion seleccionada"
        }

        val llevaAntorcha = checkAntorcha.isChecked

        // mostramos en log los datos recogidos del formulario
        Log.d(
            TAG,
            "unidad: id=$id, tipo=$tipo, proteccion=$proteccion, antorcha=$llevaAntorcha"
        )

        // avisamos si se envia una unidad sin fuego
        if (!llevaAntorcha) {
            Log.e(
                TAG,
                "¡Peligro! Unidad enviada sin antorcha"
            )
        }

        Toast.makeText(
            this,
            getString(R.string.toast_enviada, id),
            Toast.LENGTH_LONG
        ).show()
    }

}