package com.knjsoft.the_geek

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.google.android.material.textfield.TextInputEditText

lateinit var textinput:TextInputEditText;
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        

    }

    /*override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId==R.id.set_home){
            Intent(this,Authentification::class.java).also {
                startActivity(it)
            }
            finish()

        }
        if (item.itemId==R.id.profile){
            Intent(this,Settings::class.java).also {
                startActivity(it)
            }
        }
        return super.onOptionsItemSelected(item)
    }*/
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_home,menu)
        return super.onCreateOptionsMenu(menu)
    }
}