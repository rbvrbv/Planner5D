package su.rbv.planner5d.shared_android.extensions

import android.content.Context
import android.widget.Toast

fun Context.toast(msg: String) = Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
fun Context.toast(msgId: Int) = Toast.makeText(this, this.getString(msgId), Toast.LENGTH_SHORT).show()
