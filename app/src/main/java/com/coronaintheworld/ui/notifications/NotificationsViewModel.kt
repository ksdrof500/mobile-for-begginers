package com.coronaintheworld.ui.notifications

import androidx.lifecycle.ViewModel
import com.coronaintheworld.repository.NotificationRepository

class NotificationsViewModel(val notificationRepository: NotificationRepository) : ViewModel() {

    suspend fun getAll() = notificationRepository.getAllNotification()

}