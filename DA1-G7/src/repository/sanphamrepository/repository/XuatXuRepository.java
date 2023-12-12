package repository.sanphamrepository.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.XuatXu;
import util.DBConnect;

/**
 *
 * @author cuongwf
 */
public class XuatXuRepository {

    private final Connection conn = DBConnect.getConnection();

    public List<XuatXu> getAll() {
        String query = """
                       SELECT id, ten
                       FROM XuatXu WHERE id != 1
                       """;
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            List<XuatXu> listXuatXu = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                listXuatXu.add(new XuatXu(rs.getInt(1),
                        rs.getString(2)));
            }
            return listXuatXu;
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
        }
        return null;
    }

    public List<XuatXu> getAllExport() {
        String query = """
                       SELECT id, ten
                       FROM XuatXu
                       """;
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            List<XuatXu> listXuatXu = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                listXuatXu.add(new XuatXu(rs.getInt(1),
                        rs.getString(2)));
            }
            return listXuatXu;
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
        }
        return null;
    }

    public XuatXu findByTen(String ten) {
        String query = """
                      SELECT id, ten
                      FROM XuatXu WHERE ten = ?
                      """;
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setObject(1, ten);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new XuatXu(rs.getInt(1),
                        rs.getString(2));
            }
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
        }
        return null;
    }

}
