package repository.sanphamrepository.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import model.DanhMuc;
import util.DBConnect;
import repository.sanphamrepository.IThuocTinhRepository;

/**
 *
 * @author cuongwf
 */
public class DanhMucRepository implements IThuocTinhRepository<DanhMuc> {

    private final Connection conn = DBConnect.getConnection();

    private LocalDateTime localDateTime;

    @Override
    public List<DanhMuc> getAll() {
        String query = """
                       SELECT id, ma, ten, ngayTao, ngaySua, isDelete
                       FROM DanhMuc WHERE id != 1 ORDER BY ngayTao DESC
                       """;
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            List<DanhMuc> listDanhMuc = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                listDanhMuc.add(new DanhMuc(rs.getInt(1),
                        rs.getString(2), rs.getString(3),
                        rs.getTimestamp(4).toLocalDateTime(),
                        rs.getTimestamp(5).toLocalDateTime(),
                        rs.getBoolean(6)));
            }
            return listDanhMuc;
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
        }
        return null;
    }
    
    @Override
    public List<DanhMuc> getAllExport() {
        String query = """
                       SELECT id, ma, ten, ngayTao, ngaySua, isDelete
                       FROM DanhMuc
                       """;
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            List<DanhMuc> listDanhMuc = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                listDanhMuc.add(new DanhMuc(rs.getInt(1),
                        rs.getString(2), rs.getString(3),
                        rs.getTimestamp(4).toLocalDateTime(),
                        rs.getTimestamp(5).toLocalDateTime(),
                        rs.getBoolean(6)));
            }
            return listDanhMuc;
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
        }
        return null;
    }

    @Override
    public boolean add(DanhMuc entity) {
        int check = 0;
        String query = """
                       INSERT INTO DanhMuc
                       (ma, ten, ngayTao, ngaySua, isDelete) VALUES(?, ?, ?, ?, 0);
                       """;
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setObject(1, entity.getMa());
            ps.setObject(2, entity.getTen());
            ps.setObject(3, localDateTime.now());
            ps.setObject(4, localDateTime.now());
            check = ps.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
        }
        return check > 0;
    }

    @Override
    public boolean update(DanhMuc entity) {
        int check = 0;
        String query = """
                       UPDATE DanhMuc SET ten = ?, ngaySua = ? WHERE ma = ?
                       """;
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setObject(1, entity.getTen());
            ps.setObject(2, localDateTime.now());
            ps.setObject(3, entity.getMa());
            check = ps.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
        }
        return check > 0;
    }

    @Override
    public DanhMuc findByTen(String ten) {
        String query = """
                      SELECT id, ma, ten, ngayTao, ngaySua, isDelete
                      FROM DanhMuc WHERE ten = ?
                      """;
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setObject(1, ten);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new DanhMuc(rs.getInt(1),
                        rs.getString(2), rs.getString(3),
                        rs.getTimestamp(4).toLocalDateTime(),
                        rs.getTimestamp(5).toLocalDateTime(),
                        rs.getBoolean(6));
            }
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
        }
        return null;
    }

    @Override
    public boolean isThuocTinhExists(String tenThuocTinh) {
        String query = """
                       SELECT COUNT(*) FROM DanhMuc WHERE ten = ?
                       """;
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setObject(1, tenThuocTinh);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

}
