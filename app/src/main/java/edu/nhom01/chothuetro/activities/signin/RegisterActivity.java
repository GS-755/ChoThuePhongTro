package edu.nhom01.chothuetro.activities.signin;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.Date;
import java.util.HashMap;

import edu.nhom01.chothuetro.R;
import edu.nhom01.chothuetro.data.person.AccountsData;
import edu.nhom01.chothuetro.data.person.UsersData;
import edu.nhom01.chothuetro.models.person.Account;
import edu.nhom01.chothuetro.models.person.User;
import edu.nhom01.chothuetro.utils.StrProcessor;

public class RegisterActivity extends AppCompatActivity {
    private EditText txtRegisterCid;
    private EditText txtRegisterName;
    private ImageView btnDob;
    private EditText txtRegisterAddress;
    private EditText txtRegisterUsrName;
    private EditText txtRegisterPassword;
    private EditText txtRegisterEmail;
    private EditText txtRegisterPhone;
    private RadioButton rbMale;
    private RadioButton rbFemale;
    private RadioButton rbCustomer;
    private RadioButton rbRenter;
    private Button btnRegister;

    private void setComponents() {
        this.txtRegisterCid = findViewById(R.id.txtRegisterCid);
        this.txtRegisterName = findViewById(R.id.txtRegisterName);
        this.btnDob = findViewById(R.id.btnDob);
        this.rbMale = findViewById(R.id.rbMale);
        this.rbFemale = findViewById(R.id.rbFemale);
        this.txtRegisterAddress = findViewById(R.id.txtRegisterAddress);
        this.txtRegisterPhone = findViewById(R.id.txtRegisterPhone);
        this.txtRegisterUsrName = findViewById(R.id.txtRegisterUsrName);
        this.txtRegisterPassword = findViewById(R.id.txtRegisterPassword);
        this.txtRegisterEmail = findViewById(R.id.txtRegisterEmail);
        this.rbCustomer = findViewById(R.id.rbCustomer);
        this.rbRenter = findViewById(R.id.rbRenter);
        this.btnRegister = findViewById(R.id.btnRegisterAccount);
    }
    private void setActionRegister() {
        this.btnRegister.setOnClickListener(e -> {
            try {
                String fullName = this.txtRegisterName.getText().toString();
                HashMap<String, String>
                        collectionName = StrProcessor.trimFullName(fullName);
                String firstName = collectionName.get("first_name");
                String lastName = collectionName.get("last_name");

                User user = new User();
                user.setCid(this.txtRegisterCid.getText().toString());
                user.setFirstName(firstName);
                user.setLastName(lastName);
                user.setDob(Date.valueOf("2003-09-17"));
                if(this.rbFemale.isChecked()) {
                    user.setGender(0);
                }
                else {
                    user.setGender(1);
                }
                user.setAddress(this.txtRegisterAddress.getText().toString().trim());
                UsersData usersApi = new UsersData();
                usersApi.setUser(user);
                usersApi.createUser();
                if(usersApi.isCompleted()) {
                    Account account = new Account();
                    account.setUserName(this.txtRegisterUsrName.
                            getText().toString().trim());
                    account.setPassword(this.txtRegisterPassword.
                            getText().toString());
                    account.setEmail(this.txtRegisterEmail.
                            getText().toString().trim());
                    account.setPhone(this.txtRegisterPhone.
                            getText().toString().trim());
                    if(this.rbRenter.isChecked()) {
                        account.setRoleId(1);
                    }
                    else {
                        account.setRoleId(0);
                    }
                    account.setCid(usersApi.getUser().getCid());

                    AccountsData accountsApi = new AccountsData();
                    accountsApi.setAccount(account);
                    accountsApi.createAccount();
                    if(!accountsApi.getStatus()) {
                        Toast.makeText(this,
                                usersApi.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(this,
                                usersApi.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(this,
                            usersApi.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
            catch(Exception ex) {
                Log.d("INT_ERR", ex.getMessage());
            }
        });
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        setComponents();
        setActionRegister();
    }
}


