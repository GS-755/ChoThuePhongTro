package edu.nhom01.chothuetro.api.client;

import android.util.Log;

import edu.nhom01.chothuetro.api.routes.IApiRoutes;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public final class ApiClient {
    private static ApiClient instance = new ApiClient();
    private Retrofit retrofit;
    private IApiRoutes client;

    private ApiClient() {
        try {
            Thread.sleep(1500);
            if(this.retrofit == null || this.client == null) {
                this.retrofit = new Retrofit.Builder()
                        .baseUrl(IApiRoutes.API_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                this.client = retrofit.create(IApiRoutes.class);
            }
        } catch (InterruptedException e) {
            Log.d("SYS_ERR", e.getMessage());
        }
    }

    public IApiRoutes getRoute() { return this.client; }

    public static synchronized ApiClient getInstance() {
        if(ApiClient.instance == null) {
            ApiClient.instance = new ApiClient();
        }

        return ApiClient.instance;
    }
    private static void setClient(ApiClient newInstance) {
        instance = newInstance;
    }
}
