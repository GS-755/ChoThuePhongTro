package edu.nhom01.chothuetro.activities.admin;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import edu.nhom01.chothuetro.R;
import edu.nhom01.chothuetro.api.client.ApiClient;
import edu.nhom01.chothuetro.fragments.admin.DashboardFragment;
import edu.nhom01.chothuetro.fragments.admin.SettingsFragment;
import edu.nhom01.chothuetro.fragments.admin.TransactionsFragment;
import edu.nhom01.chothuetro.models.person.Account;
import edu.nhom01.chothuetro.models.person.User;
import edu.nhom01.chothuetro.utils.Session;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashboardActivity extends AppCompatActivity {
    private Fragment fragment;
    private BottomNavigationView dashboardBottomNav;
    User user;

    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = this.getSupportFragmentManager().
                beginTransaction();
        transaction.replace(R.id.dashboardFrame, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
    private void setComponents() {
        try {
            Intent i = this.getIntent();
            String loginMessage = i.getStringExtra("login-notification");
            if(!loginMessage.isEmpty()) {
                Toast.makeText(this, loginMessage, Toast.LENGTH_SHORT).show();
            }
        }
        catch(NullPointerException ex) {
            Log.d("SYS_ERR", ex.getMessage());
        }
        this.dashboardBottomNav = findViewById(R.id.dashboardBottomNav);
        this.user = new User();
        this.fragment = new DashboardFragment();
        loadFragment(this.fragment);
    }
    private void setActionBottomNav() {
        this.dashboardBottomNav.setOnItemSelectedListener(e -> {
            int id = e.getItemId();
            if(id == R.id.itemDashboardTransacts) {
                this.fragment = new TransactionsFragment();
            }
            else if(id == R.id.itemDashboardSettings) {
                Account account = (Account) Session.get("current-account");
                String cid = account.getCid().trim();
                Call<User> callUser = ApiClient.
                        getInstance().getRoute().getUser(cid);
                callUser.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        if(response.isSuccessful()) {
                            user = response.body();
                            Session.put("current-user", user);
                        }
                    }
                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        Log.d("API_ERR", t.getMessage());
                    }
                });
                this.fragment = new SettingsFragment();
            }
            else {
                this.fragment = new DashboardFragment();
            }
            this.loadFragment(this.fragment);

            return true;
        });
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad_dashboard);

        setComponents();
        setActionBottomNav();
    }
}