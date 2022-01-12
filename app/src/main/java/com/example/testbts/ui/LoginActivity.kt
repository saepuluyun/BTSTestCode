package com.example.testbts.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.example.testbts.databinding.ActivityLoginBinding
import com.example.testbts.model.RegisterResponse
import com.example.testbts.model.entities.GenericResponse
import com.example.testbts.services.IViewService
import com.example.testbts.services.ServiceViewModel

class LoginActivity : BaseActivity(), IViewService {

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
            val presenter = ServiceViewModel(binding.root.context, iView)
            presenter.login(username, password)
        }
    }

    override fun onSuccessRegister(data: GenericResponse<RegisterResponse>) {
        Toast.makeText(this, "Login Berhasil", Toast.LENGTH_SHORT).show()
    }

    override fun onError(message: String?) {
        Toast.makeText(this, "Login Gagal", Toast.LENGTH_SHORT).show()
    }
}