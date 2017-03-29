package mosque.list.com.mosque.register;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import javax.inject.Inject;

import mosque.list.com.mosque.MainApp;
import mosque.list.com.mosque.databinding.RegisterBinding;
import mosque.list.com.mosque.di.module.AppData;
import mosque.list.com.mosque.entity.Resgister;
import mosque.list.com.mosque.entity.UserRegister;
import mosque.list.com.mosque.network.CallBack;
import mosque.list.com.mosque.network.MainService;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by domikado on 3/27/17.
 */

public class RegisterActivityPresenter {

    @Inject
    AppData appData;

    private RegisterActivityView view;

    @Inject
    MainService mainService;
    private CompositeSubscription subscriptions;
    Context context;

    RegisterBinding binding;

    public RegisterActivityPresenter(Context context){
        this.context = context;
        ((MainApp) context).provideAppComponents().inject(RegisterActivityPresenter.this);
    }

    void unSubcscription(){
        if(subscriptions.isUnsubscribed()){
            subscriptions.unsubscribe();
        }
    }

    void setRegisterActivityPresenter(RegisterActivityView view, RegisterBinding binding){
        this.view = view;
        this.binding = binding;
        subscriptions = new CompositeSubscription();
    }

    void sendRegister(UserRegister userRegister){
        Log.d("register : " , userRegister.username + userRegister.password + userRegister.email + userRegister.full_name + userRegister.last_name);
        Subscription subscription = mainService.sendRegister(userRegister, new CallBack.CallRegister() {
            @Override
            public void onError(String error) {
                Toast.makeText(context, "register error please...", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSuccess(Resgister resgister) {
               // appData.setToken(resgister.getAccess_token());
                //view.moveProfileActivity();
                Toast.makeText(context, "suucess" + resgister.getAccess_token(), Toast.LENGTH_SHORT).show();
            }
        });
        subscriptions.add(subscription);
    }

    public void onClickRegisterPresenter(){
        String username = binding.txtUsername.getText().toString().trim();
        String password = binding.txtPassword.getText().toString().trim();
        String email = binding.txtEmail.getText().toString().trim();
        String full_name = binding.txtFullname.getText().toString().trim();
        String last_name = binding.txtLastname.getText().toString().trim();
        UserRegister user = new UserRegister(username, password, email, full_name, last_name);
        sendRegister(user);
    }


}
