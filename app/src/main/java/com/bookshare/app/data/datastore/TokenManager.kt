package com.bookshare.app.data.datastore

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


private val Context.dataStore by preferencesDataStore(name = "bookshare_prefs")

class TokenManager(private val context: Context) {

    companion object {
        private val ACCESS_TOKEN =stringPreferencesKey("access_token")
        private val REFRESH_TOKEN =stringPreferencesKey("refresh_token")
    }

    suspend fun saveTokens(accessToken: String, refreshToken: String) {

        context.dataStore.edit { prefs ->
            prefs[ACCESS_TOKEN] =accessToken
            prefs[REFRESH_TOKEN] =refreshToken
        }
    }

    val accessToken: Flow<String?>

        get() = context.dataStore.data.map { it[ACCESS_TOKEN] }

    val refreshToken: Flow<String?>

        get() = context.dataStore.data.map { it[REFRESH_TOKEN] }

    suspend fun clearTokens() {

        context.dataStore.edit { it.clear() }
    }
}