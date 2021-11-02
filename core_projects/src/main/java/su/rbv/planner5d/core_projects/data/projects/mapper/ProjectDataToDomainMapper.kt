package su.rbv.planner5d.core_projects.data.projects.mapper

import su.rbv.planner5d.core_projects.data.projects.model.ProjectDataDB
import su.rbv.planner5d.core_projects.domain.model.ProjectData
import javax.inject.Inject

/**
 *      Data and domain models are identical for simplicity. The mapper is created as an example
 */
internal class ProjectDataToDomainMapper @Inject constructor(): (ProjectDataDB?) -> ProjectData? {

    override fun invoke(projectDataDB: ProjectDataDB?): ProjectData? =
        projectDataDB?.run {
            ProjectData(
                name = name,
                hash = hash,
                nextItemHash = nextItemHash
            )
        }

}