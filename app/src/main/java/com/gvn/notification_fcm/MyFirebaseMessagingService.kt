package com.gvn.notification_fcm

import android.content.Context
import android.graphics.BitmapFactory
import android.media.RingtoneManager
import android.net.Uri
import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.gvn.notification_fcm.NotificationUtils.showNotification
import com.gvn.notification_fcm.builder.Builder
import com.gvn.notification_fcm.builder.IBuilder
import android.content.Intent
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import android.content.BroadcastReceiver
import android.content.IntentFilter
import android.graphics.Bitmap
import android.widget.RemoteViews

class MyFirebaseMessagingService : FirebaseMessagingService() {

    companion object {

        private val TAG = MyFirebaseMessagingService::class.java.simpleName

    }

    private var mSmallIcon: Int = -1

    private lateinit var mLargeIcon: Bitmap

    private lateinit var mSound: Uri

    private var mAutoCancel: Boolean = false

    private var mOpeningContext: String? = null

    override fun onNewToken(token: String?) {
        Log.d(TAG, "Refreshed token: $token")
        sendRegistrationToServer(token)
    }

    private fun sendRegistrationToServer(token: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    override fun onMessageReceived(remoteMessage: RemoteMessage?) {
        Log.d(TAG, "onMessageReceived() is called")
        // Handle FCMSetting messages here.
        // If the application is in the foreground handle both data and notification messages here.
        // Also if you intend on generating your own notifications as a result of a received FCMSetting
        // message, here is where that should be initiated.
        Log.d(TAG, "From: " + remoteMessage!!.from)
        Log.d(TAG, "Notification Message Body: " + remoteMessage.notification!!.body!!)

        handleMessageReceived(remoteMessage)
    }

    private fun handleMessageReceived(remoteMessage: RemoteMessage) {
        Log.d(TAG, "handleMessageReceived() is called")
        val builder = getDataFromSharedPref()
        Log.d(TAG, "builder = " + builder.toString())

        mSmallIcon = if (builder.smallIcon == -1)
            R.mipmap.ic_launcher
        else
            builder.smallIcon

        mLargeIcon = if (builder.largeIcon == null)
            BitmapFactory.decodeResource(resources, R.drawable.ic_notification)
        else
            builder.largeIcon!!

        mSound = if (Uri.EMPTY == builder.sound)
            RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        else
            builder.sound

        mAutoCancel = builder.autoCancel

        mOpeningContext = builder.packageClass


        if (builder.notificationStatus) {
            if (builder.customLayoutStatus) {

            } else {
                showNotification(this, remoteMessage, mSmallIcon, mLargeIcon, mSound, mAutoCancel, mOpeningContext)
            }
        }

    }

    private fun getDataFromSharedPref(): Builder {
        val builder = Builder(this)
        val sharedPref = getSharedPreferences(IBuilder.PREF_NAME, Context.MODE_PRIVATE) ?: return builder
        builder.setNotificationStatus(sharedPref.getBoolean(IBuilder.KEY_NOTIFICATION_STATUS, false))
//            .setCustomLayoutStatus(sharedPref.getBoolean(IBuilder.KEY_CUSTOM_LAYOUT_STATUS, false))
            .setIsShowNotificationFromForeground(
                sharedPref.getBoolean(
                    IBuilder.KEY_SHOW_NOTIFICATION_FOREGROUND,
                    false
                )
            )
            .setNotificationSmallIcon(sharedPref.getInt(IBuilder.KEY_SMALL_ICON, -1))
//            .setNotificationLargeIcon()
            .setNotificationSound(Uri.parse(sharedPref.getString(IBuilder.KEY_SOUND, "")))
            .setNotificationAutoCancel(sharedPref.getBoolean(IBuilder.KEY_AUTO_CANCEL, false))
            .setOpeningContext(sharedPref.getString(IBuilder.KEY_OPENED_CONTEXT, null))

        return builder
    }

}