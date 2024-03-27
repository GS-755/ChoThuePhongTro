package edu.nhom01.chothuetro.api.routes;

import java.util.ArrayList;

import edu.nhom01.chothuetro.models.motels.Location;
import edu.nhom01.chothuetro.models.motels.Motel;
import edu.nhom01.chothuetro.models.nodes.VnPayRequest;
import edu.nhom01.chothuetro.models.nodes.VnPayResponse;
import edu.nhom01.chothuetro.models.person.Account;
import edu.nhom01.chothuetro.models.person.Role;
import edu.nhom01.chothuetro.models.person.User;
import edu.nhom01.chothuetro.models.transactions.Transaction;
import edu.nhom01.chothuetro.models.transactions.TransactionType;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface IApiRoutes {
    // API URL
    String API_URL = "http://192.168.40.128:3030/api/";
    String IMG_URL = "http://192.168.40.128:3030/api/images/getmotelimage?motelid=";

    // Locations API
    @GET("locations")
    @Headers({ "Content-Type: application/json" })
    Call<ArrayList<Location>> getLocations();
    @GET("locations")
    @Headers({ "Content-Type: application/json" })
    Call<Location> getLocation(@Query("id") int id);

    // Motels API
    // GET danh sách phòng trọ
    @GET("motels")
    @Headers({ "Content-Type: application/json" })
    Call<ArrayList<Motel>> getMotels();
    // GET 1 đối tượng phòng trọ thông qua Mã phòng trọ
    @GET("motels")
    @Headers({ "Content-Type: application/json" })
    Call<Motel> getMotel(@Query("id") int id);
    // POST phòng trọ
    @POST("motels/postphongtro")
    @Headers({ "Content-Type: application/json" })
    Call<Motel> postMotel(@Body Motel motel);
    // PUT phòng trọ
    @PUT("motels/putphongtro/{id}")
    @Headers({ "Content-Type: application/json" })
    Call<Motel> putMotel(@Path("id") int id, @Body Motel motel);
    // DELETE phòng trọ
    @DELETE("motels/deletephongtro")
    @Headers({ "Content-Type: application/json" })
    Call<Motel> deleteMotel(@Query("id") int id);

    // Roles API
    @GET("roles")
    @Headers({ "Content-Type: application/json" })
    Call<ArrayList<Role>> getRoles();
    @GET("roles")
    @Headers({ "Content-Type: application/json" })
    Call<Role> getRole(@Query("id") int id);

    // Users API
    @GET("users")
    @Headers({ "Content-Type: application/json" })
    Call<ArrayList<User>> getUsers();
    @GET("users")
    @Headers({ "Content-Type: application/json" })
    Call<User> getUser(@Query("id") String id);
    @POST("users/adduser")
    @Headers({ "Content-Type: application/json" })
    Call<User> postUser(@Body User user);
    @PUT("users/edituser")
    @Headers({ "Content-Type: application/json" })
    Call<User> editUser(@Query("id") String id, @Body User user);

    // Accounts API
    @POST("accounts/login")
    @Headers({ "Content-Type: application/json" })
    Call<Account> loginAccount(@Query("username")
                               String userName, @Query("password") String password);
    @POST("accounts/register")
    @Headers({ "Content-Type: application/json" })
    Call<Account> registerAccount(@Body Account account);

    // Transaction types API
    @GET("transactiontypes")
    @Headers({ "Content-Type: application/json" })
    Call<ArrayList<TransactionType>> getTransactionTypes();
    @GET("transactiontypes")
    @Headers({ "Content-Type: application/json" })
    Call<TransactionType> getTransactionType(@Query("id") int id);

    // Transactions API
    @GET("transactions")
    @Headers({ "Content-Type: application/json" })
    Call<ArrayList<Transaction>> getTransactions();
    @GET("transactions")
    @Headers({ "Content-Type: application/json" })
    Call<Transaction> getTransaction(@Query("id") String id);
    @POST("transactions/postgiaodich")
    @Headers({ "Content-Type: application/json" })
    Call<Transaction> postTransaction(@Body Transaction transaction);


    // VnPay API
    @GET("vnpay/sendtransaction")
    @Headers({ "Content-Type: application/json" })
    Call<VnPayRequest> sendTransactionLink(@Query("maGd") String transactId,
                                           @Query("vnpBankCode") String vnpBankCode);
    @GET("vnpay/getresponse")
    @Headers({ "Content-Type: application/json" })
    Call<VnPayResponse> getTransactionResponse(
            @Query("vnp_Amount") String vnp_Amount,
            @Query("vnp_BankCode") String vnp_BankCode,
            @Query("vnp_BankTranNo") String vnp_BankTranNo,
            @Query("vnp_CardType") String vnp_CardType,
            @Query("vnp_OrderInfo") String vnp_OrderInfo,
            @Query("vnp_PayDate") String vnp_PayDate,
            @Query("vnp_ResponseCode") String vnp_ResponseCode,
            @Query("vnp_TmnCode") String vnp_TmnCode,
            @Query("vnp_TransactionNo") String vnp_TransactionNo,
            @Query("vnp_TransactionStatus") String vnp_TransactionStatus,
            @Query("vnp_TxnRef") String vnp_TxnRef,
            @Query("vnp_SecureHash") String vnp_SecureHash
    );
}
