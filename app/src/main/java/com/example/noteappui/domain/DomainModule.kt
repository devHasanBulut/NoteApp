package com.example.noteappui.domain

import com.example.noteappui.repository.NoteRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DomainModule {

    @Provides
    fun provideGetNotesViewEntityUseCase(noteRepository: NoteRepository): GetNotesViewEntityUseCase {
        return GetNotesViewEntityUseCase(noteRepository)
    }

    @Provides
    fun provideCreateNoteViewEntityUseCase(noteRepository: NoteRepository): InsertNoteUseCase {
        return InsertNoteUseCase(noteRepository)
    }

    @Provides
    fun provideUpdateNoteUseCase(noteRepository: NoteRepository): UpdateNoteUseCase {
        return UpdateNoteUseCase(noteRepository)
    }

    @Provides
    fun provideGetDateViewEntityUseCase(noteRepository: NoteRepository): GetDateViewEntityUseCase {
        return GetDateViewEntityUseCase(noteRepository)
    }
}