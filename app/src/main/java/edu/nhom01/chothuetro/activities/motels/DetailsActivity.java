package edu.nhom01.chothuetro.activities.motels;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.Date;

import edu.nhom01.chothuetro.R;
import edu.nhom01.chothuetro.api.client.ApiClient;
import edu.nhom01.chothuetro.models.motels.Motel;
import edu.nhom01.chothuetro.models.person.Account;
import edu.nhom01.chothuetro.models.transactions.Transaction;
import edu.nhom01.chothuetro.utils.DisplayImage;
import edu.nhom01.chothuetro.utils.RandomID;
import edu.nhom01.chothuetro.utils.Session;
import edu.nhom01.chothuetro.utils.StrProcessor;
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
        this.labelDetailsPublisher = findViewById(R.id.labelDetailsPublisher);
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
                        setMotelDetails();
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
        try {
            String motelImageUrl = DisplayImage.getMotelImageUrl(motel.getMotelId());
            imgMotelDetails.setImageBitmap(DisplayImage.getMotelImageBitmap(motelImageUrl));
        }
        catch(Exception ex) {
            Log.e("SYS_ERR", ex.getMessage());
        }
        labelDetailsTitle.setText(motel.getTitle());
        labelDetailsPrice.setText(StrProcessor.formatVnCurrency(motel.getFullAmount()));
        labelDetailsDeposit.setText(StrProcessor.formatVnCurrency(motel.getDepositAmount()));
        labelDetailsArea.setText(String.format("%.1f m2", motel.getArea()));
        labelDetailsPublisher.setText(motel.getUserName());
    }
    private void setActionPay() {
        this.btnPay.setOnClickListener(e -> {
            try {
                Account account = (Account) Session.get("current-account");
                Transaction transaction = new Transaction();
                transaction.setTransactionId(RandomID.get(8));
                transaction.setTransactionDate(new Date());
                transaction.setUserName(account.getUserName().trim());
                transaction.setMotelId(motelId);
                if(rdFullPayment.isChecked()) {
                    transaction.setIdTransactionType(2);
                }
                else {
                    transaction.setIdTransactionType(1);
                }

                Session.put("transaction-item", transaction);
                Intent i = new Intent(this, VnPayActivity.class);
                startActivity(i);
            }
            catch(Exception ex) {
                Log.e("TRANS_ERR", ex.getMessage());
            }
        });
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        setComponents();
        fetchMotelDetails();
        setMotelDetails();
        setActionPay();
    }
}