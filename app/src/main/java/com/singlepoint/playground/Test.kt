package com.singlepoint.playground

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.O)
fun main() {
    // Get the current date and time
    val now = LocalDateTime.now()

    // Define the custom pattern
    val formatter = DateTimeFormatter.ofPattern("dd EEE, yyyy", Locale.ENGLISH)

    // Format the date
    val formattedDate = now.format(formatter)

    // Print the formatted date
    println(formattedDate)
}