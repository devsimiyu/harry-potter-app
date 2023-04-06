package com.devsimiyu.harrypotter.dao

import com.devsimiyu.harrypotter.model.Character
import com.devsimiyu.harrypotter.utils.RetrofitUtil

class CharacterDao {

    private val api = RetrofitUtil.getInstance()

    suspend fun fetchCharacters(): List<Character>? {
        val res = api.getCharacters()

        if (res.isSuccessful) return res.body()
        else throw Exception(res.message())
    }
}
