package com.example.tesisapp

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

class Galeria : AppCompatActivity() {

    private lateinit var subirVideo: ImageButton
    private val File = 1
    private val database = Firebase.database
    val myRef = database.getReference("user")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_galeria)

        subirVideo = findViewById(R.id.subirvideo)

        subirVideo.setOnClickListener {
            Upload()
        }

    }

    fun Upload(){
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type ="*/*"
        startActivityForResult(intent,File)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == File){
            if(resultCode == RESULT_OK){
                val FileUri = data!!.data
                val Folder:StorageReference = FirebaseStorage.getInstance().getReference().child("user")
                val file_name: StorageReference = Folder.child("file" + FileUri!!.lastPathSegment)
                file_name.putFile(FileUri).addOnSuccessListener {
                    file_name.downloadUrl.addOnSuccessListener { uri ->
                        val hashMap = HashMap<String,String>()
                        hashMap["link"] = java.lang.String.valueOf(uri)
                        myRef.setValue(hashMap)
                        

                    }
                }
            }
        }
    }
}