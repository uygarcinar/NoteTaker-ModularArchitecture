package com.app.notes.domain.usecase

import com.app.notes.domain.model.Note
import com.app.notes.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetNotesUseCase @Inject constructor(
    private val repository : NoteRepository
) {
    operator fun invoke() : Flow<List<Note>> = repository.getNotes()
}