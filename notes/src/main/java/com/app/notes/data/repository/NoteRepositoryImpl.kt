package com.app.notes.data.repository

import com.app.notes.NoteDao
import com.app.notes.data.mapper.toDomain
import com.app.notes.data.mapper.toEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class NoteRepositoryImpl @Inject constructor(
    private val noteDao: NoteDao
) : com.app.notes.domain.repository.NoteRepository {
    override fun getNotes(): Flow<List<com.app.notes.domain.model.Note>> =
        noteDao.getNotes().map { notes -> notes.map { it.toDomain() } }

    override suspend fun addNote(note: com.app.notes.domain.model.Note) = noteDao.insert(note.toEntity())

    override suspend fun deleteNote(note: com.app.notes.domain.model.Note) = noteDao.delete(note.toEntity())

    override suspend fun getNoteById(id: Int): com.app.notes.domain.model.Note? = noteDao.getNoteById(id)?.toDomain()
}