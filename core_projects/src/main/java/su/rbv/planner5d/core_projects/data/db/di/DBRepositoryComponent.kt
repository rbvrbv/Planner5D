package su.rbv.planner5d.core_projects.data.db.di

import dagger.Component
import su.rbv.planner5d.core_projects.domain.repository.DBRepository
import su.rbv.planner5d.shared.di.DIComponent
import su.rbv.planner5d.shared_android.di.AppComponent
import javax.inject.Singleton

internal interface DBRepositoryComponent : DIComponent {

    val dbRepository: DBRepository
}

@Singleton
@Component(
    dependencies = [
      AppComponent::class
    ],
    modules = [
        DBRepositoryModule::class
    ]
)
internal interface DBRepositoryInternalComponent : DBRepositoryComponent
