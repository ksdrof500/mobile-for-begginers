package com.coronaintheworld.repository

import android.content.Context
import androidx.room.Room
import com.coronaintheworld.local.NotificationDao
import com.coronaintheworld.local.NotificationDatabase
import com.coronaintheworld.local.NotificationModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import java.io.IOException


@RunWith(RobolectricTestRunner::class)
@Config(manifest = Config.NONE)
class NotificationRepositoryTest {
    @Mock
    private lateinit var context: Context
    private lateinit var notificationRepository: NotificationRepository
    private lateinit var notificationDatabase: NotificationDatabase
    private lateinit var notificationDao: NotificationDao

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        notificationDatabase = Room.inMemoryDatabaseBuilder(
            context, NotificationDatabase::class.java
        ).build()
        notificationDao = notificationDatabase.notificationDao
        notificationRepository = NotificationRepository(notificationDao)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun testInsertAndGetAll() {
        runBlockingTest {
            val notificationModel = NotificationModel(title = "title", text = "text")
            notificationRepository.saveNotification(notificationModel)
            val getAll = notificationRepository.getAllNotification()
            Assert.assertEquals(getAll.value?.firstOrNull(), notificationModel)
        }
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        notificationDatabase.close()
    }
}