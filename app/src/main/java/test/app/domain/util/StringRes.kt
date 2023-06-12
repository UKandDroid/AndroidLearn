package test.app.domain.util

import android.content.Context
import test.app.R
import javax.inject.Inject

class StringRes @Inject constructor( context: Context) {
    val LOADING = context.getString(R.string.loading)
    val FAILED_TO_REFRESH = context.getString(R.string.failed_to_refresh)
    val NO_MATCHING_ITEM = context.getString(R.string.no_items_found)
}