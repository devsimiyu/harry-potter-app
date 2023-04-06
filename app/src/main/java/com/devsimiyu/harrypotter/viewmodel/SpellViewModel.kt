package com.devsimiyu.harrypotter.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.devsimiyu.harrypotter.dao.SpellDao
import com.devsimiyu.harrypotter.model.SpellState
import kotlinx.coroutines.flow.flow

class SpellViewModel : ViewModel() {

    private val dao = SpellDao()

    fun fetchSpells() = flow<SpellState> {
        emit(SpellState.LOADING())

        try {
            val data = dao.fetchSpells()
            emit(SpellState.SUCCESS(data))

        } catch (e: Exception) {
            emit(SpellState.ERROR(e))
        }

    }.asLiveData()
}