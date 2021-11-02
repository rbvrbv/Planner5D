package su.rbv.planner5d.shared_android.ui.vm

import android.os.Bundle
import dagger.Reusable
import javax.inject.Inject

@Reusable
class ArgumentsHandler @Inject constructor() {

    @PublishedApi internal var arguments: Bundle? = null
}