package su.rbv.planner5d.shared_android.di

import android.app.Application
import android.content.Context
import android.content.res.Resources
import dagger.Module
import dagger.Provides

@Module
internal class AppModule(private val application: Application) {

    @Provides
    fun bind1(): Context = application.applicationContext

    @Provides
    fun bind2(): Resources = application.resources
}