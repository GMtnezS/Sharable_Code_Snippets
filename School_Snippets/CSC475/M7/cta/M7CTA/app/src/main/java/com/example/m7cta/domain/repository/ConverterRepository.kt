package com.example.m7cta.domain.repository

import com.example.m7cta.domain.model.*
import javax.inject.Inject
import javax.inject.Singleton

interface ConverterRepository {
    fun convertTemperature(value: Double, from: TemperatureUnit, to: TemperatureUnit): Double
    fun convertLength(value: Double, from: LengthUnit, to: LengthUnit): Double
    fun convertWeight(value: Double, from: WeightUnit, to: WeightUnit): Double
    fun convertVolume(value: Double, from: VolumeUnit, to: VolumeUnit): Double
}

@Singleton
class ConverterRepositoryImpl @Inject constructor() : ConverterRepository {
    
    override fun convertTemperature(value: Double, from: TemperatureUnit, to: TemperatureUnit): Double {
        if (from == to) return value
        
        // Convert to Celsius first
        val celsius = when (from) {
            TemperatureUnit.CELSIUS -> value
            TemperatureUnit.FAHRENHEIT -> (value - 32) * 5 / 9
            TemperatureUnit.KELVIN -> value - 273.15
        }
        
        // Convert from Celsius to target
        return when (to) {
            TemperatureUnit.CELSIUS -> celsius
            TemperatureUnit.FAHRENHEIT -> celsius * 9 / 5 + 32
            TemperatureUnit.KELVIN -> celsius + 273.15
        }
    }
    
    override fun convertLength(value: Double, from: LengthUnit, to: LengthUnit): Double {
        if (from == to) return value
        
        // Convert to meters first
        val meters = when (from) {
            LengthUnit.METER -> value
            LengthUnit.KILOMETER -> value * 1000
            LengthUnit.CENTIMETER -> value / 100
            LengthUnit.MILE -> value * 1609.34
            LengthUnit.FOOT -> value * 0.3048
            LengthUnit.INCH -> value * 0.0254
        }
        
        // Convert from meters to target
        return when (to) {
            LengthUnit.METER -> meters
            LengthUnit.KILOMETER -> meters / 1000
            LengthUnit.CENTIMETER -> meters * 100
            LengthUnit.MILE -> meters / 1609.34
            LengthUnit.FOOT -> meters / 0.3048
            LengthUnit.INCH -> meters / 0.0254
        }
    }
    
    override fun convertWeight(value: Double, from: WeightUnit, to: WeightUnit): Double {
        if (from == to) return value
        
        // Convert to kilograms first
        val kilograms = when (from) {
            WeightUnit.KILOGRAM -> value
            WeightUnit.GRAM -> value / 1000
            WeightUnit.POUND -> value * 0.453592
            WeightUnit.OUNCE -> value * 0.0283495
        }
        
        // Convert from kilograms to target
        return when (to) {
            WeightUnit.KILOGRAM -> kilograms
            WeightUnit.GRAM -> kilograms * 1000
            WeightUnit.POUND -> kilograms / 0.453592
            WeightUnit.OUNCE -> kilograms / 0.0283495
        }
    }
    
    override fun convertVolume(value: Double, from: VolumeUnit, to: VolumeUnit): Double {
        if (from == to) return value
        
        // Convert to liters first
        val liters = when (from) {
            VolumeUnit.LITER -> value
            VolumeUnit.MILLILITER -> value / 1000
            VolumeUnit.GALLON -> value * 3.78541
            VolumeUnit.FLUID_OUNCE -> value * 0.0295735
        }
        
        // Convert from liters to target
        return when (to) {
            VolumeUnit.LITER -> liters
            VolumeUnit.MILLILITER -> liters * 1000
            VolumeUnit.GALLON -> liters / 3.78541
            VolumeUnit.FLUID_OUNCE -> liters / 0.0295735
        }
    }
}