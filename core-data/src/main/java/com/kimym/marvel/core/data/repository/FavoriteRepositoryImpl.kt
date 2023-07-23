package com.kimym.marvel.core.data.repository

import com.kimym.marvel.core.data.di.IODispatcher
import com.kimym.marvel.core.database.MarvelDao
import com.kimym.marvel.core.model.MovieAndRating
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class FavoriteRepositoryImpl @Inject constructor(
    private val dao: MarvelDao,
    @IODispatcher private val ioDispatcher: CoroutineDispatcher
) : FavoriteRepository {
    override fun getFavorites(): Flow<List<MovieAndRating>> {
        return dao.getFavorites().flowOn(ioDispatcher)
    }
}
