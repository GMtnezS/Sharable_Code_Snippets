package com.example.m7cta.integration

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.example.m7cta.MainActivity
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class ConverterIntegrationTest {
    
    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)
    
    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<MainActivity>()
    
    @Before
    fun setup() {
        hiltRule.inject()
    }

    @Test
    fun endToEnd_temperatureConversion() {
        composeTestRule.onNodeWithTag("screen_title")
            .assertExists()
            .assertIsDisplayed()

        composeTestRule.onNodeWithTag("input_value_field")
            .performTextInput("0")

        // Add idle wait
        composeTestRule.waitForIdle()

        // Then wait for result card with longer timeout
        composeTestRule.waitUntil(timeoutMillis = 10000) {
            composeTestRule.onAllNodesWithTag("result_card")
                .fetchSemanticsNodes().isNotEmpty()
        }

        composeTestRule.onNodeWithTag("result_card")
            .assertExists()
            .assertIsDisplayed()

        composeTestRule.onNodeWithTag("result_text")
            .assertTextContains("32", substring = true, ignoreCase = true)
    }

    @Test
    fun endToEnd_lengthConversion() {
        // Change to length conversion
        composeTestRule.onNodeWithTag("conversion_type_dropdown")
            .performClick()
        
        composeTestRule.onNodeWithTag("type_item_LENGTH")
            .performClick()
        
        // Input length value
        composeTestRule.onNodeWithTag("input_value_field")
            .performTextInput("1")
        
        // Wait for result
        composeTestRule.waitUntil(timeoutMillis = 5000) {
            composeTestRule.onAllNodesWithTag("result_card")
                .fetchSemanticsNodes().isNotEmpty()
        }
        
        // Verify result contains expected value (1 meter ≈ 3.28 feet)
        composeTestRule.onNodeWithTag("result_text")
            .assertTextContains("3.28")
    }
    
    @Test
    fun endToEnd_weightConversion() {
        // Change to weight conversion
        composeTestRule.onNodeWithTag("conversion_type_dropdown")
            .performClick()
        
        composeTestRule.onNodeWithTag("type_item_WEIGHT")
            .performClick()
        
        // Input weight value
        composeTestRule.onNodeWithTag("input_value_field")
            .performTextInput("1")
        
        // Wait for result
        composeTestRule.waitUntil(timeoutMillis = 5000) {
            composeTestRule.onAllNodesWithTag("result_card")
                .fetchSemanticsNodes().isNotEmpty()
        }
        
        // Verify result contains expected value (1 kg ≈ 2.20 lbs)
        composeTestRule.onNodeWithTag("result_text")
            .assertTextContains("2.20")
    }
    
    @Test
    fun endToEnd_swapUnitsAndConvert() {
        // Input value
        composeTestRule.onNodeWithTag("input_value_field")
            .performTextInput("32")
        
        // Wait for initial result
        composeTestRule.waitUntil(timeoutMillis = 5000) {
            composeTestRule.onAllNodesWithTag("result_card")
                .fetchSemanticsNodes().isNotEmpty()
        }
        
        // Swap units
        composeTestRule.onNodeWithTag("swap_button")
            .performClick()
        
        // Wait for new result
        composeTestRule.waitForIdle()
        
        // Verify result changed (32°F = 0°C)
        composeTestRule.onNodeWithTag("result_text")
            .assertTextContains("0.0000")
    }
    
    @Test
    fun endToEnd_changeUnitsManually() {
        // Input value
        composeTestRule.onNodeWithTag("input_value_field")
            .performTextInput("100")
        
        // Change from unit to Fahrenheit
        composeTestRule.onNodeWithTag("from_unit_dropdown")
            .performClick()
        
        composeTestRule.onNodeWithTag("from_unit_item_Fahrenheit")
            .performClick()
        
        // Change to unit to Kelvin
        composeTestRule.onNodeWithTag("to_unit_dropdown")
            .performClick()
        
        composeTestRule.onNodeWithTag("to_unit_item_Kelvin")
            .performClick()
        
        // Wait for result
        composeTestRule.waitUntil(timeoutMillis = 5000) {
            composeTestRule.onAllNodesWithTag("result_card")
                .fetchSemanticsNodes().isNotEmpty()
        }
        
        // Verify result is displayed
        composeTestRule.onNodeWithTag("result_card")
            .assertIsDisplayed()
    }
    
    @Test
    fun endToEnd_invalidInputShowsError() {
        // Input invalid value
        composeTestRule.onNodeWithTag("input_value_field")
            .performTextInput("abc")
        
        // Wait for error
        composeTestRule.waitUntil(timeoutMillis = 5000) {
            try {
                composeTestRule.onNodeWithText("Invalid input").assertExists()
                true
            } catch (e: AssertionError) {
                false
            }
        }
        
        // Verify error is displayed
        composeTestRule.onNodeWithText("Invalid input")
            .assertExists()
        
        // Verify no result card
        composeTestRule.onNodeWithTag("result_card")
            .assertDoesNotExist()
    }
    
    @Test
    fun endToEnd_clearInputClearsResult() {
        // Input value
        composeTestRule.onNodeWithTag("input_value_field")
            .performTextInput("100")
        
        // Wait for result
        composeTestRule.waitUntil(timeoutMillis = 5000) {
            composeTestRule.onAllNodesWithTag("result_card")
                .fetchSemanticsNodes().isNotEmpty()
        }
        
        // Clear input
        composeTestRule.onNodeWithTag("input_value_field")
            .performTextClearance()
        
        composeTestRule.waitForIdle()
        
        // Verify result card is gone
        composeTestRule.onNodeWithTag("result_card")
            .assertDoesNotExist()
    }
    
    @Test
    fun endToEnd_multipleCOnversionsInSequence() {
        // First conversion
        composeTestRule.onNodeWithTag("input_value_field")
            .performTextInput("0")
        
        composeTestRule.waitUntil(timeoutMillis = 5000) {
            composeTestRule.onAllNodesWithTag("result_card")
                .fetchSemanticsNodes().isNotEmpty()
        }
        
        // Clear and do second conversion
        composeTestRule.onNodeWithTag("input_value_field")
            .performTextClearance()
        
        composeTestRule.onNodeWithTag("input_value_field")
            .performTextInput("100")
        
        composeTestRule.waitForIdle()
        
        // Change type and do third conversion
        composeTestRule.onNodeWithTag("conversion_type_dropdown")
            .performClick()
        
        composeTestRule.onNodeWithTag("type_item_LENGTH")
            .performClick()
        
        composeTestRule.waitForIdle()
        
        // Verify result is still displayed
        composeTestRule.onNodeWithTag("result_card")
            .assertIsDisplayed()
    }
}