package su.rbv.planner5d.preview.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import su.rbv.planner5d.preview.PreviewFragmentViewModel
import su.rbv.planner5d.shared_android.di.vm.ViewModelKey

@Module
internal abstract class PreviewFragmentModule {

    @Binds
    @IntoMap
    @ViewModelKey(PreviewFragmentViewModel::class)
    abstract fun bindViewModel(vm: PreviewFragmentViewModel): ViewModel
}