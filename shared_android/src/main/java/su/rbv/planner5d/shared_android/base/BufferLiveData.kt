package su.rbv.planner5d.shared_android.base

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import java.util.Queue
import java.util.concurrent.LinkedBlockingQueue

class BufferLiveData<T> : MutableLiveData<T>() where T : Any {

    private val buffer: Queue<T> = LinkedBlockingQueue<T>()

    override fun observe(owner: LifecycleOwner, observer: Observer<in T>) {
        super.observe(owner, {
            if (buffer.isNotEmpty()) drainBuffer(observer)
        })
    }

    private fun drainBuffer(observer: Observer<in T>) {

        var element = buffer.poll()

        while (element != null) {
            observer.onChanged(element)
            element = buffer.poll()
        }
    }

    override fun setValue(value: T?) {
        buffer.add(value)
        super.setValue(value)
    }

    fun merge(lifecycleOwner: LifecycleOwner, liveData: LiveData<T>): LiveData<T> {
        liveData.observe(lifecycleOwner) { value = it }
        return this
    }
}