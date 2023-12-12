package repository.sanphamrepository.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import model.ChatLieu;
import model.ChieuDaiTay;
import model.CoAo;
import model.KichCo;
import model.MauSac;
import model.SanPham;
import model.SanPhamChiTiet;
import model.XuatXu;
import util.DBConnect;

/**
 *
 * @author cuongwf
 */
public class SanPhamChiTietRepository {

    private final Connection conn = DBConnect.getConnection();

    private final ChatLieuRepository chatLieuRepository = new ChatLieuRepository();
    private final ChieuDaiTayRepository chieuDaiTayRepository = new ChieuDaiTayRepository();
    private final CoAoRepository coAoRepository = new CoAoRepository();
    private final KichCoRepository kichCoRepository = new KichCoRepository();
    private final MauSacRepository mauSacRepository = new MauSacRepository();
    private final XuatXuRepository xuatXuRepository = new XuatXuRepository();
    private final SanPhamRepository sanPhamRepository = new SanPhamRepository();

    public List<SanPhamChiTiet> getAll() {
        String query = """
                       SELECT spct.id, spct.ma,
                       spct.idSanPham, sp.ten,
                       spct.idChatLieu, cl.ten,
                       spct.idChieuDaiTay, cdt.ten,
                       spct.idKichCo, kc.ten,
                       spct.idMauSac, ms.ten,
                       spct.idCoAo, ca.ten,
                       spct.idXuatXu, xx.ten,
                       spct.soLuong, spct.giaBan,
                       spct.moTa, spct.trangThai
                       FROM SanPhamChiTiet spct
                       JOIN ChatLieu cl ON spct.idChatLieu = cl.id
                       JOIN ChieuDaiTay cdt ON spct.idChieuDaiTay = cdt.id
                       JOIN KichCo kc ON spct.idKichCo = kc.id
                       JOIN MauSac ms ON spct.idMauSac = ms.id
                       JOIN SanPham sp ON spct.idSanPham = sp.id
                       JOIN CoAo ca ON spct.idCoAo = ca.id
                       JOIN XuatXu xx ON spct.idXuatXu = xx.id
                       WHERE spct.trangThai = 0 AND spct.soLuong > 0
                       ORDER BY sp.ten
                       """;
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            List<SanPhamChiTiet> listSanPhamChiTiet = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                listSanPhamChiTiet.add(
                        new SanPhamChiTiet(
                                rs.getInt(1), rs.getString(2),
                                rs.getInt(3), rs.getString(4),
                                rs.getInt(5), rs.getString(6),
                                rs.getInt(7), rs.getString(8),
                                rs.getInt(9), rs.getString(10),
                                rs.getInt(11), rs.getString(12),
                                rs.getInt(13), rs.getString(14),
                                rs.getInt(15), rs.getString(16),
                                rs.getInt(17), rs.getDouble(18),
                                rs.getString(19),
                                rs.getBoolean(20)));
            }
            return listSanPhamChiTiet;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public List<SanPhamChiTiet> getAllExportExcel() {
        String query = """
                       SELECT spct.id, spct.ma,
                       spct.idSanPham, sp.ten,
                       spct.idChatLieu, cl.ten,
                       spct.idChieuDaiTay, cdt.ten,
                       spct.idKichCo, kc.ten,
                       spct.idMauSac, ms.ten,
                       spct.idCoAo, ca.ten,
                       spct.idXuatXu, xx.ten,
                       spct.soLuong, spct.giaBan,
                       spct.moTa, spct.trangThai
                       FROM SanPhamChiTiet spct
                       JOIN ChatLieu cl ON spct.idChatLieu = cl.id
                       JOIN ChieuDaiTay cdt ON spct.idChieuDaiTay = cdt.id
                       JOIN KichCo kc ON spct.idKichCo = kc.id
                       JOIN MauSac ms ON spct.idMauSac = ms.id
                       JOIN SanPham sp ON spct.idSanPham = sp.id
                       JOIN CoAo ca ON spct.idCoAo = ca.id
                       JOIN XuatXu xx ON spct.idXuatXu = xx.id
                       """;
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            List<SanPhamChiTiet> listSanPhamChiTiet = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                listSanPhamChiTiet.add(
                        new SanPhamChiTiet(
                                rs.getInt(1), rs.getString(2),
                                rs.getInt(3), rs.getString(4),
                                rs.getInt(5), rs.getString(6),
                                rs.getInt(7), rs.getString(8),
                                rs.getInt(9), rs.getString(10),
                                rs.getInt(11), rs.getString(12),
                                rs.getInt(13), rs.getString(14),
                                rs.getInt(15), rs.getString(16),
                                rs.getInt(17), rs.getDouble(18),
                                rs.getString(19),
                                rs.getBoolean(20)));
            }
            return listSanPhamChiTiet;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public List<SanPhamChiTiet> getAllByIdSanPham(String maSanPham) {
        String query = """
                       SELECT spct.id, spct.ma,
                       spct.idSanPham, sp.ten,
                       spct.idChatLieu, cl.ten,
                       spct.idChieuDaiTay, cdt.ten,
                       spct.idKichCo, kc.ten,
                       spct.idMauSac, ms.ten,
                       spct.idCoAo, ca.ten,
                       spct.idXuatXu, xx.ten,
                       spct.soLuong, spct.giaBan,
                       spct.moTa, spct.trangThai
                       FROM SanPhamChiTiet spct
                       JOIN ChatLieu cl ON spct.idChatLieu = cl.id
                       JOIN ChieuDaiTay cdt ON spct.idChieuDaiTay = cdt.id
                       JOIN KichCo kc ON spct.idKichCo = kc.id
                       JOIN MauSac ms ON spct.idMauSac = ms.id
                       JOIN SanPham sp ON spct.idSanPham = sp.id
                       JOIN CoAo ca ON spct.idCoAo = ca.id
                       JOIN XuatXu xx ON spct.idXuatXu = xx.id
                       WHERE sp.ma = ?
                       """;
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            List<SanPhamChiTiet> listSanPhamChiTiet = new ArrayList<>();
            ps.setObject(1, maSanPham);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                listSanPhamChiTiet.add(
                        new SanPhamChiTiet(
                                rs.getInt(1), rs.getString(2),
                                rs.getInt(3), rs.getString(4),
                                rs.getInt(5), rs.getString(6),
                                rs.getInt(7), rs.getString(8),
                                rs.getInt(9), rs.getString(10),
                                rs.getInt(11), rs.getString(12),
                                rs.getInt(13), rs.getString(14),
                                rs.getInt(15), rs.getString(16),
                                rs.getInt(17), rs.getDouble(18),
                                rs.getString(19),
                                rs.getBoolean(20)));
            }
            return listSanPhamChiTiet;
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
        }
        return null;
    }

    public List<SanPhamChiTiet> getAllByIdSanPham(String maSanPham, int offset, int limit) {
        String query = """
                       SELECT spct.id, spct.ma,
                       spct.idSanPham, sp.ten,
                       spct.idChatLieu, cl.ten,
                       spct.idChieuDaiTay, cdt.ten,
                       spct.idKichCo, kc.ten,
                       spct.idMauSac, ms.ten,
                       spct.idCoAo, ca.ten,
                       spct.idXuatXu, xx.ten,
                       spct.soLuong, spct.giaBan,
                       spct.moTa, spct.trangThai
                       FROM SanPhamChiTiet spct
                       JOIN ChatLieu cl ON spct.idChatLieu = cl.id
                       JOIN ChieuDaiTay cdt ON spct.idChieuDaiTay = cdt.id
                       JOIN KichCo kc ON spct.idKichCo = kc.id
                       JOIN MauSac ms ON spct.idMauSac = ms.id
                       JOIN SanPham sp ON spct.idSanPham = sp.id
                       JOIN CoAo ca ON spct.idCoAo = ca.id
                       JOIN XuatXu xx ON spct.idXuatXu = xx.id
                       WHERE sp.ma = ?
                       ORDER BY spct.ngayTao
                       OFFSET ? ROWS FETCH NEXT ? ROWS ONLY;
                       """;
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            List<SanPhamChiTiet> listSanPhamChiTiet = new ArrayList<>();
            ps.setObject(1, maSanPham);
            ps.setObject(2, offset);
            ps.setObject(3, limit);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                listSanPhamChiTiet.add(
                        new SanPhamChiTiet(
                                rs.getInt(1), rs.getString(2),
                                rs.getInt(3), rs.getString(4),
                                rs.getInt(5), rs.getString(6),
                                rs.getInt(7), rs.getString(8),
                                rs.getInt(9), rs.getString(10),
                                rs.getInt(11), rs.getString(12),
                                rs.getInt(13), rs.getString(14),
                                rs.getInt(15), rs.getString(16),
                                rs.getInt(17), rs.getDouble(18),
                                rs.getString(19),
                                rs.getBoolean(20)));
            }
            return listSanPhamChiTiet;
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
        }
        return null;
    }

    public List<SanPhamChiTiet> filterSanPhamChiTietExport(int idMauSac, int idKichCo, int idChatLieu, int idCoAo, int idChieuDaiTay, int idXuatXu) {
        String query = """
                       SELECT spct.id, spct.ma,
                                              spct.idSanPham, sp.ten,
                                              spct.idChatLieu, cl.ten,
                                              spct.idChieuDaiTay, cdt.ten,
                                              spct.idKichCo, kc.ten,
                                              spct.idMauSac, ms.ten,
                                              spct.idCoAo, ca.ten,
                                              spct.idXuatXu, xx.ten,
                                              spct.soLuong, spct.giaBan,
                                              spct.moTa, spct.trangThai
                                              FROM SanPhamChiTiet spct
                                              JOIN ChatLieu cl ON spct.idChatLieu = cl.id
                                              JOIN ChieuDaiTay cdt ON spct.idChieuDaiTay = cdt.id
                                              JOIN KichCo kc ON spct.idKichCo = kc.id
                                              JOIN MauSac ms ON spct.idMauSac = ms.id
                                              JOIN SanPham sp ON spct.idSanPham = sp.id
                                              JOIN CoAo ca ON spct.idCoAo = ca.id
                                              JOIN XuatXu xx ON spct.idXuatXu = xx.id
                       WHERE (spct.idMauSac = ? OR ? = 1)
                       AND (spct.idKichCo = ? OR ? = 1)
                       AND (spct.idChatLieu = ? OR ? = 1)
                       AND (spct.idCoAo = ? OR ? = 1)
                       AND (spct.idChieuDaiTay = ? OR ? = 1)
                       AND (spct.idXuatXu = ? OR ? = 1)
                       """;
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setObject(1, idMauSac);
            ps.setObject(2, idMauSac);
            ps.setObject(3, idKichCo);
            ps.setObject(4, idKichCo);
            ps.setObject(5, idChatLieu);
            ps.setObject(6, idChatLieu);
            ps.setObject(7, idCoAo);
            ps.setObject(8, idCoAo);
            ps.setObject(9, idChieuDaiTay);
            ps.setObject(10, idChieuDaiTay);
            ps.setObject(11, idXuatXu);
            ps.setObject(12, idXuatXu);
            List<SanPhamChiTiet> listSanPhamChiTiet = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                listSanPhamChiTiet.add(
                        new SanPhamChiTiet(
                                rs.getInt(1), rs.getString(2),
                                rs.getInt(3), rs.getString(4),
                                rs.getInt(5), rs.getString(6),
                                rs.getInt(7), rs.getString(8),
                                rs.getInt(9), rs.getString(10),
                                rs.getInt(11), rs.getString(12),
                                rs.getInt(13), rs.getString(14),
                                rs.getInt(15), rs.getString(16),
                                rs.getInt(17), rs.getDouble(18),
                                rs.getString(19),
                                rs.getBoolean(20)));
            }
            return listSanPhamChiTiet;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public List<SanPhamChiTiet> filterSanPhamChiTietBanHang(int idMauSac, int idKichCo, int idChatLieu, int idCoAo, int idChieuDaiTay) {
        String query = """
                       SELECT spct.id, spct.ma,
                                              spct.idSanPham, sp.ten,
                                              spct.idChatLieu, cl.ten,
                                              spct.idChieuDaiTay, cdt.ten,
                                              spct.idKichCo, kc.ten,
                                              spct.idMauSac, ms.ten,
                                              spct.idCoAo, ca.ten,
                                              spct.soLuong, spct.giaBan,
                                              spct.moTa, spct.trangThai
                                              FROM SanPhamChiTiet spct
                                              JOIN ChatLieu cl ON spct.idChatLieu = cl.id
                                              JOIN ChieuDaiTay cdt ON spct.idChieuDaiTay = cdt.id
                                              JOIN KichCo kc ON spct.idKichCo = kc.id
                                              JOIN MauSac ms ON spct.idMauSac = ms.id
                                              JOIN SanPham sp ON spct.idSanPham = sp.id
                                              JOIN CoAo ca ON spct.idCoAo = ca.id
                       WHERE (spct.idMauSac = ? OR ? = 1)
                       AND (spct.idKichCo = ? OR ? = 1)
                       AND (spct.idChatLieu = ? OR ? = 1)
                       AND (spct.idCoAo = ? OR ? = 1)
                       AND (spct.idChieuDaiTay = ? OR ? = 1)
                       AND spct.soLuong > 0
                       AND spct.trangThai = 0
                       """;
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setObject(1, idMauSac);
            ps.setObject(2, idMauSac);
            ps.setObject(3, idKichCo);
            ps.setObject(4, idKichCo);
            ps.setObject(5, idChatLieu);
            ps.setObject(6, idChatLieu);
            ps.setObject(7, idCoAo);
            ps.setObject(8, idCoAo);
            ps.setObject(9, idChieuDaiTay);
            ps.setObject(10, idChieuDaiTay);
            List<SanPhamChiTiet> listSanPhamChiTiet = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                listSanPhamChiTiet.add(
                        new SanPhamChiTiet(
                                rs.getInt(1), rs.getString(2),
                                rs.getInt(3), rs.getString(4),
                                rs.getInt(5), rs.getString(6),
                                rs.getInt(7), rs.getString(8),
                                rs.getInt(9), rs.getString(10),
                                rs.getInt(11), rs.getString(12),
                                rs.getInt(13), rs.getString(14),
                                rs.getInt(15), rs.getDouble(16),
                                rs.getString(17),
                                rs.getBoolean(18)
                        ));
            }
            return listSanPhamChiTiet;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public int countSanPhamChiTiet(String maSanPham) {
        String query = """
                       SELECT COUNT(*) FROM SanPhamChiTiet spct 
                       JOIN SanPham sp ON spct.idSanPham = sp.id WHERE sp.ma = ?
                       """;
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setObject(1, maSanPham);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
        }
        return 0;
    }

    public boolean add(SanPhamChiTiet sanPhamChiTiet) {
        int check = 0;
        String query = """
                       INSERT INTO SanPhamChiTiet (ma, idChatLieu, idChieuDaiTay,
                       idKichCo, idMauSac, idSanPham, idCoAo, soLuong, giaBan,
                       moTa, idXuatXu, trangThai)
                       VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);
                       """;
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setObject(1, sanPhamChiTiet.getMa());
            ps.setObject(2, sanPhamChiTiet.getIdChatLieu());
            ps.setObject(3, sanPhamChiTiet.getIdChieuDaiTay());
            ps.setObject(4, sanPhamChiTiet.getIdKichCo());
            ps.setObject(5, sanPhamChiTiet.getIdMauSac());
            ps.setObject(6, sanPhamChiTiet.getIdSanPham());
            ps.setObject(7, sanPhamChiTiet.getIdCoAo());
            ps.setObject(8, sanPhamChiTiet.getSoLuong());
            ps.setObject(9, sanPhamChiTiet.getGiaBan());
            ps.setObject(10, sanPhamChiTiet.getMoTa());
            ps.setObject(11, sanPhamChiTiet.getIdXuatXu());
            ps.setObject(12, false);
            check = ps.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
        }
        return check > 0;
    }

    public boolean update(SanPhamChiTiet sanPhamChiTiet) {
        int check = 0;
        String query = """
                       UPDATE SanPhamChiTiet 
                       SET soLuong=?, giaBan=?, moTa=? WHERE ma=?
                       """;
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setObject(1, sanPhamChiTiet.getSoLuong());
            ps.setObject(2, sanPhamChiTiet.getGiaBan());
            ps.setObject(3, sanPhamChiTiet.getMoTa());
            ps.setObject(4, sanPhamChiTiet.getMa());
            check = ps.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
        }
        return check > 0;
    }

    public SanPhamChiTiet findByAtribute(String tenSanPham, String tenChatLieu, String tenChieuDaiTay, String tenCoAo, String tenKichCo, String tenMauSac, String tenXuatXu) {
        String query = """
                       SELECT id, ma, idChatLieu,
                       idChieuDaiTay, idKichCo, idMauSac,
                       idSanPham, idCoAo, idXuatXu, soLuong,
                       giaBan, moTa
                       FROM SanPhamChiTiet WHERE idChatLieu = ? AND idChieuDaiTay = ?
                       AND idKichCo = ? AND idMauSac = ? AND idSanPham = ?
                       AND idCoAo = ? AND idXuatXu = ?;
                       """;
        ChatLieu chatLieu = chatLieuRepository.findByTen(tenChatLieu);
        ChieuDaiTay chieuDaiTay = chieuDaiTayRepository.findByTen(tenChieuDaiTay);
        CoAo coAo = coAoRepository.findByTen(tenCoAo);
        KichCo kichCo = kichCoRepository.findByTen(tenKichCo);
        MauSac mauSac = mauSacRepository.findByTen(tenMauSac);
        XuatXu xuatXu = xuatXuRepository.findByTen(tenXuatXu);
        SanPham sanPham = sanPhamRepository.findByTen(tenSanPham);

        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setObject(1, chatLieu.getId());
            ps.setObject(2, chieuDaiTay.getId());
            ps.setObject(3, kichCo.getId());
            ps.setObject(4, mauSac.getId());
            ps.setObject(5, sanPham.getId());
            ps.setObject(6, coAo.getId());
            ps.setObject(7, xuatXu.getId());
            SanPhamChiTiet sanPhamChiTiet = new SanPhamChiTiet();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                sanPhamChiTiet.setId(rs.getInt(1));
                sanPhamChiTiet.setMa(rs.getString(2));
                sanPhamChiTiet.setIdChatLieu(rs.getInt(3));
                sanPhamChiTiet.setIdChieuDaiTay(rs.getInt(4));
                sanPhamChiTiet.setIdKichCo(rs.getInt(5));
                sanPhamChiTiet.setIdMauSac(rs.getInt(6));
                sanPhamChiTiet.setIdSanPham(rs.getInt(7));
                sanPhamChiTiet.setIdCoAo(rs.getInt(8));
                sanPhamChiTiet.setIdXuatXu(rs.getInt(9));
                sanPhamChiTiet.setSoLuong(rs.getInt(10));
                sanPhamChiTiet.setGiaBan(rs.getDouble(11));
                sanPhamChiTiet.setMoTa(rs.getString(12));
            }
            return sanPhamChiTiet;
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
        }
        return null;
    }

    public int addAll(List<SanPhamChiTiet> sanPhamChiTiet) {
        String sql = """
                     MERGE INTO SanPhamChiTiet AS target
                     USING (
                         VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
                     ) AS source (ma, idChatLieu, idChieuDaiTay,idKichCo, idMauSac, idSanPham, idCoAo, soLuong, giaBan, moTa, idXuatXu, trangThai)
                     ON target.ma = source.ma
                     WHEN MATCHED THEN
                         UPDATE SET
                         target.giaBan = source.giaBan,
                         target.soLuong = target.soLuong + source.soLuong,
                         target.moTa = source.moTa
                     WHEN NOT MATCHED THEN
                         INSERT (ma, idChatLieu, idChieuDaiTay,
                                                                         idKichCo, idMauSac, idSanPham, idCoAo, soLuong, giaBan,
                                                                         moTa, idXuatXu, trangThai)
                         VALUES (source.ma, source.idChatLieu, source.idChieuDaiTay,
                                                                         source.idKichCo, source.idMauSac, source.idSanPham, source.idCoAo, source.soLuong, source.giaBan,
                                                                         source.moTa, source.idXuatXu, source.trangThai);
                     """;

        try (PreparedStatement stm = conn.prepareStatement(sql)) {
            for (SanPhamChiTiet t : sanPhamChiTiet) {
                stm.setObject(1, t.getMa());
                stm.setObject(2, t.getIdChatLieu());
                stm.setObject(3, t.getIdChieuDaiTay());
                stm.setObject(4, t.getIdKichCo());
                stm.setObject(5, t.getIdMauSac());
                stm.setObject(6, t.getIdSanPham());
                stm.setObject(7, t.getIdCoAo());
                stm.setObject(8, t.getSoLuong());
                stm.setObject(9, t.getGiaBan());
                stm.setObject(10, t.getMoTa());
                stm.setObject(11, t.getIdXuatXu());
                stm.setObject(12, false);
                stm.addBatch();
            }

            int[] results = stm.executeBatch();

            int totalAffectedRows = Arrays.stream(results).sum();

            return totalAffectedRows;
        } catch (Exception ex) {
            ex.printStackTrace();
            return 0;
        }
    }

    public SanPhamChiTiet findSanPhamChiTietByMa(String ma) {
        String query = """
                       SELECT id, ma, idChatLieu, idChieuDaiTay,
                       idKichCo, idMauSac, idSanPham, idCoAo, idXuatXu,
                       soLuong, giaBan, moTa, trangThai
                       FROM SanPhamChiTiet WHERE ma = ?
                       """;
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setObject(1, ma);
            SanPhamChiTiet sanPhamChiTiet = new SanPhamChiTiet();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                sanPhamChiTiet.setId(rs.getInt(1));
                sanPhamChiTiet.setMa(rs.getString(2));
                sanPhamChiTiet.setIdChatLieu(rs.getInt(3));
                sanPhamChiTiet.setIdChieuDaiTay(rs.getInt(4));
                sanPhamChiTiet.setIdKichCo(rs.getInt(5));
                sanPhamChiTiet.setIdMauSac(rs.getInt(6));
                sanPhamChiTiet.setIdSanPham(rs.getInt(7));
                sanPhamChiTiet.setIdCoAo(rs.getInt(8));
                sanPhamChiTiet.setIdXuatXu(rs.getInt(9));
                sanPhamChiTiet.setSoLuong(rs.getInt(10));
                sanPhamChiTiet.setGiaBan(rs.getDouble(11));
                sanPhamChiTiet.setMoTa(rs.getString(12));
                sanPhamChiTiet.setTrangThai(rs.getBoolean(13));
            }
            return sanPhamChiTiet;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public boolean reduceSoLuong(String ma, int soLuong) {
        int check = 0;
        String query = """
                       UPDATE SanPhamChiTiet SET soLuong = soLuong - ? WHERE ma = ?;
                       """;
        try (Connection conn = DBConnect.getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, soLuong);
            ps.setString(2, ma);
            check = ps.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return check > 0;
    }

    public int checkSoLuong(String ma) {
        String query = """
                       SELECT soLuong FROM SanPhamChiTiet WHERE ma = ?
                       """;
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setObject(1, ma);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
        }
        return 0;
    }

    public boolean isSanPhamChiTietExists(int idSanPham, int idKichCo, int idMauSac, int idChatLieu, int idCoAo, int idChieuDaiTay, int idXuatXu) {
        String query = """
                       SELECT COUNT(*) FROM SanPhamChiTiet
                       WHERE idSanPham = ? AND idKichCo = ?
                       AND idMauSac = ? AND idChatLieu = ?
                       AND idCoAo = ? AND idChieuDaiTay = ?
                       AND idXuatXu = ?
                       """;
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setObject(1, idSanPham);
            ps.setObject(2, idKichCo);
            ps.setObject(3, idMauSac);
            ps.setObject(4, idChatLieu);
            ps.setObject(5, idCoAo);
            ps.setObject(6, idChieuDaiTay);
            ps.setObject(7, idXuatXu);
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
