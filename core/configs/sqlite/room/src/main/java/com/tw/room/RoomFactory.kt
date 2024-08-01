package com.tw.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.tw.baseproject.core.shared_models.room_entity.DetailMovieEntity
import com.tw.baseproject.core.shared_models.room_entity.LocalGenreEntity
import com.tw.baseproject.feature.moviedetail.cacheinfra.DetailMovieDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomFactory {
    @Singleton
    @Provides
    fun createMovieDatabase(@ApplicationContext context: Context) = Room.databaseBuilder(
        context,
        AppDatabase::class.java,
        "movie_database"
    ).fallbackToDestructiveMigration().build()

}

@Database(entities = [DetailMovieEntity::class, LocalGenreEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun detailMovieDao(): DetailMovieDao
}