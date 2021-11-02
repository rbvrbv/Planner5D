package su.rbv.planner5d.core_projects.domain.repository

import su.rbv.planner5d.core_projects.domain.model.ProjectPreviewData

internal interface NetRepository {

    suspend fun getProjectPreviewData(hash: String): ProjectPreviewData?
}