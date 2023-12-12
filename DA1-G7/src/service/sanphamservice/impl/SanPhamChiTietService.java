package service.sanphamservice.impl;

import java.util.List;
import model.SanPhamChiTiet;
import repository.sanphamrepository.repository.SanPhamChiTietRepository;
import service.sanphamservice.ISanPhamChiTietService;

/**
 *
 * @author cuongwf
 */
public class SanPhamChiTietService implements ISanPhamChiTietService {

    private final SanPhamChiTietRepository sanPhamChiTietRepository = new SanPhamChiTietRepository();

    @Override
    public List<SanPhamChiTiet> getAllByIdSanPham(String maSanPham) {
        return sanPhamChiTietRepository.getAllByIdSanPham(maSanPham);
    }

    @Override
    public List<SanPhamChiTiet> getAllByIdSanPham(String maSanPham, int offset, int limit) {
        return sanPhamChiTietRepository.getAllByIdSanPham(maSanPham, offset, limit);
    }

    @Override
    public String add(SanPhamChiTiet sanPhamChiTiet) {
        return (sanPhamChiTietRepository.add(sanPhamChiTiet)
                ? "Thêm thành công!" : "Thêm thất bại!");
    }

    @Override
    public String update(SanPhamChiTiet sanPhamChiTiet) {
        return (sanPhamChiTietRepository.update(sanPhamChiTiet)
                ? "Cập nhật thành công!" : "Cập nhật thất bại!");
    }

    @Override
    public int countSanPhamChiTiet(String maSanPham) {
        return sanPhamChiTietRepository.countSanPhamChiTiet(maSanPham);
    }

    @Override
    public List<SanPhamChiTiet> getAll() {
        return sanPhamChiTietRepository.getAll();
    }

    @Override
    public SanPhamChiTiet findSanPhamChiTietByMa(String ma) {
        return sanPhamChiTietRepository.findSanPhamChiTietByMa(ma);
    }

    @Override
    public Boolean reduceSoLuong(String ma, int soLuong) {
        return sanPhamChiTietRepository.reduceSoLuong(ma, soLuong);
    }

    @Override
    public int checkSoLuong(String ma) {
        return sanPhamChiTietRepository.checkSoLuong(ma);
    }

    @Override
    public List<SanPhamChiTiet> getAllExportExcel() {
        return sanPhamChiTietRepository.getAllExportExcel();
    }

    @Override
    public List<SanPhamChiTiet> filterSanPhamChiTietExport(int idMauSac, int idKichCo, int idChatLieu, int idCoAo, int idChieuDaiTay, int idXuatXu) {
        return sanPhamChiTietRepository.filterSanPhamChiTietExport(idMauSac, idKichCo, idChatLieu, idCoAo, idChieuDaiTay, idXuatXu);
    }

    @Override
    public List<SanPhamChiTiet> filterSanPhamChiTietBanHang(int idMauSac, int idKichCo, int idChatLieu, int idCoAo, int idChieuDaiTay) {
        return sanPhamChiTietRepository.filterSanPhamChiTietBanHang(idMauSac, idKichCo, idChatLieu, idCoAo, idChieuDaiTay);
    }

    @Override
    public boolean isSanPhamChiTietExists(int idSanPham, int idKichCo, int idMauSac, int idChatLieu, int idCoAo, int idChieuDaiTay, int idXuatXu) {
        return sanPhamChiTietRepository.isSanPhamChiTietExists(idSanPham, idKichCo, idMauSac, idChatLieu, idCoAo, idChieuDaiTay, idXuatXu);
    }

}
