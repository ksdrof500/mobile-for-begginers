package com.coronaintheworld.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface NotificationDao {

    @Insert
    suspend fun insert(notificationModel: NotificationModel)

    @Query("SELECT * FROM notification_table ORDER BY id DESC")
    fun getAllNotification(): LiveData<List<NotificationModel>>
}
