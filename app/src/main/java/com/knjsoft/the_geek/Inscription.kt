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
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import com.android.volley.*
import com.android.volley.toolbox.JsonObjectRequest
import org.json.JSONException
import retrofit2.http.Body
import retrofit2.http.POST

import java.io.IOException
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
class Inscription : AppCompatActivity() {






    object RetrofitClient {

        private const val BASE_URL = "http://192.168.1.106:8000"

        private val retrofit: Retrofit by lazy {
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        fun getSignupService(): SignupService {
            return retrofit.create(SignupService::class.java)
        }
    }

    interface SignupService {

        @POST("api/post/signup")
        fun signup(
            @Body signupRequest: SignupRequest
        ): Call<SignupResponse>
    }

    data class SignupRequest(
        val username: String,
        val email: String,
        val password: String,
        val cpassword: String
    )

    data class SignupResponse(
        val success: Boolean,
        val message: String
    )

    lateinit var signup: MaterialButton
    lateinit var Username:TextInputEditText
    lateinit var Email:TextInputEditText
    lateinit var Password:TextInputEditText
    lateinit var Cpassword:TextInputEditText
    lateinit var progress: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inscription)
        signup=findViewById(R.id.btn_ins)
        Username=findViewById(R.id.username)
        Email=findViewById(R.id.Mail)
        Password=findViewById(R.id.Password)
        Cpassword=findViewById(R.id.confirpassword)
        progress=findViewById(R.id.progress)
        signup.setOnClickListener {

            val username=Username.text.toString().trim()
            val email=Email.text.toString().trim()
            val password=Password.text.toString().trim()
            val cpassword=Cpassword.text.toString().trim()
            progress.setVisibility(View.VISIBLE)
            signup.isEnabled=false
            Username.isEnabled=false
            Email.isEnabled=false
            Password.isEnabled=false
            Cpassword.isEnabled=false
            val retrofitClient = RetrofitClient
            val signupService = retrofitClient.getSignupService()
            val signupRequest = SignupRequest(
                username,
                email,
                password,
                cpassword,
            )
            val call = signupService.signup(signupRequest)
            call.enqueue(object : Callback<SignupResponse> {
                override fun onResponse(call: Call<SignupResponse>, response: Response<SignupResponse>) {
                    if (response.isSuccessful) {
                        val signupResponse =
                            response.body() ?: throw IllegalStateException("Response body is null")

                        if (signupResponse.success) {
                            signup.isEnabled = true
                            Username.isEnabled = true
                            Email.isEnabled = true
                            Password.isEnabled = true
                            Cpassword.isEnabled = true
                            progress.setVisibility(View.GONE)
                            Log.d("SignupActivity", "Inscription réussie  django")
                            Log.d("SignupActivity", "Inscription réussie!")
                            val toast = Toast.makeText(
                                applicationContext,
                                signupResponse.message,
                                Toast.LENGTH_SHORT * 5
                            )
                            toast.setGravity(
                                Gravity.TOP or Gravity.CENTER,
                                0,
                                resources.getDimensionPixelOffset(R.dimen.toast_margin_top)
                            )
                            val view = toast.view
                            if (view != null) {
                                view.setBackgroundColor(Color.GREEN)
                            }
                            toast.show()

                            Intent(applicationContext, Authentification::class.java).also {
                                startActivity(it)
                            }
                            finish()
                        } else {
                            //val errorMessage = response.errorBody()?.let { JSONObject["error"] as String } ?: "Erreur inconnue."
                            signup.isEnabled = true
                            Username.isEnabled = true
                            Email.isEnabled = true
                            Password.isEnabled = true
                            Cpassword.isEnabled = true
                            progress.setVisibility(View.GONE)

                            Log.e("SignupActivity", "djannnnnngo: ")
                            val toast = Toast.makeText(
                                applicationContext,
                                "erreur inconnue!!!!!!",
                                Toast.LENGTH_SHORT * 5
                            )
                            toast.setGravity(
                                Gravity.TOP or Gravity.CENTER,
                                0,
                                resources.getDimensionPixelOffset(R.dimen.toast_margin_top)
                            )
                            val view = toast.view
                            if (view != null) {

                                view.setBackgroundColor(Color.RED)
                            }
                            toast.show()
                        }
                    } else {
                        val errorMessage = response.errorBody()?.string()?.let { JSONObject(it)["error"] as String } ?: "Erreur inconnue."
                        println(errorMessage)
                            signup.isEnabled = true
                            Username.isEnabled = true
                            Email.isEnabled = true
                            Password.isEnabled = true
                            Cpassword.isEnabled = true
                            progress.setVisibility(View.GONE)

                            Log.e("SignupActivity", "Erreur lors de l'inscription djannnnnngo: ")
                            val toast = Toast.makeText(
                                applicationContext,
                                errorMessage,
                                Toast.LENGTH_SHORT * 5
                            )
                            toast.setGravity(
                                Gravity.TOP or Gravity.CENTER,
                                0,
                                resources.getDimensionPixelOffset(R.dimen.toast_margin_top)
                            )
                            val view = toast.view
                            if (view != null) {

                                view.setBackgroundColor(Color.RED)
                            }
                            toast.show()
                    }

                }
                override fun onFailure(call: Call<SignupResponse>, t: Throwable) {
                    signup.isEnabled = true
                    Username.isEnabled = true
                    Email.isEnabled = true
                    Password.isEnabled = true
                    Cpassword.isEnabled = true
                    progress.setVisibility(View.GONE)

                    Log.e("SignupActivity", "Erreur lors de l'inscription djannnnnngo: ")
                    val toast = Toast.makeText(
                        applicationContext,
                        "La connexion au serveur a échouée!!!!!!",
                        Toast.LENGTH_SHORT * 5
                    )
                    toast.setGravity(
                        Gravity.TOP or Gravity.CENTER,
                        0,
                        resources.getDimensionPixelOffset(R.dimen.toast_margin_top)
                    )
                    val view = toast.view
                    if (view != null) {

                        view.setBackgroundColor(Color.RED)
                    }
                    toast.show()
                }
            })



        }
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId==R.id.loging){
            Intent(this,Authentification::class.java).also {
                startActivity(it)
            }
            finish()

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