package com.example.studentregistration.ui

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import com.example.studentregistration.Models.RegistrationRequest
import com.example.studentregistration.Models.RegistrationResponse
import com.example.studentregistration.R
import com.example.studentregistration.Services.ApiClient
import com.example.studentregistration.Services.ApiInterface
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.Tag
import java.util.*

class MainActivity : AppCompatActivity() {
    lateinit var etName: EditText
    lateinit var etDob: EditText
    lateinit var spNationality: Spinner
    lateinit var etPhoneNumber: EditText
    lateinit var etEmail: EditText
    lateinit var btnRegister: Button
    lateinit var etPassword: EditText
    lateinit var tvLogin :TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        castViews()
        clickRegister()
        clickLogin()
    }

    fun castViews() {
        etName = findViewById(R.id.etName)
        etDob = findViewById(R.id.etDob)
        spNationality = findViewById(R.id.spNationality)
        etEmail = findViewById(R.id.etEmail)
        etPhoneNumber = findViewById(R.id.etPhoneNumber)
        etPassword = findViewById(R.id.etPassword)
        tvLogin = findViewById(R.id.tvLogin)

        val nationality = arrayOf("Kenyan", "Ugandan", "Rwandan", "South Sudan")
        val nationalityAdapter = ArrayAdapter(baseContext, android.R.layout.simple_spinner_item, nationality)
        nationalityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spNationality.adapter = nationalityAdapter
    }

    fun clickRegister() {
        btnRegister = findViewById(R.id.btnRegister)

        btnRegister.setOnClickListener {
            val name = etName.text.toString()
            val email = etEmail.text.toString()
            val password = etPassword.text.toString()
            val country = spNationality.selectedItem.toString()
            val dob = etDob.text.toString()
            val phone = etPhoneNumber.text.toString()
            if (email.isEmpty() || password.isEmpty()){
//                etDob.error = "Date of birth is required"
                etEmail.error = "Email required"
                etPassword.error = "ID Number required"
//                etPhoneNumber.error = "Input phone number"
//                etPhoneNumber.error = "Input phone number"
            }

             val registrationRequest = RegistrationRequest(
                 name=name,
                 phoneNumber = phone,
                 nationality = country.uppercase(),
                 dateOfBirth = dob,
                 password = password,
                 email = email
             )
            val retrofit = ApiClient.buildApiClient(ApiInterface::class.java)
            val request = retrofit.registerStudent(registrationRequest)
            request.enqueue(object : Callback<RegistrationResponse?> {
                override fun onResponse(call: Call<RegistrationResponse?>, response: Response<RegistrationResponse?>) {
                    if (response.isSuccessful){
                        val intent = Intent(baseContext,LoginActivity::class.java)
                        startActivity(intent)
                        Toast.makeText(baseContext,"Student added Successfully",Toast.LENGTH_LONG).show()
                    }
                    else{
                        try {
                            val error = JSONObject(response.errorBody()!!.string())
                            Toast.makeText(baseContext,error.toString(),Toast.LENGTH_LONG).show()

                        }catch (e:Exception){
                            Toast.makeText(baseContext,e.message,Toast.LENGTH_LONG).show()
                        }
                    }
                }

                override fun onFailure(call: Call<RegistrationResponse?>, t: Throwable) {
                    Toast.makeText(baseContext,t.message,Toast.LENGTH_LONG).show()
                }
            })

        }

    }
    fun clickLogin(){
        tvLogin.setOnClickListener {
            val intent = Intent(baseContext,LoginActivity::class.java)
            startActivity(intent)
        }
    }
}
//data class ApiError(var errrs:List<String>)
