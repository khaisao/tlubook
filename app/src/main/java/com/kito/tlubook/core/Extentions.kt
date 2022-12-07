package com.kito.tlubook.core

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Color
import android.graphics.Typeface
import android.net.Uri
import android.os.Looper
import android.text.SpannableString
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.text.style.StyleSpan
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.Nullable
import androidx.core.content.ContextCompat
import com.kito.tlubook.BuildConfig
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.OutputStream
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit


private const val TAG = "LOG_DEBUG"

fun TextView.applyText(source: Int) {
    text = context.getText(source)
}

fun View.isVisible(): Boolean {
    return visibility == View.VISIBLE
}

fun View.isInvisible(): Boolean {
    return visibility == View.INVISIBLE
}

fun View.isGone(): Boolean {
    return visibility == View.GONE
}

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.invisible() {
    this.visibility = View.INVISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}

fun Activity.setStatusBarColor(){
    window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
    window.statusBarColor = Color.parseColor("#2669FF")
}



fun Any.logd(tag: String = TAG) {
    if (!BuildConfig.DEBUG) return
    if (this is String) {
        Log.d(tag, this)
    } else {
        Log.d(tag, this.toString())
    }
}



fun Context.showToast(text: String) {
    if (Looper.myLooper() == Looper.getMainLooper()) {
        Toast.makeText(this.applicationContext, text, Toast.LENGTH_SHORT).apply {
            setText(text)
            duration = Toast.LENGTH_SHORT
            show()
        }
    }
}






fun TextView.makeBold(vararg links: Pair<String, Any?>) {
    val spannableString = SpannableString(this.text)
    for (link in links) {
        val startIndexOfLink = this.text.toString().indexOf(link.first)
        spannableString.setSpan(
            StyleSpan(Typeface.BOLD), startIndexOfLink, startIndexOfLink + link.first.length,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        val startLastIndexOfLink = this.text.toString().lastIndexOf(link.first)
        spannableString.setSpan(
            StyleSpan(Typeface.BOLD),
            startLastIndexOfLink,
            startLastIndexOfLink + link.first.length,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )

    }
    this.movementMethod = LinkMovementMethod.getInstance()
    this.setText(spannableString, TextView.BufferType.SPANNABLE)
}

fun Context.isHaveReadWritePermission(): Boolean {
    val write = ContextCompat.checkSelfPermission(
        applicationContext, Manifest.permission.WRITE_EXTERNAL_STORAGE
    )
    val read = ContextCompat.checkSelfPermission(
        applicationContext,
        Manifest.permission.READ_EXTERNAL_STORAGE
    )
    return (write == PackageManager.PERMISSION_GRANTED
            && read == PackageManager.PERMISSION_GRANTED)

}



fun Context.getDurationBreakdown(time: Long, mode: Int): String {
    val zero: Long = 0
    var millis = time
    if (millis <= 0) {
        return "now"
    }
//    require(millis >= 0) { "Duration must be greater than zero!" }
    val days = TimeUnit.MILLISECONDS.toDays(millis)
    millis -= TimeUnit.DAYS.toMillis(days)
    val hours = TimeUnit.MILLISECONDS.toHours(millis)
    millis -= TimeUnit.HOURS.toMillis(hours)
    val minutes = TimeUnit.MILLISECONDS.toMinutes(millis)
    millis -= TimeUnit.MINUTES.toMillis(minutes)
    val sb = StringBuilder(64)
    if (days != zero) {
        sb.append(days)
        if (mode == 0) {
            sb.append("d")
        } else {
            sb.append(" day")
        }
        return sb.toString()

    }
    if (hours != zero) {
        sb.append(hours)
        if (mode == 0) {
            sb.append("h")
        } else {
            sb.append(" hour")
        }
        return sb.toString()

    }
    if (minutes != zero) {
        sb.append(minutes)
        if (mode == 0) {
            sb.append("m")
        } else {
            sb.append(" minute")
        }
        return sb.toString()
    } else {
        return "Now"
    }
}

fun Context.getTimeFromSystemCurrentTime(time: Long): String {
    return SimpleDateFormat("MMMM dd, yyyy", Locale.US).format(Date(time))
}

fun Context.hideSoftKeyboard(activity: Activity) {
    val inputMethodManager: InputMethodManager = activity.getSystemService(
        Activity.INPUT_METHOD_SERVICE
    ) as InputMethodManager
    if (inputMethodManager.isAcceptingText) {
        inputMethodManager.hideSoftInputFromWindow(
            activity.currentFocus!!.windowToken,
            0
        )
    }
}

@Nullable
fun getRealPathFromUri(
    context: Context, uri: Uri
): String? {
    val contentResolver = context.contentResolver ?: return null

    // Create file path inside app's data dir
    val filePath = (context.applicationInfo.dataDir + File.separator
            + System.currentTimeMillis())
    val file = File(filePath)
    try {
        val inputStream = contentResolver.openInputStream(uri) ?: return null
        val outputStream: OutputStream = FileOutputStream(file)
        val buf = ByteArray(1024)
        var len: Int
        while (inputStream.read(buf).also { len = it } > 0) outputStream.write(buf, 0, len)
        outputStream.close()
        inputStream.close()
    } catch (ignore: IOException) {
        return null
    }
    return file.absolutePath
}






