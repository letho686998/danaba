/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DomainModels;

import model.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author ledun
 */
public class QLHoaDon {

    private String maHoaDon;
    private String tenKhacHang;
    private String tenNhanVien;
    private BigDecimal giaTriVoucher;
    private Date NgayTao;
    private String loaiHoaDon;
    private BigDecimal tongTien;
    private BigDecimal tongTienGiam;
    private String ghiChu;
    private int Trangthai;

    public QLHoaDon() {
    }

    public QLHoaDon(String maHoaDon, String tenKhacHang, String tenNhanVien, BigDecimal giaTriVoucher, Date NgayTao, String loaiHoaDon, BigDecimal tongTien, BigDecimal tongTienGiam,String ghiChu, int Trangthai) {
        this.maHoaDon = maHoaDon;
        this.tenKhacHang = tenKhacHang;
        this.tenNhanVien = tenNhanVien;
        this.giaTriVoucher = giaTriVoucher;
        this.NgayTao = NgayTao;
        this.loaiHoaDon = loaiHoaDon;
        this.tongTien = tongTien;
        this.tongTienGiam = tongTienGiam;
        this.Trangthai = Trangthai;
        this.ghiChu = ghiChu;
    }

    public String getmaHoaDon() {
        return maHoaDon;
    }

    public void setmaHoaDon(String id) {
        this.maHoaDon = id;
    }

    public String gettenKhacHang() {
        return tenKhacHang;
    }

    public void settenKhacHang(String idKhacHang) {
        this.tenKhacHang = idKhacHang;
    }

    public String gettenNhanVien() {
        return tenNhanVien;
    }

    public void settenNhanVien(String idNhanVien) {
        this.tenNhanVien = idNhanVien;
    }

    public BigDecimal getGiaTriVoucher() {
        return giaTriVoucher;
    }

    public void setGiaTriVoucher(BigDecimal giaTriVoucher) {
        this.giaTriVoucher = giaTriVoucher;
    }

    public Date getNgayTao() {
        return NgayTao;
    }

    public void setNgayTao(Date NgayTao) {
        this.NgayTao = NgayTao;
    }

    public String getLoaiHoaDon() {
        return loaiHoaDon;
    }

    public void setLoaiHoaDon(String loaiHoaDon) {
        this.loaiHoaDon = loaiHoaDon;
    }

    public BigDecimal getTongTien() {
        return tongTien;
    }

    public void setTongTien(BigDecimal tongTien) {
        this.tongTien = tongTien;
    }

    public BigDecimal getTongTienGiam() {
        return tongTienGiam;
    }

    public void setTongTienGiam(BigDecimal tongTienGiam) {
        this.tongTienGiam = tongTienGiam;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public int getTrangthai() {
        return Trangthai;
    }

    public void setTrangthai(int Trangthai) {
        this.Trangthai = Trangthai;
    }

    
    
}
