package br.com.rodrigo.note.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import br.com.rodrigo.note.models.Note
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDAO {
    @Query("select * from note")
    fun getAll(): Flow<List<Note>>

    @Insert
    suspend fun insert(note: Note)

    @Update
    suspend fun update(note: Note)

    @Delete
    suspend fun delete(note: Note)
}