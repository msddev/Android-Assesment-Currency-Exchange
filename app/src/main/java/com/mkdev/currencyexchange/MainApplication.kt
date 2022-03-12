package com.mkdev.currencyexchange

import android.app.Application
import com.mkdev.currencyexchange.core.theme.ThemeUtils
import com.mkdev.presentation.utils.PresentationPreferencesHelper
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class MainApplication : Application() {

    @Inject
    lateinit var themeUtils: ThemeUtils

    @Inject
    lateinit var preferencesHelper: PresentationPreferencesHelper

    override fun onCreate() {
        super.onCreate()

        initNightMode()
    }

    /**
     * Initialize Night Mode based on user last saved state (day/night themes).
     */
    private fun initNightMode() {
        themeUtils.setNightMode(preferencesHelper.isNightMode)
    }
}