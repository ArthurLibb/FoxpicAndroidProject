package com.example.app

import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import com.example.app.ui.AndroidApp
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class NavigationTest {
    private val nameFoxPic: String = "name foxpic"

    @get: Rule
    val composeTestRule = createComposeRule()
    lateinit var navController: TestNavHostController

    @Before
    fun setupNavHost() {
        composeTestRule.setContent {
            navController = TestNavHostController(LocalContext.current)
            navController.navigatorProvider.addNavigator(ComposeNavigator())
            AndroidApp(navController = navController)
        }
    }

    @Test
    fun startDestination() {
        composeTestRule.waitForIdle()
        composeTestRule
            .onNodeWithText("Overview")
            .assertIsDisplayed()
    }

    @Test
    fun navigateToAdd() {
        composeTestRule
            .onNodeWithContentDescription("add buttons")
            .performClick()
        composeTestRule
            .onNodeWithText("Add New Foxpic")
            .assertIsDisplayed()
    }

    @Test
    fun clickAddPic() {
        composeTestRule
            .onNodeWithContentDescription("add buttons")
            .performClick()
        composeTestRule
            .onNodeWithText("Add")
            .performClick()
        composeTestRule
            .onNodeWithText("How would you like to name the pic?")
            .assertIsDisplayed()
    }

    @Test
    fun clickRefresh() {
        composeTestRule
            .onNodeWithContentDescription("add buttons")
            .performClick()
        composeTestRule
            .onNodeWithText("Other Pic")
            .performClick()
        composeTestRule
            .onNodeWithContentDescription("async fox image")
            .assertIsDisplayed()
    }

    @Test
    fun canAddFoxPic() {
        composeTestRule
            .onNodeWithContentDescription("add buttons")
            .performClick()
        composeTestRule
            .onNodeWithText("Add")
            .performClick()
        composeTestRule
            .onNodeWithText("How would you like to name the pic?")
            .performTextInput(nameFoxPic)
        composeTestRule
            .onNodeWithText("Confirm")
            .performClick()
        composeTestRule
            .onNodeWithContentDescription("home button")
            .performClick()
        composeTestRule
            .onNodeWithText(nameFoxPic)
            .assertIsDisplayed()
    }
}
