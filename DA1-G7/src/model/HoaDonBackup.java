/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.LocalDateTime;

/**
 *
 * @author cuongwf
 */
public class HoaDonBackup {

    private int id;
    private String maHoaDon;
    private int idNhanVien;
    private String tenNhanVien;
    private int idKhachHang;
    private String tenKhachHang;
    private String hinhThucThanhToan;
    private Double tongTien;
    private Double tongTienGiam;
    private LocalDateTime ngayTao;
    private boolean trangThai;
    private int idVoucher;
    private String tenVoucher;

    public HoaDonBackup() {
    }

    public HoaDonBackup(int id, String maHoaDon, int idNhanVien, String tenNhanVien, int idKhachHang, String tenKhachHang, String hinhThucThanhToan, Double tongTien, Double tongTienGiam, LocalDateTime ngayTao, boolean trangThai, int idVoucher, String tenVoucher) {
        this.id = id;
        this.maHoaDon = maHoaDon;
        this.idNhanVien = idNhanVien;
        this.tenNhanVien = tenNhanVien;
        this.idKhachHang = idKhachHang;
        this.tenKhachHang = tenKhachHang;
        this.hinhThucThanhToan = hinhThucThanhToan;
        this.tongTien = tongTien;
        this.tongTienGiam = tongTienGiam;
        this.ngayTao = ngayTao;
        this.trangThai = trangThai;
        this.idVoucher = idVoucher;
        this.tenVoucher = tenVoucher;
    }
    
    public HoaDonBackup(int id, String maHoaDon, int idNhanVien, String tenNhanVien, int idKhachHang, String tenKhachHang, String hinhThucThanhToan, Double tongTien, Double tongTienGiam, LocalDateTime ngayTao, boolean trangThai) {
        this.id = id;
        this.maHoaDon = maHoaDon;
        this.idNhanVien = idNhanVien;
        this.tenNhanVien = tenNhanVien;
        this.idKhachHang = idKhachHang;
        this.tenKhachHang = tenKhachHang;
        this.hinhThucThanhToan = hinhThucThanhToan;
        this.tongTien = tongTien;
        this.tongTienGiam = tongTienGiam;
        this.ngayTao = ngayTao;
        this.trangThai = trangThai;
    }

    public HoaDonBackup(String maHoaDon, int idNhanVien, int idKhachHang) {
        this.maHoaDon = maHoaDon;
        this.idNhanVien = idNhanVien;
        this.idKhachHang = idKhachHang;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(String maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public int getIdNhanVien() {
        return idNhanVien;
    }

    public void setIdNhanVien(int idNhanVien) {
        this.idNhanVien = idNhanVien;
    }

    public String getTenNhanVien() {
        return tenNhanVien;
    }

    public void setTenNhanVien(String tenNhanVien) {
        this.tenNhanVien = tenNhanVien;
    }

    public int getIdKhachHang() {
        return idKhachHang;
    }

    public void setIdKhachHang(int idKhachHang) {
        this.idKhachHang = idKhachHang;
    }

    public String getTenKhachHang() {
        return tenKhachHang;
    }

    public void setTenKhachHang(String tenKhachHang) {
        this.tenKhachHang = tenKhachHang;
    }

    public String getHinhThucThanhToan() {
        return hinhThucThanhToan;
    }

    public void setHinhThucThanhToan(String hinhThucThanhToan) {
        this.hinhThucThanhToan = hinhThucThanhToan;
    }

    public Double getTongTien() {
        return tongTien;
    }

    public void setTongTien(Double tongTien) {
        this.tongTien = tongTien;
    }

    public Double getTongTienGiam() {
        return tongTienGiam;
    }

    public void setTongTienGiam(Double tongTienGiam) {
        this.tongTienGiam = tongTienGiam;
    }

    public LocalDateTime getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(LocalDateTime ngayTao) {
        this.ngayTao = ngayTao;
    }

    public boolean isTrangThai() {
        return trangThai;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }

    public int getIdVoucher() {
        return idVoucher;
    }

    public void setIdVoucher(int idVoucher) {
        this.idVoucher = idVoucher;
    }

    public String getTenVoucher() {
        return tenVoucher;
    }

    public void setTenVoucher(String tenVoucher) {
        this.tenVoucher = tenVoucher;
    }

}
