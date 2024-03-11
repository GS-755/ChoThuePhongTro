package edu.nhom01.chothuetro.models.person;

import com.google.gson.annotations.SerializedName;
import java.util.Date;

import edu.nhom01.chothuetro.fragments.widgets.utils.StrProcessor;

public class User {
    @SerializedName("CCCD")
    private String cid;
    @SerializedName("Ho")
    private String firstName;
    @SerializedName("Ten")
    private String lastName;
    @SerializedName("NgaySinh")
    private Date dob;
    @SerializedName("GioiTinh")
    private int gender;
    @SerializedName("DiaChi")
    private String address;

    public User() { }
    public User(String cid, String firstName,
                String lastName, Date dob, int gender, String address) {
        this.cid = cid;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.gender = gender;
        this.address = address;
    }

    @SerializedName("CCCD")
    public String getCid() { return this.cid; }
    @SerializedName("Ho")
    public String getFirstName() { return this.firstName; }
    @SerializedName("Ten")
    public String getLastName() {
        return this.lastName;
    }
    @SerializedName("NgaySinh")
    public Date getDob() {
        return this.dob;
    }
    @SerializedName("GioiTinh")
    public int getGender() {
        return this.gender;
    }
    @SerializedName("DiaChi")
    public String getAddress() {
        return address;
    }
    public void setCid(String cid) {
        this.cid = cid;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public void setDob(Date dob) { this.dob = dob; }
    public void setGender(int gender) {
        this.gender = gender;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    public String getFullName() {
        return String.format("%s %s", this.firstName.trim(), this.lastName.trim());
    }
    public String getStrChineseDate() {
        return StrProcessor.getStrChineseDate(
                this.dob.getYear(),
                this.dob.getMonth(),
                this.dob.getDay()
        );
    }
}
