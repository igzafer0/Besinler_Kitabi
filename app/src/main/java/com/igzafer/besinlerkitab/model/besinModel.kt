package com.igzafer.besinlerkitab.model

import com.google.gson.annotations.SerializedName

data class besinModel(
    @SerializedName("isim")
    val besinIsim: String?,
    @SerializedName("kalori")
    val besinKalori: String?,
    @SerializedName("karbonhidrat")
    val besinKarbonhidrat: String?,
    @SerializedName("protein")
    val besinProtein: String?,
    @SerializedName("yag")
    val besinYag: String?,
    @SerializedName("gorsel")
    val besinGorsel: String?,
) {

}