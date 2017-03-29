package mosque.list.com.mosque.di.components;

import javax.inject.Singleton;

import dagger.Component;
import mosque.list.com.mosque.di.module.AppModule;
import mosque.list.com.mosque.home.ProfileActivity;
import mosque.list.com.mosque.login.LogActivity;
import mosque.list.com.mosque.login.LogPresenter;
import mosque.list.com.mosque.logins.LoginActivityPresenter;
import mosque.list.com.mosque.logins.LoginsActivity;
import mosque.list.com.mosque.register.RegisterActivity;
import mosque.list.com.mosque.register.RegisterActivityPresenter;

/**
 * Created by domikado on 3/25/17.
 */
@Singleton
@Component(modules = {AppModule.class})
public interface AppComponents {

    void inject(LoginsActivity activity);

    void inject(LoginActivityPresenter loginActivityPresenter);

    void inject(ProfileActivity profileActivity);

    void inject(RegisterActivityPresenter registerActivityPresenter);

    void inject(RegisterActivity registerActivity);

    void inject(LogPresenter logPresenter);

    void inject(LogActivity logActivity);

}
