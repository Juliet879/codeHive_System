package com.example.studentregistration.ui

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.activity.viewModels
import com.example.studentregistration.Constants
import com.example.studentregistration.models.RegistrationRequest
import com.example.studentregistration.databinding.ActivityMainBinding
import com.example.studentregistration.viewmodel.RegisterViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    val registerViewModel: RegisterViewModel by viewModels()
    var error = false
    lateinit var prefs: SharedPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        prefs = getSharedPreferences(Constants.SHAREDPREFS, Context.MODE_PRIVATE)
        redirectStudent()

        val nationality = arrayOf("Kenyan", "Ugandan", "Rwandan", "South Sudan")
        val nationalityAdapter =
            ArrayAdapter(baseContext, android.R.layout.simple_spinner_item, nationality)
        nationalityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spNationality.adapter = nationalityAdapter


        binding.tvLogin.setOnClickListener {
            val intent = Intent(baseContext, LoginActivity::class.java)
            startActivity(intent)
        }

    }

    private fun redirectStudent() {
        var accessToken = prefs.getString(Constants.SHAREDPREFS, Constants.EMPTYSTRING)
        if (accessToken!!.isNotEmpty()) {
            startActivity(Intent(baseContext, CoursesActivity::class.java))
        } else {
            startActivity(Intent(baseContext, LoginActivity::class.java))
        }
    }

    override fun onResume() {
        super.onResume()
        binding.btnRegister.setOnClickListener {
            validate()
        }

        registerViewModel.registrationLiveData.observe(this, { regResponse ->
            if (!regResponse.studentId.isNullOrEmpty()) {
               Toast.makeText(baseContext, "Registration successful", Toast.LENGTH_LONG).show()
                val intent = Intent(baseContext, LoginActivity::class.java)
                startActivity(intent)
            }
        })
        registerViewModel.registrationFailedLiveData.observe(this, { str ->
            Toast.makeText(baseContext, str, Toast.LENGTH_LONG).show()
        })

    }

    fun validate() {
        if (binding.etEmail.text.toString().isEmpty() || binding.etDob.text.toString().isEmpty()) {
            error = true
            binding.etEmail.error = "Email required"
            binding.etDob.error = "Date of Birth required"
        } else {

            val registrationRequest = RegistrationRequest(
                name = binding.etName.text.toString(),
                phoneNumber = binding.etPhoneNumber.text.toString(),
                nationality = binding.spNationality.selectedItem.toString().uppercase(),
                dateOfBirth = binding.etDob.text.toString(),
                password = binding.etPassword.text.toString(),
                email = binding.etEmail.text.toString()
            )
            registerViewModel.registerUser(registrationRequest)

        }

    }
}












//spinner code for nationality
//Validating views
// Instantiating the registerUser function from the viewmodel in the activity
