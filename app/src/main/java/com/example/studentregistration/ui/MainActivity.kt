package com.example.studentregistration.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.activity.viewModels
import com.example.studentregistration.models.RegistrationRequest
import com.example.studentregistration.databinding.ActivityMainBinding
import com.example.studentregistration.viewmodel.RegisterViewModel

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    val registerViewModel: RegisterViewModel by viewModels()
    var error = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val nationality = arrayOf("Kenyan", "Ugandan", "Rwandan", "South Sudan")
        val nationalityAdapter =
            ArrayAdapter(baseContext, android.R.layout.simple_spinner_item, nationality)
        nationalityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spNationality.adapter = nationalityAdapter


        binding.tvLogin.setOnClickListener {
            val intent = Intent(baseContext,LoginActivity::class.java)
            startActivity(intent)
        }

    }
    override fun onResume() {
        super.onResume()
        binding.btnRegister.setOnClickListener {

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
                val intent = Intent(baseContext,LoginActivity::class.java)
                startActivity(intent)
        }
    }
        registerViewModel.registrationLiveData.observe(this, { regResponse->
            if (!regResponse.studentId.isEmpty()){
                Toast.makeText(baseContext, "Registration successful", Toast.LENGTH_LONG).show()
            }
        })
        registerViewModel.registrationFailedLiveData.observe(this,  { str ->
                Toast.makeText(baseContext, str, Toast.LENGTH_LONG).show()
            })

    }
}












//spinner code for nationality
//Validating views
// Instantiating the registerUser function from the viewmodel in the activity
