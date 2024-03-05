package edu.nhom01.chothuetro.models.person;

import com.google.gson.annotations.SerializedName;

public class Account {
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
    public Account(String userName, String password,
                   String email, String phone, int roleId, String cid) {
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.roleId = roleId;
        this.cid = cid;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }
}
