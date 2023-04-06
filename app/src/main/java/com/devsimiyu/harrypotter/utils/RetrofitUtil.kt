package com.devsimiyu.harrypotter.utils

import com.devsimiyu.harrypotter.model.Character
import com.devsimiyu.harrypotter.model.Spell
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

class RetrofitUtil {
    companion object {

        private var instance : ApiService? = null

        @Synchronized
        fun getInstance(): ApiService {
            if (instance == null) {
                instance = Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl("https://hp-api.onrender.com/api/")
                    .build()
                    .create(ApiService::class.java)
            }
            return instance as ApiService
        }
    }
}

interface ApiService {

    @GET("characters")
    suspend fun getCharacters(): Response<List<Character>>

    @GET("spells")
    suspend fun getSpells(): Response<List<Spell>>
}
