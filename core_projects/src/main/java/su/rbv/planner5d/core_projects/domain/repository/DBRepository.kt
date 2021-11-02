package su.rbv.planner5d.core_projects.domain.repository

import su.rbv.planner5d.core_projects.domain.model.ProjectPreviewData

internal interface DBRepository {

    suspend fun getProjectPreviewDataByHash(hash: String): ProjectPreviewData?
    suspend fun storeProjectPreviewData(hash: String, projectPreviewData: ProjectPreviewData)
}