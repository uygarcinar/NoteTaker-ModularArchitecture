package com.app.notes

import androidx.room.Database
import androidx.room.RoomDatabase
import com.app.notes.data.model.NoteEntity

@Database(entities = [NoteEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao
}