package com.example.testbts.ui.register

import android.os.Bundle
import android.widget.Toast
import com.example.testbts.databinding.ActivityRegisterBinding
import com.example.testbts.model.entities.RegisterResponse
import com.example.testbts.model.GenericResponse
import com.example.testbts.services.register.IViewRegister
import com.example.testbts.services.register.RegisterViewModel
import com.example.testbts.ui.BaseActivity
import kotlinx.android.synthetic.main.activity_login.*

class RegisterActivity : BaseActivity(), IViewRegister {

    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)


        btnSubmit.setOnClickListener {
            val username = binding.etUsername.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()
            val email = binding.etUsername.text.toString().trim()

            // Call Api
            val iView = this
            val presenter = RegisterViewModel(binding.root.context, iView)
            presenter.register(username, email, password)
        }
    }

    override fun onSuccessRegister(data: GenericResponse<RegisterResponse>) {
        Toast.makeText(this, "Berhasil mendaftar", Toast.LENGTH_SHORT).show()
        onBackPressed()
    }

    override fun onError(message: String?) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}