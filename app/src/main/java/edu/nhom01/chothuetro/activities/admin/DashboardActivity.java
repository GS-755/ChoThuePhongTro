package edu.nhom01.chothuetro.activities.admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import edu.nhom01.chothuetro.R;

public class DashboardActivity extends AppCompatActivity {
    private Fragment fragment;
    private BottomNavigationView dashboardBottomNav;

    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = this.getSupportFragmentManager().
                beginTransaction();
        transaction.replace(R.id.dashboardFrame, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
    private void setComponents() {
        this.dashboardBottomNav = findViewById(R.id.dashboardBottomNav);
    }
    private void setActionBottomNav() {
//        this.dashboardBottomNav.setOnItemSelectedListener(e -> {
//
//        });
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad_dashboard);
    }
}