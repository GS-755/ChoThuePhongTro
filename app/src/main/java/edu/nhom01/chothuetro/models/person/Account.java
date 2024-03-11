package edu.nhom01.chothuetro.models.person;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import edu.nhom01.chothuetro.models.motels.Motel;
import edu.nhom01.chothuetro.models.nodes.FavoriteMotel;
import edu.nhom01.chothuetro.models.transactions.Transaction;

public class Account {
    @SerializedName("NguoiDung")
    private User user;
    @SerializedName("GiaoDich")
    private ArrayList<Transaction> transaction;
    @SerializedName("PTYeuThich")
    private ArrayList<FavoriteMotel> favoriteMotel;
    @SerializedName("PhongTro")
    private ArrayList<Motel> motel;
    @SerializedName("VaiTro")
    private Role role;
    @SerializedName("TenDangNhap")
    private String userName;
    @SerializedName("MatKhau")
    private String password;
    @SerializedName("Email")
    private String email;
    @SerializedName("SoDT")
    private String phone;
    @SerializedName("MaVaiTro")
    private int roleId;
    @SerializedName("CCCD")
    private String cid;

    public Account() { }

    @SerializedName("NguoiDung")
    public User getUser() { return this.user; }
    @SerializedName("GiaoDich")
    public ArrayList<Transaction> getTransaction() { return this.transaction; }
    @SerializedName("PTYeuThich")
    public ArrayList<FavoriteMotel> getFavoriteMotel() { return this.favoriteMotel; }
    @SerializedName("PhongTro")
    public ArrayList<Motel> getMotel() { return this.motel; }
    @SerializedName("VaiTro")
    public Role getRole() { return this.role; }
    @SerializedName("TenDangNhap")
    public String getUserName() {
        return this.userName;
    }
    @SerializedName("MatKhau")
    public String getPassword() {
        return this.password;
    }
    @SerializedName("Email")
    public String getEmail() {
        return this.email;
    }
    @SerializedName("SoDT")
    public String getPhone() { return this.phone; }
    @SerializedName("MaVaiTro")
    public int getRoleId() {
        return this.roleId;
    }
    @SerializedName("CCCD")
    public String getCid() { return this.cid; }

    public void setUser(User user) {
        this.user = user;
    }
    public void setTransaction(ArrayList<Transaction> transaction) {
        this.transaction = transaction;
    }
    public void setFavoriteMotel(ArrayList<FavoriteMotel> favoriteMotel) {
        this.favoriteMotel = favoriteMotel;
    }
    public void setMotel(ArrayList<Motel> motel) {
        this.motel = motel;
    }
    public void setRole(Role role) {
        this.role = role;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }
    public void setCid(String cid) {
        this.cid = cid;
    }
}
