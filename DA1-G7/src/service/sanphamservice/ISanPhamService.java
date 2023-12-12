package service.sanphamservice;

import java.util.List;
import model.SanPham;

/**
 *
 * @author cuongwf
 */
public interface ISanPhamService {

    List<SanPham> getAll();

    List<SanPham> getAll(int offset, int limit);

    boolean isSanPhamExists(String ten, int idThuongHieu, int idDanhMuc);

    String add(SanPham sanPham);
    
    String update(SanPham sanPham);

    int countSanPham();
}
