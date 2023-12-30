package com.example.app

import com.example.app.fake.FakeFoxPicRepository
import com.example.app.ui.overviewScreen.AppOverviewViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestWatcher
import org.junit.runner.Description

class OverviewViewmodelTest {

    @get:Rule
    val testDispatcher  = TestDispatcherRule()

    @Test
    fun overviewViewmodel_Initialization() = testDispatcher.run{
        val viewmodel = AppOverviewViewModel(repo = FakeFoxPicRepository())
        Assert.assertEquals(viewmodel.foxpicListState.value.foxpicList.size, 2)
    }


}

class TestDispatcherRule(
    val testDispatcher: TestDispatcher = UnconfinedTestDispatcher(),
) : TestWatcher() {
    override fun starting(description: Description) {
        Dispatchers.setMain(testDispatcher)
    }
    override fun finished(description: Description) {
        Dispatchers.resetMain()
    }
}