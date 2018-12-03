package com.gvn.notification_fcm.builder

import android.app.Activity
import android.content.Context
import android.graphics.Bitmap
import android.net.Uri

interface IBuilder {

    companion object {

        const val PREF_NAME = "SharePref_FCM"
        const val KEY_NOTIFICATION_STATUS = "KEY_NOTIFICATION_STATUS"
        const val KEY_CUSTOM_LAYOUT_STATUS = "KEY_CUSTOM_LAYOUT_STATUS"
        const val KEY_SHOW_NOTIFICATION_FOREGROUND = "KEY_SHOW_NOTIFICATION_FOREGROUND"
        const val KEY_SMALL_ICON = "KEY_SMALL_ICON"
        const val KEY_LARGE_ICON = "KEY_LARGE_ICON"
        const val KEY_SOUND = "KEY_SOUND"
        const val KEY_AUTO_CANCEL = "KEY_AUTO_CANCEL"
        const val KEY_OPENED_CONTEXT = "KEY_OPENED_CONTEXT"
    }

    /**
     * This method set may show notification.
     * @param status [true] to show notification, [false] otherwise.
     * @return [Builder]
     */
    fun setNotificationStatus(status: Boolean): Builder

    /**
     * This method set may using custom notification or not.
     * @param status [true] to use custom notification, [false] otherwise.
     * If [status] = [true], context must implement interface [Builder.ICustomNotifyListener].
     * @return [Builder]
     */
//    fun setCustomLayoutStatus(status: Boolean): Builder

    /**
     * This method set custom layout to use custom notification.
     * @param layout id of layout to use custom notification.
     * @return [Builder]
     */
//    fun setCustomLayout(layout: Int): Builder

    /**
     * This method set show notification in foreground or background.
     * @param isShow [true] to show in foreground, [false] to show in background.
     * @return [Builder]
     */
    fun setIsShowNotificationFromForeground(isShow: Boolean): Builder

    /**
     * This method set small icon to notification.
     * @param iconId id of small icon.
     * @return [Builder]
     */
    fun setNotificationSmallIcon(iconId: Int): Builder

    /**
     * This method set large icon to notification.
     * @param iconId id of large icon.
     * @return [Builder]
     */
    fun setNotificationLargeIcon(iconId: Bitmap): Builder

    /**
     * This method set sound to notification.
     * @param iconId link uri of notification sound.
     * @return [Builder]
     */
    fun setNotificationSound(uri: Uri): Builder

    /**
     * This method set auto cancel to notification.
     * @param cancelAble [true] to auto cancel notification, [false] otherwise.
     * @return [Builder]
     */
    fun setNotificationAutoCancel(cancelAble: Boolean): Builder

    /**
     * This method set destination when clicked in notification .
     * @param packageClass destination.
     * @return [Builder]
     */
    fun setOpeningContext(packageClass: String?): Builder

    /**
     * This method call when user complete the installation process.
     * It save setting of user to system.
     * @return [Builder]
     */
    fun run(): Builder
}