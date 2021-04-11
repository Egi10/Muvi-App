package id.buaja.muvi_app

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import id.buaja.data.di.dataModule
import id.buaja.domain.di.domainModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

/**
 * Created by Julsapargi Nursam on 4/11/21.
 */

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        // Dart Off
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        // Start Koin
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@App)

            modules(listOf(
                dataModule,
                domainModule
            ))
        }
    }
}