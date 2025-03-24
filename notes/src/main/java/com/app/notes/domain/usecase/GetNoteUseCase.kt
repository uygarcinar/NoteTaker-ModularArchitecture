package com.app.notes.domain.usecase

import com.app.notes.domain.repository.NoteRepository
import javax.inject.Inject

class GetNoteUseCase @Inject constructor(
    private val repository : NoteRepository
) {
    suspend operator fun invoke(id : Int) = repository.getNoteById(id)
}