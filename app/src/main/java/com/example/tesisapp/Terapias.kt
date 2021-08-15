package com.example.tesisapp

import android.content.Context
import android.icu.util.TimeUnit.values
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import java.time.chrono.JapaneseEra.values

import android.app.Activity
import android.hardware.*
import android.hardware.SensorEventListener
import android.widget.Button
import kotlin.properties.Delegates
import android.hardware.SensorEvent

import android.hardware.SensorManager
import android.widget.Toast


interface SensorEventListener



class Terapias : AppCompatActivity() {


    private lateinit var Angulos: TextView
    private lateinit var btn: Button






    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_terapias)



        Angulos = findViewById(R.id.txtangulos)
        btn = findViewById(R.id.btnsensar)






btn.setOnClickListener{

    val text = "Entro"
    val duration = Toast.LENGTH_SHORT

    val toast = Toast.makeText(applicationContext, text, duration).show()



}



    }



}


