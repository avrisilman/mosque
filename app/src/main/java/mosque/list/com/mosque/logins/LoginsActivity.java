package mosque.list.com.mosque.logins;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import javax.inject.Inject;
import mosque.list.com.mosque.MainApp;
import mosque.list.com.mosque.R;
import mosque.list.com.mosque.databinding.LoginActivityBinding;
import mosque.list.com.mosque.entity.UserLogin;
import mosque.list.com.mosque.home.ProfileActivity;
import mosque.list.com.mosque.network.Builders;

/**
 * Created by domikado on 3/27/17.
 */

public class LoginsActivity extends AppCompatActivity implements LoginActivityView {

    LoginActivityBinding binding;

    @Inject
    LoginActivityPresenter presenter;

    @Inject
    Builders builders;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((MainApp)getApplication()).provideAppComponents().inject(LoginsActivity.this);
        binding = DataBindingUtil.setContentView(LoginsActivity.this, R.layout.login_activity);
        presenter.setLoginActivityPresenter(LoginsActivity.this, binding);
        binding.setPresenter(presenter);

    }

    @Override
    protected void onDestroy() {
        presenter.unSubcscription();
        super.onDestroy();
    }

    @Override
    public void goTo(){
        startActivity(new Intent(this, ProfileActivity.class));
    }

//    @Override
//    public void clickLogin() {
//        presenter.sendLogins(new UserLogin(binding.txtusername.getText().toString(), binding.txtpassword.getText().toString()));
//    }
}
