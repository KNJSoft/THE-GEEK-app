package com.knjsoft.the_geek

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.android.volley.DefaultRetryPolicy
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import org.json.JSONException
import org.json.JSONObject

class Authentification : AppCompatActivity() {

    lateinit var Loging: MaterialButton
    lateinit var Username:TextInputEditText
    lateinit var Password: TextInputEditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_authentification)
        val sharedPreferences = getSharedPreferences("THE-GEEK", Context.MODE_PRIVATE)
        val isLoggedIn = sharedPreferences.getBoolean("is_authenticated", false)
        Loging=findViewById(R.id.btn_auth)
        Username=findViewById(R.id.username_auth)
        Password=findViewById(R.id.Password_auth)


        //debut send post request

        Loging.setOnClickListener {
            val username=Username.text.toString().trim()
            val password=Password.text.toString().trim()
            val jsonData = JSONObject()
            jsonData.put("username", username)
            jsonData.put("password", password)
            // Créer une requête POST vers l'API Django
            val queue = Volley.newRequestQueue(this)
            val url = "http://192.168.1.106:8000/api/post/signin" // Remplacer par l'URL de votre API Django
            val request = JsonObjectRequest(
                Request.Method.POST, url, jsonData,
                { response ->
                    val editor = sharedPreferences.edit()
                    editor.putBoolean("is_authenticated", true)
                    editor.apply()
                    val successMessage = response.getString("msg")
                    Log.d("SignupActivity", "connexion réussie  django: $successMessage")
                    Log.d("SignupActivity", "connexion réussie!")
                    val toast = Toast.makeText(this, successMessage, Toast.LENGTH_SHORT*5)
                    toast.setGravity(Gravity.TOP or Gravity.CENTER, 0, resources.getDimensionPixelOffset(R.dimen.toast_margin_top))
                    val view = toast.view
                    if (view != null) {
                        view.setBackgroundColor(Color.GREEN)
                    }
                    toast.show()
                    Intent(this,MainActivity::class.java).also {
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
                            Log.e("SignupActivity", "Erreur lors de la connexion djannnnnngo: $errorMessage")
                            val toast = Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT*5)
                            toast.setGravity(Gravity.TOP or Gravity.CENTER, 0, resources.getDimensionPixelOffset(R.dimen.toast_margin_top))
                            val view = toast.view
                            if (view != null) {

                                view.setBackgroundColor(Color.RED)
                            }
                            toast.show()
                            // Utiliser le message d'erreur comme vous le souhaitez
                        } catch (e: JSONException) {
                            //debut except
                            val toast = Toast.makeText(this, "La connexion au serveur a échouer!!!", Toast.LENGTH_SHORT*5)
                            toast.setGravity(Gravity.TOP or Gravity.CENTER, 0, resources.getDimensionPixelOffset(R.dimen.toast_margin_top))
                            val view = toast.view
                            if (view != null) {

                                view.setBackgroundColor(Color.RED)
                            }
                            toast.show()
                            //fin
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
        //end
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId==R.id.signup){
            Intent(this,Inscription::class.java).also {
                startActivity(it)
            }
            finish()

        }
        if (item.itemId==R.id.idsettings_log){
            Intent(this,Settings::class.java).also {
                startActivity(it)
            }

        }
        return super.onOptionsItemSelected(item)
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_signin,menu)
        return super.onCreateOptionsMenu(menu)
    }
}