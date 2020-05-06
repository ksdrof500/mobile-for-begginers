package com.coronaintheworld.repository

import com.coronaintheworld.local.NotificationDatabase
import com.coronaintheworld.local.NotificationModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class NotificationRepository(
    val database: NotificationDatabase
) {

    suspend fun getAllNotification() = withContext(Dispatchers.IO) {
        database.notificationDao.getAllNotification()
    }


    suspend fun saveNotification(notificationModel: NotificationModel) {
        withContext(Dispatchers.IO) {
            database.notificationDao.insert(notificationModel)
        }
    }
}