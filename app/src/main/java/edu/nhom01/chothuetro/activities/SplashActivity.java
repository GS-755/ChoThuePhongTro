package edu.nhom01.chothuetro.activities;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import edu.nhom01.chothuetro.R;
import edu.nhom01.chothuetro.activities.signin.LoginActivity;

public class SplashActivity extends AppCompatActivity {
    private Button btnStartApp;

    private void setComponents() {
        this.btnStartApp = findViewById(R.id.btnStartApp);
    }
    private void setActionStartApp() {
        this.btnStartApp.setOnClickListener(e -> {
            Intent i = new Intent(this, LoginActivity.class);
            startActivity(i);
            finish();
        });
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        setComponents();
        setActionStartApp();
    }
}