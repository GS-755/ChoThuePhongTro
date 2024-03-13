package edu.nhom01.chothuetro.models.transactions;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

import edu.nhom01.chothuetro.models.motels.Motel;
import edu.nhom01.chothuetro.models.person.Account;

public class Transaction {
    @SerializedName("MaGD")
    private String transactionId;
    @SerializedName("MaLoaiGD")
    private int idTransactionType;
    @SerializedName("NgayGD")
    private Date transactionDate;
    @SerializedName("SoTien")
    private double amount;
    @SerializedName("TenDangNhap")
    private String userName;
    @SerializedName("MaPT")
    private int motelId;
    @SerializedName("LoaiGiaoDich")
    private TransactionType transactionType;
    @SerializedName("PhongTro")
    private Motel motel;

    public Transaction() { }

    @SerializedName("MaGD")
    public String getTransactionId() { return this.transactionId; }
    @SerializedName("MaLoaiGD")
    public int getIdTransactionType() { return this.idTransactionType; }
    @SerializedName("NgayGD")
    public Date getTransactionDate() { return this.transactionDate; }
    @SerializedName("SoTien")
    public double getAmount() { return this.amount;}
    @SerializedName("TenDangNhap")
    public String getUserName() { return this.userName; }
    @SerializedName("MaPT")
    public int getMotelId() { return this.motelId; }
    @SerializedName("LoaiGiaoDich")
    public TransactionType getTransactionType() { return this.transactionType; }
    @SerializedName("PhongTro")
    public Motel getMotel() { return this.motel; }
    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }
    public void setIdTransactionType(int idTransactionType) {
        this.idTransactionType = idTransactionType;
    }
    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }
    public void setAmount(double amount) {
        this.amount = amount;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public void setMotelId(int motelId) {
        this.motelId = motelId;
    }
    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }
    public void setMotel(Motel motel) {
        this.motel = motel;
    }
}
