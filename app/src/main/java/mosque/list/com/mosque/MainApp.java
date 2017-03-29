package mosque.list.com.mosque;

import android.app.Application;

import mosque.list.com.mosque.di.components.AppComponents;
import mosque.list.com.mosque.di.components.DaggerAppComponents;
import mosque.list.com.mosque.di.module.AppModule;

/**
 * Created by domikado on 3/25/17.
 */

public class MainApp extends Application {
    private AppComponents appComponents;
    private static MainApp mainApp;

    @Override
    public void onCreate() {
        super.onCreate();
        mainApp = MainApp.this;
        appComponents = DaggerAppComponents.builder()
                .appModule(new AppModule(this))
                .build();
    }

    public AppComponents provideAppComponents(){
        return appComponents;
    }

    public static synchronized MainApp getInstance(){
        return mainApp;
    }

}
