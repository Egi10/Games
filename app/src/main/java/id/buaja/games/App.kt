package id.buaja.games

import android.app.Application
import id.buaja.core.di.databaseModule
import id.buaja.core.di.networkModule
import id.buaja.core.di.repositoryModule
import id.buaja.games.di.useCaseModule
import id.buaja.games.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import timber.log.Timber
import timber.log.Timber.DebugTree


/**
 * Created by Julsapargi Nursam on 12/18/20.
 */
class App : Application() {
    override fun onCreate() {
        super.onCreate()

        // Start Koin Inject
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@App)
            modules(
                listOf(
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule,
                    databaseModule
                )
            )
        }

        // Timber
        if (BuildConfig.DEBUG) {
            Timber.plant(DebugTree())
        }
    }
}