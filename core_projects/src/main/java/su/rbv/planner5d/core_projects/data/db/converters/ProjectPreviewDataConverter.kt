package su.rbv.planner5d.core_projects.data.db.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import su.rbv.planner5d.core_projects.data.db.model.ProjectPreviewDataDB

/**  The converter was created as an example */
internal class ProjectPreviewDataConverter {

    @TypeConverter
    fun fromProjectPreviewData(value: ProjectPreviewDataDB): String = Gson().toJson(value)

    @TypeConverter
    fun toProjectPreviewData(value: String): ProjectPreviewDataDB = Gson().fromJson(value, ProjectPreviewDataDB::class.java)
}