package com.kimym.marvel.core

import android.content.Context
import android.util.Log
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.work.Configuration
import androidx.work.ListenableWorker.Result
import androidx.work.testing.SynchronousExecutor
import androidx.work.testing.TestListenableWorkerBuilder
import androidx.work.testing.WorkManagerTestInitHelper
import com.kimym.marvel.core.database.MarvelDatabase
import com.kimym.marvel.core.database.dao.MovieDao
import com.kimym.marvel.core.worker.MarvelDatabasePrePopulateWorker
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MarvelDatabasePrePopulateWorkerTest {
    private lateinit var movieDao: MovieDao
    private lateinit var db: MarvelDatabase

    @Before
    fun setUp() {
        createDb()
        initWorkManager()
    }

    private fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, MarvelDatabase::class.java).build()
        movieDao = db.movieDao()
    }

    private fun initWorkManager() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        val config = Configuration.Builder()
            .setMinimumLoggingLevel(Log.DEBUG)
            .setExecutor(SynchronousExecutor())
            .build()
        WorkManagerTestInitHelper.initializeTestWorkManager(context, config)
    }

    @After
    fun closeDb() {
        db.close()
    }

    @Test
    fun marvelDatabasePrePopulateWorker_doWork_success() = runTest {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        val worker = TestListenableWorkerBuilder<MarvelDatabasePrePopulateWorker>(context)
            .setWorkerFactory(MarvelDatabasePrePopulateWorkerFactory(movieDao))
            .build()

        assertEquals(Result.success(), worker.doWork())
    }
}
