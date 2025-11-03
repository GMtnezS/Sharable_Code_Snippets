package com.example.m7cta.domain.model

enum class ConversionType(val displayName: String) {
    TEMPERATURE("Temperature"),
    LENGTH("Length"),
    WEIGHT("Weight"),
    VOLUME("Volume")
}

enum class TemperatureUnit(val displayName: String, val symbol: String) {
    CELSIUS("Celsius", "°C"),
    FAHRENHEIT("Fahrenheit", "°F"),
    KELVIN("Kelvin", "K")
}

enum class LengthUnit(val displayName: String, val symbol: String) {
    METER("Meter", "m"),
    KILOMETER("Kilometer", "km"),
    CENTIMETER("Centimeter", "cm"),
    MILE("Mile", "mi"),
    FOOT("Foot", "ft"),
    INCH("Inch", "in")
}

enum class WeightUnit(val displayName: String, val symbol: String) {
    KILOGRAM("Kilogram", "kg"),
    GRAM("Gram", "g"),
    POUND("Pound", "lb"),
    OUNCE("Ounce", "oz")
}

enum class VolumeUnit(val displayName: String, val symbol: String) {
    LITER("Liter", "L"),
    MILLILITER("Milliliter", "mL"),
    GALLON("Gallon", "gal"),
    FLUID_OUNCE("Fluid Ounce", "fl oz")
}

data class ConversionResult(
    val value: Double,
    val fromUnit: String,
    val toUnit: String,
    val result: Double
)