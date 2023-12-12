
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.voucherrepository.impl;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.Voucher;
import model.VoucherBackup;
import repository.voucherrepository.IVoucherResponsitory;
import util.DBConnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author LENOVO
 */
public class Voucherrespository implements IVoucherResponsitory {

    private final Connection conn = DBConnect.getConnection();

    private ArrayList<Voucher> _listVoucher;
    final String SELECT_ALL_SQL = "SELECT ten,ma,ngayBatDau,ngayKetThuc,loaiGiamGia,giaTriGiam\n"
            + ",soLuong,GiaTriDhToiThieu,trangThai FROM \n"
            + "Voucher ORDER BY NgayKetThuc DESC , TrangThai ASC";

    final String SELECT_ALL_SQL012 = "SELECT ten,ma,ngayBatDau,ngayKetThuc,loaiGiamGia,giaTriGiam,\n"
            + "soLuong,GiaTriDhToiThieu,trangThai FROM Voucher WHERE TrangThai = 0 OR TrangThai = 1 \n"
            + "OR TrangThai = 2 ORDER BY trangThai ASC ,ma ASC ,ngayKetThuc DESC";
    final String INSERT_SQL = "INSERT INTO Voucher(ma,ten,ngayBatDau,ngayKetThuc,loaiGiamGia,giaTriGiam,trangThai,soLuong,GiaTriDhToiThieu) VALUES (?,?,?,?,?,?,?,?,?);";
    final String UPDATE_SQL = "UPDATE Voucher "
            + "SET ten = ?, "
            + "ngayBatDau = ?,"
            + " ngayKetThuc = ?,\n"
            + "loaiGiamGia = ?, "
            + "giaTriGiam = ?,"
            + "soLuong=?,"
            + "GiaTriDhToiThieu = ?,"
            + "trangThai= ?"
            + " WHERE ma = ?";
    final String SELECT_ALL_SQL_BY_MA_0 = "SELECT * FROM Voucher\n"
            + "WHERE Ma = ? AND (TrangThai = 0 OR TrangThai = 1 OR TrangThai = 2) ORDER BY NgayKetThuc DESC";
    final String SELECT_ALL_SQL_BY_NAMEKM_0 = "SELECT * FROM Voucher \n"
            + "WHERE ten=? AND (TrangThai = 0 OR\n"
            + "TrangThai = 1 OR TrangThai = 2)\n"
            + "ORDER BY NgayKetThuc DESC";
    final String SELECT_ALL_SQL_BY_NGAY_0 = "SELECT *\n"
            + "FROM Voucher\n"
            + "WHERE ( CONVERT(DATE, ngayKetThuc,103) >= CONVERT(DATE,?,103))\n"
            + "  AND (TrangThai = 0 OR TrangThai = 1 OR TrangThai = 2)";
    final String UPDATE_SoLuong_SQL = "UPDATE Voucher\n"
            + "SET SoLuong = ? - 1\n"
            + "WHERE Id = ? AND TrangThai = 0";
//
//    final String SELECT_ALL_SQL_BY_TRANGTHAI0 = "SELECT * FROM Voucher\n"
//            + "WHERE TrangThai = 0 ORDER BY NgayKetThuc DESC";
//    final String SELECT_ALL_SQL_BY_TRANGTHAI1 = "SELECT * FROM Voucher\n"
//            + "WHERE TrangThai = 1 ORDER BY NgayKetThuc DESC";
//    final String SELECT_ALL_SQL_BY_TRANGTHAI2 = "SELECT * FROM Voucher\n"
//            + "WHERE TrangThai = 2 ORDER BY NgayKetThuc DESC";

    final String SELECT_ALL_SQL_BY_MA_3 = "SELECT * FROM Voucher\n"
            + "WHERE Ma = ? AND TrangThai = 3 ORDER BY NgayKetThuc DESC";
    final String SELECT_ALL_SQL_BY_NGAY_3 = "SELECT * FROM Voucher \n"
            + "WHERE (NgayKetThuc <= ?) AND TrangThai = 3 \n"
            + "ORDER BY NgayKetThuc DESC";
    final String SELECT_ALL_SQL_BY_TRANGTHAI3 = "SELECT * FROM Voucher\n"
            + "WHERE TrangThai = 3 ORDER BY NgayKetThuc DESC";
    final String SELECT_ALL_SQL_BY_Name_3 = "SELECT * FROM Voucher WHERE ten = ? AND "
            + "TrangThai = 3 ORDER BY ngayKetThuc DESC";
    final String UPDATE_TRANGTHAI_SQL = "UPDATE Voucher\n"
            + "SET TrangThai = ?\n"
            + "WHERE ma = ?";
   final String findMa = "SELECT ten,ma,ngayBatDau,ngayKetThuc,loaiGiamGia,giaTriGiam,soLuong,GiaTriDhToiThieu,trangThai FROM \n"
            + "     Voucher where ma=?";

    public Voucherrespository() {
        _listVoucher = new ArrayList<>();
    }

    @Override
    public ArrayList<Voucher> getAll() {
        _listVoucher = new ArrayList<>();
        return selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public ArrayList<Voucher> selectBySql(String sql, Object... args) {
        try {
            ResultSet rs = DBConnect.excuteQuery(sql, args);
            while (rs.next()) {
                _listVoucher.add(new Voucher(
                        rs.getString("ma"),
                        rs.getString("ten"),
                        rs.getDate("ngayBatDau"),
                        rs.getDate("ngayKetThuc"),
                        rs.getBoolean("loaiGiamGia"),
                        rs.getInt("giaTriGiam"),
                        rs.getInt("soLuong"),
                        rs.getBigDecimal("GiaTriDhToiThieu"),
                        rs.getInt("trangThai")
                ));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return _listVoucher;
    }

    @Override
    public ArrayList<Voucher> getAllTrangThai012() {
        _listVoucher = new ArrayList<>();
        return selectBySql(SELECT_ALL_SQL012);
    }

    @Override
    public Voucher addVoucher(Voucher voucher) {
        DBConnect.excuteUpdate(INSERT_SQL, voucher.getMa(),
                voucher.getTen(),
                voucher.getNgayBatDau(),
                voucher.getNgayKetThuc(),
                voucher.isLoaiGiamGia(),
                voucher.getGiaTriGiam(),
                voucher.getTrangThai(),
                voucher.getSoLuong(),
                voucher.getGiaTriDHTT());
        return voucher;
    }

    @Override
    public Voucher updateVoucher(Voucher voucher) {
        DBConnect.excuteUpdate(UPDATE_SQL,
                voucher.getTen(),
                voucher.getNgayBatDau(),
                voucher.getNgayKetThuc(),
                voucher.isLoaiGiamGia(),
                voucher.getGiaTriGiam(),
                voucher.getSoLuong(),
                voucher.getGiaTriDHTT(),
                voucher.getTrangThai(),
                voucher.getMa()
        );
        return voucher;
    }

    @Override
    public ArrayList<Voucher> getAllByMaTrangThai0(String input) {
        _listVoucher = new ArrayList<>();
        return selectBySql(SELECT_ALL_SQL_BY_MA_0, input);
    }

    @Override
    public ArrayList<Voucher> getAllByNgay0(Date input) {
        _listVoucher = new ArrayList<>();
        System.out.println("SQL Query: " + SELECT_ALL_SQL_BY_NGAY_0);

        return selectBySql(SELECT_ALL_SQL_BY_NGAY_0, input);
    }

    @Override
    public ArrayList<Voucher> getAllVoucherDeleted() {
        _listVoucher = new ArrayList<>();
        return selectBySql(SELECT_ALL_SQL_BY_TRANGTHAI3);
    }

    @Override
    public Voucher updateTrangThaiVoucher(Voucher voucher) {
        DBConnect.excuteUpdate(UPDATE_TRANGTHAI_SQL,
                voucher.getTrangThai(),
                voucher.getMa());
        return voucher;
    }

    @Override
    public ArrayList<Voucher> getAllBynameKmTrangThai0(String input) {
        _listVoucher = new ArrayList<>();
        return selectBySql(SELECT_ALL_SQL_BY_NAMEKM_0, input);
    }

//    @Override
//    public ArrayList<Voucher> getAllByTrangThai0() {
//        _listVoucher = new ArrayList<>();
//        return selectBySql(SELECT_ALL_SQL_BY_TRANGTHAI0);
//    }
//
//    @Override
//    public ArrayList<Voucher> getAllByTrangThai1() {
//        _listVoucher = new ArrayList<>();
//        return selectBySql(SELECT_ALL_SQL_BY_TRANGTHAI1);
//    }
//
//    @Override
//    public ArrayList<Voucher> getAllByTrangThai2() {
//        _listVoucher = new ArrayList<>();
//        return selectBySql(SELECT_ALL_SQL_BY_TRANGTHAI2);
//    }
    @Override
    public ArrayList<Voucher> getAllByMaTrangThai3(String input) {
        _listVoucher = new ArrayList<>();
        return selectBySql(SELECT_ALL_SQL_BY_MA_3, input);
    }

    @Override
    public ArrayList<Voucher> getAllByNgay3(Date input) {
        _listVoucher = new ArrayList<>();
        return selectBySql(SELECT_ALL_SQL_BY_NGAY_3, input);
    }

    @Override
    public ArrayList<Voucher> getAllBynameKmTrangThai3(String input) {
        _listVoucher = new ArrayList<>();
        return selectBySql(SELECT_ALL_SQL_BY_Name_3, input);
    }

    @Override
    public List<VoucherBackup> getAllByTongTien(double tongTien) {
        String query = """
                       SELECT id, ma, ten, loaiGiamGia, giaTriGiam, soLuong, GiaTriDhToiThieu FROM Voucher WHERE ? >= GiaTriDhToiThieu
                       """;
        try ( PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setObject(1, tongTien);
            List<VoucherBackup> listVoucher = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                listVoucher.add(new VoucherBackup(rs.getInt(1),
                        rs.getString(2), rs.getString(3),
                        rs.getBoolean(4), rs.getDouble(5),
                        rs.getInt(6), rs.getDouble(7)));
            }
            return listVoucher;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean updateSoLuongVoucher(int idVoucher) {
        int check = 0;
        String query = """
                       UPDATE Voucher SET soLuong = soLuong - 1 WHERE id = ?
                       """;
        try ( PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setObject(1, idVoucher);
            check = ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return check > 0;
    }

    @Override
    public ArrayList<Voucher> findMa(String ma) {
        ArrayList<Voucher> listVoucher = new ArrayList<>();

        try {
            ResultSet rs = DBConnect.excuteQuery(findMa, ma);

            while (rs.next()) {
                Voucher voucher = new Voucher(
                        rs.getString("ma"),
                        rs.getString("ten"),
                        rs.getDate("ngayBatDau"),
                        rs.getDate("ngayKetThuc"),
                        rs.getBoolean("loaiGiamGia"),
                        rs.getInt("giaTriGiam"),
                        rs.getInt("soLuong"),
                        rs.getBigDecimal("GiaTriDhToiThieu"),
                        rs.getInt("trangThai")
                );

                listVoucher.add(voucher);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return listVoucher;
    }

}
