package com.example.testbts.ui.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.example.testbts.databinding.ActivityLoginBinding
import com.example.testbts.model.GenericResponse
import com.example.testbts.model.entities.LoginResponse
import com.example.testbts.services.login.IViewLogin
import com.example.testbts.services.login.LoginViewModel
import com.example.testbts.ui.BaseActivity
import com.example.testbts.ui.ceklist.CheckListActivity
import com.example.testbts.ui.register.RegisterActivity
import com.example.testbts.utils.Session

class LoginActivity : BaseActivity(), IViewLogin {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var session: Session

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        session = Session(this)

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
        session.createSession(data.data?.token)
        Toast.makeText(this, "Login Berhasil", Toast.LENGTH_SHORT).show()
        val intent = Intent(this, CheckListActivity::class.java)
        startActivity(intent)
    }

    override fun onError(message: String?) {
        Toast.makeText(this, "Login Gagal", Toast.LENGTH_SHORT).show()
    }
}