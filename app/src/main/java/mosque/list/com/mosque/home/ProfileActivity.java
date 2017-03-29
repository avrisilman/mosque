package mosque.list.com.mosque.home;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import javax.inject.Inject;

import mosque.list.com.mosque.MainApp;
import mosque.list.com.mosque.R;
import mosque.list.com.mosque.di.module.AppData;

/**
 * Created by domikado on 3/27/17.
 */

public class ProfileActivity extends AppCompatActivity {
    @Inject
    AppData appData;

    TextView token;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((MainApp)getApplication()).provideAppComponents().inject(ProfileActivity.this);

        setContentView(R.layout.profile_activity);
        token = (TextView)findViewById(R.id.token);

        token.setText(appData.getToken());
    }
}
