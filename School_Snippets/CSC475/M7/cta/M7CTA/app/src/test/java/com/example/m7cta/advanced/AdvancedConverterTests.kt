package com.example.m7cta.advanced

import com.example.m7cta.domain.model.*
import com.example.m7cta.domain.repository.ConverterRepositoryImpl
import com.example.m7cta.domain.usecase.ConvertValueUseCase
import com.example.m7cta.test.TestFixtures
import com.example.m7cta.test.assertEqualsWithDelta
import com.example.m7cta.test.assertFailure
import com.example.m7cta.test.assertSuccess
import com.example.m7cta.ui.viewmodel.ConverterViewModel
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

/**
 * Advanced test scenarios using test fixtures and utilities
 */
class AdvancedConverterTests {
    
    private lateinit var repository: ConverterRepositoryImpl
    
    @Before
    fun setup() {
        repository = ConverterRepositoryImpl()
    }
    
    // Parameterized-style tests using test fixtures
    @Test
    fun `test all temperature conversions with fixtures`() {
        val testCases = listOf(
            Triple(
                TestFixtures.Temperature.CELSIUS_ZERO,
                TestFixtures.Temperature.celsiusUnit,
                TestFixtures.Temperature.fahrenheitUnit
            ) to TestFixtures.Temperature.FAHRENHEIT_FREEZING,
            
            Triple(
                TestFixtures.Temperature.CELSIUS_BOILING,
                TestFixtures.Temperature.celsiusUnit,
                TestFixtures.Temperature.fahrenheitUnit
            ) to TestFixtures.Temperature.FAHRENHEIT_BOILING,
            
            Triple(
                TestFixtures.Temperature.CELSIUS_ZERO,
                TestFixtures.Temperature.celsiusUnit,
                TestFixtures.Temperature.kelvinUnit
            ) to TestFixtures.Temperature.KELVIN_ZERO
        )
        
        testCases.forEach { (input, expected) ->
            val (value, from, to) = input
            val result = repository.convertTemperature(value, from, to)
            assertEqualsWithDelta(expected, result, 0.01, "Failed for $value $from to $to")
        }
    }
    
    @Test
    fun `test all length conversions with fixtures`() {
        val testCases = mapOf(
            Triple(
                TestFixtures.Length.METER_ONE,
                TestFixtures.Length.meterUnit,
                TestFixtures.Length.footUnit
            ) to TestFixtures.Length.FOOT_PER_METER,
            
            Triple(
                TestFixtures.Length.KILOMETER_ONE,
                TestFixtures.Length.kilometerUnit,
                TestFixtures.Length.meterUnit
            ) to TestFixtures.Length.METER_IN_KILOMETER,
            
            Triple(
                TestFixtures.Length.MILE_TO_KM,
                TestFixtures.Length.kilometerUnit,
                TestFixtures.Length.mileUnit
            ) to 1.0
        )
        
        testCases.forEach { (input, expected) ->
            val (value, from, to) = input
            val result = repository.convertLength(value, from, to)
            assertEqualsWithDelta(expected, result, 0.01)
        }
    }
    
    @Test
    fun `test conversion chain equivalence`() {
        // Convert through multiple units and back should equal original
        val original = 100.0
        
        // Celsius -> Fahrenheit -> Kelvin -> Celsius
        val toFahrenheit = repository.convertTemperature(
            original,
            TemperatureUnit.CELSIUS,
            TemperatureUnit.FAHRENHEIT
        )
        val toKelvin = repository.convertTemperature(
            toFahrenheit,
            TemperatureUnit.FAHRENHEIT,
            TemperatureUnit.KELVIN
        )
        val backToCelsius = repository.convertTemperature(
            toKelvin,
            TemperatureUnit.KELVIN,
            TemperatureUnit.CELSIUS
        )
        
        assertEqualsWithDelta(original, backToCelsius, 0.01)
    }
    
    @Test
    fun `test symmetry of conversions`() {
        val value = 50.0
        
        // Forward conversion
        val forward = repository.convertTemperature(
            value,
            TemperatureUnit.CELSIUS,
            TemperatureUnit.FAHRENHEIT
        )
        
        // Backward conversion
        val backward = repository.convertTemperature(
            forward,
            TemperatureUnit.FAHRENHEIT,
            TemperatureUnit.CELSIUS
        )
        
        assertEqualsWithDelta(value, backward, 0.01)
    }
    
    @Test
    fun `test precision with very large numbers`() {
        val largeValue = 1000000.0
        val result = repository.convertLength(
            largeValue,
            LengthUnit.METER,
            LengthUnit.KILOMETER
        )
        
        assertEquals(1000.0, result, 0.01)
    }
    
    @Test
    fun `test precision with very small numbers`() {
        val smallValue = 0.000001
        val result = repository.convertWeight(
            smallValue,
            WeightUnit.KILOGRAM,
            WeightUnit.GRAM
        )
        
        assertEquals(0.001, result, 0.000001)
    }
    
    @Test
    fun `test boundary values`() {
        // Test with zero
        val zeroResult = repository.convertLength(
            0.0,
            LengthUnit.METER,
            LengthUnit.FOOT
        )
        assertEquals(0.0, zeroResult, 0.01)
        
        // Test with negative (valid for temperature)
        val negativeResult = repository.convertTemperature(
            -40.0,
            TemperatureUnit.CELSIUS,
            TemperatureUnit.FAHRENHEIT
        )
        assertEquals(-40.0, negativeResult, 0.01)
    }
}

/**
 * Advanced use case tests with custom assertions
 */
class AdvancedUseCaseTests {
    
    private lateinit var repository: ConverterRepositoryImpl
    private lateinit var useCase: ConvertValueUseCase
    
    @Before
    fun setup() {
        repository = ConverterRepositoryImpl()
        useCase = ConvertValueUseCase(repository)
    }
    
    @Test
    fun `useCase with valid input returns success using custom assertion`() {
        val result = useCase(
            value = TestFixtures.Temperature.CELSIUS_ZERO,
            conversionType = ConversionType.TEMPERATURE,
            fromUnit = TestFixtures.Temperature.celsiusUnit.displayName,
            toUnit = TestFixtures.Temperature.fahrenheitUnit.displayName
        )
        
        val value = result.assertSuccess()
        assertEqualsWithDelta(TestFixtures.Temperature.FAHRENHEIT_FREEZING, value)
    }
    
    @Test
    fun `useCase with NaN returns failure with custom assertion`() {
        val result = useCase(
            value = Double.NaN,
            conversionType = ConversionType.TEMPERATURE,
            fromUnit = "Celsius",
            toUnit = "Fahrenheit"
        )
        
        val exception = result.assertFailure()
        assertEquals("Invalid input value", exception.message)
    }
    
    @Test
    fun `useCase handles all conversion types correctly`() {
        val testCases = listOf(
            ConversionType.TEMPERATURE to listOf("Celsius", "Fahrenheit"),
            ConversionType.LENGTH to listOf("Meter", "Foot"),
            ConversionType.WEIGHT to listOf("Kilogram", "Pound"),
            ConversionType.VOLUME to listOf("Liter", "Gallon")
        )
        
        testCases.forEach { (type, units) ->
            val result = useCase(
                value = 1.0,
                conversionType = type,
                fromUnit = units[0],
                toUnit = units[1]
            )
            
            assertTrue("$type conversion failed", result.isSuccess)
            assertNotNull(result.getOrNull())
        }
    }
}

/**
 * Advanced ViewModel tests with flow testing
 */
@OptIn(ExperimentalCoroutinesApi::class)
class AdvancedViewModelTests {
    
    private val testDispatcher = StandardTestDispatcher()
    private lateinit var convertValueUseCase: ConvertValueUseCase
    private lateinit var viewModel: ConverterViewModel
    
    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        convertValueUseCase = mockk()
        viewModel = ConverterViewModel(convertValueUseCase)
    }
    
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
    
    @Test
    fun `viewModel state updates correctly through multiple operations`() = runTest {
        every { convertValueUseCase(any(), any(), any(), any()) } returns Result.success(100.0)
        
        // Initial state
        assertEquals("", viewModel.uiState.value.inputValue)
        
        // Set input
        viewModel.setInputValue("50")
        advanceUntilIdle()
        assertEquals("50", viewModel.uiState.value.inputValue)
        
        // Change type
        viewModel.setConversionType(ConversionType.LENGTH)
        assertEquals(ConversionType.LENGTH, viewModel.uiState.value.conversionType)
        
        // Swap units
        val originalFrom = viewModel.uiState.value.fromUnit
        val originalTo = viewModel.uiState.value.toUnit
        viewModel.swapUnits()
        assertEquals(originalTo, viewModel.uiState.value.fromUnit)
        assertEquals(originalFrom, viewModel.uiState.value.toUnit)
    }
    
    @Test
    fun `viewModel handles rapid state changes`() = runTest {
        every { convertValueUseCase(any(), any(), any(), any()) } returns Result.success(100.0)
        
        // Rapidly change input values
        repeat(10) {
            viewModel.setInputValue(it.toString())
            advanceUntilIdle()
        }
        
        // Should handle gracefully
        assertEquals("9", viewModel.uiState.value.inputValue)
        assertFalse(viewModel.uiState.value.isLoading)
    }
    
    @Test
    fun `viewModel handles conversion type changes with existing input`() = runTest {
        every { convertValueUseCase(any(), any(), any(), any()) } returns Result.success(50.0)
        
        viewModel.setInputValue("100")
        advanceUntilIdle()
        
        // Change types and verify conversions are triggered
        ConversionType.values().forEach { type ->
            viewModel.setConversionType(type)
            advanceUntilIdle()
            
            assertEquals(type, viewModel.uiState.value.conversionType)
            assertNotEquals("", viewModel.uiState.value.result)
        }
    }
}