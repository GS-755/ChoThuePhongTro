package edu.nhom01.chothuetro.activities.admin.motels;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;

import edu.nhom01.chothuetro.R;
import edu.nhom01.chothuetro.activities.admin.DashboardActivity;
import edu.nhom01.chothuetro.api.client.ApiClient;
import edu.nhom01.chothuetro.fragments.widgets.FileChooserFragment;
import edu.nhom01.chothuetro.models.motels.Location;
import edu.nhom01.chothuetro.models.motels.Motel;
import edu.nhom01.chothuetro.models.person.Account;
import edu.nhom01.chothuetro.utils.ImageUtils;
import edu.nhom01.chothuetro.utils.Session;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddMotelActivity extends AppCompatActivity {
    private int locationId;
    private EditText txtCurdMotelTitle, txtCrudMotelArea;
    private EditText txtCrudMotelDesc, txtCrudMotelAmount;
    private Button btnCrudAddMotel;
    private Spinner spnCrudMotelLocation;
    private FileChooserFragment imgChooserFragment;
    ArrayList<Location> locationArrayList;
    ArrayAdapter<Location> locationsArrayAdapter;

    private void loadImageChooser(Fragment fragment) {
        FragmentTransaction transaction = this.getSupportFragmentManager().
                beginTransaction();
        transaction.replace(R.id.frameImgChooser, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
    private void setComponents() {
        this.locationArrayList = new ArrayList<>();
        this.imgChooserFragment = new FileChooserFragment();
        this.loadImageChooser(this.imgChooserFragment);
        this.txtCurdMotelTitle = findViewById(R.id.txtCrudMotelTitle);
        this.txtCrudMotelArea = findViewById(R.id.txtCrudMotelArea);
        this.txtCrudMotelAmount = findViewById(R.id.txtCrudMotelAmount);
        this.txtCrudMotelDesc = findViewById(R.id.txtCrudMotelDesc);
        this.spnCrudMotelLocation = findViewById(R.id.spnCrudMotelLocation);
        this.btnCrudAddMotel = findViewById(R.id.btnCrudMotel);
    }
    private void setContentComboBox() {
        int spinnerItem = android.R.layout.simple_spinner_item;
        Context context = this;
        Call<ArrayList<Location>> callLocations = ApiClient.
                getInstance().getRoute().getLocations();
        callLocations.enqueue(new Callback<ArrayList<Location>>() {
            @Override
            public void onResponse(Call<ArrayList<Location>> call, Response<ArrayList<Location>> response) {
                if(response.isSuccessful()) {
                    locationArrayList = response.body();
                    locationsArrayAdapter = new ArrayAdapter<>(context,
                            spinnerItem, locationArrayList);
                    spnCrudMotelLocation.setAdapter(locationsArrayAdapter);
                }
            }
            @Override
            public void onFailure(Call<ArrayList<Location>> call, Throwable t) {
                Log.e("API_ERR", t.getMessage());
            }
        });
    }
    private void setActionCreateMotel() {
        this.btnCrudAddMotel.setOnClickListener(e -> {
            Account account = (Account)Session.get("current-account");
            Motel motel = new Motel();
            motel.setTitle(this.txtCurdMotelTitle.getText().toString().trim());
            motel.setDateUpload(new Date());
            motel.setDescription(this.txtCrudMotelDesc.getText().toString().trim());
            motel.setArea(Double.parseDouble(this.txtCrudMotelArea.getText().toString().trim()));
            motel.setFullAmount(Double.parseDouble(this.txtCrudMotelAmount.
                    getText().toString().trim()));
            motel.setUserName(account.getUserName());
            motel.setIdLocation(spnCrudMotelLocation.getSelectedItemPosition() + 1);
            if(this.imgChooserFragment.getBitmap() != null) {
                motel.setBase64Image(ImageUtils.convert(this.imgChooserFragment.getBitmap()));
            }
            motel.setIdStatus(1);

            try {
                Call<Motel> postMotel = ApiClient.getInstance().getRoute().postMotel(motel);
                postMotel.enqueue(new Callback<Motel>() {
                    @Override
                    public void onResponse(Call<Motel> call, Response<Motel> response) {
                        if(response.isSuccessful()) {
                            Intent i = new Intent(AddMotelActivity.this,
                                    DashboardActivity.class);
                            startActivity(i);
                            finish();
                            Toast.makeText(AddMotelActivity.this,
                                    "Thêm trọ thành công!", Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onFailure(Call<Motel> call, Throwable t) {
                        Toast.makeText(AddMotelActivity.this,
                                t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
            catch(Exception ex) {

            }
        });
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_motel);

        setComponents();
        setContentComboBox();
        setActionCreateMotel();
    }
}