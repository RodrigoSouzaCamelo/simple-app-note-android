package br.com.rodrigo.note.viewmodels

import androidx.lifecycle.ViewModel
import br.com.rodrigo.note.data.repositories.NoteRepository
import br.com.rodrigo.note.models.Note
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel
@Inject constructor(
    private val noteRepository: NoteRepository
) : ViewModel(), IHomeViewModel {
    override val noteList: Flow<List<Note>> = noteRepository.getAll()

    override fun addNote(note: Note) {
        CoroutineScope(Dispatchers.IO).launch {
            noteRepository.insert(note)
        }
    }

    override fun updateNote(note: Note) {
        CoroutineScope(Dispatchers.IO).launch {
            noteRepository.update(note)
        }
    }

    override fun deleteNote(note: Note) {
        CoroutineScope(Dispatchers.IO).launch {
            noteRepository.delete(note)
        }
    }
}