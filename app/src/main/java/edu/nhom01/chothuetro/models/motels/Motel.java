package edu.nhom01.chothuetro.models.motels;

import com.google.gson.annotations.SerializedName;
import java.util.Date;

public class Motel {
    @SerializedName("MaPT")
    private int motelId;
    @SerializedName("TieuDe")
    private String title;
    @SerializedName("NgayDang")
    private Date dateUpload;
    @SerializedName("DienTich")
    private double area;
    @SerializedName("SoTien")
    private double fullAmount;
    @SerializedName("TienCoc")
    private double depositAmount;
    @SerializedName("MoTa")
    private String description;
    @SerializedName("HinhAnh")
    private String imageFileName;
    @SerializedName("DiaChi")
    private String address;
    @SerializedName("Base64Image")
    private String base64Image;
    @SerializedName("TenDangNhap")
    private String userName;
    @SerializedName("MaVT")
    private int idLocation;
    @SerializedName("MaTT")
    private int idStatus;

    public Motel() { }

    @SerializedName("MaPT")
    public int getMotelId() { return this.motelId; }
    @SerializedName("TieuDe")
    public String getTitle() { return this.title; }
    @SerializedName("NgayDang")
    public Date getDateUpload() { return this.dateUpload; }
    @SerializedName("DienTich")
    public double getArea() { return this.area; }
    @SerializedName("SoTien")
    public double getFullAmount() { return this.fullAmount; }
    @SerializedName("TienCoc")
    public double getDepositAmount() { return this.depositAmount; }
    @SerializedName("MoTa")
    public String getDescription() { return this.description; }
    @SerializedName("HinhAnh")
    public String getImageFileName() { return this.imageFileName; }
    @SerializedName("DiaChi")
    public String getAddress() { return this.address; }
    @SerializedName("Base64Image")
    public String getBase64Image() { return this.base64Image; }
    @SerializedName("TenDangNhap")
    public String getUserName() { return this.userName; }
    @SerializedName("MaVT")
    public int getIdLocation() { return this.idLocation; }
    @SerializedName("MaTT")
    public int getIdStatus() { return this.idStatus; }
    public void setMotelId(int motelId) {
        this.motelId = motelId;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setDateUpload(Date dateUpload) {
        this.dateUpload = dateUpload;
    }
    public void setArea(double area) {
        this.area = area;
    }
    public void setFullAmount(double fullAmount) {
        this.fullAmount = fullAmount;
    }
    public void setDepositAmount(double depositAmount) {
        this.depositAmount = depositAmount;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setImageFileName(String imageFileName) {
        this.imageFileName = imageFileName;
    }
    public void setBase64Image(String base64Image) {
        this.base64Image = base64Image;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public void setIdLocation(int idLocation) {
        this.idLocation = idLocation;
    }
    public void setIdStatus(int idStatus) {
        this.idStatus = idStatus;
    }
}
