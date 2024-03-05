package edu.nhom01.chothuetro.api;

import java.util.ArrayList;

import edu.nhom01.chothuetro.models.motels.Location;
import edu.nhom01.chothuetro.models.motels.Motel;
import edu.nhom01.chothuetro.models.nodes.VnPayNode;
import edu.nhom01.chothuetro.models.person.Account;
import edu.nhom01.chothuetro.models.person.Role;
import edu.nhom01.chothuetro.models.person.User;
import edu.nhom01.chothuetro.models.transactions.Transaction;
import edu.nhom01.chothuetro.models.transactions.TransactionType;
import edu.nhom01.chothuetro.models.transactions.VnPayReturn;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface IMyApi {
    // API URL
    String API_URL = "http://192.168.40.128:3030/api/";

    // Locations API
    @GET("locations")
    Call<ArrayList<Location>> getLocations();
    @GET("locations")
    Call<Location> getLocation(@Query("id") int id);

    // Motels API
    @GET("motels")
    Call<ArrayList<Motel>> getMotels();
    @GET("motels")
    Call<Motel> getMotel(@Query("id") int id);
    @Headers({ "Content-Type: application/json" })
    @POST("motels/postphongtro")
    Call<Motel> postMotel(@Body Motel motel);
    @Headers({ "Content-Type: application/json" })
    @PUT("motels/putphongtro/{id}")
    Call<Motel> putMotel(@Path("id") int id, @Body Motel motel);
    @DELETE("motels/deletephongtro")
    Call<Motel> deleteMotel(@Query("id") int id);

    // Roles API
    @GET("roles")
    Call<ArrayList<Role>> getRoles();
    @GET("roles")
    Call<Role> getRole(@Query("id") int id);

    // Users API
    @GET("users")
    Call<ArrayList<User>> getUsers();
    @GET("users")
    Call<User> getUser(@Query("id") String id);
    @Headers({ "Content-Type: application/json" })
    @POST("users/adduser")
    Call<User> postUser(@Body User user);
    @Headers({ "Content-Type: application/json" })
    @PUT("users/edituser")
    Call<User> editUser(@Query("id") String id, @Body User user);

    // Accounts API
    @Headers({ "Content-Type: application/json" })
    @POST("accounts/login")
    Call<Account> loginAccount(@Query("username")
                               String userName, @Query("password") String password);
    @Headers({ "Content-Type: application/json" })
    @POST("accounts/register")
    Call<Account> registerAccount(@Body Account account);
    // Transaction types API
    @GET("transactiontypes")
    Call<ArrayList<TransactionType>> getTransactionTypes();
    @GET("transactiontypes")
    Call<TransactionType> getTransactionType(@Query("id") int id);

    // Transactions API
    @GET("transactions")
    Call<ArrayList<Transaction>> getTransactions();
    @GET("transactions")
    Call<Transaction> getTransaction(@Query("id") String id);
    @Headers({ "Content-Type: application/json" })
    @POST("transactions/postgiaodich")
    Call<Transaction> postTransaction(@Body Transaction transaction);


    // VnPay API
    @GET("vnpay/sendtransaction")
    Call<VnPayNode> sendTransactionLink(@Query("maGd") String transactId,
                                        @Query("vnpBankCode") String vnpBankCode);
}
