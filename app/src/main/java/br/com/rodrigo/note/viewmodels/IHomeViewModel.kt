package br.com.rodrigo.note.viewmodels

import br.com.rodrigo.note.models.Note
import kotlinx.coroutines.flow.Flow

interface IHomeViewModel {
    val noteList: Flow<List<Note>>
    fun addNote(note: Note)
    fun updateNote(note: Note)
    fun deleteNote(note: Note)
}