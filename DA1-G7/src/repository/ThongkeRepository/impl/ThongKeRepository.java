/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.ThongkeRepository.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import model.HoaDon;
import java.sql.DriverManager;
import model.ThongKe;
import model.ThongkeBanHang;
import repository.ThongkeRepository.IThongRepository;
import util.Xhelp;
import java.sql.*;
import java.sql.Connection;
import java.sql.SQLException;
import util.DBConnect;



public class ThongKeRepository implements IThongRepository {
  private final Connection conn = DBConnect.getConnection();
    final String selectAllSanPhamBanchay = """
                                    SELECT 
                                    sp.ma AS N'Mã Sản Phẩm',
                                    sp.ten AS N'Tên Sản Phẩm',
                                    KichCo.ten as N'Kích Cỡ',
                                    spct.soLuong AS N'Số Lượng Tồn Kho',
                                    spct.trangthai AS N'Trạng Thái'	
                                    FROM 
                                    SanPham sp
                                    JOIN 
                                    SanPhamChiTiet spct ON sp.id = spct.idSanPham left join KichCo on KichCo.id = spct.idKichCo
                                    ORDER BY 
                                    spct.soLuong ASC;
                                           """;
    final String selectAllSanPhamTon = """
                                      SELECT 
                                           sp.ma AS N'Mã Sản Phẩm',
                                           sp.ten AS N'Tên Sản Phẩm',
                                       		KichCo.ten as N'Kích Cỡ',
                                           spct.soLuong AS N'Số Lượng Tồn Kho',
                                           spct.trangthai AS N'Trạng Thái'	
                                       FROM 
                                           SanPham sp
                                       JOIN 
                                           SanPhamChiTiet spct ON sp.id = spct.idSanPham left join KichCo on KichCo.id = spct.idKichCo
                                       ORDER BY 
                                           spct.soLuong DESC;
                                       """;

    final String selectcBanHang = """
                            SELECT 
                            sp.ma AS 'Mã Sản Phẩm', 
                            sp.ten AS 'Tên Sản Phẩm', 
               		    kichco.ten as kichThuoc,
                            SUM(hdct.soLuong) AS 'Tổng Số Lượng Bán Ra', 
                            SUM(hdct.thanhTien) AS 'Tổng Tiền' 
                            FROM 
                            SanPham sp 
                            JOIN 
                            SanPhamChiTiet spct ON sp.id = spct.idSanPham 
                            JOIN 
                            HoaDonChiTiet hdct ON spct.id = hdct.idSanPhamChiTiet 
                            JOIN hoaDon ON hoaDon.id = hdct.idHoaDon left join kichCo on  KichCo.id = spct.idKichCo
                            GROUP BY 
                            sp.ma, sp.ten ,KichCo.ten
                            ORDER BY 
                            SUM(hdct.soLuong) DESC;
                                  """;
    final String selectcBanHangbyMa = """
                                      		SELECT sp.ma AS 'Mã Sản Phẩm', sp.ten AS 'Tên Sản Phẩm',kichco.ten as kichthuoc ,
                                                SUM(hdct.soLuong) AS 'Tổng Số Lượng Bán Ra', 
                                                SUM(hdct.thanhTien) AS 'Tổng Tiền' 
                                                FROM SanPham sp 
                                                JOIN SanPhamChiTiet spct ON sp.id = spct.idSanPham 
                                                JOIN HoaDonChiTiet hdct ON spct.id = hdct.idSanPhamChiTiet 
                                                JOIN HoaDon ON HoaDon.id = hdct.idHoaDon left join KichCo on KichCo.id =spct.idKichCo
                                                WHERE sp.ma = ? and kichco.ten = ?
                                                GROUP BY sp.ma, sp.ten,kichco.ten 
                                                ORDER BY SUM(hdct.soLuong) DESC
                                      
                                      """;

    @Override
    public ArrayList<ThongKe> selectAllSanPhamBanchay() {
        return selectAllSanPhamBanchay(selectAllSanPhamBanchay);
    }

    @Override
    public ArrayList<ThongKe> selectAllSanPhamTon() {
        return selectAllSanPhamBanchay(selectAllSanPhamTon);
    }

    @Override
    public ArrayList<ThongkeBanHang> selectcBanHang() {
        return selectcBanHang(selectcBanHang);
    }

    @Override
public ArrayList<ThongkeBanHang> SeleccLocBanHang(Date bd, Date kt) {
    return selectclocBanHang("SELECT\n"
            + "    sp.ma AS 'Mã Sản Phẩm',\n"
            + "    sp.ten AS 'Tên Sản Phẩm',\n"
            + "    kichco.ten AS 'kichThuoc',\n"
            + "    ISNULL(SUM(hdct.soLuong), 0) AS 'Tổng Số Lượng Bán Ra',\n"
            + "    ISNULL(SUM(hdct.thanhTien), 0) AS 'Tổng Tiền'\n"
            + "FROM\n"
            + "    SanPham sp\n"
            + "JOIN\n"
            + "    SanPhamChiTiet spct ON sp.id = spct.idSanPham\n"
            + "JOIN\n"
            + "    HoaDonChiTiet hdct ON spct.id = hdct.idSanPhamChiTiet\n"
            + "JOIN\n"
            + "    HoaDon hd ON hdct.idHoaDon = hd.id\n"
            + "LEFT JOIN\n"
            + "    KichCo kichco ON spct.idKichCo = kichco.id\n"
            + "WHERE\n"
            + "    hd.ngayTao >= ?"
            + " AND hd.ngayTao < ?\n"
            + " GROUP BY\n"
            + "    sp.ma, sp.ten, kichco.ten\n"
            + "ORDER BY\n"
            + "    SUM(hdct.soLuong) DESC;", bd, kt);
}

public ArrayList<ThongkeBanHang> selectclocBanHang(String sql, Date bd, Date kt) {
    ArrayList<ThongkeBanHang> listbanHang = new ArrayList<>();
    try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
        preparedStatement.setDate(1, new java.sql.Date(bd.getTime()));
        preparedStatement.setDate(2, new java.sql.Date(kt.getTime()));
        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()) {
            ThongkeBanHang sp = new ThongkeBanHang();
            sp.setMa(rs.getString(1));
            sp.setTen(rs.getNString(2));
            sp.setKichthuoc(rs.getString(3));
            sp.setSoLuong(rs.getInt(4));
            sp.setTongTien(rs.getDouble(5));
            listbanHang.add(sp);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return listbanHang;
}

    public ArrayList<ThongKe> selectAllSanPhamBanchay(String sql, Object... args) {
        ArrayList<ThongKe> list = new ArrayList<>();
        try {
            ResultSet rs = Xhelp.excuteQuery(sql, args);
            while (rs.next()) {
                ThongKe sp = new ThongKe();
                sp.setMa(rs.getString(1));
                sp.setTen(rs.getNString(2));
                sp.setKichThuoc(rs.getString(3));
                sp.setSoLuong(rs.getInt(4));
                sp.setTrangThai(rs.getInt(5));
                list.add(sp);
            }
        } catch (Exception e) {
        }
        return list;
    }

    public ArrayList<ThongkeBanHang> selectcBanHang(String sql, Object... args) {
        ArrayList<ThongkeBanHang> listbanHang = new ArrayList<>();
        try {
            ResultSet rs = Xhelp.excuteQuery(sql, args);
            while (rs.next()) {
                ThongkeBanHang sp = new ThongkeBanHang();
                sp.setMa(rs.getString(1));
                sp.setTen(rs.getNString(2));
                sp.setKichthuoc(rs.getString(3));
                sp.setSoLuong(rs.getInt(4));
                sp.setTongTien(rs.getDouble(5));
                listbanHang.add(sp);
            }
        } catch (Exception e) {
        }
        return listbanHang;
    }

    public ArrayList<ThongkeBanHang> selectcBanHangTK(String sql, Object... args) {
        ArrayList<ThongkeBanHang> listbanHang = new ArrayList<>();
        try {
            ResultSet rs = Xhelp.excuteQuery(sql, args);
            while (rs.next()) {
                ThongkeBanHang sp = new ThongkeBanHang();
                sp.setMa(rs.getString(1));
                sp.setTen(rs.getNString(2));
                sp.setKichthuoc(rs.getString(3));
                sp.setSoLuong(rs.getInt(4));
                sp.setTongTien(rs.getDouble(5));
                listbanHang.add(sp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listbanHang;
    }

    @Override
    public ArrayList<ThongkeBanHang> selectcBanHangbyten(String ten) {
        return selectcBanHang("SELECT \n"
                + "                           sp.ma AS 'Mã Sản Phẩm', \n"
                + "                           sp.ten AS 'Tên Sản Phẩm', \n"
                + "                           kichco.ten as kichThuoc,\n"
                + "                           SUM(hdct.soLuong) AS 'Tổng Số Lượng Bán Ra', \n"
                + "                           SUM(hdct.thanhTien) AS 'Tổng Tiền' \n"
                + "                           FROM \n"
                + "                           SanPham sp \n"
                + "                           JOIN \n"
                + "                           SanPhamChiTiet spct ON sp.id = spct.idSanPham \n"
                + "                           JOIN \n"
                + "                           HoaDonChiTiet hdct ON spct.id = hdct.idSanPhamChiTiet \n"
                + "                           JOIN hoaDon ON hoaDon.id = hdct.idHoaDon left join kichCo on  KichCo.id = spct.idKichCo\n"
                + "                           where sp.ten like N'%" + ten + "%'"
                + "                           GROUP BY \n"
                + "                           sp.ma, sp.ten ,KichCo.ten\n"
                + "                           ORDER BY \n"
                + "                           SUM(hdct.soLuong) DESC");
    }
    public ArrayList<ThongkeBanHang> selectcBanHangbyma(String ma,String size) {
        return selectcBanHang(selectcBanHangbyMa, ma,size);
    }
}
