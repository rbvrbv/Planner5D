package su.rbv.planner5d.core_projects.data.net

import retrofit2.http.GET
import retrofit2.http.Path
import su.rbv.planner5d.core_projects.data.net.model.ProjectPreviewDataNet

internal interface NetApi {

    companion object {
        const val BASE_URL = "https://planner5d.com/api/project/"
    }

    @GET("{hash}")
    suspend fun getProjectPreviewData(@Path("hash") hash: String): ProjectPreviewDataNet

}
