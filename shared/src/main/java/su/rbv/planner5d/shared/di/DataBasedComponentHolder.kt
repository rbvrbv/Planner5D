package su.rbv.planner5d.shared.di

/**
 * Base component holder, that initializes with input data
 */
abstract class DataBasedComponentHolder<T : DIComponent, R : Any> : ComponentHolder<T>() {

    private lateinit var dataForBuild: R

    protected abstract fun buildComponent(data: R): T

    final override fun buildComponent(): T = error("Data for build not found")

    override fun createComponent(): T = buildComponent(dataForBuild)

    /**
     * Creates a component that can be received later without having input data
     */
    fun createComponent(data: R): T {
        dataForBuild = data
        return super.getComponent()
    }
}
