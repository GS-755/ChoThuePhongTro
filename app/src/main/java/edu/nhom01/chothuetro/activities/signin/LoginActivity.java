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
import edu.nhom01.chothuetro.models.nodes.LoginNode;

public class LoginActivity extends AppCompatActivity {
    private Button btnLogin;
    private TextView labelRegister, labelForgotPassword, labelFailToLogin;
    private EditText txtUsrName, txtPassword;

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
            LoginNode loginNode = this.collectLoginInfo();

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