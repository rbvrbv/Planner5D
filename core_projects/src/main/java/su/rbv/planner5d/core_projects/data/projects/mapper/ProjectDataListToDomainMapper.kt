package su.rbv.planner5d.core_projects.data.projects.mapper

import su.rbv.planner5d.core_projects.data.projects.model.ProjectDataDB
import su.rbv.planner5d.core_projects.domain.model.ProjectData
import javax.inject.Inject

/**
 *      Data and domain models are identical for simplicity. The mapper is created as an example
 */
internal class ProjectDataListToDomainMapper @Inject constructor(
    private val mapper: ProjectDataToDomainMapper
): (List<ProjectDataDB>) -> List<ProjectData> {

    override fun invoke(projectDataDBList: List<ProjectDataDB>): List<ProjectData> =
        projectDataDBList.map {
            mapper(it)!!
        }

}