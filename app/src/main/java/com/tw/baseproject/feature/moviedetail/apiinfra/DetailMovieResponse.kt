package com.tw.baseproject.feature.moviedetail.apiinfra

import com.squareup.moshi.Json

data class DetailMovieResponse(

	@Json(name="original_language")
	val originalLanguage: String,

	@Json(name="imdb_id")
	val imdbId: String? = null,

	@Json(name="video")
	val video: Boolean,

	@Json(name="title")
	val title: String,

	@Json(name="backdrop_path")
	val backdropPath: String? = null,

	@Json(name="revenue")
	val revenue: Int,

	@Json(name="genres")
	val genres: List<GenresItem>,

	@Json(name="popularity")
	val popularity: Any,

	@Json(name="production_countries")
	val productionCountries: List<ProductionCountriesItem>,

	@Json(name="id")
	val id: Int,

	@Json(name="vote_count")
	val voteCount: Int,

	@Json(name="budget")
	val budget: Int,

	@Json(name="overview")
	val overview: String,

	@Json(name="original_title")
	val originalTitle: String,

	@Json(name="runtime")
	val runtime: Int,

	@Json(name="poster_path")
	val posterPath: String,

	@Json(name="origin_country")
	val originCountry: List<String>,

	@Json(name="spoken_languages")
	val spokenLanguages: List<SpokenLanguagesItem>,

	@Json(name="production_companies")
	val productionCompanies: List<ProductionCompaniesItem>,

	@Json(name="release_date")
	val releaseDate: String,

	@Json(name="vote_average")
	val voteAverage: Any,

	@Json(name="belongs_to_collection")
	val belongsToCollection: BelongsToCollection? = null,

	@Json(name="tagline")
	val tagline: String,

	@Json(name="adult")
	val adult: Boolean,

	@Json(name="homepage")
	val homepage: String,

	@Json(name="status")
	val status: String
)

data class ProductionCountriesItem(

	@Json(name="iso_3166_1")
	val iso31661: String,

	@Json(name="name")
	val name: String
)

data class ProductionCompaniesItem(

	@Json(name="logo_path")
	val logoPath: String? = null,

	@Json(name="name")
	val name: String,

	@Json(name="id")
	val id: Int,

	@Json(name="origin_country")
	val originCountry: String
)

data class BelongsToCollection(

	@Json(name="backdrop_path")
	val backdropPath: String? = null,

	@Json(name="name")
	val name: String,

	@Json(name="id")
	val id: Int,

	@Json(name="poster_path")
	val posterPath: String
)

data class SpokenLanguagesItem(

	@Json(name="name")
	val name: String,

	@Json(name="iso_639_1")
	val iso6391: String,

	@Json(name="english_name")
	val englishName: String
)

data class GenresItem(

	@Json(name="name")
	val name: String,

	@Json(name="id")
	val id: Int
)
