package com.mkdev.data.repository

import com.mkdev.data.models.RateEntity

interface RateRemote {
    suspend fun getRates(): List<RateEntity>
}
