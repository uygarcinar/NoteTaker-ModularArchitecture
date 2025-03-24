package com.app.notes.data.mapper

import com.app.notes.data.model.NoteEntity
import com.app.notes.domain.model.Note

fun NoteEntity.toDomain() =
    com.app.notes.domain.model.Note(id, title, content, createdAt, updatedAt)
fun com.app.notes.domain.model.Note.toEntity() = NoteEntity(id,title,content, createdAt, updatedAt)