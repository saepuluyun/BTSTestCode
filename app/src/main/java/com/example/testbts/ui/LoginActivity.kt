package com.example.testbts.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.example.testbts.databinding.ActivityLoginBinding
import com.example.testbts.model.entities.RegisterResponse
import com.example.testbts.model.GenericResponse
import com.example.testbts.model.entities.LoginResponse
import com.example.testbts.services.login.IViewLogin
import com.example.testbts.services.login.LoginViewModel
import com.example.testbts.services.register.IViewRegister
import com.example.testbts.services.register.RegisterViewModel

class LoginActivity : BaseActivity(), IViewLogin {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        binding.btnSubmit.setOnClickListener {
            val username = binding.etUsername.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()

            // Call Api
            val iView = this
            val presenter = LoginViewModel(binding.root.context, iView)
            presenter.login(username, password)
        }
    }

    override fun onSuccessLogin(data: GenericResponse<LoginResponse>) {
        Toast.makeText(this, "Login Berhasil", Toast.LENGTH_SHORT).show()
    }

    override fun onError(message: String?) {
        Toast.makeText(this, "Login Gagal", Toast.LENGTH_SHORT).show()
    }
}