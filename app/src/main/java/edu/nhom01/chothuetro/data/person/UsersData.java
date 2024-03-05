package edu.nhom01.chothuetro.data.person;

import java.util.ArrayList;
import edu.nhom01.chothuetro.models.interfaces.IApiData;
import edu.nhom01.chothuetro.models.person.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import static edu.nhom01.chothuetro.api.client.ApiClient.apiClient;
import android.util.Log;

public class UsersData implements IApiData {
    private User user;
    private ArrayList<User> userArrayList;
    private boolean isCompleted;
    private String message;

    public UsersData() {
        this.message = "";
        this.user = new User();
        this.userArrayList = new ArrayList<>();
    }

    @Override
    public int getItemCount() {
        return this.userArrayList.size();
    }
    public User getUser() { return this.user; }
    public ArrayList<User> getUserArrayList() { return this.userArrayList; }
    public boolean isCompleted() { return this.isCompleted; }
    public String getMessage() { return this.message; }
    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public void setUserArrayList(ArrayList<User> userArrayList) {
        this.userArrayList = userArrayList;
    }

    @Override
    public void fetchData() {
        Call<ArrayList<User>> callUserList = apiClient().
                prepareExecution().getUsers();
        callUserList.enqueue(new Callback<ArrayList<User>>() {
            @Override
            public void onResponse(Call<ArrayList<User>> call, Response<ArrayList<User>> response) {
                if(response.isSuccessful()) {
                    userArrayList = response.body();
                }
            }
            @Override
            public void onFailure(Call<ArrayList<User>> call, Throwable t) {
                Log.d("API_ERR", t.getMessage());
            }
        });
    }

    @Override
    public void fetchData(int id) {

    }

    @Override
    public void fetchData(String id) {
        Call<User> callUser = apiClient().prepareExecution().getUser(id);
        callUser.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.isSuccessful()) {
                    user = response.body();
                }
            }
            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.d("API_ERR", t.getMessage());
            }
        });
    }
    public void createUser() {
        Call<User> callUser = apiClient().
                prepareExecution().postUser(this.user);
        callUser.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.isSuccessful()) {
                    user = response.body();
                    isCompleted = true;
                    message = "Đăng ký thành công!";
                }
                else if(response.code() == 400) {
                    isCompleted = false;
                    message = "Lỗi dữ liệu";
                }
                else {
                    isCompleted = true;
                    message = "Không thể tạo tài khoản!";
                }
            }
            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.d("API_ERR", t.getMessage());
            }
        });
    }
    public void updateUser(String id) {
        Call<User> callUser = apiClient().
                prepareExecution().editUser(id, this.user);
        callUser.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.isSuccessful()) {
                    user = response.body();
                    isCompleted = true;
                    message = "Cập nhật thành công!";
                }
                else if(response.code() == 400) {
                    isCompleted = false;
                    message = "Lỗi dữ liệu";
                }
                else {
                    isCompleted = true;
                    message = "Không thể chỉnh sửa người dùng!";
                }
            }
            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.d("API_ERR", t.getMessage());
            }
        });
    }
}
