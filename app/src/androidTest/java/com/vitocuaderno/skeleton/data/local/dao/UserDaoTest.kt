package com.vitocuaderno.skeleton.data.local.dao

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.asLiveData
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.vitocuaderno.skeleton.data.local.AppDatabase
import com.vitocuaderno.skeleton.data.local.models.User
import com.vitocuaderno.skeleton.getOrAwaitValue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@SmallTest
class UserDaoTest {
    private lateinit var database: AppDatabase
    private lateinit var userDao: UserDao

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).allowMainThreadQueries().build()

        userDao = database.userDao()
    }

    @After
    fun teardown() {
        database.close()
    }

    @Test
    fun insertItem() = runBlockingTest {
        val user = User(
            1,
            "test@email.com",
            "John",
            "Doe",
            "test"
        )

        userDao.insert(user)

        val insertedUser = userDao.getOne(user.id).asLiveData().getOrAwaitValue()

        assertEquals(insertedUser, user)
    }

    @Test
    fun insertItems() = runBlockingTest {
        val users = listOf(
            User(
                1,
                "test@email.com",
                "John",
                "Doe",
                "test"
            ),
            User(
                2,
                "test2@email.com",
                "Jane",
                "Doe",
                "test"
            ),
        )

        userDao.insertAll(users)

        val insertedUsers = userDao.getAll().asLiveData().getOrAwaitValue()

        assertTrue("Not inserted", insertedUsers.containsAll(users))
    }

    @Test
    fun clear() = runBlockingTest {
        val users = listOf(
            User(
                1,
                "test@email.com",
                "John",
                "Doe",
                "test"
            ),
            User(
                2,
                "test2@email.com",
                "Jane",
                "Doe",
                "test"
            ),
        )

        userDao.insertAll(users)

        userDao.clear()

        val insertedUsers = userDao.getAll().asLiveData().getOrAwaitValue()

        assertTrue("Not cleared", insertedUsers.isEmpty())
    }

    @Test
    fun pagingSource() = runBlockingTest {
        // TODO
//        val actual = userDao.getAllPaged()
//
//        assertEquals(actual as? PagingSource.LoadResult.Page)?.data, listOf(...))
    }
}
