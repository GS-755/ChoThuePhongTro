package edu.nhom01.chothuetro.models.motels;

import com.google.gson.annotations.SerializedName;

public class Location {
    @SerializedName("MaVT")
    private int idLocation;
    @SerializedName("Quan")
    private String nameLocation;
    @SerializedName("KinhDo")
    private double latitulde;
    @SerializedName("ViDo")
    private double longtitude;

    public Location() { }

    @SerializedName("MaVT")
    public int getIdLocation() { return this.idLocation; }
    @SerializedName("Quan")
    public String getNameLocation() { return this.nameLocation; }
    @SerializedName("KinhDo")
    public double getLatitulde() { return this.latitulde; }
    @SerializedName("ViDo")
    public double getLongtitude() { return this.longtitude; }
    public void setIdLocation(int idLocation) {
        this.idLocation = idLocation;
    }
    public void setNameLocation(String nameLocation) {
        this.nameLocation = nameLocation;
    }
    public void setLatitulde(double latitulde) {
        this.latitulde = latitulde;
    }
    public void setLongtitude(double longtitude) {
        this.longtitude = longtitude;
    }
}
