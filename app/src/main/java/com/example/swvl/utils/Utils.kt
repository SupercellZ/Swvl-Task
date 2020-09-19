package com.example.swvl.utils

import com.example.swvl.utils.callbacks.functions.Func1

class Utils {

    companion object {

        fun runWithCaution(
            action: () -> Unit,
            exceptionCallback: Func1<Exception>? = null
        ) {
            try {
                action.invoke()
            } catch (e: Exception) {
                e.printStackTrace()
                exceptionCallback?.apply(e)
            }
        }

    }

}