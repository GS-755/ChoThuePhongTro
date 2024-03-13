package edu.nhom01.chothuetro.activities.admin.motels;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;
import java.util.Date;

import edu.nhom01.chothuetro.R;
import edu.nhom01.chothuetro.activities.admin.DashboardActivity;
import edu.nhom01.chothuetro.api.client.ApiClient;
import edu.nhom01.chothuetro.fragments.widgets.FileChooserFragment;
import edu.nhom01.chothuetro.models.motels.Location;
import edu.nhom01.chothuetro.models.motels.Motel;
import edu.nhom01.chothuetro.utils.DisplayImage;
import edu.nhom01.chothuetro.utils.ImageUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditMotelActivity extends AppCompatActivity {
    private int motelId;
    private EditText txtCurdMotelTitle, txtCrudMotelArea;
    private EditText txtCrudMotelDesc, txtCrudMotelAmount;
    private Button btnCrudAddMotel;
    private Spinner spnCrudMotelLocation;
    private FileChooserFragment imgChooserFragment;
    ArrayList<Location> locationArrayList;
    ArrayAdapter<Location> locationsArrayAdapter;
    Motel oldMotel = new Motel();

    private void loadImageChooser(Fragment fragment) {
        FragmentTransaction transaction = this.getSupportFragmentManager().
                beginTransaction();
        transaction.replace(R.id.frameEdtImgChooser, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
    private void setComponents() {
        try {
            motelId = getIntent().getIntExtra("ds-hit-motel", 0);
        }
        catch(Exception ex) {
            Log.e("SYS_ERR", ex.getMessage());
        }
        this.locationArrayList = new ArrayList<>();
        this.imgChooserFragment = new FileChooserFragment();
        this.loadImageChooser(this.imgChooserFragment);
        this.txtCurdMotelTitle = findViewById(R.id.txtEditMotelTitle);
        this.txtCrudMotelArea = findViewById(R.id.txtEditMotelArea);
        this.txtCrudMotelAmount = findViewById(R.id.txtEditMotelAmount);
        this.txtCrudMotelDesc = findViewById(R.id.txtEditMotelDesc);
        this.spnCrudMotelLocation = findViewById(R.id.spnEditMotelLocation);
        this.btnCrudAddMotel = findViewById(R.id.btnUpdateMotel);
    }
    private void setContentComboBox() {
        int spinnerItem = android.R.layout.simple_spinner_item;
        Context context = this;
        Call<ArrayList<Location>> callLocations = ApiClient.
                getInstance().getRoute().getLocations();
        callLocations.enqueue(new Callback<ArrayList<Location>>() {
            @Override
            public void onResponse(Call<ArrayList<Location>> call,
                                   Response<ArrayList<Location>> response) {
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
    private void fillCurrentMotelData() {
        Call<Motel> callMotel = ApiClient.
                getInstance().getRoute().getMotel(motelId);
        callMotel.enqueue(new Callback<Motel>() {
            @Override
            public void onResponse(Call<Motel> call, Response<Motel> response) {
                if(response.isSuccessful()) {
                    oldMotel = response.body();
                    txtCurdMotelTitle.setText(response.body().getTitle());
                    spnCrudMotelLocation.setSelection(response.body().getIdLocation());
                    spnCrudMotelLocation.setEnabled(false);
                }
            }
            @Override
            public void onFailure(Call<Motel> call, Throwable t) {
                Log.e("API_ERR", t.getMessage());
            }
        });
    }
    private void setActionCreateMotel() {
        this.btnCrudAddMotel.setOnClickListener(e -> {
            try {
                oldMotel.setTitle(this.txtCurdMotelTitle.getText().toString().trim());
                oldMotel.setDateUpload(new Date());
                oldMotel.setDescription(this.txtCrudMotelDesc.getText().toString().trim());
                oldMotel.setArea(Double.parseDouble(this.txtCrudMotelArea.getText().toString().trim()));
                oldMotel.setFullAmount(Double.parseDouble(this.txtCrudMotelAmount.
                        getText().toString().trim()));
                if(this.imgChooserFragment.getBitmap() != null) {
                    oldMotel.setBase64Image(ImageUtils.convert(this.imgChooserFragment.getBitmap()));
                }
                else {
                    String motelImageUrl = DisplayImage.getMotelImageUrl(oldMotel.getMotelId());
                    Bitmap bitmap = DisplayImage.getMotelImageBitmap(motelImageUrl);
                    oldMotel.setBase64Image(ImageUtils.convert(bitmap));
                }
                oldMotel.setIdStatus(1);
            }
            catch(Exception ex) {
                Log.e("INT_ERR", ex.getMessage());
            }

            try {
                Call<Motel> updateMotel = ApiClient.getInstance().
                        getRoute().putMotel(motelId, oldMotel);
                updateMotel.enqueue(new Callback<Motel>() {
                    @Override
                    public void onResponse(Call<Motel> call, Response<Motel> response) {
                        if(response.isSuccessful()) {
                            Intent i = new Intent(EditMotelActivity.this,
                                    DashboardActivity.class);
                            startActivity(i);
                            finish();
                            Toast.makeText(EditMotelActivity.this,
                                    "Cập nhật thành công!", Toast.LENGTH_SHORT).show();
                            try {
                                Thread.sleep(1200);
                            } catch (InterruptedException ex) {
                                throw new RuntimeException(ex);
                            }
                        }
                    }
                    @Override
                    public void onFailure(Call<Motel> call, Throwable t) {
                        Toast.makeText(EditMotelActivity.this,
                                t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
            catch(Exception ex) {
                Log.e("SYS_ERR", ex.getMessage());
            }
        });
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_motel);

        setComponents();
        setContentComboBox();
        fillCurrentMotelData();
        setActionCreateMotel();
    }
}