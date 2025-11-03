package com.example.m7cta.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.SwapVert
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.m7cta.domain.model.ConversionType
import com.example.m7cta.ui.viewmodel.ConverterViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConverterScreen(
    modifier: Modifier = Modifier,
    viewModel: ConverterViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val availableUnits = viewModel.getAvailableUnits()
    
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Unit Converter",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.testTag("screen_title")
        )
        
        // Conversion Type Selector
        var showTypeMenu by remember { mutableStateOf(false) }
        ExposedDropdownMenuBox(
            expanded = showTypeMenu,
            onExpandedChange = { showTypeMenu = it },
            modifier = Modifier.testTag("conversion_type_dropdown")
        ) {
            OutlinedTextField(
                value = uiState.conversionType.displayName,
                onValueChange = {},
                readOnly = true,
                label = { Text("Conversion Type") },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = showTypeMenu) },
                modifier = Modifier
                    .fillMaxWidth()
                    .menuAnchor()
            )
            ExposedDropdownMenu(
                expanded = showTypeMenu,
                onDismissRequest = { showTypeMenu = false }
            ) {
                ConversionType.values().forEach { type ->
                    DropdownMenuItem(
                        text = { Text(type.displayName) },
                        onClick = {
                            viewModel.setConversionType(type)
                            showTypeMenu = false
                        },
                        modifier = Modifier.testTag("type_item_${type.name}")
                    )
                }
            }
        }
        
        // Input Value
        OutlinedTextField(
            value = uiState.inputValue,
            onValueChange = { viewModel.setInputValue(it) },
            label = { Text("Enter Value") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
            isError = uiState.error != null,
            supportingText = if (uiState.error != null) {
                { Text(uiState.error!!) }
            } else null,
            modifier = Modifier
                .fillMaxWidth()
                .testTag("input_value_field")
        )
        
        // From Unit
        var showFromMenu by remember { mutableStateOf(false) }
        ExposedDropdownMenuBox(
            expanded = showFromMenu,
            onExpandedChange = { showFromMenu = it },
            modifier = Modifier.testTag("from_unit_dropdown")
        ) {
            OutlinedTextField(
                value = uiState.fromUnit,
                onValueChange = {},
                readOnly = true,
                label = { Text("From") },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = showFromMenu) },
                modifier = Modifier
                    .fillMaxWidth()
                    .menuAnchor()
            )
            ExposedDropdownMenu(
                expanded = showFromMenu,
                onDismissRequest = { showFromMenu = false }
            ) {
                availableUnits.forEach { unit ->
                    DropdownMenuItem(
                        text = { Text(unit) },
                        onClick = {
                            viewModel.setFromUnit(unit)
                            showFromMenu = false
                        },
                        modifier = Modifier.testTag("from_unit_item_$unit")
                    )
                }
            }
        }
        
        // Swap Button
        IconButton(
            onClick = { viewModel.swapUnits() },
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .testTag("swap_button")
        ) {
            Icon(Icons.Default.SwapVert, contentDescription = "Swap units")
        }
        
        // To Unit
        var showToMenu by remember { mutableStateOf(false) }
        ExposedDropdownMenuBox(
            expanded = showToMenu,
            onExpandedChange = { showToMenu = it },
            modifier = Modifier.testTag("to_unit_dropdown")
        ) {
            OutlinedTextField(
                value = uiState.toUnit,
                onValueChange = {},
                readOnly = true,
                label = { Text("To") },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = showToMenu) },
                modifier = Modifier
                    .fillMaxWidth()
                    .menuAnchor()
            )
            ExposedDropdownMenu(
                expanded = showToMenu,
                onDismissRequest = { showToMenu = false }
            ) {
                availableUnits.forEach { unit ->
                    DropdownMenuItem(
                        text = { Text(unit) },
                        onClick = {
                            viewModel.setToUnit(unit)
                            showToMenu = false
                        },
                        modifier = Modifier.testTag("to_unit_item_$unit")
                    )
                }
            }
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Result
        if (uiState.result.isNotEmpty()) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("result_card")
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        text = "Result",
                        style = MaterialTheme.typography.labelLarge
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "${uiState.result} ${uiState.toUnit}",
                        style = MaterialTheme.typography.headlineMedium,
                        modifier = Modifier.testTag("result_text")
                    )
                }
            }
        }
        
        if (uiState.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .testTag("loading_indicator")
            )
        }
    }
}