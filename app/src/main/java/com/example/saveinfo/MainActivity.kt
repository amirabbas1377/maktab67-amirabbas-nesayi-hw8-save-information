package com.example.saveinfo

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getInfoFromUser()
    }

    private fun getInfoFromUser(){
        val fullNameEditText:EditText = findViewById(R.id.full_name_editor)
        val usernameEditText:EditText = findViewById(R.id.user_name_editor)
        val emailEditText:EditText = findViewById(R.id.email_editor)
        val passwordEditText:EditText = findViewById(R.id.password_editor)
        val secondPasswordEditText:EditText = findViewById(R.id.re_password_editor)
        val registerButton:Button = findViewById(R.id.register_button)
        val showInfoButton:Button = findViewById(R.id.show_info_button)

        var validPassword = false

        registerButton.setOnClickListener {
            var fullName = fullNameEditText.text.toString()
            var username = usernameEditText.text.toString()
            var email = emailEditText.text.toString()
            var password = passwordEditText.text.toString()
            var secondPassword = secondPasswordEditText.text.toString()

            val radioGroup: RadioGroup = findViewById(R.id.radio_group)
            var selectGender = radioGroup.checkedRadioButtonId
            val selectedRadio: RadioButton = findViewById(selectGender)
            val gender = selectedRadio.text as String

            var info = mapOf("FullName" to fullName, "Username" to username
                , "Email" to email, "Password" to password, "Gender" to gender)

            validPassword = true
            if (password != secondPassword) validPassword = false
            if (validPassword) setPrefValues(info)
        }

        showInfoButton.setOnClickListener {
            if (validPassword) showInfo()
            else Toast.makeText(this, "invalid password", Toast.LENGTH_LONG).show()
        }

    }

    fun showInfo(){
        val fragment = ShowInfo()
        val manager = supportFragmentManager
        val transaction = manager.beginTransaction()
        transaction.add(R.id.fragment_container, fragment).addToBackStack(null)
            .commitAllowingStateLoss()
    }

    fun setPrefValues(info: Map<String, String>){
        val prefs = getSharedPreferences("information", MODE_PRIVATE)
        val editor:SharedPreferences.Editor  = prefs.edit()
        for(i in info){
            editor.putString(i.key, i.value)
            editor.apply()
        }
    }
}