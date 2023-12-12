/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.nhanvienrepository;

import DomainModels.QLCtNhanVien;
import DomainModels.QLNhanVien;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;
import model.ChucVu;
import model.CtNhanVien;
import model.HoaDon;
import model.NhanVien;
import util.DBConnect;
import util.Xhelp;

/**
 *
 * @author Duongg
 */
public class NhanVienReponsitory implements INhanvienRepository {

    final String selectAll = "select NhanVien.id \n"
            + "		as IdNv,NhanVien.ma \n"
            + "		as maNv,NhanVien.ten \n"
            + "		as tenNv,NhanVien.sdt \n"
            + "		as sdtNv,ChucVu.ten \n"
            + "		as tenCv,NhanVien.ngayTao \n"
            + "		as ngayTao, NhanVien.ngaySua \n"
            + "		as ngaySua, NhanVien.gioiTinh \n"
            + "		as gioiTinh, NhanVien.trangThai\n"
            + "		as trangThai\n"
            + "		from NhanVien left join ChucVu on NhanVien.idChucVu = ChucVu.id";
    final String selectAll1 = "select id,ma,email,sdt,ngaySinh,diaChi,matkhau from NhanVien";
    final String updateNhanVienCB = "UPDATE NhanVien SET ten=?,  sdt = ?,  idchucVu = ?, \n"
            + "gioiTinh = ?, ngaySua = ?, trangThai =?  WHERE ma = ?";
    final String xoanhanvien = "update NhanVien set TrangThai = 0 where ma = ?";
    final String insertCb = "INSERT INTO NhanVien(Ten, Ma, Sdt, idChucVu, GioiTinh,ngaytao, TrangThai)\n"
            + "                                 VALUES ( ?, ?, ?, ?, ?,?,?)";
    final String selectAllChucvu = "select id,ma,ten from chucvu";
    final String updatechitiet = "update NhanVien \n"
            + "set email= ?,sdt= ?,ngaySinh = ?,diaChi = ?,matkhau=? where ma = ?";
    private ArrayList<NhanVien> list = new ArrayList<>();
    private ArrayList<CtNhanVien> list1 = new ArrayList<>();
    private ArrayList<ChucVu> listChucVu = new ArrayList<>();

    @Override
    public void setNhanvien(QLNhanVien nv) {
        DBConnect.excuteUpdate(updateNhanVienCB,  nv.getTenNv(),
                nv.getSdt(), nv.getChucVu(), nv.getGioiTinh(), nv.getNgayTao(), nv.getTrangThai(), nv.getMaNv());

    }
    @Override
    public void setNhanvien1(QLCtNhanVien nv) {
        DBConnect.excuteUpdate(updatechitiet, nv.getEmail(),nv.getSdt(), nv.getNgaySinh(), nv.getDiaChi(),nv.getPasss(), nv.getMa());
    }
    @Override
    public ArrayList<NhanVien> getList() {
        list = new ArrayList<>();
        return selectBySql(selectAll);
    }

    @Override
    public void addNhanvien(QLNhanVien nv) {
        DBConnect.excuteUpdate(insertCb, nv.getTenNv(), nv.getMaNv(), nv.getSdt(), nv.getChucVu(), nv.getGioiTinh(), nv.getNgayTao(), nv.getTrangThai());
    }

    public ArrayList<ChucVu> getChuvu() {

        return selectBySqlChuvu(selectAllChucvu);
    }

    @Override
    public void removeNhanvien(String nv) {
        DBConnect.excuteUpdate(xoanhanvien, nv);
    }

    public ArrayList<ChucVu> selectBySqlChuvu(String sql, Object... args) {
        ArrayList<ChucVu> listChucVu = new ArrayList<>();
        try {
            ResultSet rs = Xhelp.excuteQuery(sql, args);
            while (rs.next()) {
                ChucVu cv = new ChucVu();
                cv.setId(rs.getInt(1));
                cv.setMa(rs.getString(2));
                cv.setTen(rs.getString(3));
                listChucVu.add(cv);
            }
        } catch (Exception e) {
        }
        return listChucVu;
    }

    @Override
    public ArrayList<NhanVien> selectBySql(String sql, Object... args) {
        ArrayList<NhanVien> listNhanVien = new ArrayList<>();
        try {
            ResultSet rs = Xhelp.excuteQuery(sql, args);
            while (rs.next()) {
                NhanVien nv = new NhanVien();
                nv.setId(rs.getString(1));
                nv.setMaNv(rs.getString(2));
                nv.setTenNv(rs.getString(3));
                nv.setSdt(rs.getString(4));
                nv.setChucVu(rs.getString(5));
                nv.setNgayTao(rs.getDate(6));
                nv.setNgaySua(rs.getDate(7));
                nv.setGioiTinh(rs.getInt(8));
                nv.setTrangThai(rs.getInt(9));
                listNhanVien.add(nv);
            }
        } catch (Exception e) {
        }
        return listNhanVien;
    }

    @Override
    public ArrayList<CtNhanVien> getList2() {
        list1 = new ArrayList<>();
        return selectBySql1(selectAll1);
    }

    @Override
    public ArrayList<CtNhanVien> selectBySql1(String sql, Object... args) {
        ArrayList<CtNhanVien> listNhanVien = new ArrayList<>();
        try {
            ResultSet rs = Xhelp.excuteQuery(sql, args);
            while (rs.next()) {
                CtNhanVien nv = new CtNhanVien();
                nv.setId(rs.getString(1));
                nv.setMa(rs.getString(2));
                nv.setEmail(rs.getString(3));
                nv.setSdt(rs.getString(4));
                nv.setNgaySinh(rs.getDate(5));
                nv.setDiaChi(rs.getString(6));
                nv.setPasss(rs.getString(7));
                listNhanVien.add(nv);
            }
        } catch (Exception e) {
        }
        return listNhanVien;
    }



    @Override
    public void removeNhanvien1(String nv) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public ArrayList<CtNhanVien> selectBySqlupdate1(String sql, Object... args) {
        ArrayList<CtNhanVien> listNhanVien = new ArrayList<>();
        try {
            Integer rs = Xhelp.excuteUpdate(sql, args);
        } catch (Exception e) {
        }
        return listNhanVien;
    }

    @Override
    public void addNhanvien1(QLCtNhanVien nv) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
