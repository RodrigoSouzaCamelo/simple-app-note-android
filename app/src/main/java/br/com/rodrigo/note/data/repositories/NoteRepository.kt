package br.com.rodrigo.note.data.repositories

import br.com.rodrigo.note.data.NoteDAO
import br.com.rodrigo.note.models.Note
import kotlinx.coroutines.flow.Flow

class NoteRepository(
    private val noteDAO: NoteDAO
) {
    fun getAll(): Flow<List<Note>> = noteDAO.getAll()
    suspend fun insert(note: Note) = noteDAO.insert(note)
    suspend fun update(note: Note) = noteDAO.update(note)
    suspend fun delete(note: Note) = noteDAO.delete(note)
}