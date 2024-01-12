package com.example.app

import com.example.app.fake.FakeFoxPicRepository
import com.example.app.ui.overviewScreen.AppOverviewViewModel
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Test

class OverviewViewmodelTest {

    @Test
    fun overviewViewmodel_Initialization() = runBlockingTest {
        val viewmodel = AppOverviewViewModel(repo = FakeFoxPicRepository())
        var list = viewmodel.foxpicListState.value.foxpicList
        Assert.assertEquals(2, list.size)
    }
}
