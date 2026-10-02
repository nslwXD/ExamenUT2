package com.isengard.fruegas

import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
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

        // cargamos las opciones del spinner desde strings.xml
        val adaptador = ArrayAdapter.createFromResource(
            this,
            R.array.tipos_unidad,
            android.R.layout.simple_spinner_item
        )

        adaptador.setDropDownViewResource(
            android.R.layout.simple_spinner_dropdown_item
        )

        spinnerTipo.adapter = adaptador




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

    override fun onStart() {
        super.onStart()

        Log.d(
            TAG,
            "onStart: Las fraguas se encienden"
        )
    }

    override fun onResume() {
        super.onResume()

        Log.d(
            TAG,
            "onResume: Los Uruk-hai comienzan la produccion"
        )
    }

    override fun onPause() {

        Log.d(
            TAG,
            "onPause: Saruman detiene la produccion temporalmente"
        )

        super.onPause()
    }

    override fun onStop() {

        Log.d(
            TAG,
            "onStop: Las fraguas se detienen"
        )

        super.onStop()
    }

    override fun onDestroy() {

        Log.d(
            TAG,
            "onDestroy: La sala de registro se cierra"
        )

        super.onDestroy()
    }

    override fun onSaveInstanceState(outState: Bundle) {

        // guardamos el estado completo del formulario antes de un cambio de configuracion
        outState.putString(
            KEY_ID,
            etIdentificador.text.toString()
        )

        outState.putInt(
            KEY_TIPO,
            spinnerTipo.selectedItemPosition
        )

        outState.putInt(
            KEY_RADIO,
            radioProteccion.checkedRadioButtonId
        )

        outState.putBoolean(
            KEY_ANTORCHA,
            checkAntorcha.isChecked
        )

        outState.putString(
            KEY_ERROR,
            etIdentificador.error?.toString()
        )

        super.onSaveInstanceState(outState)
    }
}
