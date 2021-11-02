package su.rbv.planner5d.core_projects.data.db.entities

import androidx.room.Entity
import su.rbv.planner5d.core_projects.data.db.model.ProjectPreviewDataDB

@Entity(tableName = "project_previews", primaryKeys = ["hash"])
internal data class ProjectPreviewEntity(
    val hash: String,
    val data: ProjectPreviewDataDB
)
