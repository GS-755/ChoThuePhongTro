package edu.nhom01.chothuetro.activities.signin;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import edu.nhom01.chothuetro.R;
import edu.nhom01.chothuetro.data.person.AccountsData;
import edu.nhom01.chothuetro.models.nodes.LoginNode;

public class LoginActivity extends AppCompatActivity {
    private Button btnLogin;
    private TextView labelRegister, labelForgotPassword, labelFailToLogin;
    private EditText txtUsrName, txtPassword;
    private AccountsData accountsData;

    private void setComponents() {
        this.accountsData = new AccountsData();
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
    private void setActionLogin() {
        this.btnLogin.setOnClickListener(e -> {
            String userName = this.txtUsrName.getText().toString();
            String password = this.txtPassword.getText().toString();
            LoginNode loginNode = new LoginNode(userName, password);
            this.accountsData.setLoginNode(loginNode);
            try {
                this.accountsData.fetchData();
                this.labelFailToLogin.setText(this.accountsData.getMessage());
            }
            catch(NullPointerException ex) {
                this.labelFailToLogin.setText(ex.getMessage());
            }
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
        setActionForgotPassword();
        setActionLogin();
        setActionRegister();
    }
}