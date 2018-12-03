package com.gvn.notification_fcm

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.widget.RemoteViews
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.RemoteMessage

object NotificationUtils {

    private const val CHANNEL_ID: String = "FirebaseMessagingChannelId"

    const val NOTIFICATION_ID: Int = 1221

    /**
     * This method displays Notification with parameters.
     * @param context Context show notification.
     * @param remoteMessage Contains data of notification.
     * @param smallIcon Id of smal icon for display notification.
     * @param largeIcon Bitmap large icon.
     * @param sound Uri of sound for notification.
     * @param autoCancel auto cancel notification when clicked.
     * @param openingContext Target context when click notification.
     */
    fun showNotification(
        context: Context,
        remoteMessage: RemoteMessage,
        smallIcon: Int,
        largeIcon: Bitmap,
        sound: Uri,
        autoCancel: Boolean,
        openingContext: String?
    ) {
        val intent = if (openingContext != null)
            Intent(context, Class.forName(openingContext))
        else
            Intent()
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        val pendingIntent = PendingIntent.getActivity(
            context, 0 /* Request code */, intent,
            PendingIntent.FLAG_ONE_SHOT
        )

        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                context.resources.getString(R.string.app_name),
                NotificationManager.IMPORTANCE_DEFAULT
            )
            notificationManager.createNotificationChannel(channel)
        }

        val notificationBuilder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setContentText(remoteMessage.notification?.body)
            .setAutoCancel(autoCancel)
            .setSmallIcon(smallIcon)
            .setLargeIcon(largeIcon)
            .setSound(sound)
            .setContentIntent(pendingIntent)
        notificationManager.notify(NOTIFICATION_ID /* ID of notification */, notificationBuilder.build())
    }

    /**
     * This method displays Notification with parameters.
     * @param context Context show notification.
     * @param remoteViews Custom layout of notification.
     * @param smallIcon Id of smal icon for display notification.
     * @param sound Uri of sound for notification.
     * @param autoCancel auto cancel notification when clicked.
     * @param openingContext Target context when click notification.
     */
    fun showNotification(
        context: Context,
        remoteViews: RemoteViews,
        smallIcon: Int,
        sound: Uri,
        autoCancel: Boolean,
        openingContext: String?
    ) {
        val intent = if (openingContext != null)
            Intent(context, Class.forName(openingContext))
        else
            Intent()
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        val pendingIntent = PendingIntent.getActivity(
            context, 0 /* Request code */, intent,
            PendingIntent.FLAG_ONE_SHOT
        )

        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                context.resources.getString(R.string.app_name),
                NotificationManager.IMPORTANCE_DEFAULT
            )
            notificationManager.createNotificationChannel(channel)
        }

        val notificationBuilder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(smallIcon)
            .setContent(remoteViews)
            .setAutoCancel(autoCancel)
            .setSound(sound)
            .setContentIntent(pendingIntent)
        notificationManager.notify(NOTIFICATION_ID /* ID of notification */, notificationBuilder.build())
    }
}