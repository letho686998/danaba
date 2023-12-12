/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.hoadonservice.impl;

import DomainModels.QLHoaDon;
import DomainModels.QLHoaDonChiTiet;
import model.HoaDon;
import java.util.ArrayList;
import java.util.List;
import model.HoaDonBackup;
import model.HoaDonChiTiet;
import model.HoaDonChiTietBackup;
import repository.hoadonrepository.impl.HoaDonRepository;
import repository.hoadonrepository.IHoaDonRepository;
import service.hoadonservice.IHoaDonServices;

/**
 *
 * @author ledun
 */
public class HoaDonService implements IHoaDonServices {

    private IHoaDonRepository repo;
    private ArrayList<QLHoaDon> listHoaDon;
    private ArrayList<QLHoaDonChiTiet> listHoaDonCT;

    public HoaDonService() {
        repo = new HoaDonRepository();
        listHoaDon = new ArrayList<>();
    }

    @Override
    public ArrayList<QLHoaDon> selectAll() {
        listHoaDon = new ArrayList<>();
        var hoaDonRS = repo.selectAll();
        for (HoaDon hd : hoaDonRS) {
            listHoaDon.add(new QLHoaDon(hd.getId(), hd.getIdKhacHang(), hd.getIdNhanVien(),
                    hd.getGiaTriVoucher(), hd.getNgayTao(), hd.getLoaiHoaDon(), hd.getTongTien(),
                    hd.getTongTienGiam(), hd.getGhiChu(), hd.getTrangthai()));
            System.out.println(hd.toString());
        }

        return listHoaDon;
    }

    @Override
    public ArrayList<QLHoaDon> selectloc(String ma, int tt) {
        listHoaDon = new ArrayList<>();
        var hoaDonRS = repo.selectloc(ma, tt);
        for (HoaDon hd : hoaDonRS) {
            listHoaDon.add(new QLHoaDon(hd.getId(), hd.getIdKhacHang(), hd.getIdNhanVien(),
                    hd.getGiaTriVoucher(), hd.getNgayTao(), hd.getLoaiHoaDon(), hd.getTongTien(),
                    hd.getTongTienGiam(), hd.getGhiChu(), hd.getTrangthai()));
            System.out.println(hd.toString());
        }

        return listHoaDon;
    }

    @Override
    public ArrayList<QLHoaDon> selectlocSupper(String bd, String kt) {
        listHoaDon = new ArrayList<>();
        var hoaDonRS = repo.selectlocSupper(bd, kt);
        for (HoaDon hd : hoaDonRS) {
            listHoaDon.add(new QLHoaDon(hd.getId(), hd.getIdKhacHang(), hd.getIdNhanVien(),
                    hd.getGiaTriVoucher(), hd.getNgayTao(), hd.getLoaiHoaDon(), hd.getTongTien(),
                    hd.getTongTienGiam(), hd.getGhiChu(), hd.getTrangthai()));
            System.out.println(hd.toString());
        }

        return listHoaDon;
    }

    @Override
    public ArrayList<QLHoaDonChiTiet> selectAllCT() {
        listHoaDonCT = new ArrayList<>();
        var hoaDonRS = repo.selectAllCT();
        for (HoaDonChiTiet hd : hoaDonRS) {
            listHoaDonCT.add(new QLHoaDonChiTiet(hd.getId(), hd.getIdHoaDon(), hd.getTenSp(), hd.getSoLuong(),
                    hd.getGiaTien(), hd.getThanhTien(), hd.getNgayTao()));
        }

        return listHoaDonCT;
    }

    @Override
    public ArrayList<QLHoaDonChiTiet> selectlocCT(String ma) {
        listHoaDonCT = new ArrayList<>();
        var hoaDonRS = repo.selectlocCT(ma);
        for (HoaDonChiTiet hd : hoaDonRS) {
            listHoaDonCT.add(new QLHoaDonChiTiet(hd.getId(), hd.getIdHoaDon(), hd.getTenSp(), hd.getSoLuong(),
                    hd.getGiaTien(), hd.getThanhTien(), hd.getNgayTao()));
        }

        return listHoaDonCT;
    }

    @Override
    public ArrayList<QLHoaDonChiTiet> selectlocSupperCT(String bd, String kt) {
        listHoaDonCT = new ArrayList<>();
        var hoaDonRS = repo.selectlocSupperCT(bd, kt);
        for (HoaDonChiTiet hd : hoaDonRS) {
            listHoaDonCT.add(new QLHoaDonChiTiet(hd.getId(), hd.getIdHoaDon(), hd.getTenSp(), hd.getSoLuong(),
                    hd.getGiaTien(), hd.getThanhTien(), hd.getNgayTao()));
        }

        return listHoaDonCT;
    }

    @Override
    public String addHoaDon(HoaDonBackup hoaDonBackup) {
        return (repo.addHoaDon(hoaDonBackup))
                ? "Thêm hóa đơn thành công!" : "Thêm hóa đơn thất bại!";
    }

    @Override
    public List<HoaDonBackup> getAllHoaDonDaThanhToan() {
        return repo.getAllHoaDonDaThanhToan();
    }

    @Override
    public List<HoaDonBackup> getAllHoaDonChuaThanhToan() {
        return repo.getAllHoaDonChuaThanhToan();
    }

    @Override
    public List<HoaDonChiTietBackup> getAllByIdHoaDon(int idHoaDon) {
        return repo.getAllByIdHoaDon(idHoaDon);
    }

    @Override
    public HoaDonBackup findHoaDonByMa(String ma) {
        return repo.findHoaDonByMa(ma);
    }

    @Override
    public String updateTrangThaiHoaDon(String ma) {
        return (repo.updateTrangThaiHoaDon(ma))
                ? "Thanh toán thành công!" : "Thanh toán thất bại!";
    }

    @Override
    public Boolean addHoaDonChiTiet(HoaDonChiTietBackup hoaDonChiTietBackup) {
        return repo.addHoaDonChiTiet(hoaDonChiTietBackup);
    }

    @Override
    public void updateTongTien(int idHoaDon) {
        repo.updateTongTien(idHoaDon);
    }

    @Override
    public boolean updateSoLuongForSanPhamAndGioHang(int idHoaDon, int idSanPhamChiTiet, int newSoLuong) {
        return repo.updateSoLuongForSanPhamAndGioHang(idHoaDon, idSanPhamChiTiet, newSoLuong);
    }

    @Override
    public boolean updateHinhThucThanhToanHoaDon(String ma, String hinhThucThanhToan) {
        return repo.updateHinhThucThanhToanHoaDon(ma, hinhThucThanhToan);
    }

    @Override
    public int getSoLuongByMaSPCTAndIdHDCT(String maSPCT, int idHDCT) {
        return repo.getSoLuongByMaSPCTAndIdHDCT(maSPCT, idHDCT);
    }

    @Override
    public HoaDonChiTietBackup findHDCTByMaSPCTAndMaHD(String maSPCT, String maHD) {
        return repo.findHDCTByMaSPCTAndMaHD(maSPCT, maHD);
    }

    @Override
    public boolean deleteHDCTWhereSLEqualZero(int id) {
        return repo.deleteHDCTWhereSLEqualZero(id);
    }

    @Override
    public boolean updateVoucherForHoaDon(String ma, int idVoucher, double tongTienSauGiam) {
        return repo.updateVoucherForHoaDon(ma, idVoucher, tongTienSauGiam);
    }

    @Override
    public boolean updateKhachHangForHoaDon(String ma, int idKhachHang) {
        return repo.updateKhachHangForHoaDon(ma, idKhachHang);
    }

    @Override
    public boolean deleteHDCTByIdHoaDon(int idHoaDon) {
        return repo.deleteHDCTByIdHoaDon(idHoaDon);
    }

    @Override
    public boolean deleteHoaDon() {
        return repo.deleteHoaDon();
    }

}
