package su.rbv.planner5d.core_projects.domain.model

data class ProjectPreviewData (
    val floors: List<Floor>?
) {

    data class Floor (
        val name: String?,
        val rooms: List<Room>?
    )

    data class Room (
        val walls: List<Wall>?
    )

    data class Wall (
        val isHidden: Boolean?,
        val points: List<Point>?
    )

    data class Point (
        val x: Float,
        val y: Float
    )

    companion object {
        const val NAME_FIRST_FLOOR = "First floor"
    }
}
