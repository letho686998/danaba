/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DomainModels;

import java.util.Date;

/**
 *
 * @author ledun
 */
public class QLCtNhanVien {

    private String id;
    private String ma;
    private String email;
    private String sdt;
    private Date ngaySinh;
    private String diaChi;
    private String passs;
    
    
    public QLCtNhanVien() {
    }

    public QLCtNhanVien(String id, String ma, String email, String sdt, Date ngaySinh, String diaChi, String passs) {
        this.id = id;
        this.ma = ma;
        this.email = email;
        this.sdt = sdt;
        this.ngaySinh = ngaySinh;
        this.diaChi = diaChi;
        this.passs = passs;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getPasss() {
        return passs;
    }

    public void setPasss(String passs) {
        this.passs = passs;
    }

    
}
