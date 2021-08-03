package com.example.tesisapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import androidx.appcompat.app.AlertDialog
import com.google.firebase.auth.FirebaseAuth


class MainActivity : AppCompatActivity() {


    private lateinit var btnlog: ImageButton
    private lateinit var btnreg: Button
    private lateinit var txtlogUsuario: EditText
    private lateinit var txtlogClave: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnlog = findViewById(R.id.btnlog)
        btnreg = findViewById(R.id.btnreg)
        txtlogUsuario = findViewById(R.id.txtlogusuario)
        txtlogClave = findViewById(R.id.txtlogclave)

        btnlog.setOnClickListener {

            // Configuracion
            Setup()

        }
        btnreg.setOnClickListener{
            val regIntent: Intent = Intent(this,Home::class.java)
            startActivity(regIntent)
        }





    }

    private fun Setup(){
        title = "Autenticación"
        btnlog.setOnClickListener{
            if (txtlogUsuario.text.isNotEmpty() && txtlogClave.text.isNotEmpty()){

                FirebaseAuth.getInstance().signInWithEmailAndPassword(txtlogUsuario.text.toString(),txtlogClave.text.toString()).addOnCompleteListener{
                    if(it.isSuccessful){

                        val confirmIntent: Intent = Intent(this,Home::class.java)
                        startActivity(confirmIntent)

                    } else {
                        Alerta()
                    }
                }
            }
        }
    }

    private fun Alerta(){

        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("No se ha podido autenticar el usuario")
        builder.setPositiveButton("Aceptar",null)
        val dialog: AlertDialog = builder.create()
        dialog.show()

    }


}