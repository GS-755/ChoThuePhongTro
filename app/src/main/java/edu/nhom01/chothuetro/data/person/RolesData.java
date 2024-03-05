package edu.nhom01.chothuetro.data.person;

import java.util.ArrayList;

import static edu.nhom01.chothuetro.api.client.ApiClient.apiClient;

import android.util.Log;

import edu.nhom01.chothuetro.models.interfaces.IApiData;
import edu.nhom01.chothuetro.models.person.Role;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RolesData implements IApiData {
    private Role role;
    private ArrayList<Role> roles;

    public RolesData() {
        this.role = new Role();
        this.roles = new ArrayList<>();
    }

    @Override
    public int getItemCount() {
        return this.roles.size();
    }

    @Override
    public void fetchData() {
        Call<ArrayList<Role>> callRoles = apiClient().
                prepareExecution().getRoles();
        callRoles.enqueue(new Callback<ArrayList<Role>>() {
            @Override
            public void onResponse(Call<ArrayList<Role>> call, Response<ArrayList<Role>> response) {
                if(response.isSuccessful()) {
                    roles = response.body();
                }
            }
            @Override
            public void onFailure(Call<ArrayList<Role>> call, Throwable t) {
                Log.d("API_ERR", t.getMessage());
            }
        });
    }

    @Override
    public void fetchData(int id) {
        Call<Role> callRole = apiClient().
                prepareExecution().getRole(id);
        callRole.enqueue(new Callback<Role>() {
            @Override
            public void onResponse(Call<Role> call, Response<Role> response) {
                if(response.isSuccessful()) {
                    role = response.body();
                }
            }

            @Override
            public void onFailure(Call<Role> call, Throwable t) {
                Log.d("API_ERR", t.getMessage());
            }
        });
    }

    @Override
    public void fetchData(String id) {

    }
}
