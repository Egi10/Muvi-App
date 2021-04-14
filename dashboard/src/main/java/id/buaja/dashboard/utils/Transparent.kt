package id.buaja.dashboard.utils

/**
 * Created by Julsapargi Nursam on 4/14/21.
 */


import android.app.Activity
import android.os.Build
import android.view.View
import android.view.WindowManager
import androidx.core.content.ContextCompat

fun Activity.setTransparentStatusBar(colorTransparent: Int = android.R.color.transparent) {
    window.decorView.systemUiVisibility =
        View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
    if (Build.VERSION.SDK_INT >= 21) {
        setWindowFlag(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false, this)

        window.statusBarColor =
            ContextCompat.getColor(this, colorTransparent)
    }
}

private fun setWindowFlag(bits: Int, on: Boolean, activity: Activity) {
    val win = activity.window
    val winParams = win.attributes
    if (on) {
        winParams.flags = winParams.flags or bits
    } else {
        winParams.flags = winParams.flags and bits.inv()
    }
    win.attributes = winParams
}