package edu.nhom01.chothuetro.models.motels;

import com.google.gson.annotations.SerializedName;

public class MotelStatus {
    @SerializedName("PhongTro")
    private Motel motel;
    @SerializedName("MaTT")
    private int idMotelStatus;
    @SerializedName("TenTT")
    private String nameMotelStatus;

    public MotelStatus() { }

    @SerializedName("PhongTro")
    public Motel getMotel() { return this.motel; }
    @SerializedName("MaTT")
    public int getIdMotelStatus() { return this.idMotelStatus; }
    @SerializedName("TenTT")
    public String getNameMotelStatus() { return this.nameMotelStatus; }
    public void setMotel(Motel motel) {
        this.motel = motel;
    }
    public void setIdMotelStatus(int idMotelStatus) {
        this.idMotelStatus = idMotelStatus;
    }
    public void setNameMotelStatus(String nameMotelStatus) {
        this.nameMotelStatus = nameMotelStatus;
    }
}
