package edu.nhom01.chothuetro.activities.motels;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import edu.nhom01.chothuetro.R;
import edu.nhom01.chothuetro.api.client.ApiClient;
import edu.nhom01.chothuetro.models.nodes.VnPayNode;
import edu.nhom01.chothuetro.models.transactions.Transaction;
import edu.nhom01.chothuetro.utils.Session;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VnPayActivity extends AppCompatActivity {
    WebView vnPayWebView;
    TextView labelTransactId;
    Transaction transaction;
    VnPayNode vnPayNode;

    private void setComponents() {
        vnPayNode = new VnPayNode();
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
                    Call<VnPayNode> callVnPay = ApiClient.getInstance()
                            .getRoute().sendTransactionLink(transaction.
                                    getTransactionId().trim(), "VNPAY");
                    callVnPay.enqueue(new Callback<VnPayNode>() {
                        @Override
                        public void onResponse(Call<VnPayNode> call, Response<VnPayNode> response) {
                            if(response.isSuccessful()) {
                                String url = response.body().getValueNode().trim();
                                labelTransactId.setText(transaction.getTransactionId());
                                vnPayNode = response.body();
                                WebViewClient client = new WebViewClient();
                                vnPayWebView.setWebViewClient(client);
                                vnPayWebView.getSettings().setJavaScriptEnabled(true);
                                vnPayWebView.loadUrl(url);
                            }
                        }
                        @Override
                        public void onFailure(Call<VnPayNode> call, Throwable t) {
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