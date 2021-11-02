package su.rbv.planner5d.core_projects.domain.repository

import su.rbv.planner5d.core_projects.domain.model.ProjectData

internal interface ProjectsRepository {

    suspend fun getAllProjects(): List<ProjectData>
    suspend fun getProjectByHash(hash: String): ProjectData?
}