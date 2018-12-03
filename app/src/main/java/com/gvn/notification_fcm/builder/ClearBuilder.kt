package com.gvn.notification_fcm.builder

import android.content.Context
import android.util.Log

class ClearBuilder(val  context: Context) {

    /**
     * This method build new [Builder] object.
     * @return [Builder]
     */
    fun build(): Builder{
        return Builder(context)
    }

}