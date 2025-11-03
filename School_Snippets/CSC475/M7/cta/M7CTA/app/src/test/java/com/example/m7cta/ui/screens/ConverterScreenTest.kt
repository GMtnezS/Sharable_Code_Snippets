package com.example.m7cta.ui.screens

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import com.example.m7cta.domain.model.ConversionType
import com.example.m7cta.domain.usecase.ConvertValueUseCase
import com.example.m7cta.ui.theme.UnitConverterTheme
import com.example.m7cta.ui.viewmodel.ConverterViewModel
import io.mockk.every
import io.mockk.mockk
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ConverterScreenTest {
    
    @get:Rule
    val composeTestRule = createComposeRule()
    
    private lateinit var convertValueUseCase: ConvertValueUseCase
    private lateinit var viewModel: ConverterViewModel
    
    @Before
    fun setup() {
        convertValueUseCase = mockk(relaxed = true)
        viewModel = ConverterViewModel(convertValueUseCase)
    }
    
    @Test
    fun screenDisplaysTitle() {
        composeTestRule.setContent {
            UnitConverterTheme {
                ConverterScreen(viewModel = viewModel)
            }
        }
        
        composeTestRule.onNodeWithTag("screen_title")
            .assertExists()
            .assertIsDisplayed()
            .assertTextEquals("Unit Converter")
    }
    
    @Test
    fun screenDisplaysAllInputFields() {
        composeTestRule.setContent {
            UnitConverterTheme {
                ConverterScreen(viewModel = viewModel)
            }
        }
        
        composeTestRule.onNodeWithTag("conversion_type_dropdown").assertExists()
        composeTestRule.onNodeWithTag("input_value_field").assertExists()
        composeTestRule.onNodeWithTag("from_unit_dropdown").assertExists()
        composeTestRule.onNodeWithTag("to_unit_dropdown").assertExists()
        composeTestRule.onNodeWithTag("swap_button").assertExists()
    }
    
    @Test
    fun inputFieldAcceptsText() {
        composeTestRule.setContent {
            UnitConverterTheme {
                ConverterScreen(viewModel = viewModel)
            }
        }
        
        composeTestRule.onNodeWithTag("input_value_field")
            .performTextInput("100")
        
        composeTestRule.onNodeWithTag("input_value_field")
            .assertTextContains("100")
    }
    
    @Test
    fun swapButtonIsClickable() {
        composeTestRule.setContent {
            UnitConverterTheme {
                ConverterScreen(viewModel = viewModel)
            }
        }
        
        composeTestRule.onNodeWithTag("swap_button")
            .assertHasClickAction()
            .performClick()
    }
    
    @Test
    fun resultCardDisplaysWhenResultIsAvailable() {
        every {
            convertValueUseCase(any(), any(), any(), any())
        } returns Result.success(32.0)
        
        composeTestRule.setContent {
            UnitConverterTheme {
                ConverterScreen(viewModel = viewModel)
            }
        }
        
        composeTestRule.onNodeWithTag("input_value_field")
            .performTextInput("0")
        
        composeTestRule.waitUntil(timeoutMillis = 5000) {
            composeTestRule.onAllNodesWithTag("result_card")
                .fetchSemanticsNodes().isNotEmpty()
        }
        
        composeTestRule.onNodeWithTag("result_card")
            .assertExists()
            .assertIsDisplayed()
    }
    
    @Test
    fun conversionTypeDropdownCanBeClicked() {
        composeTestRule.setContent {
            UnitConverterTheme {
                ConverterScreen(viewModel = viewModel)
            }
        }
        
        composeTestRule.onNodeWithTag("conversion_type_dropdown")
            .performClick()
        
        // Check if menu items appear
        composeTestRule.onNodeWithTag("type_item_TEMPERATURE").assertExists()
        composeTestRule.onNodeWithTag("type_item_LENGTH").assertExists()
        composeTestRule.onNodeWithTag("type_item_WEIGHT").assertExists()
        composeTestRule.onNodeWithTag("type_item_VOLUME").assertExists()
    }
    
    @Test
    fun selectingLengthTypeChangesUnits() {
        composeTestRule.setContent {
            UnitConverterTheme {
                ConverterScreen(viewModel = viewModel)
            }
        }
        
        // Open dropdown and select LENGTH
        composeTestRule.onNodeWithTag("conversion_type_dropdown")
            .performClick()
        
        composeTestRule.onNodeWithTag("type_item_LENGTH")
            .performClick()
        
        // Verify the conversion type changed by checking the dropdown text
        composeTestRule.onNodeWithTag("conversion_type_dropdown")
            .assertTextContains("Length")
    }
    
    @Test
    fun fromUnitDropdownCanBeOpened() {
        composeTestRule.setContent {
            UnitConverterTheme {
                ConverterScreen(viewModel = viewModel)
            }
        }
        
        composeTestRule.onNodeWithTag("from_unit_dropdown")
            .performClick()
        
        // Should show temperature units (default)
        composeTestRule.onNodeWithTag("from_unit_item_Celsius").assertExists()
        composeTestRule.onNodeWithTag("from_unit_item_Fahrenheit").assertExists()
        composeTestRule.onNodeWithTag("from_unit_item_Kelvin").assertExists()
    }
    
    @Test
    fun toUnitDropdownCanBeOpened() {
        composeTestRule.setContent {
            UnitConverterTheme {
                ConverterScreen(viewModel = viewModel)
            }
        }
        
        composeTestRule.onNodeWithTag("to_unit_dropdown")
            .performClick()
        
        // Should show temperature units (default)
        composeTestRule.onNodeWithTag("to_unit_item_Celsius").assertExists()
        composeTestRule.onNodeWithTag("to_unit_item_Fahrenheit").assertExists()
        composeTestRule.onNodeWithTag("to_unit_item_Kelvin").assertExists()
    }
    
    @Test
    fun errorIsDisplayedForInvalidInput() {
        composeTestRule.setContent {
            UnitConverterTheme {
                ConverterScreen(viewModel = viewModel)
            }
        }
        
        composeTestRule.onNodeWithTag("input_value_field")
            .performTextInput("invalid")
        
        // Wait for error to appear
        composeTestRule.waitUntil(timeoutMillis = 5000) {
            try {
                composeTestRule.onNodeWithText("Invalid input").assertExists()
                true
            } catch (e: AssertionError) {
                false
            }
        }
    }
    
    @Test
    fun completeConversionFlow() {
        every {
            convertValueUseCase(any(), any(), any(), any())
        } returns Result.success(212.0)
        
        composeTestRule.setContent {
            UnitConverterTheme {
                ConverterScreen(viewModel = viewModel)
            }
        }
        
        // Input value
        composeTestRule.onNodeWithTag("input_value_field")
            .performTextInput("100")
        
        // Wait for result
        composeTestRule.waitUntil(timeoutMillis = 5000) {
            composeTestRule.onAllNodesWithTag("result_card")
                .fetchSemanticsNodes().isNotEmpty()
        }
        
        // Verify result is displayed
        composeTestRule.onNodeWithTag("result_card").assertExists()
        composeTestRule.onNodeWithTag("result_text").assertExists()
    }
}