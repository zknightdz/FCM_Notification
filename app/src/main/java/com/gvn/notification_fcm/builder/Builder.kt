package com.gvn.notification_fcm.builder

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.widget.RemoteViews
import com.google.firebase.messaging.RemoteMessage

class Builder : IBuilder {

    lateinit var context: Context

    var notificationStatus: Boolean

    var customLayoutStatus: Boolean

    var showNotificationFromForeground: Boolean

//    var customLayoutId: Int

    var smallIcon: Int

    var largeIcon: Bitmap? = null

    var sound: Uri

    var autoCancel: Boolean

    var packageClass: String? = null

    override fun setNotificationStatus(status: Boolean): Builder {
        notificationStatus = status
        return this
    }

//    override fun setCustomLayoutStatus(status: Boolean): Builder {
//        customLayoutStatus = status
//        return this
//    }

//    override fun setCustomLayout(layout: Int): Builder {
//
//        return this
//    }

    override fun setIsShowNotificationFromForeground(isShow: Boolean): Builder {
        showNotificationFromForeground = isShow
        return this
    }

    override fun setNotificationSmallIcon(iconId: Int): Builder {
        smallIcon = iconId
        return this
    }

    override fun setNotificationLargeIcon(iconId: Bitmap): Builder {
        largeIcon = iconId
        return this
    }

    override fun setNotificationSound(uri: Uri): Builder {
        sound = uri
        return this
    }

    override fun setNotificationAutoCancel(cancelAble: Boolean): Builder {
        autoCancel = cancelAble
        return this
    }

    override fun setOpeningContext(packageClass: String?): Builder {
        this.packageClass = packageClass
        return this
    }

    constructor() {
        this.notificationStatus = false
        this.customLayoutStatus = false
        this.showNotificationFromForeground = false
//        this.customLayoutId = -1
        this.smallIcon = -1
        this.sound = Uri.EMPTY
        this.autoCancel = true
    }

    /**
     * Construtor of [Builder] class.
     * @param context Usage for get SharedPreferences.
     */
    constructor(context: Context) {
        this.context = context
        this.notificationStatus = false
        this.customLayoutStatus = false
        this.showNotificationFromForeground = false
//        this.customLayoutId = -1
        this.smallIcon = -1
        this.sound = Uri.EMPTY
        this.autoCancel = true
    }

    override fun run(): Builder {
        saveToSharedPref()
        return this
    }

    private fun saveToSharedPref() {
        val sharedPref = context.getSharedPreferences(IBuilder.PREF_NAME, Context.MODE_PRIVATE) ?: return
        with(sharedPref.edit()) {
            putBoolean(IBuilder.KEY_NOTIFICATION_STATUS, notificationStatus)
            putBoolean(IBuilder.KEY_CUSTOM_LAYOUT_STATUS, customLayoutStatus)
            putBoolean(IBuilder.KEY_SHOW_NOTIFICATION_FOREGROUND, showNotificationFromForeground)
            putInt(IBuilder.KEY_SMALL_ICON, smallIcon)
            //putInt(IBuilder.KEY_LARGE_ICON, largeIcon)
            putString(IBuilder.KEY_SOUND, sound.toString())
            putBoolean(IBuilder.KEY_AUTO_CANCEL, autoCancel)
            putString(IBuilder.KEY_OPENED_CONTEXT, packageClass)
            apply()
        }
    }

    override fun toString(): String {
        return "Builder: notificationStatus = $notificationStatus,\tcustomLayoutStatus = $customLayoutStatus,\t" +
                "showNotificationFromForeground = $showNotificationFromForeground,\t" +
                "smallIcon = $smallIcon,\tlargeIcon = $largeIcon,\tsound = $sound,\tautoCancel = $autoCancel,\t" +
                "packageClass = $packageClass."

    }
}