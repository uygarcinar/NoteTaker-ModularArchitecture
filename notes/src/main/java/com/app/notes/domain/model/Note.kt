package com.app.notes.domain.model

data class Note(
    val id: Int,
    val title: String,
    val content: String,
    val createdAt: Long,
    val updatedAt: Long
)