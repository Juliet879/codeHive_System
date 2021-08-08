package com.example.studentregistration.ui

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.example.studentregistration.api.SessionManager
import com.example.studentregistration.models.LoginRequest
import com.example.studentregistration.databinding.ActivityLoginBinding
import com.example.studentregistration.viewmodel.LoginViewModel

class LoginActivity : AppCompatActivity() {
    lateinit var binding:ActivityLoginBinding
    val loginViewModel:LoginViewModel by viewModels()
    lateinit var sharedPreferences: SharedPreferences
    lateinit var sessionManager:SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }

    override fun onResume() {
        super.onResume()
        binding.btnLogin.setOnClickListener {
            sessionManager = SessionManager(this)

            if (binding.etLoginEmail.text.toString().isEmpty() || binding.etLoginPassword.text.toString().isEmpty()) {
                binding.etLoginEmail.setError("Email Required")
                binding.etLoginPassword.setError("Password Required")
            }
            else{
                val loginRequest = LoginRequest(
                    email = binding.etLoginEmail.text.toString(),
                    password = binding.etLoginPassword.text.toString()
                )
                loginViewModel.loginStudent(loginRequest)
            }
        }
        loginViewModel.loginLiveData.observe(this,{loginResponse ->
            if (!loginResponse.studentId.isEmpty()){
                Toast.makeText(baseContext,"Login Successfully",Toast.LENGTH_LONG).show()
                sessionManager.saveAccessToken(loginResponse.accessToken)
                val intent = Intent(baseContext,CoursesActivity::class.java)
                startActivity(intent)
            }
        })

        loginViewModel.loginFailedLiveData.observe(this, {error ->
            Toast.makeText(baseContext,error,Toast.LENGTH_LONG).show()
        })
    }
}