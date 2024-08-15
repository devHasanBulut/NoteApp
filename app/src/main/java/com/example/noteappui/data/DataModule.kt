package com.example.noteappui.data

import android.content.Context
import androidx.room.Room
import com.example.noteappui.NetworkChecker
import com.example.noteappui.data.localdatasource.AppDatabase
import com.example.noteappui.data.remotedatasource.NotesApiService
import com.example.noteappui.data.localdatasource.NotesModelDao
import com.example.noteappui.repository.NoteRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {
    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            "app_database",
        ).build()
    }

    @Singleton
    @Provides
    fun provideNotesModelDao(appDatabase: AppDatabase) = appDatabase.notesModelDao()

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().connectTimeout(30, TimeUnit.SECONDS).readTimeout(30, TimeUnit.SECONDS).writeTimeout(30, TimeUnit.SECONDS).build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder().baseUrl("http://192.168.1.35:8080/").client(okHttpClient).addConverterFactory(GsonConverterFactory.create()).build()
    }

    @Singleton
    @Provides
    fun provideNetworkStatusChecker(@ApplicationContext context: Context): NetworkChecker {
        return NetworkChecker(context)
    }

    @Singleton
    @Provides
    fun provideNoteApiService(retrofit: Retrofit): NotesApiService {
        return retrofit.create(NotesApiService::class.java)
    }

    @Provides
    fun provideNoteRepository(
        noteDao: NotesModelDao,
        notesApiService: NotesApiService,
        networkChecker: NetworkChecker
    ): NoteRepository {
        return NoteRepository(noteDao, notesApiService, networkChecker)
    }
}