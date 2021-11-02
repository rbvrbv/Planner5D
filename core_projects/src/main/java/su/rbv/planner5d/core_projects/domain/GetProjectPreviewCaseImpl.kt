package su.rbv.planner5d.core_projects.domain

import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext
import su.rbv.planner5d.core_projects.domain.model.ProjectPreviewData
import su.rbv.planner5d.core_projects.domain.repository.DBRepository
import su.rbv.planner5d.core_projects.domain.repository.NetRepository
import javax.inject.Inject

internal class GetProjectPreviewCaseImpl @Inject constructor(
    private val netRepository: NetRepository,
    private val dbRepository: DBRepository,
) : GetProjectPreviewCase {

    override suspend fun execute(hash: String): ProjectPreviewData? =
        withContext(IO) {
            dbRepository.getProjectPreviewDataByHash(hash) ?: getAndCacheProjectPreviewDataFromNet(hash)
        }

    private suspend fun getAndCacheProjectPreviewDataFromNet(hash: String): ProjectPreviewData? =
        netRepository.getProjectPreviewData(hash)?.also { projectPreviewDataFromNet ->
            dbRepository.storeProjectPreviewData(hash, projectPreviewDataFromNet)
        }

}