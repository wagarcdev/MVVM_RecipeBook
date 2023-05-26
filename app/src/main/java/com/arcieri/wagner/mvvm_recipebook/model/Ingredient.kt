package com.arcieri.wagner.mvvm_recipebook.model

import com.google.gson.annotations.JsonAdapter

data class Ingredient(


    var name: String,
    var weightInKg: Float? = null,
    var weightInGrams: Int? = null,
    var volumeInLiters: Float? = null,
    var volumeInMilliliters: Int? = null,
    var quantity: Double? = null,
    @JsonAdapter(MeasurementType.MeasurementTypeAdapter::class)
    var measurementType: MeasurementType? = null,
    var textHowToMeasure: String? = null
    ) {

    fun adjustMeasuringUnits(){
        when(measurementType){
            MeasurementType.LIQUID -> {
                if (volumeInLiters != null && volumeInMilliliters == null) {
                    volumeInMilliliters = volumeInLiters!!.toInt() * 1000
                }

                if (volumeInMilliliters != null && volumeInLiters == null) {
                    volumeInLiters = volumeInMilliliters!!.toFloat() / 1000
                }
            }
            MeasurementType.WEIGHT -> {
                if (weightInGrams != null && weightInKg == null) {
                    weightInKg = weightInGrams!!.toFloat() / 1000
                }

                if (weightInKg != null && weightInGrams == null) {
                    weightInGrams = weightInKg!!.toInt() * 1000
                }
            }
            else -> {}
        }
    }
}