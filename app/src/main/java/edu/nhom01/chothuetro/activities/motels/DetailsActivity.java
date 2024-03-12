package edu.nhom01.chothuetro.activities.motels;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import edu.nhom01.chothuetro.R;
import edu.nhom01.chothuetro.api.client.ApiClient;
import edu.nhom01.chothuetro.fragments.widgets.utils.StrProcessor;
import edu.nhom01.chothuetro.models.motels.Motel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailsActivity extends AppCompatActivity {
    private int motelId;
    private String motelPublisher;
    private ImageView imgMotelDetails;
    private TextView labelDetailsTitle, labelDetailsPrice, labelDetailsDeposit;
    private TextView labelDetailsArea, labelDetailsLocation, labelDetailsAddress;
    private TextView labelDetailsPublisher, labelDetailsPhone;
    private RadioButton rdDeposit, rdFullPayment;
    private Button btnPay;
    Motel motel;

    private void readIntentData() {
        Intent i = this.getIntent();
        try {
            this.motelId = i.getIntExtra("motel-id", 0);
            this.motelPublisher = i.getStringExtra("motel-publisher");
        }
        catch(NullPointerException ex) {
            Log.e("INT_ERR", ex.getMessage());
        }
    }
    private void setComponents() {
        motel = new Motel();
        this.readIntentData();
        this.imgMotelDetails = findViewById(R.id.imgMotelDetails);
        this.labelDetailsTitle = findViewById(R.id.labelDetailsTitle);
        this.labelDetailsPrice = findViewById(R.id.labelDetailsPrice);
        this.labelDetailsDeposit = findViewById(R.id.labelDetailsDeposit);
        this.labelDetailsArea = findViewById(R.id.labelDetailsArea);
        this.labelDetailsLocation = findViewById(R.id.labelDetailsLocation);
        this.labelDetailsAddress = findViewById(R.id.labelDetailsAddress);
        this.rdDeposit = findViewById(R.id.rdDeposit);
        this.rdFullPayment = findViewById(R.id.rdFullPayment);
        this.btnPay = findViewById(R.id.btnPay);
    }
    private void fetchMotelDetails() {
        if(this.motelId > 0 && this.motelPublisher.length() > 0) {
            Call<Motel> callMotel = ApiClient.
                    getInstance().getRoute().getMotel(this.motelId);
            callMotel.enqueue(new Callback<Motel>() {
                @Override
                public void onResponse(Call<Motel> call, Response<Motel> response) {
                    if(response.isSuccessful()) {
                        motel = response.body();
                    }
                }
                @Override
                public void onFailure(Call<Motel> call, Throwable t) {
                    Log.e("API_ERR", t.getMessage());
                }
            });
        }
    }
    private void setMotelDetails() {
        this.labelDetailsTitle.setText(motel.getTitle());
        this.labelDetailsPrice.setText(StrProcessor.formatVnCurrency(motel.getFullAmount()));
        this.labelDetailsDeposit.setText(StrProcessor.formatVnCurrency(motel.getDepositAmount()));
        this.labelDetailsArea.setText(String.
                format("%s%.1f m2", this.labelDetailsArea.getText().toString(), motel.getArea()));
        this.labelDetailsLocation.setText(String.format("%d", motel.getIdLocation()));
        this.labelDetailsAddress.setText(motel.getAddress());
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        setComponents();
        fetchMotelDetails();
        setMotelDetails();
    }
}