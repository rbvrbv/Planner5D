package su.rbv.planner5d.core_projects.data.db.model

internal data class ProjectPreviewDataDB (
    val floors: List<Floor>?
) {

    internal data class Floor (
        val name: String?,
        val rooms: List<Room>?
    )

    internal data class Room (
        val walls: List<Wall>?
    )

    internal data class Wall (
        val isHidden: Boolean?,
        val points: List<Point>?
    )

    internal data class Point (
        val x: Float,
        val y: Float
    )

}
