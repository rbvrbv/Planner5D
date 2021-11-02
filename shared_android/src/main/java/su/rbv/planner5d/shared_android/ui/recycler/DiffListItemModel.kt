package su.rbv.planner5d.shared_android.ui.recycler

interface DiffListItemModel {

    fun isSameAs(other: DiffListItemModel): Boolean = other == this

}