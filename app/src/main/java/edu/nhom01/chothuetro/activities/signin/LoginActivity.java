package edu.nhom01.chothuetro.activities.signin;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import edu.nhom01.chothuetro.R;
import edu.nhom01.chothuetro.activities.admin.DashboardActivity;
import edu.nhom01.chothuetro.activities.home.HomeActivity;
import edu.nhom01.chothuetro.api.client.ApiClient;
import edu.nhom01.chothuetro.models.nodes.LoginNode;
import edu.nhom01.chothuetro.models.person.Account;
import edu.nhom01.chothuetro.fragments.widgets.utils.Session;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private Button btnLogin;
    private TextView labelRegister, labelForgotPassword, labelFailToLogin;
    private EditText txtUsrName, txtPassword;
    private Account account;
    private LoginNode loginNode;

    private void setComponents() {
        try {
            Intent i = this.getIntent();
            String registerNotification = i.getStringExtra("register-notification");
            if(!registerNotification.isEmpty()) {
                Toast.makeText(this,
                        registerNotification, Toast.LENGTH_SHORT).show();
            }
        }
        catch(NullPointerException ex) {
            Log.d("SYS_ERR", ex.getMessage());
        }
        this.account = new Account();
        this.loginNode = new LoginNode();
        this.txtUsrName = findViewById(R.id.txtUserName);
        this.txtPassword = findViewById(R.id.txtPassword);
        this.labelForgotPassword = findViewById(R.id.labelForgotPassword);
        this.labelFailToLogin = findViewById(R.id.labelFailToLogin);
        this.btnLogin = findViewById(R.id.btnLogin);
        this.labelRegister = findViewById(R.id.labelRegister);
    }
    private void setActionForgotPassword() {
        this.labelForgotPassword.setOnClickListener(e -> {
            Intent i = new Intent(this, ForgotPasswordActivity.class);
            startActivity(i);
        });
    }
    private LoginNode collectLoginInfo() {
        LoginNode loginNode = new LoginNode();
        try {
            String userName = this.txtUsrName.getText().toString();
            String password = this.txtPassword.getText().toString();
            loginNode = new LoginNode(userName, password);
        }
        catch(Exception ex) {
            Log.d("SYS_ERR", ex.getMessage());
        }

        return loginNode;
    }
    private void setActionLogin() {
        this.btnLogin.setOnClickListener(e -> {
            this.loginNode = this.collectLoginInfo();
            Call<Account> callLoginAccount = ApiClient.getInstance().getRoute().
                    loginAccount(this.loginNode.getUserName(), this.loginNode.getPassword());
            callLoginAccount.enqueue(new Callback<Account>() {
                @Override
                public void onResponse(Call<Account> call, Response<Account> response) {
                    if(response.isSuccessful()) {
                        account = response.body();
                        Session.put("current-account", account);
                        if(account.getRoleId() == 1) {
                            Intent i = new Intent(LoginActivity.this,
                                    DashboardActivity.class);
                            i.putExtra("login-notification", "Đăng nhập thành công!");
                            startActivity(i);
                            finish();
                        }
                        else {
                            Intent i = new Intent(LoginActivity.this,
                                    HomeActivity.class);
                            i.putExtra("login-notification", "Đăng nhập thành công!");
                            startActivity(i);
                            finish();
                        }
                    }
                    else if(response.code() == 400) {
                        Toast.makeText(LoginActivity.this, "Vui lòng " +
                                "điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(LoginActivity.this, String.format(
                                "Lỗi không xác định \n (Mã lỗi: %d)",
                                response.code()), Toast.LENGTH_SHORT
                        ).show();
                    }
                }
                @Override
                public void onFailure(Call<Account> call, Throwable t) {
                    Log.d("API_ERR", t.getMessage());
                }
            });
        });
    }
    private void setActionRegister() {
        this.labelRegister.setOnClickListener(e -> {
            Intent i = new Intent(this, RegisterActivity.class);
            startActivity(i);
        });
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setComponents();
        setActionLogin();
        setActionRegister();
        setActionForgotPassword();
    }
}