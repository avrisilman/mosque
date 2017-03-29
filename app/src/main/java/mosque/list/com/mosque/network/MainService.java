package mosque.list.com.mosque.network;

import android.util.Log;
import mosque.list.com.mosque.entity.UserLogin;
import mosque.list.com.mosque.entity.UserRegister;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by domikado on 3/25/17.
 */

public class MainService {

    private NetService service;

    public MainService(NetService service) {
        this.service = service;
    }

    public Subscription requestData(final CallBack.CallData callData) {
        return service.getData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        value -> callData.onSuccess(value),
                        throwable -> callData.onError("this is error"),
                        () -> Log.e("", "Complete")
                );
    }

    public Subscription sendLogin(UserLogin userLogin, CallBack.CallLogin callLogin) {
        return service.logins(userLogin.username, userLogin.password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        value -> callLogin.onSuccess(value),
                        throwable -> callLogin.onError("this is error"),
                        () -> Log.e("", "Complete")
                );
    }

    public Subscription sendRegister(UserRegister userRegister, CallBack.CallRegister callRegister){
        return service.registers(userRegister.username, userRegister.password, userRegister.email, userRegister.full_name, userRegister.last_name)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                  value -> callRegister.onSuccess(value),
                        throwable -> callRegister.onError("register error"),
                        () -> Log.e("",  "Register Complete")
                );
    }

}
