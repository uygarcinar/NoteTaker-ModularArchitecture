package com.app.notes.domain.usecase

import com.app.notes.domain.model.Note
import com.app.notes.domain.repository.NoteRepository
import javax.inject.Inject

class DeleteNoteUseCase @Inject constructor(
    private val repository : NoteRepository
) {
    suspend operator fun invoke(note : Note) = repository.deleteNote(note)
}