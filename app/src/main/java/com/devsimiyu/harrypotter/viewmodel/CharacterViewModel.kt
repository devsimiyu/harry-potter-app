package com.devsimiyu.harrypotter.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.devsimiyu.harrypotter.dao.CharacterDao
import com.devsimiyu.harrypotter.model.CharacterState
import kotlinx.coroutines.flow.flow

class CharacterViewModel : ViewModel() {

    private val dao = CharacterDao()

    fun fetchCharacters() = flow<CharacterState> {
        emit(CharacterState.LOADING())

        try {
            val data = dao.fetchCharacters()
            emit(CharacterState.SUCCESS(data))

        } catch (e: Exception) {
            emit(CharacterState.ERROR(e))
        }

    }.asLiveData()
}