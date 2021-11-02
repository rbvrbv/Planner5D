package su.rbv.planner5d.core_projects.data.net.di

import dagger.Component
import su.rbv.planner5d.core_projects.domain.repository.NetRepository
import su.rbv.planner5d.shared.di.DIComponent
import su.rbv.planner5d.shared_android.di.AppComponent
import javax.inject.Singleton

internal interface NetRepositoryComponent : DIComponent {

    val netRepository: NetRepository
}

@Singleton
@Component(
    dependencies = [
      AppComponent::class
    ],
    modules = [
        NetRepositoryModule::class
    ]
)
internal interface NetRepositoryInternalComponent : NetRepositoryComponent
