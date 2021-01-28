package id.buaja.games.utils

import android.content.Context
import android.util.TypedValue

/**
 * Created by Julsapargi Nursam on 1/28/21.
 */
 
fun Float.dipToPx(context: Context): Float {
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        this,
        context.resources.displayMetrics
    )
}