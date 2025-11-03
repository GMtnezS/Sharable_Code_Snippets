package com.example.m7cta.ui.viewmodel

import com.example.m7cta.domain.model.ConversionType
import com.example.m7cta.domain.usecase.ConvertValueUseCase
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class ConverterViewModelTest {
    
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
    fun `initial state is correct`() {
        val state = viewModel.uiState.value
        
        assertEquals(ConversionType.TEMPERATURE, state.conversionType)
        assertEquals("", state.inputValue)
        assertEquals("Celsius", state.fromUnit)
        assertEquals("Fahrenheit", state.toUnit)
        assertEquals("", state.result)
        assertNull(state.error)
        assertFalse(state.isLoading)
    }
    
    @Test
    fun `setConversionType to LENGTH updates state correctly`() {
        viewModel.setConversionType(ConversionType.LENGTH)
        
        val state = viewModel.uiState.value
        assertEquals(ConversionType.LENGTH, state.conversionType)
        assertEquals("Meter", state.fromUnit)
        assertEquals("Foot", state.toUnit)
    }
    
    @Test
    fun `setConversionType to WEIGHT updates state correctly`() {
        viewModel.setConversionType(ConversionType.WEIGHT)
        
        val state = viewModel.uiState.value
        assertEquals(ConversionType.WEIGHT, state.conversionType)
        assertEquals("Kilogram", state.fromUnit)
        assertEquals("Pound", state.toUnit)
    }
    
    @Test
    fun `setConversionType to VOLUME updates state correctly`() {
        viewModel.setConversionType(ConversionType.VOLUME)
        
        val state = viewModel.uiState.value
        assertEquals(ConversionType.VOLUME, state.conversionType)
        assertEquals("Liter", state.fromUnit)
        assertEquals("Gallon", state.toUnit)
    }
    
    @Test
    fun `setInputValue updates state`() {
        viewModel.setInputValue("100")
        
        assertEquals("100", viewModel.uiState.value.inputValue)
    }
    
    @Test
    fun `setInputValue with valid number triggers conversion`() = runTest {
        every {
            convertValueUseCase(any(), any(), any(), any())
        } returns Result.success(32.0)
        
        viewModel.setInputValue("0")
        advanceUntilIdle()
        
        val state = viewModel.uiState.value
        assertEquals("32.0000", state.result)
        assertNull(state.error)
    }
    
    @Test
    fun `setInputValue with empty string clears result`() {
        viewModel.setInputValue("")
        
        assertEquals("", viewModel.uiState.value.result)
    }
    
    @Test
    fun `setInputValue with invalid number shows error`() = runTest {
        viewModel.setInputValue("abc")
        advanceUntilIdle()
        
        val state = viewModel.uiState.value
        assertEquals("Invalid input", state.error)
        assertEquals("", state.result)
    }
    
    @Test
    fun `setFromUnit updates state and triggers conversion`() = runTest {
        every {
            convertValueUseCase(any(), any(), any(), any())
        } returns Result.success(212.0)
        
        viewModel.setInputValue("100")
        advanceUntilIdle()
        
        viewModel.setFromUnit("Celsius")
        advanceUntilIdle()
        
        assertEquals("Celsius", viewModel.uiState.value.fromUnit)
        verify(atLeast = 1) { convertValueUseCase(any(), any(), any(), any()) }
    }
    
    @Test
    fun `setToUnit updates state and triggers conversion`() = runTest {
        every {
            convertValueUseCase(any(), any(), any(), any())
        } returns Result.success(100.0)
        
        viewModel.setInputValue("100")
        advanceUntilIdle()
        
        viewModel.setToUnit("Kelvin")
        advanceUntilIdle()
        
        assertEquals("Kelvin", viewModel.uiState.value.toUnit)
        verify(atLeast = 1) { convertValueUseCase(any(), any(), any(), any()) }
    }
    
    @Test
    fun `swapUnits swaps from and to units`() {
        val initialFrom = viewModel.uiState.value.fromUnit
        val initialTo = viewModel.uiState.value.toUnit
        
        viewModel.swapUnits()
        
        val state = viewModel.uiState.value
        assertEquals(initialTo, state.fromUnit)
        assertEquals(initialFrom, state.toUnit)
    }
    
    @Test
    fun `swapUnits with input value triggers conversion`() = runTest {
        every {
            convertValueUseCase(any(), any(), any(), any())
        } returns Result.success(0.0)
        
        viewModel.setInputValue("32")
        advanceUntilIdle()
        
        viewModel.swapUnits()
        advanceUntilIdle()
        
        verify(atLeast = 2) { convertValueUseCase(any(), any(), any(), any()) }
    }
    
    @Test
    fun `conversion failure updates error state`() = runTest {
        every {
            convertValueUseCase(any(), any(), any(), any())
        } returns Result.failure(Exception("Conversion error"))
        
        viewModel.setInputValue("100")
        advanceUntilIdle()
        
        val state = viewModel.uiState.value
        assertEquals("Conversion error", state.error)
        assertEquals("", state.result)
        assertFalse(state.isLoading)
    }
    
    @Test
    fun `getAvailableUnits returns correct units for TEMPERATURE`() {
        viewModel.setConversionType(ConversionType.TEMPERATURE)
        
        val units = viewModel.getAvailableUnits()
        assertEquals(3, units.size)
        assertTrue(units.contains("Celsius"))
        assertTrue(units.contains("Fahrenheit"))
        assertTrue(units.contains("Kelvin"))
    }
    
    @Test
    fun `getAvailableUnits returns correct units for LENGTH`() {
        viewModel.setConversionType(ConversionType.LENGTH)
        
        val units = viewModel.getAvailableUnits()
        assertEquals(6, units.size)
        assertTrue(units.contains("Meter"))
        assertTrue(units.contains("Kilometer"))
        assertTrue(units.contains("Foot"))
    }
    
    @Test
    fun `getAvailableUnits returns correct units for WEIGHT`() {
        viewModel.setConversionType(ConversionType.WEIGHT)
        
        val units = viewModel.getAvailableUnits()
        assertEquals(4, units.size)
        assertTrue(units.contains("Kilogram"))
        assertTrue(units.contains("Pound"))
    }
    
    @Test
    fun `getAvailableUnits returns correct units for VOLUME`() {
        viewModel.setConversionType(ConversionType.VOLUME)
        
        val units = viewModel.getAvailableUnits()
        assertEquals(4, units.size)
        assertTrue(units.contains("Liter"))
        assertTrue(units.contains("Gallon"))
    }
    
    @Test
    fun `successful conversion formats result to 4 decimal places`() = runTest {
        every {
            convertValueUseCase(any(), any(), any(), any())
        } returns Result.success(3.14159265359)
        
        viewModel.setInputValue("1")
        advanceUntilIdle()
        
        assertEquals("3.1416", viewModel.uiState.value.result)
    }
}