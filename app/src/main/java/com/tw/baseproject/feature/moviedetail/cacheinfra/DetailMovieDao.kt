package com.tw.baseproject.feature.moviedetail.cacheinfra

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.tw.room_entity.DetailMovieEntity
import com.tw.room_entity.DetailMovieWithGenres
import com.tw.room_entity.LocalGenreEntity

@Dao
interface DetailMovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDetailMovie(detailMovieEntity: com.tw.room_entity.DetailMovieEntity)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGenre(genre: List<com.tw.room_entity.LocalGenreEntity>)

    @Transaction
    @Query("SELECT * FROM movie_detail WHERE id == (:movieId)")
    suspend fun getDetailMovieById(movieId: Int): com.tw.room_entity.DetailMovieWithGenres

    @Query("UPDATE movie_detail SET is_favorite = (:isFavorite) WHERE id == (:movieId)")
    suspend fun setDetailMovieFavorite(isFavorite: Boolean, movieId: Int)
}