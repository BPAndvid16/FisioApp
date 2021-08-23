package com.example.tesisapp

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
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
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast




open class Terapias : AppCompatActivity(), SensorEventListener{



    //Views

    private var tvGyro: ArrayList<TextView> = ArrayList();
    private lateinit var progreso: TextView

    //Buttons

    private lateinit var btnStart: Button
    private lateinit var btnStop: Button
    private lateinit var btnReset: Button


    //Viewid

    private var idGyro: ArrayList<Int> = arrayListOf(R.id.tv_gy_x,R.id.tv_gy_y,R.id.tv_gy_z)

    //Sensores

    private lateinit var sensorManager: SensorManager
    private lateinit var sensorGyro: Sensor


    //SensorData

    private var gyroData: SensorData? = null

    //GyroDaten

    private var gyroX: Float = 0f
    private var gyroY: Float = 0f
    private var gyroZ: Float = 0f

    private var timeGyro: Long = 0





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_terapias)



        initViews()
        initSensor()

    }

    @SuppressLint("ObjectAnimatorBinding")
    private fun initViews(){
        for (i in idGyro){
            tvGyro.add(findViewById(i))
        }



        btnStart = findViewById(R.id.btnStart)
        btnStop = findViewById(R.id.btnStop)
        btnReset = findViewById(R.id.btnReset)
        progreso = findViewById(R.id.txtProgreso)





        btnStart.setOnClickListener{
            registerListener()
            btnStart.isEnabled = false
            btnStop.isEnabled = true

        }

        btnStop.setOnClickListener {

            unregisteredListener()
            btnStart.isEnabled = true
            btnStop.isEnabled = false
        }

        btnReset.setOnClickListener {
            gyroX = 0f
            gyroY = 0f
            gyroZ = 0f
        }


    }

    private fun initSensor(){
        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager

        if(sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE) != null){

            sensorGyro = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE)



        }
    }

    private fun registerListener(){

        if(sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE) != null){

            sensorManager.registerListener(this,sensorGyro,SensorManager.SENSOR_DELAY_NORMAL)

        }

    }

    private fun unregisteredListener(){
        sensorManager.unregisterListener(this,sensorGyro)
    }

    override fun onSensorChanged(event: SensorEvent?) {
        if(event!!.sensor.type == Sensor.TYPE_GYROSCOPE){
            getGyroData(event)
        }
    }

    override fun onAccuracyChanged(event: Sensor?, p1: Int) {

    }


    private fun getGyroData(e:SensorEvent?){

        if(gyroData == null) {
            gyroData = SensorData(e!!.values[0], e!!.values[1], e!!.values[2], e!!.timestamp)
            timeGyro = System.currentTimeMillis()

        }
        else{
            var time = (System.currentTimeMillis() - timeGyro)/1000f
            gyroData!!.x1 = e!!.values[0]
            gyroData!!.x2 = e!!.values[1]
            gyroData!!.x3 = e!!.values[2]
            gyroX += gyroData!!.x1 * time
            gyroY = gyroData!!.x2 * time
            gyroZ = gyroData!!.x3 * time
        }

        tvGyro[0].text = "x1: ${"%.2f".format(gyroData!!.x1*(180.0/Math.PI))} grados \t\t gyroX: ${"%.2f".format(gyroX*(180.0/Math.PI))} grados"
        tvGyro[1].text = "x1: ${"%.2f".format(gyroData!!.x2*(180.0/Math.PI))} grados \t\t gyroY: ${"%.2f".format(gyroY*(180.0/Math.PI))} grados"
        tvGyro[2].text = "x1: ${"%.2f".format(gyroData!!.x3*(180.0/Math.PI))} grados \t\t gyroZ: ${"%.2f".format(gyroZ*(180.0/Math.PI))} grados"
        progreso.text = "${"%.2f".format(gyroX*(180.0/Math.PI))} grados"
        timeGyro = System.currentTimeMillis()


    }

}






