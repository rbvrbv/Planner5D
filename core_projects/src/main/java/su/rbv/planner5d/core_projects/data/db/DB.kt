package su.rbv.planner5d.core_projects.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import su.rbv.planner5d.core_projects.data.db.converters.ProjectPreviewDataConverter
import su.rbv.planner5d.core_projects.data.db.entities.ProjectPreviewEntity

@Database(
    entities = [ ProjectPreviewEntity::class ],
    version = 1,
    exportSchema = false
)
@TypeConverters(ProjectPreviewDataConverter::class)
internal abstract class DB : RoomDatabase() {

    abstract fun projectPreviewDao(): ProjectPreviewDao

    companion object {
        const val DB_NAME = "DATABASE_PROJECTS"
    }
}
