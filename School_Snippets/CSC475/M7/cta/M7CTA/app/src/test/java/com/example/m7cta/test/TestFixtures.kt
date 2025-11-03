package com.example.m7cta.test

import com.example.m7cta.domain.model.*

/**
 * Test fixtures for creating test data
 */
object TestFixtures {
    
    // Temperature test data
    object Temperature {
        const val CELSIUS_ZERO = 0.0
        const val FAHRENHEIT_FREEZING = 32.0
        const val CELSIUS_BOILING = 100.0
        const val FAHRENHEIT_BOILING = 212.0
        const val KELVIN_ZERO = 273.15
        
        val celsiusUnit = TemperatureUnit.CELSIUS
        val fahrenheitUnit = TemperatureUnit.FAHRENHEIT
        val kelvinUnit = TemperatureUnit.KELVIN
    }
    
    // Length test data
    object Length {
        const val METER_ONE = 1.0
        const val KILOMETER_ONE = 1.0
        const val METER_IN_KILOMETER = 1000.0
        const val FOOT_PER_METER = 3.28084
        const val MILE_TO_KM = 1.60934
        const val INCH_TO_CM = 2.54
        
        val meterUnit = LengthUnit.METER
        val kilometerUnit = LengthUnit.KILOMETER
        val footUnit = LengthUnit.FOOT
        val mileUnit = LengthUnit.MILE
        val inchUnit = LengthUnit.INCH
        val centimeterUnit = LengthUnit.CENTIMETER
    }
    
    // Weight test data
    object Weight {
        const val KILOGRAM_ONE = 1.0
        const val GRAM_IN_KILOGRAM = 1000.0
        const val POUND_PER_KILOGRAM = 2.20462
        const val OUNCE_TO_GRAM = 28.3495
        
        val kilogramUnit = WeightUnit.KILOGRAM
        val gramUnit = WeightUnit.GRAM
        val poundUnit = WeightUnit.POUND
        val ounceUnit = WeightUnit.OUNCE
    }
    
    // Volume test data
    object Volume {
        const val LITER_ONE = 1.0
        const val MILLILITER_IN_LITER = 1000.0
        const val GALLON_TO_LITER = 3.78541
        const val FLUID_OUNCE_TO_ML = 29.5735
        
        val literUnit = VolumeUnit.LITER
        val milliliterUnit = VolumeUnit.MILLILITER
        val gallonUnit = VolumeUnit.GALLON
        val fluidOunceUnit = VolumeUnit.FLUID_OUNCE
    }
    
    // Conversion results
    fun createConversionResult(
        value: Double = 100.0,
        fromUnit: String = "Celsius",
        toUnit: String = "Fahrenheit",
        result: Double = 212.0
    ) = ConversionResult(
        value = value,
        fromUnit = fromUnit,
        toUnit = toUnit,
        result = result
    )
}