package edu.nhom01.chothuetro.api.client;

import edu.nhom01.chothuetro.api.IMyApi;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public final class ApiClient {
    private static ApiClient instance = new ApiClient();
    private IMyApi client;

    private ApiClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(IMyApi.API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        this.client = retrofit.create(IMyApi.class);
    }

    public IMyApi prepareExecution() { return this.client; }

    public static synchronized ApiClient apiClient() {
        return instance;
    }
    private static void setClient(ApiClient newInstance) {
        instance = newInstance;
    }
}
