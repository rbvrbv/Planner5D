package su.rbv.planner5d.shared_android.di

import android.content.Context
import android.content.res.Resources
import dagger.Component
import su.rbv.planner5d.shared.di.DIComponent

@Component(
    modules = [
        AppModule::class
    ]
)
interface AppComponent : DIComponent {

    val context: Context
    val resource: Resources
}