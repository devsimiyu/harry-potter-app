package com.devsimiyu.harrypotter.model

import com.devsimiyu.harrypotter.model.Character

sealed class CharacterState(
    val status: CharacterStatus,
    val data: List<Character>? = null,
    val error: Exception? = null
) {
    class SUCCESS(data: List<Character>?): CharacterState(CharacterStatus.SUCCESS, data)
    class ERROR(error: Exception): CharacterState(CharacterStatus.ERROR, error = error)
    class LOADING: CharacterState(CharacterStatus.LOADING)
}

enum class CharacterStatus {
    LOADING,
    SUCCESS,
    ERROR
}