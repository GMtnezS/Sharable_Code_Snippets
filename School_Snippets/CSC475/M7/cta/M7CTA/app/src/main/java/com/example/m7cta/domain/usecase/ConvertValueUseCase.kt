
package com.example.m7cta.domain.usecase

import com.example.m7cta.domain.model.*
import com.example.m7cta.domain.repository.ConverterRepository
import javax.inject.Inject

class ConvertValueUseCase @Inject constructor(
    private val repository: ConverterRepository
) {
    operator fun invoke(
        value: Double,
        conversionType: ConversionType,
        fromUnit: String,
        toUnit: String
    ): Result<Double> {
        return try {
            if (value.isNaN() || value.isInfinite()) {
                return Result.failure(IllegalArgumentException("Invalid input value"))
            }
            
            val result = when (conversionType) {
                ConversionType.TEMPERATURE -> {
                    val from = TemperatureUnit.values().find { it.displayName == fromUnit }
                        ?: return Result.failure(IllegalArgumentException("Invalid from unit"))
                    val to = TemperatureUnit.values().find { it.displayName == toUnit }
                        ?: return Result.failure(IllegalArgumentException("Invalid to unit"))
                    repository.convertTemperature(value, from, to)
                }
                ConversionType.LENGTH -> {
                    val from = LengthUnit.values().find { it.displayName == fromUnit }
                        ?: return Result.failure(IllegalArgumentException("Invalid from unit"))
                    val to = LengthUnit.values().find { it.displayName == toUnit }
                        ?: return Result.failure(IllegalArgumentException("Invalid to unit"))
                    repository.convertLength(value, from, to)
                }
                ConversionType.WEIGHT -> {
                    val from = WeightUnit.values().find { it.displayName == fromUnit }
                        ?: return Result.failure(IllegalArgumentException("Invalid from unit"))
                    val to = WeightUnit.values().find { it.displayName == toUnit }
                        ?: return Result.failure(IllegalArgumentException("Invalid to unit"))
                    repository.convertWeight(value, from, to)
                }
                ConversionType.VOLUME -> {
                    val from = VolumeUnit.values().find { it.displayName == fromUnit }
                        ?: return Result.failure(IllegalArgumentException("Invalid from unit"))
                    val to = VolumeUnit.values().find { it.displayName == toUnit }
                        ?: return Result.failure(IllegalArgumentException("Invalid to unit"))
                    repository.convertVolume(value, from, to)
                }
            }
            
            Result.success(result)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}