package com.knjsoft.the_geek

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import org.json.JSONObject
import android.util.Log
import android.view.Gravity
import android.widget.Toast
import com.android.volley.DefaultRetryPolicy
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.android.volley.NetworkResponse
import org.json.JSONException

class Inscription : AppCompatActivity() {

    lateinit var signup: MaterialButton
    lateinit var Username:TextInputEditText
    lateinit var Email:TextInputEditText
    lateinit var Password:TextInputEditText
    lateinit var Cpassword:TextInputEditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inscription)
        signup=findViewById(R.id.btn_ins)
        Username=findViewById(R.id.username)
        Email=findViewById(R.id.Mail)
        Password=findViewById(R.id.Password)
        Cpassword=findViewById(R.id.confirpassword)
        signup.setOnClickListener {
            val username=Username.text.toString().trim()
            val email=Email.text.toString().trim()
            val password=Password.text.toString().trim()
            val cpassword=Cpassword.text.toString().trim()
            val jsonData = JSONObject()
            jsonData.put("username", username)
            jsonData.put("email", email)
            jsonData.put("password", password)
            jsonData.put("cpassword", cpassword)
            // Créer une requête POST vers l'API Django
            val queue = Volley.newRequestQueue(this)
            val url = "http://192.168.1.106:8000/api/signup" // Remplacer par l'URL de votre API Django
            val request = JsonObjectRequest(Request.Method.POST, url, jsonData,
                { response ->
                    val successMessage = response.getString("msg")
                    Log.d("SignupActivity", "Inscription réussie  django: $successMessage")
                    Log.d("SignupActivity", "Inscription réussie!")
                    val toast = Toast.makeText(this, successMessage, Toast.LENGTH_SHORT)
                    toast.setGravity(Gravity.TOP or Gravity.CENTER, 0, resources.getDimensionPixelOffset(R.dimen.toast_margin_top))
                    val view = toast.view
                    if (view != null) {
                        view.setBackgroundColor(Color.GREEN)
                    }
                    toast.show()
                    Intent(this,Authentification::class.java).also {
                        startActivity(it)
                    }
                    finish()


                },
                { error ->
                    if (error.networkResponse != null) {
                        val statusCode = error.networkResponse.statusCode
                        val errorResponse = String(error.networkResponse.data)
                        try {
                            val jsonResponse = JSONObject(errorResponse)
                            val errorMessage = jsonResponse.getString("error")
                            Log.e("SignupActivity", "Erreur lors de l'inscription djannnnnngo: $errorMessage")
                            val toast = Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT)
                            toast.setGravity(Gravity.TOP or Gravity.CENTER, 0, resources.getDimensionPixelOffset(R.dimen.toast_margin_top))
                            val view = toast.view
                            if (view != null) {

                                view.setBackgroundColor(Color.RED)
                            }
                            toast.show()
                            // Utiliser le message d'erreur comme vous le souhaitez
                        } catch (e: JSONException) {
                            Log.e("SignupActivity", "Erreur lors de l'analyse de la réponse JSON : $e")
                        }
                    }


                    // Gérer les erreurs de l'API ici
                    //Log.e("SignupActivity", "Erreur lors de l'inscription: " + error.message)
                })
            // Désactiver le réessai automatique
                request.retryPolicy = DefaultRetryPolicy(
                0, // Nombre maximal de tentatives (0 signifie aucune tentative de réessai)
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES, // Nombre maximal de réessais
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT // Facteur d'attente entre les réessais
                )
//            val status=request.body.get(0)
//            println(status)
            queue.add(request)

        }


    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId==R.id.loging){
            Intent(this,Authentification::class.java).also {
                startActivity(it)
            }

        }
        if (item.itemId==R.id.idsettings){
            Intent(this,Settings::class.java).also {
                startActivity(it)
            }
        }
        return super.onOptionsItemSelected(item)
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_signup,menu)
        return super.onCreateOptionsMenu(menu)
    }
}