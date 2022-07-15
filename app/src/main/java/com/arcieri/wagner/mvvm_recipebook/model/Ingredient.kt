package com.arcieri.wagner.mvvm_recipebook.model

import android.os.Parcelable

data class Ingredient(


    var name: String,
    var weightInKg: Float? = null,
    var weightInGrams: Int? = null,
    var volumeInLiters: Float? = null,
    var volumeInMilliliters: Int? = null,
    var quantity: Double? = null,
    var isLiquid: Boolean,
    var isUnit: Boolean,
    var isWeight: Boolean,
    var textHowToMeasure: String? = null
    ) {

    fun adjustMeasuringUnits(){

        if (!isUnit) {
            if (isLiquid) {
                if (volumeInLiters != null && volumeInMilliliters == null) {
                    volumeInMilliliters = volumeInLiters!!.toInt() * 1000
                }

                if (volumeInMilliliters != null && volumeInLiters == null) {
                    volumeInLiters = volumeInMilliliters!!.toFloat() / 1000
                }
                return
            }

            if (weightInGrams != null && weightInKg == null) {
                weightInKg = weightInGrams!!.toFloat() / 1000
            }

            if (weightInKg != null && weightInGrams == null) {
                weightInGrams = weightInKg!!.toInt() * 1000
            }
        }






    }

}