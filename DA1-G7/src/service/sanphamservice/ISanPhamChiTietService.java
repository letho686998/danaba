package service.sanphamservice;

import java.util.List;
import model.SanPhamChiTiet;

/**
 *
 * @author cuongwf
 */
public interface ISanPhamChiTietService {

    List<SanPhamChiTiet> getAllByIdSanPham(String maSanPham);

    List<SanPhamChiTiet> getAllByIdSanPham(String maSanPham, int offset, int limit);

    List<SanPhamChiTiet> getAllExportExcel();

    List<SanPhamChiTiet> filterSanPhamChiTietExport(int idMauSac, int idKichCo, int idChatLieu, int idCoAo, int idChieuDaiTay, int idXuatXu);

    List<SanPhamChiTiet> filterSanPhamChiTietBanHang(int idMauSac, int idKichCo, int idChatLieu, int idCoAo, int idChieuDaiTay);

    List<SanPhamChiTiet> getAll();

    int countSanPhamChiTiet(String maSanPham);

    String add(SanPhamChiTiet sanPhamChiTiet);

    String update(SanPhamChiTiet sanPhamChiTiet);

    SanPhamChiTiet findSanPhamChiTietByMa(String ma);

    Boolean reduceSoLuong(String ma, int soLuong);

    int checkSoLuong(String ma);

    boolean isSanPhamChiTietExists(int idSanPham, int idKichCo, int idMauSac, int idChatLieu, int idCoAo, int idChieuDaiTay, int idXuatXu);

}
