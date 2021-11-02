package su.rbv.planner5d.core_projects.data.net.mapper

import su.rbv.planner5d.core_projects.domain.model.ProjectPreviewData
import su.rbv.planner5d.core_projects.data.net.model.ProjectPreviewDataNet
import su.rbv.planner5d.core_projects.data.net.model.ProjectPreviewDataNet.Companion.CLASS_NAME_POINT
import su.rbv.planner5d.core_projects.data.net.model.ProjectPreviewDataNet.Companion.CLASS_NAME_ROOM
import su.rbv.planner5d.core_projects.data.net.model.ProjectPreviewDataNet.Companion.CLASS_NAME_WALL
import su.rbv.planner5d.core_projects.data.net.model.ProjectPreviewDataNet.Companion.ERROR_CODE_SUCCESS
import javax.inject.Inject

internal class ProjectPreviewNetDataToDomainMapper @Inject constructor() :
        (ProjectPreviewDataNet?) -> ProjectPreviewData? {

    override fun invoke(previewDataNet: ProjectPreviewDataNet?): ProjectPreviewData? =

        if (previewDataNet != null && previewDataNet.errorCode == ERROR_CODE_SUCCESS) {
            ProjectPreviewData(
                floors = mapFloors(previewDataNet.itemsMain)
            )
        } else null

    private fun mapFloors(itemsMain: List<ProjectPreviewDataNet.ItemMain>?): List<ProjectPreviewData.Floor>? =
        itemsMain?.first()?.data?.floors?.map {
            ProjectPreviewData.Floor(
                name = it.name,
                rooms = mapRooms(it.elements)
            )
        }

    private fun mapRooms(elements: List<ProjectPreviewDataNet.ItemElement>?): List<ProjectPreviewData.Room>? =
        elements?.filter {
            it.className == CLASS_NAME_ROOM
        }?.map {
            ProjectPreviewData.Room(
                walls = mapWalls(it.x, it.y, it.walls)
            )
        }

    private fun mapWalls(rx: Float, ry: Float, elements: List<ProjectPreviewDataNet.ItemWall>?): List<ProjectPreviewData.Wall>? =
        elements?.filter {
            it.className == CLASS_NAME_WALL
        }?.map {
            ProjectPreviewData.Wall(
                isHidden = it.isHidden,
                points = mapPoints(rx, ry, it.points)
            )
        }

    private fun mapPoints(rx: Float, ry: Float, points: List<ProjectPreviewDataNet.ItemPoint>?): List<ProjectPreviewData.Point>? =
        points?.filter {
            it.className == CLASS_NAME_POINT
        }?.map {
            ProjectPreviewData.Point(
                x = rx + it.x,      // absolute coordinates
                y = ry + it.y
            )
        }
}