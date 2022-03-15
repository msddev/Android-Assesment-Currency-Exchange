package com.mkdev.currencyexchange.extension

import android.app.Activity
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment

fun Fragment.hideKeyboard() {
    activity?.let { activity ->
        val imm = activity.getSystemService(Activity.INPUT_METHOD_SERVICE)
                as InputMethodManager
        activity.currentFocus?.let {
            imm.hideSoftInputFromWindow(it.windowToken, 0)
        }
    }
}