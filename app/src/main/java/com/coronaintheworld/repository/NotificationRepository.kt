package com.coronaintheworld.repository

import com.coronaintheworld.local.NotificationDao
import com.coronaintheworld.local.NotificationModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class NotificationRepository(val notificationDap: NotificationDao) {

    suspend fun getAllNotification() = withContext(Dispatchers.IO) {
        notificationDap.getAllNotification()
    }

    suspend fun saveNotification(notificationModel: NotificationModel) {
        withContext(Dispatchers.IO) {
            notificationDap.insert(notificationModel)
        }
    }
}
