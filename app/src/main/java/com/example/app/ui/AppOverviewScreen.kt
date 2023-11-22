package com.example.app.ui


import androidx.annotation.StringRes
import com.example.app.R

enum class AppOverviewScreen(@StringRes val title: Int) {
    Start(title = R.string.title_overview),
    Detail(title = R.string.title_detail),
}

