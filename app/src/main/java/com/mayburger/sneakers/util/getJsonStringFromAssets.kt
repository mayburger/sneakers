package com.mayburger.sneakers.util

import android.content.Context
import java.io.*

fun getJsonStringFromAssets(context: Context, assetPath: String): String {
    val `is`: InputStream = context.assets.open(assetPath)
    val writer: Writer = StringWriter()
    val buffer = CharArray(1024)
    `is`.use { `is` ->
        val reader: Reader = BufferedReader(InputStreamReader(`is`, "UTF-8"))
        var n: Int
        while (reader.read(buffer).also { n = it } != -1) {
            writer.write(buffer, 0, n)
        }
    }
    return writer.toString()
}