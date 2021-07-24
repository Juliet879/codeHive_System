package com.example.studentregistration.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.studentregistration.Models.LoginRequest
import com.example.studentregistration.Models.LoginResponse
import com.example.studentregistration.R
import com.example.studentregistration.Services.ApiClient
import com.example.studentregistration.Services.ApiInterface
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    lateinit var etLoginEmail: EditText
    lateinit var etLoginPassword: EditText
    lateinit var btnLogin: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        etLoginEmail = findViewById(R.id.etLoginEmail)
        etLoginPassword = findViewById(R.id.etLoginPassword)

        loginStudent()
    }

    fun loginStudent() {
        btnLogin = findViewById(R.id.btnLogin)

        btnLogin.setOnClickListener {

            val nameIntent = intent.getStringExtra("name")

            val loggedEmail = etLoginEmail.text.toString()
            val loggedPass = etLoginPassword.text.toString()
            if (loggedPass.isEmpty() || loggedEmail.isEmpty()) {
                etLoginEmail.setError("Email Required")
                etLoginPassword.setError("Password Required")
            } else {


                val loginRequest = LoginRequest(
                    email = loggedEmail,
                    password = loggedPass
                )

                val retrofit = ApiClient.buildApiClient(ApiInterface::class.java)
                val request = retrofit.loginStudent(loginRequest)
                request.enqueue(object : Callback<LoginResponse?> {
                    override fun onResponse(call: Call<LoginResponse?>, response: Response<LoginResponse?>) {
                        if (response.isSuccessful) {
                            val intent = Intent(baseContext, CoursesActivity::class.java)
                            intent.putExtra("name", nameIntent)
                            startActivity(intent)
                            Toast.makeText(baseContext,"Student loggedin Successfully", Toast.LENGTH_LONG).show()
                        }
                        else{
                            try {
                                val error = JSONObject(response.errorBody()!!.string())
                                Toast.makeText(baseContext,error.toString(), Toast.LENGTH_LONG).show()

                            }catch (e:Exception){
                                Toast.makeText(baseContext,e.message, Toast.LENGTH_LONG).show()
                            }
                        }
                    }

                    override fun onFailure(call: Call<LoginResponse?>, t: Throwable) {
                        Toast.makeText(baseContext, t.message, Toast.LENGTH_LONG).show()
                    }
                })
            }
        }
    }
}