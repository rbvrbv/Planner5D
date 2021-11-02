package su.rbv.planner5d.core_projects.data.db.mapper

import su.rbv.planner5d.core_projects.data.db.model.ProjectPreviewDataDB
import su.rbv.planner5d.core_projects.domain.model.ProjectPreviewData
import javax.inject.Inject

internal class ProjectPreviewDataDBFromDomainMapper @Inject constructor() : (ProjectPreviewData) -> ProjectPreviewDataDB {

    override fun invoke(previewData: ProjectPreviewData): ProjectPreviewDataDB =
        ProjectPreviewDataDB(
            floors = mapFloors(previewData.floors)
        )

    private fun mapFloors(floors: List<ProjectPreviewData.Floor>?): List<ProjectPreviewDataDB.Floor>? =
        floors?.map { floor ->
            ProjectPreviewDataDB.Floor(
                name = floor.name,
                rooms = mapRooms(floor.rooms)
            )
        }

    private fun mapRooms(rooms: List<ProjectPreviewData.Room>?): List<ProjectPreviewDataDB.Room>? =
        rooms?.map { room ->
            ProjectPreviewDataDB.Room(
                walls = mapWalls(room.walls)
            )
        }

    private fun mapWalls(walls: List<ProjectPreviewData.Wall>?): List<ProjectPreviewDataDB.Wall>? =
        walls?.map { wall ->
            ProjectPreviewDataDB.Wall(
                isHidden = wall.isHidden,
                points = mapPoints(wall.points)
            )
        }

    private fun mapPoints(points: List<ProjectPreviewData.Point>?): List<ProjectPreviewDataDB.Point>? =
        points?.map { point ->
            ProjectPreviewDataDB.Point(
                x = point.x,
                y = point.y
            )
        }
}