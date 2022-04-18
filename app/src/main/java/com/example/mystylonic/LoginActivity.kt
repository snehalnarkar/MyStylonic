package com.example.mystylonic

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var emailTIL: TextInputLayout
    private lateinit var passwordTIL: TextInputLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

      auth = FirebaseAuth.getInstance()

        emailTIL = findViewById(R.id.login_email)
        passwordTIL = findViewById(R.id.login_password)


       findViewById<TextView>(R.id.login_forgot_password_tv).setOnClickListener { userForgotPassword() }

    }
   //Checking if user is logged in already or not
    override fun onStart() {
        super.onStart()
       val user = auth.currentUser
        if(user != null && user.isEmailVerified) {
           startActivity(Intent(this, MainActivity::class.java))
           finish()
       }
    }

    private fun validateEmail(): Boolean {
        val email = emailTIL.editText!!.text.toString().trim()
        if(email.isEmpty()) {
            emailTIL.error = getString(R.string.field_empty)
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

        val pass = passwordTIL.editText!!.text.toString().trim()

        if(pass.isEmpty()) {
            passwordTIL.error = getString(R.string.field_empty)
            return false
        }

        if(pass.length < 6) {
            passwordTIL.error = "Password is too short (Min. 6 Characters)"
            return false
        }

        passwordTIL.error = null
        return true


    }
    fun loginUser(view: View) {
        if (!validateEmail() or !validatePassword())
            return

        findViewById<ProgressBar>(R.id.login_progress_bar).visibility =ViewGroup.VISIBLE

        auth.signInWithEmailAndPassword(emailTIL.editText!!.text.toString(), passwordTIL.editText!!.text.toString())
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }.addOnFailureListener { exception ->
                Toast.makeText(applicationContext, exception.localizedMessage, Toast.LENGTH_SHORT).show()
            }
    }

        fun openRegisterActivity(view: View)
        {
            startActivity(Intent(this, RegisterActivity::class.java))
            finish()
        }

        private fun userForgotPassword() {
            val forgotDialog = ForgotPassword()
          forgotDialog.show(supportFragmentManager, "Forgot Password Dialog")
        }

}


