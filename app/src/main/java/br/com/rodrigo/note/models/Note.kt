package br.com.rodrigo.note.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Note")
data class Note(
    @PrimaryKey(autoGenerate = true) val id: Long? = null,
    val text: String
)
