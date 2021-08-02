package com.example.tesisapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity

class login : AppCompatActivity() {
    private lateinit var usuario: EditText
    private lateinit var clave: EditText
    private  lateinit var btnlog:ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)
        usuario = findViewById(R.id.txtlogusuario)
        clave = findViewById(R.id.txtlogclave)
        btnlog = findViewById(R.id.btnlog)






    }

}