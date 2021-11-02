package su.rbv.planner5d.shared_android.ui.recycler

interface DiffAdapter<T> {

    fun updateItems(itemList: List<T>)

    fun add(item: T)
    fun addAll(itemList: List<T>)

    fun addAtPosition(position: Int, item: T)
    fun addAllAtPosition(position: Int, itemList: List<T>)

    fun clearAll()
}