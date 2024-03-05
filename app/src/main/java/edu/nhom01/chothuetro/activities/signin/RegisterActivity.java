package edu.nhom01.chothuetro.activities.signin;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import androidx.appcompat.app.AppCompatActivity;
import edu.nhom01.chothuetro.R;

public class RegisterActivity extends AppCompatActivity {
    private EditText txtRegisterCid;
    private EditText txtRegisterName;
    private ImageView btnDOB;
    private EditText txtRegisterAddress;
    private EditText txtRegisterUsrName;
    private EditText txtRegisterPassword;
    private EditText txtRegisterEmail;
    private EditText txtRegisterPhone;
    private RadioButton radioMale;
    private RadioButton radioFemale;
    private Button btnRegister;

    private void setComponents() {
        this.txtRegisterCid = findViewById(R.id.txtRegisterCid);
        this.txtRegisterName = findViewById(R.id.txtRegisterName);
        this.btnDOB = findViewById(R.id.btnDob);
        this.txtRegisterAddress = findViewById(R.id.txtRegisterAddress);
        this.txtRegisterPhone = findViewById(R.id.txtRegisterPhone);
        this.txtRegisterUsrName = findViewById(R.id.txtRegisterUsrName);
        this.txtRegisterPassword = findViewById(R.id.txtRegisterPassword);
        this.txtRegisterEmail = findViewById(R.id.txtRegisterEmail);
        this.radioMale = findViewById(R.id.rbMale);
        this.radioFemale = findViewById(R.id.rbFemale);
        this.btnRegister = findViewById(R.id.btnRegisterAccount);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        setComponents();
    }
}


