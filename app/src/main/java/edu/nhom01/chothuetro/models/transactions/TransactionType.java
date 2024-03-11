package edu.nhom01.chothuetro.models.transactions;

import com.google.gson.annotations.SerializedName;

public class TransactionType {
    @SerializedName("MaLoaiGD")
    private int idTransType;
    @SerializedName("TenLoaiGD")
    private String nameTransType;

    public TransactionType() { }

    @SerializedName("MaLoaiGD")
    public int getIdTransType() { return this.idTransType; }
    @SerializedName("TenLoaiGD")
    public String getNameTransType() { return this.nameTransType; }
}
