package com.example.myquintaapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.airbnb.lottie.LottieAnimationView
import android.widget.Button
import android.content.Intent

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val animationView = findViewById<LottieAnimationView>(R.id.lottieAnimationView)
        animationView.setAnimation(R.raw.retail_animation)
        animationView.playAnimation()

        val btnVendedores = findViewById<Button>(R.id.btnVendedores)
        btnVendedores.setOnClickListener {
            val intent = Intent(this, VendedoresActivity::class.java)
            startActivity(intent)
        }
    }
}
