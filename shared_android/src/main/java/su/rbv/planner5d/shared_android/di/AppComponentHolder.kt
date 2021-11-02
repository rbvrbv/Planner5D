package su.rbv.planner5d.shared_android.di

import android.app.Application
import su.rbv.planner5d.shared.di.DataBasedComponentHolder

object AppComponentHolder : DataBasedComponentHolder<AppComponent, Application>() {

    override val mode: ComponentHolderMode = ComponentHolderMode.GLOBAL_SINGLETON

    override fun buildComponent(data: Application): AppComponent =
        DaggerAppComponent.builder()
            .appModule(AppModule(data))
            .build()
}