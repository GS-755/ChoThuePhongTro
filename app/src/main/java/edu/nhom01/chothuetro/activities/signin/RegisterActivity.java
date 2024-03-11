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
import java.util.HashMap;
import edu.nhom01.chothuetro.R;
import edu.nhom01.chothuetro.api.client.ApiClient;
import edu.nhom01.chothuetro.fragments.widgets.DatePickerFragment;
import edu.nhom01.chothuetro.models.person.Account;
import edu.nhom01.chothuetro.models.person.User;
import edu.nhom01.chothuetro.utils.StrProcessor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {
    private EditText txtRegisterCid, txtRegisterName;
    private DatePickerFragment datePickerFragment;
    private ImageView btnDob;
    private EditText txtRegisterAddress, txtRegisterUsrName;
    private EditText txtRegisterPassword, txtRegisterEmail;
    private EditText txtRegisterPhone;
    private RadioButton rbMale, rbFemale;
    private RadioButton rbCustomer, rbRenter;
    private Button btnRegister;

    private void setComponents() {
        this.txtRegisterCid = findViewById(R.id.txtRegisterCid);
        this.txtRegisterName = findViewById(R.id.txtRegisterName);
        this.datePickerFragment = new DatePickerFragment();
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
    private User collectUserInfo() {
        User user = new User();
        try {
            user.setCid(this.txtRegisterCid.getText().toString());
            String fullName = this.txtRegisterName.getText().toString();
            HashMap<String, String>
                    collectionName = StrProcessor.splitFullName(fullName);
            String firstName = collectionName.get("first-name");
            String lastName = collectionName.get("last-name");
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setDob(this.datePickerFragment.getDate());
            if(this.rbFemale.isChecked()) {
                user.setGender(0);
            }
            else {
                user.setGender(1);
            }
            user.setAddress(this.txtRegisterAddress.getText().toString().trim());
        }
        catch(Exception ex) {
            Log.d("INT_ERR", ex.getMessage());
        }

        return user;
    }
    private Account collectAccountInfo(String cid) {
        Account account = new Account();
        try {
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
            account.setCid(cid);
        }
        catch(Exception ex) {
            Log.d("INT_ERR", ex.getMessage());
        }

        return account;
    }
    private void setActionDatePicker() {
        this.btnDob.setOnClickListener(e -> {
            this.datePickerFragment.
                    show(this.getSupportFragmentManager(), "datePicker");
        });
    }
    private void setActionRegister() {
        this.btnRegister.setOnClickListener(e -> {
            try {
                User user = collectUserInfo();
                Account account = collectAccountInfo(user.getCid());
                Call<User> callRegisterUser = ApiClient.
                        getInstance().getRoute().postUser(user);
                callRegisterUser.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        if(response.isSuccessful()) {
                            Call<Account> callRegisterAccount = ApiClient.getInstance().
                                    getRoute().registerAccount(account);
                            callRegisterAccount.enqueue(new Callback<Account>() {
                                @Override
                                public void onResponse(Call<Account> call,
                                                       Response<Account> response) {
                                    if(response.isSuccessful()) {
                                        Intent i = new Intent(RegisterActivity.this,
                                                LoginActivity.class);
                                        i.putExtra("register-notification",
                                                "Đăng ký thành công!");
                                        startActivity(i);
                                        finish();
                                    }
                                }
                                @Override
                                public void onFailure(Call<Account> call, Throwable t) {
                                    Log.d("API_ERR", t.getMessage());
                                }
                            });
                        }
                        else if(response.code() == 400) {
                            Toast.makeText(RegisterActivity.this, "Vui lòng " +
                                    "điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                        }
                        else if(response.code() == 409) {
                            Toast.makeText(RegisterActivity.this, "Người dùng " +
                                    "đã có trên hệ thống", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(RegisterActivity.this, String.format(
                                    "Lỗi không xác định \n(mã lỗi: %d)",
                                    response.code()), Toast.LENGTH_SHORT
                            ).show();
                        }
                    }
                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        Log.d("API_ERR", t.getMessage());
                    }
                });
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
        setActionDatePicker();
        setActionRegister();
    }
}


