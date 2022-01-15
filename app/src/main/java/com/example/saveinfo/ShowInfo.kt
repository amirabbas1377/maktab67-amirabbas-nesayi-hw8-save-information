package com.example.saveinfo

import android.content.Context.MODE_PRIVATE
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import android.content.SharedPreferences
import android.widget.Button

public class ShowInfo:Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.information, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getPrefValue(view)
        removeFragment(view)
    }

    fun getPrefValue(view: View){
        val prefs = requireActivity().getSharedPreferences("information", MODE_PRIVATE)
        val fullNameText:TextView = view.findViewById(R.id.full_name_info)
        val usernameText:TextView = view.findViewById(R.id.user_name_info)
        val emailText:TextView = view.findViewById(R.id.email_info)
        val passwordText:TextView = view.findViewById(R.id.password_info)
        val genderText:TextView = view.findViewById(R.id.gender_info)

        fullNameText.text = prefs.getString("FullName", "Full name")
        usernameText.text = prefs.getString("Username", "Username")
        emailText.text = prefs.getString("Email", "Email")
        passwordText.text = prefs.getString("Password", "Password")
        genderText.text = prefs.getString("Gender", "Gender")
    }

    fun removeFragment(view: View){
        val hideButton:Button = view.findViewById(R.id.hide_info_button)
        hideButton.setOnClickListener {
            activity?.supportFragmentManager?.popBackStack()
        }
    }
}