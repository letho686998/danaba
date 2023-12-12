package repository.sanphamrepository.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import model.KichCo;
import util.DBConnect;
import repository.sanphamrepository.IThuocTinhRepository;

/**
 *
 * @author cuongwf
 */
public class KichCoRepository implements IThuocTinhRepository<KichCo> {

    private final Connection conn = DBConnect.getConnection();

    private LocalDateTime localDateTime;

    @Override
    public List<KichCo> getAll() {
        String query = """
                       SELECT id, ma, ten, ngayTao, ngaySua, isDelete
                       FROM KichCo WHERE id != 1 ORDER BY ngayTao DESC
                       """;
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            List<KichCo> listKichCo = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                listKichCo.add(new KichCo(rs.getInt(1),
                        rs.getString(2), rs.getString(3),
                        rs.getTimestamp(4).toLocalDateTime(),
                        rs.getTimestamp(5).toLocalDateTime(),
                        rs.getBoolean(6)));
            }
            return listKichCo;
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
        }
        return null;
    }
    
    @Override
    public List<KichCo> getAllExport() {
        String query = """
                       SELECT id, ma, ten, ngayTao, ngaySua, isDelete
                       FROM KichCo
                       """;
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            List<KichCo> listKichCo = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                listKichCo.add(new KichCo(rs.getInt(1),
                        rs.getString(2), rs.getString(3),
                        rs.getTimestamp(4).toLocalDateTime(),
                        rs.getTimestamp(5).toLocalDateTime(),
                        rs.getBoolean(6)));
            }
            return listKichCo;
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
        }
        return null;
    }

    @Override
    public boolean add(KichCo entity) {
        int check = 0;
        String query = """
                       INSERT INTO KichCo
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
    public boolean update(KichCo entity) {
        int check = 0;
        String query = """
                       UPDATE KichCo SET ten = ?, ngaySua = ? WHERE ma = ?
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
    public KichCo findByTen(String ten) {
        String query = """
                      SELECT id, ma, ten, ngayTao, ngaySua, isDelete
                      FROM KichCo WHERE ten = ?
                      """;
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setObject(1, ten);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new KichCo(rs.getInt(1),
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
                       SELECT COUNT(*) FROM KichCo WHERE ten = ?
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
