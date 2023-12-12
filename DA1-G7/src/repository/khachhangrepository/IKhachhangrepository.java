/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package repository.khachhangrepository;

import java.util.ArrayList;
import java.util.List;
import model.HoaDon;
import model.KhachHang;

/**
 *
 * @author LENOVO
 */
public interface IKhachhangrepository {

    ArrayList<KhachHang> selectAll();

    ArrayList<KhachHang> getAllByMaTrangThai0(String input);

    ArrayList<KhachHang> getAllBynameKmTrangThai0(String input);

    KhachHang insert(KhachHang khachHang);

    KhachHang update(KhachHang khachHang);

    Integer delete(Integer id);

    ArrayList<KhachHang> selectById(String ma);

    ArrayList<KhachHang> selectBySql(String sql, Object... args);

    List<KhachHang> getAllKhachHang();

    KhachHang findKhachHangByMa(String ma);

    KhachHang findKhachHangById(int id);

    ArrayList<HoaDon> selectCtKhachhang(String ma);
}
