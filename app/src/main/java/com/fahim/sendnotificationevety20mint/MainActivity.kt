package com.fahim.sendnotificationevety20mint

import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Constraints
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequest
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.fahim.sendnotificationevety20mint.ui.theme.SendNotificationEvety20MintTheme
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment
import java.util.concurrent.TimeUnit

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SendNotificationEvety20MintTheme {
                // A surface container using the 'background' color from the theme
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                ) {
                    Button(onClick = { setWorker() }) {
                        Text(text = "Send Notification")

                    }
                }
            }
        }
    }


    @RequiresApi(Build.VERSION_CODES.O)
    private fun setWorker() {


        val channel =
            NotificationChannel("default", "Default", NotificationManager.IMPORTANCE_DEFAULT)
        val notificationManager = getSystemService(NotificationManager::class.java)
        notificationManager.createNotificationChannel(channel)


        val constrainst =
            androidx.work.Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED)
                .build()
        val workRequest = PeriodicWorkRequest.Builder(
            NotificationWorker::class.java,
            15, TimeUnit.MINUTES
        )
            .setConstraints(constrainst)
            .build()
        Log.i("WorkManager", workRequest.toString())

        WorkManager.getInstance(this).enqueue(workRequest)

    }
}

