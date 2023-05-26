package com.arcieri.wagner.mvvm_recipebook.model

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson

enum class MeasurementType() {
    LIQUID,
    WEIGHT,
    UNIT,
    OTHER;

    class MeasurementTypeAdapter {
        @FromJson
        fun fromJson(json: String): MeasurementType {
            return when (json) {
                "LIQUID" -> LIQUID
                "WEIGHT" -> WEIGHT
                "UNIT" -> UNIT
                "OTHER" -> OTHER
                else -> throw IllegalArgumentException("Unknown measurement type: $json")
            }
        }

        @ToJson
        fun toJson(measurementType: MeasurementType): String {
            return measurementType.name
        }
    }
}
