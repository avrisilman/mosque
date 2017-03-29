package mosque.list.com.mosque.logins;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import javax.inject.Inject;

import mosque.list.com.mosque.MainApp;
import mosque.list.com.mosque.databinding.LoginActivityBinding;
import mosque.list.com.mosque.di.module.AppData;
import mosque.list.com.mosque.entity.Login;
import mosque.list.com.mosque.entity.UserLogin;
import mosque.list.com.mosque.network.CallBack;
import mosque.list.com.mosque.network.MainService;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by domikado on 3/27/17.
 */

public class LoginActivityPresenter {

    @Inject
    AppData appData;

    private LoginActivityView view;

    @Inject
    MainService mainService;

    private CompositeSubscription subscriptions;
    Context context;

    LoginActivityBinding binding;

    public LoginActivityPresenter(Context context){
        this.context = context;
        ((MainApp) context).provideAppComponents().inject(LoginActivityPresenter.this);
    }

    void unSubcscription(){
        if(subscriptions.isUnsubscribed()){
            subscriptions.unsubscribe();
        }
    }

    void setLoginActivityPresenter(LoginActivityView view, LoginActivityBinding binding){
        this.view = view;
        this.binding = binding;
        subscriptions = new CompositeSubscription();
    }

    void sendLogins(UserLogin userLogin){
        Subscription subscription = mainService.sendLogin(userLogin, new CallBack.CallLogin() {
            @Override
            public void onError(String error) {
                Toast.makeText(context, "this is Error", Toast.LENGTH_SHORT).show();
                binding.txtprogressbar.setVisibility(View.GONE);
            }

            @Override
            public void onSuccess(Login login) {
               // appData.setToken(login.getAccess_token());
               // view.goTo();
            }
        });
        subscriptions.add(subscription);
    }

    public void onClickLoginPresenter(){
        String username = binding.txtusername.getText().toString().trim();
        String password = binding.txtpassword.getText().toString().trim();
        UserLogin user = new UserLogin(username, password);
        sendLogins(user);
        binding.txtprogressbar.setVisibility(View.VISIBLE);
    }

}
