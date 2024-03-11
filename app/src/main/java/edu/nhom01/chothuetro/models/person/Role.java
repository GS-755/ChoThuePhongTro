package edu.nhom01.chothuetro.models.person;

import com.google.gson.annotations.SerializedName;

public class Role {
    @SerializedName("MaVaiTro")
    private int roleId;
    @SerializedName("TenVaiTro")
    private String roleName;


    public Role() { }
    public Role(int roleId, String roleName) {
        this.roleId = roleId;
        this.roleName = roleName;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
