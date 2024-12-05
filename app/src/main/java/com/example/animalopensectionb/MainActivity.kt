package com.example.animalopensectionb

import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.viewpager2.widget.ViewPager2

class MainActivity : AppCompatActivity() {

    private lateinit var viewPager: ViewPager2
    private lateinit var mediaPlayer: MediaPlayer

    //List of animal images and sounds

    private val animalImages = listOf(
        R.drawable.cat,
        R.drawable.dog,
        R.drawable.cow
    )

    private val animalSounds = listOf(
        R.raw.cat,
        R.raw.dog,
        R.raw.cow
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //Initialize the viewpage2
        viewPager = findViewById(R.id.viewPager)
        val adapter = AnimalAdapter(animalImages, this)
        viewPager.adapter = adapter

        //Handle click events on images
        adapter.setOnItemClickListener { position ->
            playSound(position)

        }
    }

    private fun playSound(position: Int) {
        try {
            //Release any previously playing sound
            if(::mediaPlayer.isInitialized) {
                mediaPlayer.release()
            }
            mediaPlayer = MediaPlayer.create(this, animalSounds[position])
            mediaPlayer.start()

        } catch (e: Exception) {
            Log.e("Main Activity", "Error playing sound: ${e.message}")
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if(::mediaPlayer.isInitialized) {
            mediaPlayer.release()
        }
    }
}