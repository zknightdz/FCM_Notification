package com.gvn.notification_fcm.builder

import android.content.Context

class FCMSetting {
    companion object {

        /**
         * This method build new FCM Builder object.
         * @param context context use for [Builder] class.
         * @return [Builder]
         */
        fun build(context: Context) : Builder{
            return Builder(context)
        }

        /**
         * This method clear previous settings.
         * @param context context use for get SharedPreferences and [ClearBuilder] class.
         * @return [ClearBuilder]
         */
        fun clear(context: Context): ClearBuilder{
            val sharedPref = context.getSharedPreferences(IBuilder.PREF_NAME, Context.MODE_PRIVATE)
            sharedPref.edit().clear().apply()
            return ClearBuilder(context)
        }
    }
}