package model;

import java.util.ArrayList;
import java.util.UUID;

public class TaiKhoan {

    private int idNV;
    private String email;
    private String UserName;
    private String PassWord;
    private String role;
    private String sdt;

    public TaiKhoan() {
    }

    public TaiKhoan(int idNV, String email, String UserName, String PassWord, String role, String sdt) {
        this.idNV = idNV;
        this.email = email;
        this.UserName = UserName;
        this.PassWord = PassWord;
        this.role = role;
        this.sdt = sdt;
    }

    public TaiKhoan(int idNV, String email, String sdt, String PassWord, String role) {
        this.idNV = idNV;
        this.UserName = email;
        this.PassWord = PassWord;
        this.sdt = sdt;
        this.role = role;

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getIdNV() {
        return idNV;
    }

    public void setIdNV(int idNV) {
        this.idNV = idNV;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String UserName) {
        this.UserName = UserName;
    }

    public String getPassWord() {
        return PassWord;
    }

    public void setPassWord(String PassWord) {
        this.PassWord = PassWord;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

}
