package edu.nhom01.chothuetro.models.nodes;

public class LoginNode {
    private String userName;
    private String password;

    public LoginNode() { }
    public LoginNode(String userName, String password) {
        this.userName = userName.trim();
        this.password = password;
    }

    public String getUserName() { return this.userName.trim(); }
    public String getPassword() { return this.password; }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
