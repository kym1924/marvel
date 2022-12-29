package com.kimym.marvel.di

import com.kimym.marvel.data.repository.DetailRepository
import com.kimym.marvel.data.repository.DetailRepositoryImpl
import com.kimym.marvel.data.repository.FavoriteRepository
import com.kimym.marvel.data.repository.FavoriteRepositoryImpl
import com.kimym.marvel.data.repository.MovieRepository
import com.kimym.marvel.data.repository.MovieRepositoryImpl
import com.kimym.marvel.data.repository.RatingRepository
import com.kimym.marvel.data.repository.RatingRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindMovieRepository(
        movieRepositoryImpl: MovieRepositoryImpl
    ): MovieRepository

    @Binds
    @Singleton
    abstract fun bindFavoriteRepository(
        favoriteRepositoryImpl: FavoriteRepositoryImpl
    ): FavoriteRepository

    @Binds
    @Singleton
    abstract fun bindDetailRepository(
        detailRepositoryImpl: DetailRepositoryImpl
    ): DetailRepository

    @Binds
    @Singleton
    abstract fun bindRatingRepository(
        ratingRepositoryImpl: RatingRepositoryImpl
    ): RatingRepository
}
