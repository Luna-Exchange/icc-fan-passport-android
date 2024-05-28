package com.insomnia.fanpassport

import android.content.Context
import android.content.Context.MODE_PRIVATE
import com.google.gson.Gson


class SharedPrefProvider(private val context: Context) {

    val gson = Gson()
    private fun readString(settingName: String, defaultValue: String?): String? {
        val sharedPref = context.getSharedPreferences(PREFERENCES_FILE, MODE_PRIVATE)
        return sharedPref.getString(settingName, defaultValue)
    }

    private fun saveString(settingName: String, settingValue: String?) {
        val sharedPref = context.getSharedPreferences(PREFERENCES_FILE, MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putString(settingName, settingValue)
        editor.apply()
    }

    fun saveAccessToken(token: String) {
        saveString(TOKEN, token)
    }

    fun getAccessToken(): String {
        return readString(TOKEN, "") ?: ""
    }

    fun saveUser(user: User?) {
        saveString(USER, gson.toJson(user))
    }

    fun getUser(): User? {
        val userString = readString(USER, "")
        return gson.fromJson(userString, User::class.java)
    }




    companion object {
        const val PREFERENCES_FILE = "pref_file"
        const val TOKEN = "access_token"
        const val USER = "user_storage"



    }
}