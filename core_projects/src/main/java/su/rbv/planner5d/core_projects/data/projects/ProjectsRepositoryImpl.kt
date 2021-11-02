package su.rbv.planner5d.core_projects.data.projects

import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import su.rbv.planner5d.core_projects.data.projects.mapper.ProjectDataListToDomainMapper
import su.rbv.planner5d.core_projects.data.projects.mapper.ProjectDataToDomainMapper
import su.rbv.planner5d.core_projects.data.projects.model.ProjectDataDB
import su.rbv.planner5d.core_projects.domain.model.ProjectData
import su.rbv.planner5d.core_projects.domain.repository.ProjectsRepository
import javax.inject.Inject

internal class ProjectsRepositoryImpl @Inject constructor(
    private val mapperProjectDataListToDomainMapper: ProjectDataListToDomainMapper,
    private val mapperProjectDataToDomain: ProjectDataToDomainMapper
) : ProjectsRepository {

    private val projectsList = listOf(
        ProjectDataDB(
            name = "Rounded corners: Design battle contest",
            hash = "20d0a9d00b2d5b6a1d07e492f36d1b8e",
            nextItemHash = "295cd4c922234e205549319dfeba4283"
        ),
        ProjectDataDB(
            name = "Modern minimalistic house",
            hash = "295cd4c922234e205549319dfeba4283",
            nextItemHash = "1e15df86b3ca859549cc93f723264a5c"
        ),
        ProjectDataDB(
            name = "Scandinavian Beach House",
            hash = "1e15df86b3ca859549cc93f723264a5c",
            nextItemHash = "0fa955334280f7628f22f04523a35d04"
        ),
        ProjectDataDB(
            name = "The Big Bang Theory",
            hash = "0fa955334280f7628f22f04523a35d04",
            nextItemHash = "e1650fb8c6e3eba2209b5a26b17352fa"
        ),
        ProjectDataDB(
            name = "Classic bathroom: Design battle contest",
            hash = "e1650fb8c6e3eba2209b5a26b17352fa",
            nextItemHash = "79e1e11a068b1e45a077d918badcd2df"
        ),
        ProjectDataDB(
            name = "Modern house",
            hash = "79e1e11a068b1e45a077d918badcd2df",
            nextItemHash = "92704d74d3de7cb2f53256994ac15494"
        ),
        ProjectDataDB(
            name = "Desert house",
            hash = "92704d74d3de7cb2f53256994ac15494",
            nextItemHash = "c3a3bd9ba5991e6ef0ac98c16b54b2da"
        ),
        ProjectDataDB(
            name = "6x6 Mq with 2 Floors",
            hash = "c3a3bd9ba5991e6ef0ac98c16b54b2da",
            nextItemHash = "incorrect_hash"
        ),
        ProjectDataDB(
            name = "Test project with incorrect hash",
            hash = "incorrect_hash",
            nextItemHash = "ddeb7099e65a72fc979785c890d4c542"
        ),
        ProjectDataDB(
            name = "Natural bathroom: Design battle contest",
            hash = "ddeb7099e65a72fc979785c890d4c542",
            nextItemHash = ""
        ),
    )

    override suspend fun getAllProjects(): List<ProjectData> =
        withContext(IO) {
            delay(1000)     // slow network emulation
            mapperProjectDataListToDomainMapper(projectsList)
        }

    override suspend fun getProjectByHash(hash: String): ProjectData? =
        withContext(IO) {
            projectsList.firstOrNull { it.hash == hash }?.let { projectData ->
                mapperProjectDataToDomain(projectData)
            }
        }

}