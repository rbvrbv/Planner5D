package su.rbv.planner5d.core_projects.data.db.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import su.rbv.planner5d.core_projects.data.db.DB
import su.rbv.planner5d.core_projects.domain.repository.DBRepository
import su.rbv.planner5d.core_projects.data.db.DBRepositoryImpl
import su.rbv.planner5d.core_projects.data.db.ProjectPreviewDao
import su.rbv.planner5d.core_projects.data.db.mapper.ProjectPreviewDataDBFromDomainMapper
import su.rbv.planner5d.core_projects.data.db.mapper.ProjectPreviewDataDBToDomainMapper
import javax.inject.Singleton

@Module
internal class DBRepositoryModule {

    @Provides
    @Singleton
    fun provideDBRepository(
        dao: ProjectPreviewDao,
        mapperPreviewDataDBToDomain: ProjectPreviewDataDBToDomainMapper,
        mapperPreviewDataDBFromDomainMapper: ProjectPreviewDataDBFromDomainMapper
    ): DBRepository = DBRepositoryImpl(dao, mapperPreviewDataDBToDomain, mapperPreviewDataDBFromDomainMapper)

    @Provides
    @Singleton
    fun provideDatabase(context: Context): DB =
        Room.databaseBuilder(context, DB::class.java, DB.DB_NAME).fallbackToDestructiveMigration().build()

    @Provides
    @Singleton
    fun provideProjectPreviewDao(db: DB): ProjectPreviewDao = db.projectPreviewDao()

}