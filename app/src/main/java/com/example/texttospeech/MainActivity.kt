package com.example.texttospeech

import android.annotation.SuppressLint
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.Locale

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    private lateinit var tts: TextToSpeech
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        val et: EditText =findViewById(R.id.et)
        val btn: Button =findViewById(R.id.btn)
        tts=TextToSpeech(applicationContext){status->
            if(status==TextToSpeech.SUCCESS){
                btn.setOnClickListener(){
                    val result=tts.setLanguage(Locale.getDefault())
                   if(result==TextToSpeech.LANG_MISSING_DATA||result==TextToSpeech.LANG_NOT_SUPPORTED){
                       Toast.makeText(this,"Language not supported",Toast.LENGTH_SHORT).show()
                   }
                }
            }
        }
        btn.setOnClickListener(){
            var tospeak:String=et.text.toString()
            tts.speak(tospeak,TextToSpeech.QUEUE_FLUSH,null,null)
        }

    }
    override fun onDestroy() {
        super.onDestroy()
        tts.stop()
        tts.shutdown()
    }
}