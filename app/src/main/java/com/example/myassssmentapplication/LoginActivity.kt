package com.example.myassssmentapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.myassssmentapplication.databinding.ActivityLoginBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val loginViewModel: LoginViewModel by viewModels()

    // 🔁 Change this to "footscray", "ort", etc. based on your class
    private val classLocation = "sydney"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener {
            val username = binding.etUsername.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()

            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please enter both fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            loginViewModel.login(username, password, classLocation)
        }

        loginViewModel.loginResult.observe(this, Observer { result ->
            if (result.isSuccess) {
                val keypass = result.getOrNull()
                val intent = Intent(this, DashboardActivity::class.java)
                intent.putExtra("keypass", keypass)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Login failed: ${result.exceptionOrNull()?.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}

