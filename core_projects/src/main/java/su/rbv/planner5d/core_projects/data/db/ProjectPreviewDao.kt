package su.rbv.planner5d.core_projects.data.db

import androidx.room.*
import su.rbv.planner5d.core_projects.data.db.entities.ProjectPreviewEntity
import su.rbv.planner5d.core_projects.data.db.model.ProjectPreviewDataDB

@Dao
internal interface ProjectPreviewDao {

    @Query("SELECT data FROM project_previews WHERE hash = :hash")
    suspend fun getPreviewDataByHash(hash: String): ProjectPreviewDataDB?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(data: ProjectPreviewEntity)

}
