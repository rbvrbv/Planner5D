package su.rbv.planner5d;

import android.app.Application;
import su.rbv.planner5d.shared_android.di.AppComponentHolder;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        AppComponentHolder.INSTANCE.createComponent(this);
    }
}
