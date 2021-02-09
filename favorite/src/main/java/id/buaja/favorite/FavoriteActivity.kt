package id.buaja.favorite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import id.buaja.favorite.di.favoriteModule
import org.koin.core.context.loadKoinModules

class FavoriteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /**
         * Load Koin Modules Meanggil Inject di Module Lain Setelah startKoin
         */
        loadKoinModules(favoriteModule)

        /**
         * Set View
         */
        setContentView(R.layout.activity_favorite)

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }
}