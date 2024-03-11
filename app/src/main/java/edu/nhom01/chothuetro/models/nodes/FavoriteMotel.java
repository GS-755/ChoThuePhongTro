package edu.nhom01.chothuetro.models.nodes;

import com.google.gson.annotations.SerializedName;

import edu.nhom01.chothuetro.models.motels.Motel;
import edu.nhom01.chothuetro.models.person.Account;

public class FavoriteMotel {
    @SerializedName("GhiChu")
    private String note;
    @SerializedName("TenDangNhap")
    private String userName;
    @SerializedName("MaPT")
    private int motelId;
    @SerializedName("TaiKhoan")
    private Account account;
    @SerializedName("PhongTro")
    private Motel motel;

    public FavoriteMotel() { }

    @SerializedName("GhiChu")
    public String getNote() { return this.note; }
    @SerializedName("TenDangNhap")
    public String getUserName() { return this.userName; }
    @SerializedName("MaPT")
    public int getMotelId() { return this.motelId; }
    @SerializedName("TaiKhoan")
    public Account getAccount() { return this.account; }
    @SerializedName("PhongTro")
    public Motel getMotel() { return this.motel; }
    public void setNote(String note) {
        this.note = note;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public void setMotelId(int motelId) {
        this.motelId = motelId;
    }
    public void setAccount(Account account) {
        this.account = account;
    }
    public void setMotel(Motel motel) {
        this.motel = motel;
    }
}
