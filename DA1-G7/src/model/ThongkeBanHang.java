/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import DomainModels.*;
import java.util.Date;

/**
 *
 * @author ledun
 */
public class ThongkeBanHang {
    private String ma;
    private String ten;
    private String kichthuoc;
    private int soLuong;
    private Double tongTien;
    private Date ngayTao;
    public ThongkeBanHang() {
    }

    public ThongkeBanHang(String ma, String ten, String kichthuoc, int soLuong, Double tongTien, Date ngayTao) {
        this.ma = ma;
        this.ten = ten;
        this.kichthuoc = kichthuoc;
        this.soLuong = soLuong;
        this.tongTien = tongTien;
        this.ngayTao = ngayTao;
    }

    public ThongkeBanHang(String ma, String ten, String kichthuoc, int soLuong, Double tongTien) {
        this.ma = ma;
        this.ten = ten;
        this.kichthuoc = kichthuoc;
        this.soLuong = soLuong;
        this.tongTien = tongTien;
    }

    public String getKichthuoc() {
        return kichthuoc;
    }

    public void setKichthuoc(String kichthuoc) {
        this.kichthuoc = kichthuoc;
    }



    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public Double getTongTien() {
        return tongTien;
    }

    public void setTongTien(Double tongTien) {
        this.tongTien = tongTien;
    }

    @Override
    public String toString() {
        return "ThongkeBanHang{" + "ma=" + ma + ", ten=" + ten + ", soLuong=" + soLuong + ", tongTien=" + tongTien + '}';
    }

    public Date getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }
    
    
    
}
