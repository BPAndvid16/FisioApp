package com.example.tesisapp

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class Contacto : AppCompatActivity() {

    lateinit var nombre: EditText
    lateinit var asunto: EditText
    lateinit var mensaje: EditText
    lateinit var EnviarBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contacto)

        nombre = findViewById(R.id.nombreContacto)
        asunto = findViewById(R.id.asuntoContacto)
        mensaje = findViewById(R.id.mensajeContacto)
        EnviarBtn = findViewById(R.id.btncontacto)


        EnviarBtn.setOnClickListener{
            enviar()
        }

    }

    fun enviar(){
        val link = "https://wa.me/573208863746?text=" + "Nombre:%0A%0A"+ nombre.text + "%0A%0A" + "Asunto:%0A%0A" + asunto.text + "%0A%0AMensaje:" +  "%0A%0A" + mensaje.text
        val whaIntent: Intent = Intent(Intent.ACTION_VIEW)
        whaIntent.setData(Uri.parse(link))
        startActivity(whaIntent)
    }

}