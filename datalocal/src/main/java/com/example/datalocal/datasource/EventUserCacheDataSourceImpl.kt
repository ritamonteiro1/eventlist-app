package com.example.datalocal.datasource

import android.content.Context
import androidx.core.content.edit
import com.example.datalocal.model.User

class EventUserCacheDataSourceImpl(context: Context) : EventUserCacheDataSource {

    private val preferences =
        context.getSharedPreferences(PREFERENCE_FILE_KEY, Context.MODE_PRIVATE)

    private companion object {
        const val PREFERENCE_FILE_KEY: String =
            "com.example.eventlist.PREFERENCE_FILE_KEY"
        const val NAME_KEY: String = "name"
        const val EMAIL_KEY: String = "email"
    }


    override fun saveUserEmail(email: String) {
        preferences.edit {
            putString(EMAIL_KEY, email)
        }
    }

    override fun saveUserName(name: String) {
        preferences.edit {
            putString(NAME_KEY, name)
        }
    }

    override fun getUserCredentials(): User {
        return preferences.let {
            User(
                it.getString(NAME_KEY, "") ?: "",
                it.getString(EMAIL_KEY, "") ?: ""
            )
        }
    }
}