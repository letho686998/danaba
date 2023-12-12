package model;

/**
 *
 * @author cuongwf
 */
public class HoaDonChiTietBackup {

    private int id;
    private int idSanPhamChiTiet;
    private String maSanPhamChiTiet;
    private int idSanPham;
    private String tenSanPham;
    private int idHoaDon;
    private int soLuong;
    private double giaTien;
    private double thanhTien;

    public HoaDonChiTietBackup() {
    }

    public HoaDonChiTietBackup(int id, int idSanPhamChiTiet, String maSanPhamChiTiet, int idSanPham, String tenSanPham, int idHoaDon, int soLuong, double giaTien, double thanhTien) {
        this.id = id;
        this.idSanPhamChiTiet = idSanPhamChiTiet;
        this.maSanPhamChiTiet = maSanPhamChiTiet;
        this.idSanPham = idSanPham;
        this.tenSanPham = tenSanPham;
        this.idHoaDon = idHoaDon;
        this.soLuong = soLuong;
        this.giaTien = giaTien;
        this.thanhTien = thanhTien;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdSanPhamChiTiet() {
        return idSanPhamChiTiet;
    }

    public void setIdSanPhamChiTiet(int idSanPhamChiTiet) {
        this.idSanPhamChiTiet = idSanPhamChiTiet;
    }

    public String getMaSanPhamChiTiet() {
        return maSanPhamChiTiet;
    }

    public void setMaSanPhamChiTiet(String maSanPhamChiTiet) {
        this.maSanPhamChiTiet = maSanPhamChiTiet;
    }

    public int getIdSanPham() {
        return idSanPham;
    }

    public void setIdSanPham(int idSanPham) {
        this.idSanPham = idSanPham;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public int getIdHoaDon() {
        return idHoaDon;
    }

    public void setIdHoaDon(int idHoaDon) {
        this.idHoaDon = idHoaDon;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public double getGiaTien() {
        return giaTien;
    }

    public void setGiaTien(double giaTien) {
        this.giaTien = giaTien;
    }

    public double getThanhTien() {
        return thanhTien;
    }

    public void setThanhTien(double thanhTien) {
        this.thanhTien = thanhTien;
    }

}
