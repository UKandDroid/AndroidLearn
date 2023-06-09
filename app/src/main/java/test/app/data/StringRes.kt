package test.app.data

import android.content.Context
import test.app.R
import javax.inject.Inject

class StringRes @Inject constructor(val context: Context) {
    val LOADING = context.getString(R.string.loading)

}