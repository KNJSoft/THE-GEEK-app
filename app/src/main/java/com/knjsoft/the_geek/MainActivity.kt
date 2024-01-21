package com.knjsoft.the_geek

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.PopupMenu
import com.google.android.material.textfield.TextInputEditText

lateinit var textinput:TextInputEditText;
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId==R.id.set_home){
            //start

            val anchorView = findViewById<View>(R.id.set_home)
            showPopupMenu(anchorView)
            true
            //end


        }
        if (item.itemId==R.id.profile){
            Intent(this,Settings::class.java).also {
                startActivity(it)
            }
        }
        if (item.itemId==R.id.search){
            Intent(this,Search::class.java).also {
                startActivity(it)
            }
        }
        return super.onOptionsItemSelected(item)
    }
    //fonction affichant le menu deroulante
    private fun showPopupMenu(anchorView: View) {
        val popupMenu = PopupMenu(this, anchorView)
        popupMenu.inflate(R.menu.settings)

        // Définir un écouteur de clic pour les éléments du menu
        popupMenu.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.menu_set -> {

                    Intent(this,Settings::class.java).also {
                        startActivity(it)
                    }
                    true
                }
                R.id.menu_mes -> {
                    // Action à effectuer lorsque le deuxième élément du menu est cliqué
                    true
                }
                R.id.menu_cours -> {
                    // Action à effectuer lorsque le deuxième élément du menu est cliqué
                    true
                }
                R.id.menu_logout -> {
                    val sharedPreferences = getSharedPreferences("THE-GEEK", Context.MODE_PRIVATE)
                    val editor = sharedPreferences.edit()
                    editor.putBoolean("is_authenticated", false)
                    editor.apply()
                    Intent(this,Anonyme::class.java).also {
                        startActivity(it)
                    }
                    finish()
                    true
                }
                // Ajouter d'autres éléments du menu ici

                else -> false
            }
        }

        // Afficher le menu déroulant
        popupMenu.show()
    }
    //fin fonction
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_home,menu)
        return super.onCreateOptionsMenu(menu)
    }
}