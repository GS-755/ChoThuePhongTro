package edu.nhom01.chothuetro.activities.motels;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.net.URL;
import java.util.Map;

import edu.nhom01.chothuetro.R;
import edu.nhom01.chothuetro.activities.home.HomeActivity;
import edu.nhom01.chothuetro.api.client.ApiClient;
import edu.nhom01.chothuetro.models.motels.Motel;
import edu.nhom01.chothuetro.models.nodes.VnPayResponse;
import edu.nhom01.chothuetro.models.transactions.Transaction;
import edu.nhom01.chothuetro.utils.Session;
import edu.nhom01.chothuetro.utils.StrProcessor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VnPayResponseActivity extends AppCompatActivity {
    private Button btnBackToHome;
    private TextView txtTransactedAmount;
    private TextView txtTransactedMessage;
    private ImageView imgTransactIcon;
    String responseUrl;
    Transaction transaction;
    Motel motel;
    VnPayResponse vnPayResponse;

    private void collectResponseUrlFromIntent() {
        try {
            if(this.getIntent().getStringExtra("VnPay-Response") != null) {
                this.responseUrl = this.getIntent().getStringExtra("VnPay-Response");
            }
        }
        catch (Exception ex) {
            Log.e("INT_ERR", ex.getMessage());
        }
    }
    private void setHashSetResponseFromUrl() {
        try {
            URL url = new URL(responseUrl);
            Map<String, String> vnPayResponseParams = StrProcessor.splitQueryParams(url);
            vnPayResponse.setTerminalId(vnPayResponseParams.get("vnp_TmnCode"));
            vnPayResponse.setClientTransactId(vnPayResponseParams.get(""));
            vnPayResponse.setServerTransactId(vnPayResponseParams.get(""));
            vnPayResponse.setBankCode(vnPayResponseParams.get("vnp_BankCode"));
            vnPayResponse.setPaymentAmount(Double.parseDouble(vnPayResponseParams.get("")));
            vnPayResponse.setTransactionStatus(Integer.parseInt(vnPayResponseParams.get("")));
            if(vnPayResponse.getTransactionStatus() == 0) {
                vnPayResponse.setReturnText(vnPayResponseParams.get("Cảm ơn quý khách đã giao dịch"));
            }
            else {
                vnPayResponse.setReturnText(vnPayResponseParams.get(
                        String.format("Có lỗi xảy ra trong quá trình giao dịch \n Mã lỗi: %d",
                                vnPayResponse.getTransactionStatus()))
                );
            }
        }
        catch (Exception ex) {
            Log.e("SYS_ERR", ex.getMessage());
        }
    }
    private void setComponents() {
        vnPayResponse = new VnPayResponse();
        transaction = (Transaction)Session.get("transaction-item");
        this.collectResponseUrlFromIntent();
        this.setHashSetResponseFromUrl();

        motel = new Motel();
        Call<Motel> callMotel = ApiClient.getInstance().
                getRoute().getMotel(transaction.getMotelId());
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
        this.imgTransactIcon = findViewById(R.id.imgTransactIcon);
        this.txtTransactedMessage = findViewById(R.id.txtTransactedMessage);
        this.txtTransactedAmount = findViewById(R.id.txtTransactedAmount);
        this.btnBackToHome = findViewById(R.id.btnBackToHome);
    }
    private void setActionBackToHome() {
        this.btnBackToHome.setOnClickListener(e -> {
            Intent i = new Intent(this, HomeActivity.class);
            startActivity(i);
            finish();
        });
    }
    private void setContentResponse() throws InterruptedException {
        Thread.sleep(1200);
        if(transaction != null) {
            this.txtTransactedAmount.setText(
                    StrProcessor.formatVnCurrency(transaction.getAmount()));
        }
        if(vnPayResponse != null) {
            if(vnPayResponse.getTransactionStatus() != 0) {
                Drawable drawable = getDrawable(R.drawable.transact_fail);
                imgTransactIcon.setImageDrawable(drawable);
            }
            txtTransactedMessage.setText(vnPayResponse.getReturnText().trim());
        }
    }
    private void updateMotelStatusFromTransaction() {
        try {
            Motel editedMotel = this.motel;
            Call<Motel> callMotel = ApiClient.getInstance().
                    getRoute().putMotel(this.motel.getMotelId(), editedMotel);
            callMotel.enqueue(new Callback<Motel>() {
                @Override
                public void onResponse(Call<Motel> call, Response<Motel> response) {
                    String message = "Đổi trạng thái trọ thành công.";
                    if(!response.isSuccessful()) {
                        message = "Không thể đổi trạng thái phòng trọ";
                    }

                    Toast.makeText(VnPayResponseActivity.this,
                            message, Toast.LENGTH_SHORT).show();
                }
                @Override
                public void onFailure(Call<Motel> call, Throwable t) {
                    Log.e("API_ERR", t.getMessage());
                }
            });
        }
        catch (Exception ex) {
            Log.e("API_ERR", ex.getMessage());
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vn_pay_response);

        this.setComponents();
        this.setActionBackToHome();
        try {
            this.setContentResponse();
        } catch (InterruptedException ex) {
            Log.e("INT_ERR", ex.getMessage());
        }
    }
}