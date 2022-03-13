package com.mkdev.remote.models

import com.google.gson.annotations.SerializedName

data class RateResponse(
    @SerializedName("success")
    val success: Boolean,
    @SerializedName("timestamp")
    val timestamp: Int,
    @SerializedName("base")
    val base: String,
    @SerializedName("date")
    val date: String,
    @SerializedName("rates")
    val rates: HashMap<String, Double> = hashMapOf()
)