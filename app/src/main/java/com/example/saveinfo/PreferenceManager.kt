package com.example.saveinfo

import android.content.Context
import android.content.SharedPreferences
import android.view.View

class PreferenceManager private constructor(){

    companion object{
        private var instance:PreferenceManager? = null
        fun getInstanceFromPre(context: Context):PreferenceManager?{
            if (instance == null) instance = PreferenceManager()
            return instance
        }
    }

    var sharedPreferences:SharedPreferences? = null
    var editor:SharedPreferences.Editor? = null
    private constructor(context: Context) : this() {
        sharedPreferences = context.getSharedPreferences("information", Context.MODE_PRIVATE)
        editor = sharedPreferences?.edit()
    }
    
    fun getInfo():MutableMap<String, String>{
        var info = mutableMapOf<String, String>()
        info["FullName"] = sharedPreferences?.getString("FullName", "Full name").toString()
        info["Username"] = sharedPreferences?.getString("Username", "Username").toString()
        info["Email"] = sharedPreferences?.getString("Email", "Email").toString()
        info["Password"] = sharedPreferences?.getString("Password", "Password").toString()
        info["Gender"] = sharedPreferences?.getString("Gender", "Gender").toString()
        return info
    }
    
    fun setInfo(info:Map<String,String>){
        for (i in info){
            editor?.putString(i.key, i.value)
            editor?.apply()
        }
    }
}