package mosque.list.com.mosque.login;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import mosque.list.com.mosque.MainApp;
import mosque.list.com.mosque.R;
import mosque.list.com.mosque.entity.UserLogin;
import mosque.list.com.mosque.logins.LoginsActivity;

/**
 * Created by domikado on 3/28/17.
 */

public class LogActivity extends AppCompatActivity implements LogView {

    private Button btnClick;
    private EditText txtusername;
    private EditText txtpassword;
    LogPresenter presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log_activity);
        ((MainApp)getApplication()).provideAppComponents().inject(LogActivity.this);
        presenter = new LogPresenter(this);

        btnClick = (Button)findViewById(R.id.btn_login);
        txtusername = (EditText)findViewById(R.id.txtusername);
        txtpassword = (EditText)findViewById(R.id.txtpassword);
        btnClick.setOnClickListener(this::dataLogin);

    }

    @Override
    protected void onDestroy() {
        presenter.unSubcscription();
        super.onDestroy();
    }

    private void dataLogin(View view) {
        String username = txtusername.getText().toString().trim();
        String password = txtpassword.getText().toString().trim();
        presenter.sendLogins(username, password);
    }

    @Override
    public void sendLogin(UserLogin userLogin) {

    }
}
