package com.devsimiyu.harrypotter.viewmodel

import androidx.lifecycle.*
import com.devsimiyu.harrypotter.dao.CharacterDao
import com.devsimiyu.harrypotter.model.CharacterState
import kotlinx.coroutines.flow.flow

class CharacterViewModel : ViewModel() {

    private val dao = CharacterDao()
    private val _search = MutableLiveData<String>()
    val searchResult: LiveData<CharacterState> = _search.switchMap {query ->
        liveData {
            emit(CharacterState.LOADING())
            try {
                val data = dao.fetchCharacters()
                val res = data?.filter { character ->
                    character.name.contains(query, true) || character.house.contains(query, true)
                }
                emit(CharacterState.SUCCESS(res))
            } catch (e: Exception) { emit(CharacterState.ERROR(e)) }
        }
    }

    fun fetchCharacters() = flow<CharacterState> {
        emit(CharacterState.LOADING())
        try {
            val data = dao.fetchCharacters()
            emit(CharacterState.SUCCESS(data))
        } catch (e: Exception) { emit(CharacterState.ERROR(e)) }

    }.asLiveData()

    fun searchCharacters(query: String) {
        _search.value = query
    }
}