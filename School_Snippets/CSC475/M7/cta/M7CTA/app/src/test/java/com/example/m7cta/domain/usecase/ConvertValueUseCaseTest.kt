package com.example.m7cta.domain.usecase

import com.example.m7cta.domain.model.*
import com.example.m7cta.domain.repository.ConverterRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class ConvertValueUseCaseTest {
    
    private lateinit var repository: ConverterRepository
    private lateinit var useCase: ConvertValueUseCase
    
    @Before
    fun setup() {
        repository = mockk()
        useCase = ConvertValueUseCase(repository)
    }
    
    @Test
    fun `invoke with valid temperature conversion returns success`() {
        every {
            repository.convertTemperature(
                0.0,
                TemperatureUnit.CELSIUS,
                TemperatureUnit.FAHRENHEIT
            )
        } returns 32.0
        
        val result = useCase(
            value = 0.0,
            conversionType = ConversionType.TEMPERATURE,
            fromUnit = "Celsius",
            toUnit = "Fahrenheit"
        )
        
        assertTrue(result.isSuccess)
        assertEquals(32.0, result.getOrNull()!!, 0.01)
        verify {
            repository.convertTemperature(
                0.0,
                TemperatureUnit.CELSIUS,
                TemperatureUnit.FAHRENHEIT
            )
        }
    }
    
    @Test
    fun `invoke with valid length conversion returns success`() {
        every {
            repository.convertLength(
                1.0,
                LengthUnit.METER,
                LengthUnit.FOOT
            )
        } returns 3.28084
        
        val result = useCase(
            value = 1.0,
            conversionType = ConversionType.LENGTH,
            fromUnit = "Meter",
            toUnit = "Foot"
        )
        
        assertTrue(result.isSuccess)
        assertEquals(3.28084, result.getOrNull()!!, 0.01)
    }
    
    @Test
    fun `invoke with valid weight conversion returns success`() {
        every {
            repository.convertWeight(
                1.0,
                WeightUnit.KILOGRAM,
                WeightUnit.POUND
            )
        } returns 2.20462
        
        val result = useCase(
            value = 1.0,
            conversionType = ConversionType.WEIGHT,
            fromUnit = "Kilogram",
            toUnit = "Pound"
        )
        
        assertTrue(result.isSuccess)
        assertEquals(2.20462, result.getOrNull()!!, 0.01)
    }
    
    @Test
    fun `invoke with valid volume conversion returns success`() {
        every {
            repository.convertVolume(
                1.0,
                VolumeUnit.LITER,
                VolumeUnit.GALLON
            )
        } returns 0.264172
        
        val result = useCase(
            value = 1.0,
            conversionType = ConversionType.VOLUME,
            fromUnit = "Liter",
            toUnit = "Gallon"
        )
        
        assertTrue(result.isSuccess)
        assertEquals(0.264172, result.getOrNull()!!, 0.01)
    }
    
    @Test
    fun `invoke with NaN value returns failure`() {
        val result = useCase(
            value = Double.NaN,
            conversionType = ConversionType.TEMPERATURE,
            fromUnit = "Celsius",
            toUnit = "Fahrenheit"
        )
        
        assertTrue(result.isFailure)
        assertEquals("Invalid input value", result.exceptionOrNull()?.message)
    }
    
    @Test
    fun `invoke with infinite value returns failure`() {
        val result = useCase(
            value = Double.POSITIVE_INFINITY,
            conversionType = ConversionType.TEMPERATURE,
            fromUnit = "Celsius",
            toUnit = "Fahrenheit"
        )
        
        assertTrue(result.isFailure)
        assertEquals("Invalid input value", result.exceptionOrNull()?.message)
    }
    
    @Test
    fun `invoke with invalid from unit returns failure`() {
        val result = useCase(
            value = 100.0,
            conversionType = ConversionType.TEMPERATURE,
            fromUnit = "InvalidUnit",
            toUnit = "Fahrenheit"
        )
        
        assertTrue(result.isFailure)
        assertEquals("Invalid from unit", result.exceptionOrNull()?.message)
    }
    
    @Test
    fun `invoke with invalid to unit returns failure`() {
        val result = useCase(
            value = 100.0,
            conversionType = ConversionType.TEMPERATURE,
            fromUnit = "Celsius",
            toUnit = "InvalidUnit"
        )
        
        assertTrue(result.isFailure)
        assertEquals("Invalid to unit", result.exceptionOrNull()?.message)
    }
    
    @Test
    fun `invoke with repository exception returns failure`() {
        every {
            repository.convertTemperature(any(), any(), any())
        } throws RuntimeException("Repository error")
        
        val result = useCase(
            value = 0.0,
            conversionType = ConversionType.TEMPERATURE,
            fromUnit = "Celsius",
            toUnit = "Fahrenheit"
        )
        
        assertTrue(result.isFailure)
        assertEquals("Repository error", result.exceptionOrNull()?.message)
    }
}