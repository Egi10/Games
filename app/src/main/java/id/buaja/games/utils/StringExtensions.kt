package id.buaja.games.utils

import androidx.core.text.HtmlCompat

/**
 * Created by Julsapargi Nursam on 2/14/21.
 */
 
fun String.setHtml(): String {
    return HtmlCompat.fromHtml(this, HtmlCompat.FROM_HTML_MODE_COMPACT).toString()
}