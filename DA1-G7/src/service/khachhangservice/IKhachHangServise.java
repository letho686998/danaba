/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service.khachhangservice;

import java.util.ArrayList;
import java.util.List;
import model.HoaDon;
import model.KhachHang;
import modelViews.QlKhachHang;
import modelViews.QlVoucher;

/**
 *
 * @author LENOVO
 */
public interface IKhachHangServise {

    ArrayList<QlKhachHang> selectAll();

    ArrayList<QlKhachHang> getAllKhByMa0(String input);

    ArrayList<QlKhachHang> getAllKhByNameKM0(String input);

    QlKhachHang insert(QlKhachHang qlKhachHang);

    QlKhachHang update(QlKhachHang qlKhachHang);

    Integer delete(Integer id);

    ArrayList<KhachHang> selectById(String ma);

    List<KhachHang> getAllKhachHang();

    KhachHang findKhachHangByMa(String ma);

    KhachHang findKhachHangById(int id);

    ArrayList<HoaDon> selectCtKhachhang(String ma);
}
