package su.rbv.planner5d.core_projects.data.db.mapper

import su.rbv.planner5d.core_projects.data.db.model.ProjectPreviewDataDB
import su.rbv.planner5d.core_projects.domain.model.ProjectPreviewData
import javax.inject.Inject

internal class ProjectPreviewDataDBToDomainMapper @Inject constructor() : (ProjectPreviewDataDB?) -> ProjectPreviewData? {

    override fun invoke(previewDataDB: ProjectPreviewDataDB?): ProjectPreviewData? =
        previewDataDB?.let {
            ProjectPreviewData(
                floors = mapFloors(previewDataDB.floors)
            )
        }

    private fun mapFloors(floors: List<ProjectPreviewDataDB.Floor>?): List<ProjectPreviewData.Floor>? =
        floors?.map { floor ->
            ProjectPreviewData.Floor(
                name = floor.name,
                rooms = mapRooms(floor.rooms)
            )
        }

    private fun mapRooms(rooms: List<ProjectPreviewDataDB.Room>?): List<ProjectPreviewData.Room>? =
        rooms?.map { room ->
            ProjectPreviewData.Room(
                walls = mapWalls(room.walls)
            )
        }

    private fun mapWalls(walls: List<ProjectPreviewDataDB.Wall>?): List<ProjectPreviewData.Wall>? =
        walls?.map { wall ->
            ProjectPreviewData.Wall(
                isHidden = wall.isHidden,
                points = mapPoints(wall.points)
            )
        }

    private fun mapPoints(points: List<ProjectPreviewDataDB.Point>?): List<ProjectPreviewData.Point>? =
        points?.map { point ->
            ProjectPreviewData.Point(
                x = point.x,
                y = point.y
            )
        }
}