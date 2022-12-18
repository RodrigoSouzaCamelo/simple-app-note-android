package br.com.rodrigo.note.data

import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import br.com.rodrigo.note.models.Note
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test

class NoteDAOAndroidTest {
    private lateinit var noteDAO: NoteDAO
    private lateinit var database: AppDatabase

    @Before
    fun createDB() {
        database = Room.inMemoryDatabaseBuilder(
            InstrumentationRegistry.getInstrumentation().context,
            AppDatabase::class.java
        ).allowMainThreadQueries().build()

        noteDAO = database.noteDAO()
    }

    @After
    fun cleanUp() {
        database.close()
    }

    @Test
    fun testInsertNoteAndReadInList() = runBlocking {
        // arrange
        val fakeText = "some text"
        val fakeNote = Note(text = fakeText)

        // act
        noteDAO.insert(fakeNote)
        val noteList = noteDAO.getAll().first()

        // assert
        assertThat(noteList.first().text).isEqualTo(fakeText)
    }

    @Test
    fun testUpdateNoteAndReadInList() = runBlocking {
        // arrange
        val fakeText = "some text"
        val fakeTextUpdated = "updated text"
        val fakeNote = Note(text = fakeText)

        // act
        noteDAO.insert(fakeNote)
        val noteList = noteDAO.getAll().first()
        noteDAO.update(noteList.first().copy(text = fakeTextUpdated))
        val noteListUpdated = noteDAO.getAll().first()

        // assert
        assertThat(noteListUpdated.first().text).isEqualTo(fakeTextUpdated)
    }

    @Test
    fun testDeleteNoteAndReadInList() = runBlocking {
        // arrange
        val fakeText = "some text"
        val fakeNote = Note(text = fakeText)

        // act
        noteDAO.insert(fakeNote)
        val noteList = noteDAO.getAll().first()
        noteDAO.delete(noteList.first())
        val noteListUpdated = noteDAO.getAll().first()

        // assert
        assertThat(noteListUpdated).isEmpty()
    }
}