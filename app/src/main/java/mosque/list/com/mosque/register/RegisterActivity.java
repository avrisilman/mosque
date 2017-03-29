package mosque.list.com.mosque.register;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;

import javax.inject.Inject;

import mosque.list.com.mosque.MainApp;
import mosque.list.com.mosque.R;
import mosque.list.com.mosque.databinding.RegisterBinding;
import mosque.list.com.mosque.home.ProfileActivity;
import mosque.list.com.mosque.network.Builders;

/**
 * Created by domikado on 3/27/17.
 */

public class RegisterActivity extends AppCompatActivity implements RegisterActivityView{

    RegisterBinding binding;

    @Inject
    RegisterActivityPresenter presenter;

    @Inject
    Builders builders;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((MainApp)getApplication()).provideAppComponents().inject(RegisterActivity.this);
        binding = DataBindingUtil.setContentView(RegisterActivity.this, R.layout.register);
        presenter.setRegisterActivityPresenter(RegisterActivity.this, binding);
        binding.setPresenter(presenter);
    }

    @Override
    protected void onDestroy() {
        presenter.unSubcscription();
        super.onDestroy();
    }

    @Override
    public void moveProfileActivity() {
        startActivity(new Intent(this, ProfileActivity.class));
    }
}
