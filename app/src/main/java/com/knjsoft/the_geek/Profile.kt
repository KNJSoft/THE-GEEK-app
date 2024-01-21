package com.knjsoft.the_geek

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.button.MaterialButton
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.textfield.TextInputLayout


lateinit var username: TextInputLayout
lateinit var email: TextInputLayout
lateinit var nom: TextInputLayout
lateinit var prenom: TextInputLayout
lateinit var btn: MaterialButton
class Profile : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        username=findViewById(R.id.usernamepro)
        email=findViewById(R.id.Mail_pro)
        nom=findViewById(R.id.Nom_pro)
        prenom=findViewById(R.id.Prenom)
        btn=findViewById(R.id.enrs)
        //get values
        val username1 = intent.getStringExtra("username")
        val email1 = intent.getStringExtra("email")
        val nom1 = intent.getStringExtra("nom")
        val prenom1 = intent.getStringExtra("prenom")
        //default
        username.editText?.setText(username1)
        email.editText?.setText(email1)
        nom.editText?.setText(nom1)
        prenom.editText?.setText(prenom1)
    }
}