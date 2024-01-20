package com.knjsoft.the_geek

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu

class Anonyme : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_anonyme)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.anonyme,menu)
        return super.onCreateOptionsMenu(menu)
    }
}