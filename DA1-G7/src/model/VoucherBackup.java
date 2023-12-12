/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author cuongwf
 */
public class VoucherBackup {

    private int id;
    private String ma;
    private String ten;
    private boolean loaiGiamGia;
    private double giaTriGiam;
    private int soLuong;
    private double giaTriDonHangToiThieu;
    private int trangThai;

    public VoucherBackup() {
    }

    public VoucherBackup(int id, String ma, String ten, boolean loaiGiamGia, double giaTriGiam, int soLuong, double giaTriDonHangToiThieu, int trangThai) {
        this.id = id;
        this.ma = ma;
        this.ten = ten;
        this.loaiGiamGia = loaiGiamGia;
        this.giaTriGiam = giaTriGiam;
        this.soLuong = soLuong;
        this.giaTriDonHangToiThieu = giaTriDonHangToiThieu;
        this.trangThai = trangThai;
    }
    
    public VoucherBackup(int id, String ma, String ten, boolean loaiGiamGia, double giaTriGiam, int soLuong, double giaTriDonHangToiThieu) {
        this.id = id;
        this.ma = ma;
        this.ten = ten;
        this.loaiGiamGia = loaiGiamGia;
        this.giaTriGiam = giaTriGiam;
        this.soLuong = soLuong;
        this.giaTriDonHangToiThieu = giaTriDonHangToiThieu;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public boolean isLoaiGiamGia() {
        return loaiGiamGia;
    }

    public void setLoaiGiamGia(boolean loaiGiamGia) {
        this.loaiGiamGia = loaiGiamGia;
    }

    public double getGiaTriGiam() {
        return giaTriGiam;
    }

    public void setGiaTriGiam(double giaTriGiam) {
        this.giaTriGiam = giaTriGiam;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public double getGiaTriDonHangToiThieu() {
        return giaTriDonHangToiThieu;
    }

    public void setGiaTriDonHangToiThieu(double giaTriDonHangToiThieu) {
        this.giaTriDonHangToiThieu = giaTriDonHangToiThieu;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

}
