package com.devsimiyu.harrypotter.model

sealed class SpellState(
    val status: SpellStatus,
    val data: List<Spell>? = null,
    val error: Exception? = null
) {
    class SUCCESS(data: List<Spell>?): SpellState(SpellStatus.SUCCESS, data)
    class ERROR(error: Exception): SpellState(SpellStatus.ERROR, error = error)
    class LOADING: SpellState(SpellStatus.LOADING)
}

enum class SpellStatus {
    LOADING,
    SUCCESS,
    ERROR
}
