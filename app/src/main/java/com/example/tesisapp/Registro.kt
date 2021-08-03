package com.example.tesisapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import androidx.appcompat.app.AlertDialog
import com.google.firebase.auth.FirebaseAuth

class Registro : AppCompatActivity() {

    private lateinit var txtregUsuario: EditText
    private lateinit var txtregClave: EditText
    private lateinit var btnRegConfirmacion: ImageButton


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)

        txtregUsuario = findViewById(R.id.txtregusuario)
        txtregClave = findViewById(R.id.txtregclave)
        btnRegConfirmacion = findViewById(R.id.btnregconfirmacion)


        //Configuracion

        Setup()

    }

    private fun Setup(){
        btnRegConfirmacion.setOnClickListener{
            if (txtregUsuario.text.isNotEmpty() && txtregClave.text.isNotEmpty()){

                FirebaseAuth.getInstance().createUserWithEmailAndPassword(txtregUsuario.text.toString(),txtregClave.text.toString()).addOnCompleteListener{
                    if(it.isSuccessful){

                        val confirmIntent: Intent = Intent(this,MainActivity::class.java)
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
        builder.setMessage("No se ha podido registrar el usuario, el usuario debe ser un Email y la contraseña debe tener mínimo 6 caracteres")
        builder.setPositiveButton("Aceptar",null)
        val dialog: AlertDialog = builder.create()
        dialog.show()

    }
}