package edu.nhom01.chothuetro.activities.home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import android.os.Bundle;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import edu.nhom01.chothuetro.R;
import edu.nhom01.chothuetro.fragments.home.DepositFragment;
import edu.nhom01.chothuetro.fragments.home.ExploreFragment;
import edu.nhom01.chothuetro.fragments.home.HomeFragment;
import edu.nhom01.chothuetro.fragments.home.ProfileFragment;

public class HomeActivity extends AppCompatActivity {
    private Fragment fragment;
    private BottomNavigationView homeBottomNav;

    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = this.getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frameHome, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
    private void setComponents() {
        this.fragment = new HomeFragment();
        this.homeBottomNav = findViewById(R.id.homeBottomNav);
        this.loadFragment(this.fragment);
    }
    private void setActionBottomNav() {
        this.homeBottomNav.setOnItemSelectedListener(e -> {
            int id = e.getItemId();
            if(id == R.id.itemDefaultExplore) {
                this.fragment = new ExploreFragment();
            }
            else if(id == R.id.itemDefaultDeposit) {
                this.fragment = new DepositFragment();
            }
            else if (id == R.id.itemDefaultProfile) {
                this.fragment = new ProfileFragment();
            }
            else {
                this.fragment = new HomeFragment();
            }
            this.loadFragment(this.fragment);

            return true;
        });
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        setComponents();
        setActionBottomNav();
    }
}