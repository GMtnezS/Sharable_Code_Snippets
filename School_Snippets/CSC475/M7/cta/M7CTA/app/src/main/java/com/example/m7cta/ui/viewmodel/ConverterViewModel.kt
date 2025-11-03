package com.example.m7cta.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.m7cta.domain.model.*
import com.example.m7cta.domain.usecase.ConvertValueUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class ConverterUiState(
    val conversionType: ConversionType = ConversionType.TEMPERATURE,
    val inputValue: String = "",
    val fromUnit: String = TemperatureUnit.CELSIUS.displayName,
    val toUnit: String = TemperatureUnit.FAHRENHEIT.displayName,
    val result: String = "",
    val error: String? = null,
    val isLoading: Boolean = false
)

@HiltViewModel
class ConverterViewModel @Inject constructor(
    private val convertValueUseCase: ConvertValueUseCase
) : ViewModel() {
    
    private val _uiState = MutableStateFlow(ConverterUiState())
    val uiState: StateFlow<ConverterUiState> = _uiState.asStateFlow()
    
    fun setConversionType(type: ConversionType) {
        val (defaultFrom, defaultTo) = when (type) {
            ConversionType.TEMPERATURE -> 
                TemperatureUnit.CELSIUS.displayName to TemperatureUnit.FAHRENHEIT.displayName
            ConversionType.LENGTH -> 
                LengthUnit.METER.displayName to LengthUnit.FOOT.displayName
            ConversionType.WEIGHT -> 
                WeightUnit.KILOGRAM.displayName to WeightUnit.POUND.displayName
            ConversionType.VOLUME -> 
                VolumeUnit.LITER.displayName to VolumeUnit.GALLON.displayName
        }
        
        _uiState.update {
            it.copy(
                conversionType = type,
                fromUnit = defaultFrom,
                toUnit = defaultTo,
                result = "",
                error = null
            )
        }
        
        if (_uiState.value.inputValue.isNotEmpty()) {
            convert()
        }
    }
    
    fun setInputValue(value: String) {
        _uiState.update { it.copy(inputValue = value, error = null) }
        if (value.isNotEmpty()) {
            convert()
        } else {
            _uiState.update { it.copy(result = "") }
        }
    }
    
    fun setFromUnit(unit: String) {
        _uiState.update { it.copy(fromUnit = unit) }
        if (_uiState.value.inputValue.isNotEmpty()) {
            convert()
        }
    }
    
    fun setToUnit(unit: String) {
        _uiState.update { it.copy(toUnit = unit) }
        if (_uiState.value.inputValue.isNotEmpty()) {
            convert()
        }
    }
    
    fun swapUnits() {
        val currentFrom = _uiState.value.fromUnit
        val currentTo = _uiState.value.toUnit
        _uiState.update { 
            it.copy(
                fromUnit = currentTo,
                toUnit = currentFrom
            )
        }
        if (_uiState.value.inputValue.isNotEmpty()) {
            convert()
        }
    }
    
    private fun convert() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }
            
            val inputValue = _uiState.value.inputValue.toDoubleOrNull()
            if (inputValue == null) {
                _uiState.update { 
                    it.copy(
                        error = "Invalid input",
                        result = "",
                        isLoading = false
                    )
                }
                return@launch
            }
            
            val result = convertValueUseCase(
                value = inputValue,
                conversionType = _uiState.value.conversionType,
                fromUnit = _uiState.value.fromUnit,
                toUnit = _uiState.value.toUnit
            )
            
            result.fold(
                onSuccess = { convertedValue ->
                    val formatted = String.format("%.4f", convertedValue)
                    _uiState.update { 
                        it.copy(
                            result = formatted,
                            error = null,
                            isLoading = false
                        )
                    }
                },
                onFailure = { exception ->
                    _uiState.update { 
                        it.copy(
                            error = exception.message ?: "Conversion failed",
                            result = "",
                            isLoading = false
                        )
                    }
                }
            )
        }
    }
    
    fun getAvailableUnits(): List<String> {
        return when (_uiState.value.conversionType) {
            ConversionType.TEMPERATURE -> TemperatureUnit.values().map { it.displayName }
            ConversionType.LENGTH -> LengthUnit.values().map { it.displayName }
            ConversionType.WEIGHT -> WeightUnit.values().map { it.displayName }
            ConversionType.VOLUME -> VolumeUnit.values().map { it.displayName }
        }
    }
}