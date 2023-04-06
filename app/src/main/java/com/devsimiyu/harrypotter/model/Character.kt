package com.devsimiyu.harrypotter.model

data class Character(
    val id: String,
    val name: String,
    val actor: String,
    val image: String,
    val house: String,
    val hogwartsStudent: Boolean,
    val hogwartsStaff: Boolean
)
