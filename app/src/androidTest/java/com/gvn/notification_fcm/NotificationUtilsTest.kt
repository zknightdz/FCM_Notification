package com.gvn.notification_fcm

import android.app.NotificationManager
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.RingtoneManager
import android.net.Uri
import android.os.Bundle
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.google.firebase.messaging.RemoteMessage
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class NotificationUtilsTest {

    private lateinit var mContext: Context

    private lateinit var mRemoteMessage: RemoteMessage

    private var mSmallIcon: Int = -1

    private lateinit var mLargeIcon: Bitmap

    private lateinit var mSound: Uri

    private var mAutoCancel: Boolean = false

    private lateinit var mOpeningContext: String

    private lateinit var mNotificationManager : NotificationManager

    /**
     * Set up and mock object for unit test class NotificationUtils.
     */
    @Before
    fun setup() {
        mContext = InstrumentationRegistry.getInstrumentation().context
//        val bundle = Bundle()
//        bundle.putString("gcm.n.title", "abc")
//        bundle.putString("gcm.n.body", "This is message")
        mRemoteMessage = RemoteMessage(Bundle())
        mSmallIcon = R.mipmap.ic_launcher
        mLargeIcon = BitmapFactory.decodeResource(mContext.resources, R.drawable.ic_notification)
        mSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        mAutoCancel = false
        mOpeningContext = InstrumentationRegistry.getInstrumentation().targetContext.packageName
        mNotificationManager = mContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    }

    /**
     * This method test for [NotificationUtils.showNotification] method.
     * Test case:   mContext = InstrumentationRegistry.getInstrumentation().context.
     *              mRemoteMessage = RemoteMessage(Bundle()).
     *              mSmallIcon = R.mipmap.ic_launcher.
     *              mLargeIcon = BitmapFactory.decodeResource(mContext.resources, R.drawable.ic_notification).
     *              mSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION).
     *              mAutoCancel = false.
     *              mOpeningContext = InstrumentationRegistry.getInstrumentation().targetContext.
     * Expert result:   Run success method.
     *                  Notifications are displayed.
     *                  Do not delete the notification when clicked.
     *                  Play sound.
     */
    @Test
    fun testShowNotification_1() {

        NotificationUtils.showNotification(mContext, mRemoteMessage, mSmallIcon, mLargeIcon, mSound, mAutoCancel, mOpeningContext)
//        val device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
//        device.openNotification()
//        device.wait(Until.hasObject(By.text("Notification-FCM")), 5000)
//        val title = device.findObject(By.text("Notification-FCM"))
//        title.click()

        val notifications = mNotificationManager.activeNotifications
        var checkShowNotification = false
        var pos : Int = -1
        for (i in 0 until notifications.size) {
            if (NotificationUtils.NOTIFICATION_ID == notifications[i].id) {
                checkShowNotification = true
                pos = i
            }
        }

        Assert.assertTrue(checkShowNotification)
        if (pos != -1) {
            Assert.assertEquals(mSmallIcon, notifications[pos].notification.smallIcon.resId)
//            Assert.assertEquals(mLargeIcon, notifications[pos].notification.getLargeIcon())
//            Assert.assertEquals(mSound, notifications[pos].notification.sound)
        }

    }

    /**
     * This method test for [NotificationUtils.showNotification] method.
     * Test case:   mContext = InstrumentationRegistry.getInstrumentation().context.
     *              mRemoteMessage = RemoteMessage(Bundle()).
     *              mSmallIcon = -1.
     *              mLargeIcon = BitmapFactory.decodeResource(mContext.resources, R.drawable.ic_notification).
     *              mSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION).
     *              mAutoCancel = false.
     *              mOpeningContext = InstrumentationRegistry.getInstrumentation().targetContext.
     * Expert result:   Run success method.
     *                  Notifications are not displayed.
     */
    @Test
    fun testShowNotification_2() {
        mSmallIcon = -1

        NotificationUtils.showNotification(mContext, mRemoteMessage, mSmallIcon, mLargeIcon, mSound, mAutoCancel, mOpeningContext)

        val notifications = mNotificationManager.activeNotifications
        var checkShowNotification = false
        var pos : Int = -1
        for (i in 0 until notifications.size) {
            if (NotificationUtils.NOTIFICATION_ID == notifications[i].id) {
                checkShowNotification = true
                pos = i
            }
        }

        Assert.assertTrue(checkShowNotification)
        if (pos != -1) {
            Assert.assertEquals(mSmallIcon, notifications[pos].notification.smallIcon.resId)
        }
    }

    /**
     * This method test for [NotificationUtils.showNotification] method.
     * Test case:   mContext = InstrumentationRegistry.getInstrumentation().context.
     *              mRemoteMessage = RemoteMessage(Bundle()).
     *              mSmallIcon = R.mipmap.ic_launcher.
     *              mLargeIcon = BitmapFactory.decodeResource(ImContext.resources, R.drawable.ic_notification).
     *              mSound = Uri.EMPTY
     *              mAutoCancel = false.
     *              mOpeningContext = InstrumentationRegistry.getInstrumentation().targetContext.
     * Expert result:   Run success method.
     *                  Notifications are displayed.
     *                  Do not delete the notification when clicked.
     *                  Do not play sound.
     */
    @Test
    fun testShowNotification_3() {
        mSound = Uri.EMPTY

        NotificationUtils.showNotification(mContext, mRemoteMessage, mSmallIcon, mLargeIcon, mSound, mAutoCancel, mOpeningContext)

        val notifications = mNotificationManager.activeNotifications
        var checkShowNotification = false
        for (i in 0 until notifications.size) {
            if (NotificationUtils.NOTIFICATION_ID == notifications[i].id) {
                checkShowNotification = true
            }
        }

        Assert.assertTrue(checkShowNotification)
    }

    /**
     * This method test for [NotificationUtils.showNotification] method.
     * Test case:   mContext = InstrumentationRegistry.getInstrumentation().context.
     *              mRemoteMessage = RemoteMessage(Bundle()).
     *              mSmallIcon = R.mipmap.ic_launcher.
     *              mLargeIcon = BitmapFactory.decodeResource(mContext.resources, R.drawable.ic_notification).
     *              mSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION).
     *              mAutoCancel = true.
     *              mOpeningContext = InstrumentationRegistry.getInstrumentation().targetContext.
     * Expert result:   Run success method.
     *                  Notifications are displayed.
     *                  Delete the notification when clicked.
     *                  Play sound.
     */
    @Test
    fun testShowNotification_4() {
        mAutoCancel = true

        NotificationUtils.showNotification(mContext, mRemoteMessage, mSmallIcon, mLargeIcon, mSound, mAutoCancel, mOpeningContext)

        val notifications = mNotificationManager.activeNotifications
        var checkShowNotification = false
        for (i in 0 until notifications.size) {
            if (NotificationUtils.NOTIFICATION_ID == notifications[i].id) {
                checkShowNotification = true
            }
        }

        Assert.assertTrue(checkShowNotification)
    }

    @Test
    fun testShowNotification_5() {

        NotificationUtils.showNotification(mContext, mRemoteMessage, mSmallIcon, mLargeIcon, mSound, mAutoCancel, mOpeningContext)

    }

    @After
    fun tearDown() {
        //mContext!! =null
    }

}