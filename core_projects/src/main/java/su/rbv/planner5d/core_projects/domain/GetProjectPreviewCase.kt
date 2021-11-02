package su.rbv.planner5d.core_projects.domain

import su.rbv.planner5d.core_projects.domain.model.ProjectPreviewData

interface GetProjectPreviewCase {

    suspend fun execute(hash: String): ProjectPreviewData?

}