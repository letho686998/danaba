/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DomainModels;

import java.util.Date;
import java.util.UUID;
import model.ChucVu;

/**
 *
 * @author ledun
 */
public class QLNhanVien {

    private String id;
    private String maNv;
    private String tenNv;
    private String sdt;
    private String chucVu;
    private Date ngayTao;
    private Integer gioiTinh;
    private Date ngaySua;
    private Integer trangThai;
    private ChucVu idcv;
    public QLNhanVien() {
    }

    public QLNhanVien(String id, String maNv, String tenNv, String sdt, String chucVu, Date ngayTao, Integer gioiTinh, Date ngaySua, Integer trangThai) {
        this.id = id;
        this.maNv = maNv;
        this.tenNv = tenNv;
        this.sdt = sdt;
        this.chucVu = chucVu;
        this.ngayTao = ngayTao;
        this.gioiTinh = gioiTinh;
        this.ngaySua = ngaySua;
        this.trangThai = trangThai;
    }

    public QLNhanVien(String tennhanvien, String maNv, String sdt, Date ngayDb, ChucVu idcv, int gioiTinh, int trangThai) {
        this.maNv = maNv;
        this.tenNv = tennhanvien;
        this.sdt = sdt;
        this.idcv = idcv;
        this.ngayTao = ngayDb;
        this.gioiTinh = gioiTinh;
        this.trangThai = trangThai;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMaNv() {
        return maNv;
    }

    public void setMaNv(String maNv) {
        this.maNv = maNv;
    }

    public String getTenNv() {
        return tenNv;
    }

    public void setTenNv(String tenNv) {
        this.tenNv = tenNv;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getChucVu() {
        return chucVu;
    }

    public void setChucVu(String chucVu) {
        this.chucVu = chucVu;
    }

    public Date getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }

    public Integer getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(Integer gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public Date getNgaySua() {
        return ngaySua;
    }

    public void setNgaySua(Date ngaySua) {
        this.ngaySua = ngaySua;
    }

    public Integer getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(Integer trangThai) {
        this.trangThai = trangThai;
    }

    public ChucVu getIdcv() {
        return idcv;
    }

    public void setIdcv(ChucVu idcv) {
        this.idcv = idcv;
    }

    @Override
    public String toString() {
        return "QLNhanVien{" + "id=" + id + ", maNv=" + maNv + ", tenNv=" + tenNv + ", sdt=" + sdt + ", chucVu=" + chucVu + ", ngayTao=" + ngayTao + ", gioiTinh=" + gioiTinh + ", ngaySua=" + ngaySua + ", trangThai=" + trangThai + ", idcv=" + idcv + '}';
    }

}
