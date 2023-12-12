/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.taikhoanservice.impl;

import service.taikhoanservice.QLtaiKhoanService;
import model.TaiKhoan;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import util.Xhelp;

public class QLtaiKhoan implements QLtaiKhoanService {

    @Override
    public TaiKhoan selectByDangNhap(String userName) {
        String sql;
        try {
            if (userName.contains("@")) {
                sql = """
                   SELECT NhanVien.id as id, NhanVien.ten as tenNhanVien, NhanVien.email as email, NhanVien.sdt as sdt, NhanVien.matKhau as matkhau, ChucVu.ten  as ten
                                                        FROM NhanVien 
                                                        LEFT JOIN ChucVu ON NhanVien.idChucVu = ChucVu.id 
                                                        WHERE nhanvien.email = ?;
                  """;
            } else {
                sql = """
                   SELECT NhanVien.id as id, NhanVien.ten as tenNhanVien, NhanVien.email as email, NhanVien.sdt as sdt, NhanVien.matKhau as matkhau, ChucVu.ten  as ten
                                                                          FROM NhanVien 
                                                                          LEFT JOIN ChucVu ON NhanVien.idChucVu = ChucVu.id 
                                                       WHERE nhanvien.sdt = ?;
                  """;
            }

            ResultSet rs = Xhelp.excuteQuery(sql, userName);

            if (rs.next()) {
                TaiKhoan tk = new TaiKhoan();
                int id = rs.getInt("id");
                String tenNv = rs.getNString("tenNhanVien");
                String email = rs.getString("email");
                String sdt = rs.getString("sdt");
                String matKhau = rs.getString("matKhau");
                String ten = rs.getNString("ten");
                tk.setIdNV(id);
                tk.setUserName(tenNv);
                tk.setEmail(email);
                tk.setSdt(sdt);
                tk.setPassWord(matKhau);
                tk.setRole(ten);
                System.out.println(matKhau);
                return tk;
                
            }
        } catch (Exception ex) {
            ex.printStackTrace(); // Handle the exception according to your needs
        }

        return null;
    }

    @Override
    public void resestPW(String user,String matkhau) {
        if (user.contains("@")) {
            Integer kq = Xhelp.excuteUpdate("update NhanVien set matkhau = ? where email = ?" ,matkhau,user);
            JOptionPane.showMessageDialog(null, kq == 1 ? "khôi phục tài khoản thành công" : "khôi phục tài khoản Thất Bại");
        }

    }

    @Override
    public void DoiPass(TaiKhoan tk) {
        System.out.println(tk.getUserName());
        System.out.println(tk.getPassWord());

        if (tk.getUserName().contains("@")) {
            Integer kq = Xhelp.excuteUpdate("UPDATE NhanVien SET matKhau = ? WHERE email = ?", tk.getPassWord(), tk.getUserName());
            JOptionPane.showMessageDialog(null, kq == 1 ? "Sửa thành công" : "Sửa Thất Bại");
        } else {
            Integer kq = Xhelp.excuteUpdate("UPDATE NhanVien SET matKhau = ? WHERE sdt = ?", tk.getPassWord(), tk.getUserName());
            JOptionPane.showMessageDialog(null, kq == 1 ? "Sửa thành công" : "Sửa Thất Bại");
        }

    }

}
