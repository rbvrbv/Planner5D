package su.rbv.planner5d.core_projects.data.db

import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext
import su.rbv.planner5d.core_projects.data.db.entities.ProjectPreviewEntity
import su.rbv.planner5d.core_projects.data.db.mapper.ProjectPreviewDataDBFromDomainMapper
import su.rbv.planner5d.core_projects.data.db.mapper.ProjectPreviewDataDBToDomainMapper
import su.rbv.planner5d.core_projects.domain.model.ProjectPreviewData
import su.rbv.planner5d.core_projects.domain.repository.DBRepository
import javax.inject.Inject

internal class DBRepositoryImpl @Inject constructor(
    private val projectPreviewDao: ProjectPreviewDao,
    private val mapperPreviewDataDBToDomain: ProjectPreviewDataDBToDomainMapper,
    private val mapperPreviewDataDBFromDomainMapper: ProjectPreviewDataDBFromDomainMapper
) : DBRepository {

    override suspend fun getProjectPreviewDataByHash(hash: String): ProjectPreviewData? =
        withContext(IO) {
            val projectPreviewDataDB = projectPreviewDao.getPreviewDataByHash(hash)
            mapperPreviewDataDBToDomain(projectPreviewDataDB)
        }

    override suspend fun storeProjectPreviewData(hash: String, projectPreviewData: ProjectPreviewData) {
        withContext(IO) {
            val projectPreviewDataDB = mapperPreviewDataDBFromDomainMapper(projectPreviewData)
            projectPreviewDao.insert(
                ProjectPreviewEntity(
                    hash = hash,
                    data = projectPreviewDataDB
                )
            )
        }
    }

}