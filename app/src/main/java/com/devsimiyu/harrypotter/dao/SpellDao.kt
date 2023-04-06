package com.devsimiyu.harrypotter.dao

import com.devsimiyu.harrypotter.model.Spell
import com.devsimiyu.harrypotter.utils.RetrofitUtil

class SpellDao {

    private val api = RetrofitUtil.getInstance()

    suspend fun fetchSpells(): List<Spell>? {
        val res = api.getSpells()

        if (res.isSuccessful) return res.body()
        else throw Exception(res.message())
    }
}
