package com.example.wellnessapp

import android.content.Intent
import android.os.Bundle
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.TextView

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_splash)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Animate the TextView like YouTube splash
        val splashText = findViewById<TextView>(R.id.splashText)
        val fadeIn = AlphaAnimation(0f, 1f)
        fadeIn.duration = 1500  // 2 seconds fade-in
        fadeIn.fillAfter = true  // keep the final state
        splashText.startAnimation(fadeIn)

        // Move to MainActivity after 5 seconds
        android.os.Handler().postDelayed(
            {
                val intent = Intent(applicationContext, MainActivity::class.java)
                startActivity(intent)
                finish() // finish splash so user can't go back
            }, 5000
        )
    }
}