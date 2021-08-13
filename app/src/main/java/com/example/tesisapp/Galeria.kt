package com.example.tesisapp

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.MediaController
import android.widget.VideoView
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

class Galeria : AppCompatActivity() {

    private lateinit var subirVideo: ImageButton
    private val File = 1
    private val database = Firebase.database
    val myRef = database.getReference("user")
    lateinit var Ej1: VideoView
    lateinit var Ej2: VideoView
    lateinit var Ej3: VideoView
    lateinit var Ej4: VideoView
    lateinit var Ej5: VideoView
    lateinit var Ej6: VideoView
    lateinit var Ej7: VideoView
    lateinit var Ej8: VideoView
    lateinit var Ej9: VideoView
    lateinit var Ej10: VideoView

    lateinit var mediaController1: MediaController
    lateinit var mediaController2: MediaController
    lateinit var mediaController3: MediaController
    lateinit var mediaController4: MediaController
    lateinit var mediaController5: MediaController
    lateinit var mediaController6: MediaController
    lateinit var mediaController7: MediaController
    lateinit var mediaController8: MediaController
    lateinit var mediaController9: MediaController
    lateinit var mediaController10: MediaController



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_galeria)

        subirVideo = findViewById(R.id.subirvideo)

        subirVideo.setOnClickListener {
            Upload()
        }

        Ej1 = findViewById(R.id.Ej1) as VideoView
        Ej2 = findViewById(R.id.Ej2) as VideoView
        Ej3 = findViewById(R.id.Ej3) as VideoView
        Ej4 = findViewById(R.id.Ej4) as VideoView
        Ej5 = findViewById(R.id.Ej5) as VideoView
        Ej6 = findViewById(R.id.Ej6) as VideoView
        Ej7 = findViewById(R.id.Ej7) as VideoView
        Ej8 = findViewById(R.id.Ej8) as VideoView
        Ej9 = findViewById(R.id.Ej9) as VideoView
        Ej10 = findViewById(R.id.Ej10) as VideoView

        mediaController1 = MediaController(this)
        mediaController1!!.setAnchorView(this.Ej1)
        mediaController2 = MediaController(this)
        mediaController2!!.setAnchorView(this.Ej2)
        mediaController3 = MediaController(this)
        mediaController3!!.setAnchorView(this.Ej3)
        mediaController4 = MediaController(this)
        mediaController4!!.setAnchorView(this.Ej4)
        mediaController5 = MediaController(this)
        mediaController5!!.setAnchorView(this.Ej5)
        mediaController6 = MediaController(this)
        mediaController6!!.setAnchorView(this.Ej6)
        mediaController7 = MediaController(this)
        mediaController7!!.setAnchorView(this.Ej7)
        mediaController8 = MediaController(this)
        mediaController8!!.setAnchorView(this.Ej8)
        mediaController9 = MediaController(this)
        mediaController9!!.setAnchorView(this.Ej9)
        mediaController10 = MediaController(this)
        mediaController10!!.setAnchorView(this.Ej10)


        Ej1!!.setMediaController(mediaController1)
        Ej1!!.setVideoURI(Uri.parse("android.resource://" + packageName + "/"+ R.raw.ejemplo1))
        Ej1!!.requestFocus()

        Ej2!!.setMediaController(mediaController2)
        Ej2!!.setVideoURI(Uri.parse("android.resource://" + packageName + "/"+ R.raw.ejemplo2))
        Ej2!!.requestFocus()

        Ej3!!.setMediaController(mediaController3)
        Ej3!!.setVideoURI(Uri.parse("android.resource://" + packageName + "/"+ R.raw.ejemplo3))
        Ej3!!.requestFocus()

        Ej4!!.setMediaController(mediaController4)
        Ej4!!.setVideoURI(Uri.parse("android.resource://" + packageName + "/"+ R.raw.ejemplo4))
        Ej4!!.requestFocus()

        Ej5!!.setMediaController(mediaController5)
        Ej5!!.setVideoURI(Uri.parse("android.resource://" + packageName + "/"+ R.raw.ejemplo5))
        Ej5!!.requestFocus()

        Ej6!!.setMediaController(mediaController6)
        Ej6!!.setVideoURI(Uri.parse("android.resource://" + packageName + "/"+ R.raw.ejemplo6))
        Ej6!!.requestFocus()

        Ej7!!.setMediaController(mediaController7)
        Ej7!!.setVideoURI(Uri.parse("android.resource://" + packageName + "/"+ R.raw.ejemplo7))
        Ej7!!.requestFocus()

        Ej8!!.setMediaController(mediaController8)
        Ej8!!.setVideoURI(Uri.parse("android.resource://" + packageName + "/"+ R.raw.ejemplo8))
        Ej8!!.requestFocus()

        Ej9!!.setMediaController(mediaController9)
        Ej9!!.setVideoURI(Uri.parse("android.resource://" + packageName + "/"+ R.raw.ejemplo9))
        Ej9!!.requestFocus()

        Ej10!!.setMediaController(mediaController10)
        Ej10!!.setVideoURI(Uri.parse("android.resource://" + packageName + "/"+ R.raw.ejemplo10))
        Ej10!!.requestFocus()




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