package com.knjsoft.the_geek

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem

class Anonyme : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_anonyme)
    }




    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId==R.id.log){
            Intent(this,Authentification::class.java).also {
                startActivity(it)
            }

        }
        if (item.itemId==R.id.set){
            Intent(this,Settings::class.java).also {
                startActivity(it)
            }
        }
        if (item.itemId==R.id.account){
            Intent(this,Inscription::class.java).also {
                startActivity(it)
            }
        }
        return super.onOptionsItemSelected(item)
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.anonyme,menu)
        return super.onCreateOptionsMenu(menu)
    }
}