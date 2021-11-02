package su.rbv.planner5d.shared.di

abstract class ComponentHolder<T : DIComponent> {

    protected open val mode = ComponentHolderMode.CLEANABLE_SINGLETON

    private var component: T? = null

    protected abstract fun buildComponent(): T

    open fun getComponent(): T = when (mode) {

        ComponentHolderMode.CLEANABLE_SINGLETON,
        ComponentHolderMode.GLOBAL_SINGLETON -> {
            if (component == null) {
                component = createComponent()
            }
            component!!
        }
        ComponentHolderMode.ALWAYS_CREATE_NEW -> createComponent()
    }

    fun setComponent(component: T) {
        this.component = component
    }

    protected open fun createComponent(): T = buildComponent()

    open fun clearComponent() = when (mode) {
        ComponentHolderMode.GLOBAL_SINGLETON -> {}
        else -> component = null
    }

    enum class ComponentHolderMode {

        /**
         * Singleton, that need to clear
         * @see clearComponent
         */
        CLEANABLE_SINGLETON,

        /**
         * Always new instance component
         */
        ALWAYS_CREATE_NEW,

        /**
         * Global singleton, that initializes once and for all
         */
        GLOBAL_SINGLETON
    }
}