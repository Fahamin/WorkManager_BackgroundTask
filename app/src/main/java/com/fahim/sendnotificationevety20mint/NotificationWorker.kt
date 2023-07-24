package com.fahim.sendnotificationevety20mint

import android.content.Context
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters

class NotificationWorker(private var context: Context, var param: WorkerParameters) :
    CoroutineWorker(context, param) {
    override suspend fun doWork(): Result {
        val notification = NotificationCompat.Builder(applicationContext, "default")
            .setSmallIcon(
                R.mipmap.ic_launcher
            )
            .setContentTitle("Task completed")
            .setContentText("The background task has completed successfully.")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .build()

        // Perform the background task here,
        // such as displaying a notification
        NotificationManagerCompat.from(applicationContext).notify(1, notification)
        return Result.success()
    }
}