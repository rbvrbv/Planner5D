package su.rbv.planner5d.main.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import su.rbv.planner5d.main.MainFragmentViewModel
import su.rbv.planner5d.shared_android.di.vm.ViewModelKey

@Module
internal abstract class MainFragmentModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainFragmentViewModel::class)
    abstract fun bindViewModel(vm: MainFragmentViewModel): ViewModel
}