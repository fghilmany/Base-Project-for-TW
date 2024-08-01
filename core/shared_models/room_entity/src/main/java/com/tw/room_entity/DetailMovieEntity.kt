package com.tw.room_entity

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation

@Entity(tableName = "movie_detail")
data class DetailMovieEntity(
    @PrimaryKey
    val id: Int,
    @ColumnInfo(name = "backdrop_path")
    val backdropPath: String,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "release_date")
    val releaseDate: String,
    @ColumnInfo(name = "overview")
    val overview: String,
    @ColumnInfo(name = "is_favorite")
    var isFavorite: Boolean = false,
)

data class DetailMovieWithGenres(
    @Embedded val detailMovieEntity: DetailMovieEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "movie_id"
    )
    val genres: List<LocalGenreEntity>
)

@Entity(tableName = "movie_detail_genre")
data class LocalGenreEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "genre_id")
    val genreId: Int,
    @ColumnInfo(name = "movie_id")
    val movieId: Int,
    @ColumnInfo(name = "name")
    val name: String
)