package com.knjsoft.the_geek

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper

class Start : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)
        Handler(Looper.getMainLooper()).postDelayed({
            val sharedPreferences = getSharedPreferences("THE-GEEK", Context.MODE_PRIVATE)
            val isLoggedIn = sharedPreferences.getBoolean("is_authenticated", false)

            if (isLoggedIn) {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }else {


                Intent(this, Anonyme::class.java).also {
                    startActivity(it)
                }
                finish()
            }
        }, 5000)
    }
}