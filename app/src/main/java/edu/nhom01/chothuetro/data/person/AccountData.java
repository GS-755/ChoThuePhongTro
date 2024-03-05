package edu.nhom01.chothuetro.data.person;

import static edu.nhom01.chothuetro.api.client.ApiClient.apiClient;
import android.util.Log;
import edu.nhom01.chothuetro.models.nodes.LoginNode;
import edu.nhom01.chothuetro.models.person.Account;
import edu.nhom01.chothuetro.utils.Session;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AccountData {
    private Account account;
    private LoginNode loginNode;
    private boolean isLoggedIn;
    private String message;

    public AccountData() {
        this.message = "";
        this.account = new Account();
        this.loginNode = new LoginNode();
    }
    public AccountData(LoginNode loginNode) {
        this.message = "";
        this.account = new Account();
        this.loginNode = loginNode;
        this.fetchData();
    }

    public void fetchData() {
        try {
            Call<Account> loginAccount = apiClient().
                    prepareExecution().loginAccount(
                            this.loginNode.getUserName(),
                            this.loginNode.getPassword()
                    );
            loginAccount.enqueue(new Callback<Account>() {
                @Override
                public void onResponse(Call<Account> call, Response<Account> response) {
                    if(response.isSuccessful()) {
                        account = response.body();
                        isLoggedIn = true;
                        message = "Đăng nhập thành công!";
                        Session.put("current_account", account);
                    }
                    else if(response.code() == 400) {
                        account = new Account();
                        isLoggedIn = false;
                        message = "Sai tài khoản hoặc mật khẩu.";
                    }
                    else {
                        account = new Account();
                        isLoggedIn = false;
                        message = "Có lỗi xảy ra trong quá trình đăng nhập.";
                    }
                }

                @Override
                public void onFailure(Call<Account> call, Throwable t) {
                    account = new Account();
                    isLoggedIn = false;
                    message = t.getMessage();
                }
            });
        }
        catch(NullPointerException ex) {
            Log.d("INT_ERR", ex.getMessage());
        }
    }
    public Account getAccount() {
        return this.account;
    }
    public boolean isLoggedIn() { return this.isLoggedIn; }
    public String getMessage() { return this.message; }
    public void setLoginNode(LoginNode loginNode) { this.loginNode = loginNode; }
    public void setMessage(String message) { this.message = message; }
}
