package su.rbv.planner5d.main

import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.launch
import su.rbv.planner5d.base.BaseLoadingViewModel
import su.rbv.planner5d.main.di.MainFragmentComponentHolder
import su.rbv.planner5d.main.events.ProjectErrorEvent
import su.rbv.planner5d.core_projects.domain.GetProjectsListCase
import su.rbv.planner5d.core_projects.domain.model.ProjectData
import su.rbv.planner5d.main.events.PreviewNavEvent
import javax.inject.Inject

internal class MainFragmentViewModel @Inject constructor(
    private val getProjectsListCase: GetProjectsListCase
): BaseLoadingViewModel(), OnProjectItemClickListener {

    val projects = MutableLiveData<List<ProjectData>>()

    override fun onCreate() {
        super.onCreate()
        if (projects.value.isNullOrEmpty()) getProjects()
    }

    private fun getProjects() {
        launch {
            getProjectsListCase.execute().also { projectsList ->
                hideProgress()
                projects.postValue(projectsList)
            }
        }
    }

    override fun onProjectClick(projectData: ProjectData) {
        postNavEvents(PreviewNavEvent(projectData.hash))
    }

    override fun handleCoroutineException(exception: Throwable) {
        super.handleCoroutineException(exception)
        hideProgress()
        postViewEvents(ProjectErrorEvent)
    }

    override fun onCleared() {
        super.onCleared()
        MainFragmentComponentHolder.clearComponent()
    }

}

