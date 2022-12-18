package br.com.rodrigo.note.di

import android.app.Application
import br.com.rodrigo.note.data.AppDatabase
import br.com.rodrigo.note.data.NoteDAO
import br.com.rodrigo.note.data.repositories.NoteRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun provideNoteRepository(
        noteDAO: NoteDAO
    ): NoteRepository {
        return NoteRepository(noteDAO)
    }

    @Singleton
    @Provides
    fun provideAppDatabase(app: Application): AppDatabase {
        return AppDatabase.getInstance(context = app)
    }

    @Singleton
    @Provides
    fun provideNoteDAO(appDatabase: AppDatabase): NoteDAO {
        return appDatabase.noteDAO()
    }

}