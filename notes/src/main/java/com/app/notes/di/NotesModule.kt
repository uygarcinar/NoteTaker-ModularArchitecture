package com.app.notes.di

import com.app.notes.NoteDao
import com.app.notes.data.repository.NoteRepositoryImpl
import com.app.notes.domain.repository.NoteRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NotesModule {
    @Provides
    @Singleton
    fun bindNoteRepository(
        dao: NoteDao
    ): NoteRepository =
        NoteRepositoryImpl(dao)
}