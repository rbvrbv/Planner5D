package su.rbv.planner5d.core_projects.data.net.model

import com.google.gson.annotations.SerializedName

internal data class ProjectPreviewDataNet (

    @SerializedName("items")
    val itemsMain: List<ItemMain>?,

    @SerializedName("error")
    val errorCode: Int?
) {

    internal data class ItemMain(

        @SerializedName("data")
        val data: ItemData?,
    )

    internal data class ItemData (

        @SerializedName("items")
        val floors: List<ItemFloor>?,
    )

    internal data class ItemFloor (

        @SerializedName("name")
        val name: String?,

        @SerializedName("items")
        val elements: List<ItemElement>?,
    )

    internal data class ItemElement (

        @SerializedName("className")
        val className: String?,

        @SerializedName("x")
        val x: Float,

        @SerializedName("y")
        val y: Float,

        @SerializedName("sX")
        val sX: Float,

        @SerializedName("sY")
        val sY: Float,

        @SerializedName("items")
        val walls: List<ItemWall>?
    )

    internal data class ItemWall (

        @SerializedName("className")
        val className: String?,

        @SerializedName("hidden")
        val isHidden: Boolean?,

        @SerializedName("w")
        val width: Float?,

        @SerializedName("items")
        val points: List<ItemPoint>?
    )

    internal data class ItemPoint (

        @SerializedName("className")
        val className: String?,

        @SerializedName("x")
        val x: Float,

        @SerializedName("y")
        val y: Float
    )

    companion object {
        const val CLASS_NAME_ROOM = "Room"
        const val CLASS_NAME_WALL = "Wall"
        const val CLASS_NAME_POINT = "Point"

        const val ERROR_CODE_SUCCESS = 0
    }
}
