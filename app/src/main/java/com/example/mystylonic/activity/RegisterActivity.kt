package com.example.mystylonic.activity

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Button
import com.example.mystylonic.R
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth

class RegisterActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var emailTIL: TextInputLayout
    private lateinit var createPasswordTIL: TextInputLayout
    private lateinit var confirmPasswordTIL: TextInputLayout
    private lateinit var registerBtn: Button

    private lateinit var registerProgressDialog: ProgressDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        auth = FirebaseAuth.getInstance()
        emailTIL = findViewById(R.id.register_email_til)
        createPasswordTIL = findViewById(R.id.register_create_password_til)
        confirmPasswordTIL = findViewById(R.id.register_confirm_password_til)
        registerBtn = findViewById(R.id.register_emp_btn)

        registerProgressDialog = ProgressDialog(this)
        registerBtn.setOnClickListener { registerUser() }
    }

    private fun validateEmail(): Boolean {
        val email = emailTIL.editText!!.text.toString().trim()              //coverting string format
        if(email.isEmpty()) {
            emailTIL.error = getString(R.string.field_empty)                        //msg pass
            return false
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailTIL.error = getString(R.string.invalid_email)
            return false
        }
        emailTIL.error = null
        return true
    }
    private fun validatePassword(): Boolean {
        createPasswordTIL.error = null
        confirmPasswordTIL.error = null

        val createPass = createPasswordTIL.editText!!.text.toString().trim()
        val confirmPass = confirmPasswordTIL.editText!!.text.toString().trim()

        if(createPass.isEmpty()) { createPasswordTIL.error = getString(R.string.field_empty) }
        if(confirmPass.isEmpty()) { confirmPasswordTIL.error = getString(R.string.field_empty) }
        if(createPass.isEmpty() || confirmPass.isEmpty()) return false

        if(createPass.length < 6) {
            createPasswordTIL.error = "Password is too short (Min. 6 Characters)"
            return false
        }
        if(createPass != confirmPass) {
            confirmPasswordTIL.error = "Password don't match"
            return false
        }
        createPasswordTIL.error = null
        confirmPasswordTIL.error = null
        return true
    }
    private fun registerUser() {
        if (!validateEmail() or !validatePassword())
            return

        val email = emailTIL.editText!!.text.toString()
        val password = confirmPasswordTIL.editText!!.text.toString()

        registerProgressDialog.setTitle("Registering...")
        registerProgressDialog.setMessage("We are creating your account")
        registerProgressDialog.show()

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
    }
    fun openLoginActivity(view: View) {
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }

    }

