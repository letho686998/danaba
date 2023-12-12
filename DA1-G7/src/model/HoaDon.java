/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author ledun
 */
public class HoaDon {

    private String id;
    private String idKhacHang;
    private String idNhanVien;
    private BigDecimal giaTriVoucher;
    private Date NgayTao;
    private String loaiHoaDon;
    private BigDecimal tongTien;
    private BigDecimal tongTienGiam;
    private String ghiChu;
    private int Trangthai;
     private String hinhThucThanhToan;
    private KhachHang idKh;

    public HoaDon() {
    }

    public HoaDon(String id, String idKhacHang, String idNhanVien, BigDecimal giaTriVoucher, Date NgayTao, String loaiHoaDon, BigDecimal tongTien, BigDecimal tongTienGiam,String ghiChu, int Trangthai) {
        this.id = id;
        this.idKhacHang = idKhacHang;
        this.idNhanVien = idNhanVien;
        this.giaTriVoucher = giaTriVoucher;
        this.NgayTao = NgayTao;
        this.loaiHoaDon = loaiHoaDon;
        this.tongTien = tongTien;
        this.tongTienGiam = tongTienGiam;
        this.Trangthai = Trangthai;
        this.ghiChu = ghiChu;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdKhacHang() {
        return idKhacHang;
    }

    public void setIdKhacHang(String idKhacHang) {
        this.idKhacHang = idKhacHang;
    }

    public String getIdNhanVien() {
        return idNhanVien;
    }

    public void setIdNhanVien(String idNhanVien) {
        this.idNhanVien = idNhanVien;
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

    public String getHinhThucThanhToan() {
        return hinhThucThanhToan;
    }

    public void setHinhThucThanhToan(String hinhThucThanhToan) {
        this.hinhThucThanhToan = hinhThucThanhToan;
    }

    public KhachHang getIdKh() {
        return idKh;
    }

    public void setIdKh(KhachHang idKh) {
        this.idKh = idKh;
    }

    
    
}
