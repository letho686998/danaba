/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package repository.hoadonrepository;

import service.hoadonservice.*;
import model.HoaDon;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.HoaDonBackup;
import model.HoaDonChiTiet;
import model.HoaDonChiTietBackup;

/**
 *
 * @author ledun
 */
public interface IHoaDonRepository {

    public ArrayList<HoaDon> selectAll();

    public ArrayList<HoaDon> selectloc(String ma, int tt);

    public ArrayList<HoaDon> selectlocSupper(String bd, String kt);

    public ArrayList<HoaDonChiTiet> selectlocSupperCT(String bd, String kt);

    public ArrayList<HoaDon> selectBySql(String sql, Object... args);

    public ArrayList<HoaDonChiTiet> selectAllCT();

    public ArrayList<HoaDonChiTiet> selectBySqlCT(String sql, Object... args);

    public ArrayList<HoaDonChiTiet> selectlocCT(String ma);

    public ArrayList<HoaDonChiTiet> selecttimkiemCT(String sql, Object... args);

    Boolean addHoaDon(HoaDonBackup hoaDonBackup);

    List<HoaDonBackup> getAllHoaDonDaThanhToan();

    List<HoaDonBackup> getAllHoaDonChuaThanhToan();

    boolean updateTrangThaiHoaDon(String ma);

    boolean updateHinhThucThanhToanHoaDon(String ma, String hinhThucThanhToan);

    HoaDonBackup findHoaDonByMa(String ma);

    List<HoaDonChiTietBackup> getAllByIdHoaDon(int idHoaDon);

    Boolean addHoaDonChiTiet(HoaDonChiTietBackup hoaDonChiTietBackup);

    void updateTongTien(int idHoaDon);

    boolean updateSoLuongForSanPhamAndGioHang(int idHoaDon, int idSanPhamChiTiet, int newSoLuong);

    HoaDonChiTietBackup findHDCTByMaSPCTAndMaHD(String maSPCT, String maHD);

    int getSoLuongByMaSPCTAndIdHDCT(String maSPCT, int idHoaDonChiTiet);

    boolean deleteHDCTWhereSLEqualZero(int id);

    boolean updateVoucherForHoaDon(String ma, int idVoucher, double tongTienSauGiam);

    boolean updateKhachHangForHoaDon(String ma, int idKhachHang);

    boolean deleteHDCTByIdHoaDon(int idHoaDon);

    boolean deleteHoaDon();

}
