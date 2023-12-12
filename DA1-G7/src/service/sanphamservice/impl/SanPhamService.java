/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.sanphamservice.impl;

import java.util.List;
import model.SanPham;
import repository.sanphamrepository.repository.SanPhamRepository;
import service.sanphamservice.ISanPhamService;

/**
 *
 * @author cuongwf
 */
public class SanPhamService implements ISanPhamService {

    private final SanPhamRepository sanPhamRepository = new SanPhamRepository();

    @Override
    public List<SanPham> getAll() {
        return sanPhamRepository.getAll();
    }

    @Override
    public String add(SanPham sanPham) {
        return (sanPhamRepository.add(sanPham))
                ? "Thêm thành công!" : "Thêm thất bại!";
    }

    @Override
    public String update(SanPham sanPham) {
        return (sanPhamRepository.update(sanPham))
                ? "Update thành công!" : "Update thất bại!";
    }

    @Override
    public List<SanPham> getAll(int offset, int limit) {
        return sanPhamRepository.getAllWithPage(offset, limit);
    }

    @Override
    public int countSanPham() {
        return sanPhamRepository.countSanPham();
    }

    @Override
    public boolean isSanPhamExists(String ten, int idThuongHieu, int idDanhMuc) {
        return sanPhamRepository.isSanPhamExists(ten, idThuongHieu, idDanhMuc);
    }

}
