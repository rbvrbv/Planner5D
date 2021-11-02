package su.rbv.planner5d.base

import androidx.lifecycle.MutableLiveData
import su.rbv.planner5d.shared_android.ui.vm.BaseViewModel
import javax.inject.Inject

open class BaseLoadingViewModel @Inject constructor(): BaseViewModel() {

    val isLoading = MutableLiveData(true)

    protected fun hideProgress() = isLoading.postValue(false)
}

