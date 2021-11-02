package su.rbv.planner5d.core_projects.domain

import su.rbv.planner5d.core_projects.domain.model.ProjectData

interface GetProjectByHashCase {

    suspend fun execute(hash: String): ProjectData?

}