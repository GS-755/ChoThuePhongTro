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
    private ArrayList<Transaction> transactions;
    @SerializedName("PTYeuThich")
    private ArrayList<FavoriteMotel> favoriteMotels;
    @SerializedName("PhongTro")
    private ArrayList<Motel> motels;
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
    public ArrayList<Transaction> getTransactions() { return this.transactions; }
    @SerializedName("PTYeuThich")
    public ArrayList<FavoriteMotel> getFavoriteMotels() { return this.favoriteMotels; }
    @SerializedName("PhongTro")
    public ArrayList<Motel> getMotels() { return this.motels; }
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
    public void setTransactions(ArrayList<Transaction> transactions) {
        this.transactions = transactions;
    }
    public void setFavoriteMotels(ArrayList<FavoriteMotel> favoriteMotels) {
        this.favoriteMotels = favoriteMotels;
    }
    public void setMotels(ArrayList<Motel> motels) {
        this.motels = motels;
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
