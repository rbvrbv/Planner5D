package su.rbv.planner5d.preview

import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import su.rbv.planner5d.base.BaseLoadingViewModel
import su.rbv.planner5d.core_projects.domain.GetProjectByHashCase
import su.rbv.planner5d.core_projects.domain.model.ProjectPreviewData.Companion.NAME_FIRST_FLOOR
import su.rbv.planner5d.preview.di.PreviewFragmentComponentHolder
import su.rbv.planner5d.preview.events.PreviewDataEvent
import su.rbv.planner5d.preview.events.PreviewLoadingErrorEvent
import su.rbv.planner5d.preview.events.PreviewNextNavEvent
import su.rbv.planner5d.preview.events.PreviewNotFoundErrorEvent
import su.rbv.planner5d.core_projects.domain.GetProjectPreviewCase
import su.rbv.planner5d.core_projects.domain.model.ProjectData
import su.rbv.planner5d.shared_android.ui.events.NavigationEvent
import javax.inject.Inject

class PreviewFragmentViewModel @Inject constructor(
    private val getProjectPreviewCase: GetProjectPreviewCase,
    private val getProjectByHashCase: GetProjectByHashCase
): BaseLoadingViewModel() {

    private val args by navArgs<PreviewFragmentArgs>()
    private val hash by lazy { args.hash }

    val projectData = MutableLiveData<ProjectData>()

    val isLoaded = MutableLiveData(false)
    val isNextVisible = MutableLiveData(false)

    override fun onCreate() {
        super.onCreate()
        getPreview()
    }

    private fun getPreview() {
        launch {
            awaitAll(
                async {
                    getProjectPreviewCase.execute(hash)?.also { projectPreviewData ->
                        setLoaded()
                        postViewEvents(PreviewDataEvent(projectPreviewData, NAME_FIRST_FLOOR))
                    } ?: previewNotFound()
                },
                async {
                    getProjectByHashCase.execute(hash)?.apply {
                        projectData.postValue(this)
                        if (nextItemHash.isNotBlank()) {
                            loadNext(nextItemHash)
                        }
                    }
                })
        }
    }

    private suspend fun loadNext(nextItemHash: String) {
        getProjectPreviewCase.execute(nextItemHash)?.apply {
            isNextVisible.postValue(true)
        }
    }

    private fun setLoaded() {
        hideProgress()
        isLoaded.postValue(true)
    }

    private fun previewNotFound() {
        hideProgress()
        postViewEvents(PreviewNotFoundErrorEvent)
    }

    fun onProjectsClick() {
        postNavEvents(NavigationEvent.Back)
    }

    fun onNextClick(nextItemHash: String) {
        postNavEvents(PreviewNextNavEvent(nextItemHash))
    }

    override fun handleCoroutineException(exception: Throwable) {
        super.handleCoroutineException(exception)
        hideProgress()
        postViewEvents(PreviewLoadingErrorEvent)
    }

    override fun onCleared() {
        super.onCleared()
        PreviewFragmentComponentHolder.clearComponent()
    }

}
