package su.rbv.planner5d.shared_android.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.*
import su.rbv.planner5d.shared_android.di.UIComponent
import su.rbv.planner5d.shared_android.extensions.observeOnViewLifecycle
import su.rbv.planner5d.shared_android.ui.events.ViewEvent
import su.rbv.planner5d.shared_android.ui.vm.BaseViewModel
import kotlin.coroutines.CoroutineContext

abstract class BaseFragment<T : BaseViewModel> : Fragment(), CoroutineScope {

    final override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main

    val viewModelFactory: ViewModelProvider.Factory
        get() = runBlocking { _viewModelFactory.await() }

    @get:LayoutRes
    protected abstract val layoutId: Int
    protected abstract val dataBindingVariable: Int?

    protected abstract val viewModel: T

    protected abstract fun diComponent(): UIComponent

    private var binding: ViewDataBinding? = null

    private lateinit var _viewModelFactory: Deferred<ViewModelProvider.Factory>


    @CallSuper
    override fun onAttach(context: Context) {
        super.onAttach(context)

        _viewModelFactory = async(Dispatchers.Default) { createViewModelFactory() }
    }

    @CallSuper
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        return binding!!.root
    }

    @CallSuper
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        savedInstanceState?.let(this::restoreState)
        initViews(savedInstanceState)
    }

    @CallSuper
    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }

    override fun onDestroy() {
        super.onDestroy()
        lifecycle.removeObserver(viewModel)
    }

    final override fun onSaveInstanceState(outState: Bundle) = super.onSaveInstanceState(saveState(outState))

    @CallSuper
    protected open fun initViews(savedInstanceState: Bundle?) {
        initViewModel()
        subscribeBaseViewModelEvents()
        subscribeNavigationEvents()
        lifecycle.addObserver(viewModel)
    }

    @CallSuper
    protected open fun saveState(bundle: Bundle): Bundle = bundle

    @CallSuper
    protected open fun restoreState(bundle: Bundle) = Unit

    internal open fun createViewModelFactory(): ViewModelProvider.Factory = with(diComponent()) {
        argumentsHandler.arguments = arguments
        return@with viewModelFactory
    }

    fun <T : ViewDataBinding> binding(block: T.() -> Unit) = (binding as? T)?.apply { block.invoke(this) }

    private fun initViewModel() {
        dataBindingVariable?.let { binding?.setVariable(it, viewModel) }
        binding?.lifecycleOwner = viewLifecycleOwner
    }

    private fun subscribeBaseViewModelEvents() = observeOnViewLifecycle(viewModel.events) { event ->
        if (!handleEvent(event)) event.execute(this)
    }

    /** @return true - the event was handled "manually", it doesn't need to be executed */
    open fun handleEvent(event: ViewEvent): Boolean = false

    private fun subscribeNavigationEvents() = observeOnViewLifecycle(viewModel.navEvents) { event ->
        runCatching { event.navigate(this) }
    }

}