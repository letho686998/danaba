/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.khachhangservice.impl;

import java.util.ArrayList;
import java.util.List;
import model.HoaDon;
import model.KhachHang;
import model.Voucher;
import modelViews.QlKhachHang;
import modelViews.QlVoucher;
import repository.khachhangrepository.IKhachhangrepository;
import repository.khachhangrepository.impl.KhachHangRepository;
import service.khachhangservice.IKhachHangServise;

/**
 *
 * @author LENOVO
 */
public class KhachHangServise implements IKhachHangServise {

    private final IKhachhangrepository _iKhachhangrepository;
    private ArrayList<QlKhachHang> _lisKhachHangs;

    public KhachHangServise() {
        _iKhachhangrepository = new KhachHangRepository();
        _lisKhachHangs = new ArrayList<>();
    }

    @Override
    public ArrayList<QlKhachHang> selectAll() {
        _lisKhachHangs = new ArrayList<>();
        var khacHangs = _iKhachhangrepository.selectAll();
        System.out.println("qlkh" + khacHangs);
        for (KhachHang kh : khacHangs) {
            _lisKhachHangs.add(new QlKhachHang(kh.getId(), kh.getMaKh(), kh.getTenKh(),
                    kh.getGioiTinh(), kh.getSdt(), kh.getEmail(), kh.getDiaChi(), kh.getNgaySinh()));
        }
        return _lisKhachHangs;
    }

    @Override
    public QlKhachHang insert(QlKhachHang qlKhachHang) {
        var x = _iKhachhangrepository.insert(new KhachHang(
                qlKhachHang.getMaKh(),
                qlKhachHang.getTenKh(),
                qlKhachHang.getGioiTinh(),
                qlKhachHang.getSdt(),
                qlKhachHang.getEmail(),
                qlKhachHang.getDiaChi(),
                qlKhachHang.getNgaySinh()));
        return new QlKhachHang(
                x.getMaKh(),
                x.getTenKh(),
                x.getGioiTinh(),
                x.getSdt(),
                x.getEmail(),
                x.getDiaChi(),
                x.getNgaySinh()
        );

    }

    @Override
    public QlKhachHang update(QlKhachHang qlKhachHang) {
        var x = _iKhachhangrepository.update(new KhachHang(
                qlKhachHang.getMaKh(),
                qlKhachHang.getTenKh(),
                qlKhachHang.getGioiTinh(),
                qlKhachHang.getSdt(),
                qlKhachHang.getEmail(),
                qlKhachHang.getDiaChi(),
                qlKhachHang.getNgaySinh()));
        return new QlKhachHang(
                x.getMaKh(),
                x.getTenKh(),
                x.getGioiTinh(),
                x.getSdt(),
                x.getEmail(),
                x.getDiaChi(),
                x.getNgaySinh());

    }

    @Override
    public Integer delete(Integer id) {
        return _iKhachhangrepository.delete(id);
    }

    @Override
    public ArrayList<KhachHang> selectById(String ma) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<QlKhachHang> getAllKhByMa0(String input) {
        _lisKhachHangs = new ArrayList<>();
        ArrayList<KhachHang> lstKhDomain = _iKhachhangrepository.getAllByMaTrangThai0(input);
        for (KhachHang khachHang : lstKhDomain) {
            _lisKhachHangs.add(new QlKhachHang(
                    khachHang.getId(),
                    khachHang.getMaKh(),
                    khachHang.getTenKh(),
                    khachHang.getGioiTinh(),
                    khachHang.getSdt(),
                    khachHang.getEmail(),
                    khachHang.getDiaChi(),
                    khachHang.getNgaySinh()));
        }
        return _lisKhachHangs;
    }

    @Override
    public ArrayList<QlKhachHang> getAllKhByNameKM0(String input) {
        _lisKhachHangs = new ArrayList<>();
        ArrayList<KhachHang> lstKhDomain = _iKhachhangrepository.getAllBynameKmTrangThai0(input);
        for (KhachHang khachHang : lstKhDomain) {
            _lisKhachHangs.add(new QlKhachHang(
                    khachHang.getId(),
                    khachHang.getMaKh(),
                    khachHang.getTenKh(),
                    khachHang.getGioiTinh(),
                    khachHang.getSdt(),
                    khachHang.getEmail(),
                    khachHang.getDiaChi(),
                    khachHang.getNgaySinh()));
        }
        return _lisKhachHangs;
    }

    @Override
    public List<KhachHang> getAllKhachHang() {
        return _iKhachhangrepository.getAllKhachHang();
    }

    @Override
    public KhachHang findKhachHangByMa(String ma) {
        return _iKhachhangrepository.findKhachHangByMa(ma);
    }

    @Override
    public KhachHang findKhachHangById(int id) {
        return _iKhachhangrepository.findKhachHangById(id);
    }

    @Override
    public ArrayList<HoaDon> selectCtKhachhang(String ma) {
        return _iKhachhangrepository.selectCtKhachhang(ma);
    }

}
