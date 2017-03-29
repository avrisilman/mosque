package mosque.list.com.mosque.login;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import javax.inject.Inject;

import mosque.list.com.mosque.MainApp;
import mosque.list.com.mosque.databinding.LoginActivityBinding;
import mosque.list.com.mosque.di.module.AppData;
import mosque.list.com.mosque.entity.Login;
import mosque.list.com.mosque.entity.UserLogin;
import mosque.list.com.mosque.logins.LoginActivityPresenter;
import mosque.list.com.mosque.logins.LoginActivityView;
import mosque.list.com.mosque.network.CallBack;
import mosque.list.com.mosque.network.MainService;
import mosque.list.com.mosque.network.NetService;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by domikado on 3/28/17.
 */

public class LogPresenter {
    @Inject
    AppData appData;

    private LoginActivityView view;
    @Inject
    NetService service;
    private CompositeSubscription subscriptions;
    Context context;

    LoginActivityBinding binding;

    public LogPresenter(Context context){
        this.context = context;
        ((MainApp) context).provideAppComponents().inject(LogPresenter.this);
    }

    void unSubcscription(){
        if(subscriptions.isUnsubscribed()){
            subscriptions.unsubscribe();
        }
    }

    public void sendLogins(String username, String password) {
        Log.d("DATA : " , username + password);
        service.logins(username, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Login>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(context, "Errorr broo...", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNext(Login login) {
                        Toast.makeText(context, "success" + login.getAccess_token(), Toast.LENGTH_SHORT).show();
                    }
                });
    }


}
