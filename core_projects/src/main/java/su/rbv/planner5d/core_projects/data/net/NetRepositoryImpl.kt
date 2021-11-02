package su.rbv.planner5d.core_projects.data.net

import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import su.rbv.planner5d.core_projects.data.net.mapper.ProjectPreviewNetDataToDomainMapper
import su.rbv.planner5d.core_projects.domain.model.ProjectPreviewData
import su.rbv.planner5d.core_projects.domain.repository.NetRepository
import javax.inject.Inject

internal class NetRepositoryImpl @Inject constructor(
    private val api: NetApi,
    private val mapperPreviewNetDataToDomain: ProjectPreviewNetDataToDomainMapper
) : NetRepository {

    override suspend fun getProjectPreviewData(hash: String): ProjectPreviewData? =
        withContext(IO) {
            delay(500)         // slow network emulation
            val projectPreviewDataNet = api.getProjectPreviewData(hash)
            mapperPreviewNetDataToDomain(projectPreviewDataNet)
        }
}