
package com.example.m7cta.domain.repository

import com.example.m7cta.domain.model.*
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import kotlin.math.abs

class ConverterRepositoryTest {
    
    private lateinit var repository: ConverterRepository
    
    @Before
    fun setup() {
        repository = ConverterRepositoryImpl()
    }
    
    // Temperature Tests
    @Test
    fun `convertTemperature celsius to fahrenheit`() {
        val result = repository.convertTemperature(
            0.0,
            TemperatureUnit.CELSIUS,
            TemperatureUnit.FAHRENHEIT
        )
        assertEquals(32.0, result, 0.01)
    }
    
    @Test
    fun `convertTemperature fahrenheit to celsius`() {
        val result = repository.convertTemperature(
            32.0,
            TemperatureUnit.FAHRENHEIT,
            TemperatureUnit.CELSIUS
        )
        assertEquals(0.0, result, 0.01)
    }
    
    @Test
    fun `convertTemperature celsius to kelvin`() {
        val result = repository.convertTemperature(
            0.0,
            TemperatureUnit.CELSIUS,
            TemperatureUnit.KELVIN
        )
        assertEquals(273.15, result, 0.01)
    }
    
    @Test
    fun `convertTemperature kelvin to celsius`() {
        val result = repository.convertTemperature(
            273.15,
            TemperatureUnit.KELVIN,
            TemperatureUnit.CELSIUS
        )
        assertEquals(0.0, result, 0.01)
    }
    
    @Test
    fun `convertTemperature same unit returns same value`() {
        val result = repository.convertTemperature(
            100.0,
            TemperatureUnit.CELSIUS,
            TemperatureUnit.CELSIUS
        )
        assertEquals(100.0, result, 0.01)
    }
    
    @Test
    fun `convertTemperature boiling point celsius to fahrenheit`() {
        val result = repository.convertTemperature(
            100.0,
            TemperatureUnit.CELSIUS,
            TemperatureUnit.FAHRENHEIT
        )
        assertEquals(212.0, result, 0.01)
    }
    
    // Length Tests
    @Test
    fun `convertLength meter to kilometer`() {
        val result = repository.convertLength(
            1000.0,
            LengthUnit.METER,
            LengthUnit.KILOMETER
        )
        assertEquals(1.0, result, 0.01)
    }
    
    @Test
    fun `convertLength kilometer to meter`() {
        val result = repository.convertLength(
            1.0,
            LengthUnit.KILOMETER,
            LengthUnit.METER
        )
        assertEquals(1000.0, result, 0.01)
    }
    
    @Test
    fun `convertLength meter to foot`() {
        val result = repository.convertLength(
            1.0,
            LengthUnit.METER,
            LengthUnit.FOOT
        )
        assertEquals(3.28084, result, 0.01)
    }
    
    @Test
    fun `convertLength foot to meter`() {
        val result = repository.convertLength(
            3.28084,
            LengthUnit.FOOT,
            LengthUnit.METER
        )
        assertEquals(1.0, result, 0.01)
    }
    
    @Test
    fun `convertLength mile to kilometer`() {
        val result = repository.convertLength(
            1.0,
            LengthUnit.MILE,
            LengthUnit.KILOMETER
        )
        assertEquals(1.60934, result, 0.01)
    }
    
    @Test
    fun `convertLength centimeter to inch`() {
        val result = repository.convertLength(
            2.54,
            LengthUnit.CENTIMETER,
            LengthUnit.INCH
        )
        assertEquals(1.0, result, 0.01)
    }
    
    @Test
    fun `convertLength same unit returns same value`() {
        val result = repository.convertLength(
            100.0,
            LengthUnit.METER,
            LengthUnit.METER
        )
        assertEquals(100.0, result, 0.01)
    }
    
    // Weight Tests
    @Test
    fun `convertWeight kilogram to gram`() {
        val result = repository.convertWeight(
            1.0,
            WeightUnit.KILOGRAM,
            WeightUnit.GRAM
        )
        assertEquals(1000.0, result, 0.01)
    }
    
    @Test
    fun `convertWeight gram to kilogram`() {
        val result = repository.convertWeight(
            1000.0,
            WeightUnit.GRAM,
            WeightUnit.KILOGRAM
        )
        assertEquals(1.0, result, 0.01)
    }
    
    @Test
    fun `convertWeight kilogram to pound`() {
        val result = repository.convertWeight(
            1.0,
            WeightUnit.KILOGRAM,
            WeightUnit.POUND
        )
        assertEquals(2.20462, result, 0.01)
    }
    
    @Test
    fun `convertWeight pound to kilogram`() {
        val result = repository.convertWeight(
            2.20462,
            WeightUnit.POUND,
            WeightUnit.KILOGRAM
        )
        assertEquals(1.0, result, 0.01)
    }
    
    @Test
    fun `convertWeight ounce to gram`() {
        val result = repository.convertWeight(
            1.0,
            WeightUnit.OUNCE,
            WeightUnit.GRAM
        )
        assertEquals(28.3495, result, 0.01)
    }
    
    @Test
    fun `convertWeight same unit returns same value`() {
        val result = repository.convertWeight(
            100.0,
            WeightUnit.KILOGRAM,
            WeightUnit.KILOGRAM
        )
        assertEquals(100.0, result, 0.01)
    }
    
    // Volume Tests
    @Test
    fun `convertVolume liter to milliliter`() {
        val result = repository.convertVolume(
            1.0,
            VolumeUnit.LITER,
            VolumeUnit.MILLILITER
        )
        assertEquals(1000.0, result, 0.01)
    }
    
    @Test
    fun `convertVolume milliliter to liter`() {
        val result = repository.convertVolume(
            1000.0,
            VolumeUnit.MILLILITER,
            VolumeUnit.LITER
        )
        assertEquals(1.0, result, 0.01)
    }
    
    @Test
    fun `convertVolume liter to gallon`() {
        val result = repository.convertVolume(
            3.78541,
            VolumeUnit.LITER,
            VolumeUnit.GALLON
        )
        assertEquals(1.0, result, 0.01)
    }
    
    @Test
    fun `convertVolume gallon to liter`() {
        val result = repository.convertVolume(
            1.0,
            VolumeUnit.GALLON,
            VolumeUnit.LITER
        )
        assertEquals(3.78541, result, 0.01)
    }
    
    @Test
    fun `convertVolume fluid ounce to milliliter`() {
        val result = repository.convertVolume(
            1.0,
            VolumeUnit.FLUID_OUNCE,
            VolumeUnit.MILLILITER
        )
        assertEquals(29.5735, result, 0.01)
    }
    
    @Test
    fun `convertVolume same unit returns same value`() {
        val result = repository.convertVolume(
            100.0,
            VolumeUnit.LITER,
            VolumeUnit.LITER
        )
        assertEquals(100.0, result, 0.01)
    }
}