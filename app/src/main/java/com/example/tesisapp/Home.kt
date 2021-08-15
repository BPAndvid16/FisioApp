package com.example.tesisapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton

class Home : AppCompatActivity() {

    private lateinit var btnVideo: ImageButton
    private lateinit var btnTerapia: ImageButton
    private lateinit var btnChat: ImageButton


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        btnVideo = findViewById(R.id.btnvideo)
        btnTerapia = findViewById(R.id.btnterapia)
        btnChat = findViewById(R.id.btnchat)

        btnVideo.setOnClickListener {
            val videoIntent: Intent = Intent(this,Galeria::class.java)
            startActivity(videoIntent)
        }

        btnTerapia.setOnClickListener {
            val terapiaIntent: Intent = Intent(this,Terapias::class.java)
            startActivity(terapiaIntent)
        }

        btnChat.setOnClickListener {
            val chatIntent: Intent = Intent(this,Contacto::class.java)
            startActivity(chatIntent)
        }


    }
}