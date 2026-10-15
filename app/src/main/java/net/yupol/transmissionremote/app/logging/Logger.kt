package net.yupol.transmissionremote.app.logging

import android.util.Log
import net.yupol.transmissionremote.app.BuildConfig
import javax.inject.Inject

class Logger @Inject constructor() {
    fun log(message: String) {
        if (BuildConfig.DEBUG) {
            Log.d(TAG, message)
        }
    }

    fun log(throwable: Throwable) {
        if (BuildConfig.DEBUG) {
            Log.e(TAG, null, throwable)
        }
    }

    companion object {
        private const val TAG = "Logger"
    }
}
