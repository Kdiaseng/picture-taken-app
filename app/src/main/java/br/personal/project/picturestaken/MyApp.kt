package br.personal.project.picturestaken

import android.app.Application
import br.personal.project.picturestaken.di.adapter
import br.personal.project.picturestaken.di.mainModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApp : Application (){

    override fun onCreate() {
        super.onCreate()

        startKoin {
            //verifica qual o log
            androidLogger()
            androidContext(this@MyApp)

            modules(listOf(mainModule, adapter))
        }
    }
}