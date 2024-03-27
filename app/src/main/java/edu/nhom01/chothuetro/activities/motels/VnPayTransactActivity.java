package edu.nhom01.chothuetro.activities.motels;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import edu.nhom01.chothuetro.R;
import edu.nhom01.chothuetro.api.client.ApiClient;
import edu.nhom01.chothuetro.api.routes.IApiRoutes;
import edu.nhom01.chothuetro.models.nodes.VnPayRequest;
import edu.nhom01.chothuetro.models.transactions.Transaction;
import edu.nhom01.chothuetro.utils.Session;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VnPayTransactActivity extends AppCompatActivity {
    WebView vnPayWebView;
    TextView labelTransactId;
    Transaction transaction;
    VnPayRequest vnPayNode;

    private void setComponents() {
        vnPayNode = new VnPayRequest();
        transaction = new Transaction();
        labelTransactId = findViewById(R.id.labelTransactId);
        vnPayWebView = findViewById(R.id.webVnPay);
    }
    private void setVnPayWebView() {
        transaction = (Transaction) Session.get("transaction-item");
        Call<Transaction> postTransaction = ApiClient.
                getInstance().getRoute().postTransaction(transaction);
        postTransaction.enqueue(new Callback<Transaction>() {
            @Override
            public void onResponse(Call<Transaction> call, Response<Transaction> response) {
                if(response.isSuccessful()) {
                    Call<VnPayRequest> callVnPay = ApiClient.getInstance()
                            .getRoute().sendTransactionLink(transaction.
                                    getTransactionId().trim(), "VNPAY");
                    callVnPay.enqueue(new Callback<VnPayRequest>() {
                        @Override
                        public void onResponse(Call<VnPayRequest> call,
                                               Response<VnPayRequest> response) {
                            if(response.isSuccessful()) {
                                String paymentUrl = response.body().getValueNode().trim();
                                labelTransactId.setText(transaction.getTransactionId());
                                vnPayNode = response.body();
                                vnPayWebView.setWebViewClient(new WebViewClient() {
                                    @Override
                                    public void onPageFinished(WebView view, String url) {
                                        super.onPageFinished(view, url);
                                        if(url.contains(IApiRoutes.API_URL.trim())) {
                                            Intent i = new Intent(
                                                    VnPayTransactActivity.this,
                                                    VnPayResponseActivity.class
                                            );
                                            i.putExtra("VnPay-Response", url.trim());
                                            startActivity(i);
                                            finish();
                                        }
                                    }
                                });
                                vnPayWebView.getSettings().setJavaScriptEnabled(true);
                                vnPayWebView.loadUrl(paymentUrl);
                            }
                        }
                        @Override
                        public void onFailure(Call<VnPayRequest> call, Throwable t) {
                            Log.e("API_ERR", t.getMessage());
                        }
                    });
                }
            }
            @Override
            public void onFailure(Call<Transaction> call, Throwable t) {
                Log.e("API_ERR", t.getMessage());
            }
        });
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vn_pay);

        setComponents();
        setVnPayWebView();
    }
}