package edu.nhom01.chothuetro.models.person;

import com.google.gson.annotations.SerializedName;
import java.util.Date;

public class User {
    @SerializedName("CCCD")
    private String cid;
    @SerializedName("HoLot")
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

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    public String getFullName() {
        return String.format("%s %s", this.firstName.trim(), this.lastName.trim());
    }
}
