package util;

import model.ChatLieu;
import model.ChieuDaiTay;
import model.CoAo;
import model.DanhMuc;
import model.KichCo;
import model.MauSac;
import model.SanPham;
import model.SanPhamChiTiet;
import model.ThuongHieu;
import repository.sanphamrepository.repository.ChatLieuRepository;
import repository.sanphamrepository.repository.ChieuDaiTayRepository;
import repository.sanphamrepository.repository.CoAoRepository;
import repository.sanphamrepository.repository.DanhMucRepository;
import repository.sanphamrepository.repository.KichCoRepository;
import repository.sanphamrepository.repository.MauSacRepository;
import repository.sanphamrepository.repository.SanPhamChiTietRepository;
import repository.sanphamrepository.repository.SanPhamRepository;
import repository.sanphamrepository.repository.ThuongHieuRepository;
import repository.sanphamrepository.repository.XuatXuRepository;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import javax.swing.JOptionPane;
import model.XuatXu;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author cuongwf
 */
public class ImportExcelSanPhamChiTiet {

    private final SanPhamChiTietRepository sanPhamChiTietRepository = new SanPhamChiTietRepository();
    private final SanPhamRepository sanPhamRepository = new SanPhamRepository();
    private final ChatLieuRepository chatLieuRepository = new ChatLieuRepository();
    private final ChieuDaiTayRepository chieuDaiTayRepository = new ChieuDaiTayRepository();
    private final CoAoRepository coAoRepository = new CoAoRepository();
    private final DanhMucRepository danhMucRepository = new DanhMucRepository();
    private final KichCoRepository kichCoRepository = new KichCoRepository();
    private final MauSacRepository mauSacRepository = new MauSacRepository();
    private final ThuongHieuRepository thuongHieuRepository = new ThuongHieuRepository();
    private final XuatXuRepository xuatXuRepository = new XuatXuRepository();

    private ConcurrentMap<String, SanPham> mapSanPham = new ConcurrentHashMap<>();
    private ConcurrentMap<String, ChatLieu> mapChatLieu = new ConcurrentHashMap<>();
    private ConcurrentMap<String, ChieuDaiTay> mapChieuDaiTay = new ConcurrentHashMap<>();
    private ConcurrentMap<String, CoAo> mapCoAo = new ConcurrentHashMap<>();
    private ConcurrentMap<String, DanhMuc> mapDanhMuc = new ConcurrentHashMap<>();
    private ConcurrentMap<String, KichCo> mapKichCo = new ConcurrentHashMap<>();
    private ConcurrentMap<String, MauSac> mapMauSac = new ConcurrentHashMap<>();
    private ConcurrentMap<String, ThuongHieu> mapThuongHieu = new ConcurrentHashMap<>();
    private ConcurrentMap<String, XuatXu> mapXuatXu = new ConcurrentHashMap<>();

    public void ImportFile(String path) {
        try {
            List<SanPhamChiTiet> listSanPhamChiTiet = new ArrayList<>();
            FileInputStream excelFile = new FileInputStream(new File(path));
            Workbook workbook = new XSSFWorkbook(excelFile);
            Sheet datatypeSheet = workbook.getSheetAt(0);
            DataFormatter fmt = new DataFormatter();
            Iterator<Row> iterator = datatypeSheet.iterator();
            Row firstRow = iterator.next();
            Cell firstCell = firstRow.getCell(0);
            addDataInMapCL(mapChatLieu);
            addDataInMapKT(mapKichCo);
            addDataInMapMS(mapMauSac);
            addDataInMapSP(mapSanPham);
            addDataInMapCA(mapCoAo);
            addDataInMapCDT(mapChieuDaiTay);
            addDataInMapXX(mapXuatXu);
            while (iterator.hasNext()) {
                Row currentRow = iterator.next();
                String sanPhamStr = String.valueOf(getCellValue(currentRow.getCell(1))).trim();
                String soLuongTon = String.valueOf(getCellValue(currentRow.getCell(2))).trim();
                String giaBan = String.valueOf(getCellValue(currentRow.getCell(3))).trim();
                String mauSacStr = String.valueOf(getCellValue(currentRow.getCell(4))).trim();
                String tenkichThuoc = String.valueOf(getCellValue(currentRow.getCell(5))).trim();
                String chatLieuStr = String.valueOf(getCellValue(currentRow.getCell(6))).trim();
                String coAoStr = String.valueOf(getCellValue(currentRow.getCell(7))).trim();
                String chieuDaiTayStr = String.valueOf(getCellValue(currentRow.getCell(8))).trim();
                String xuatXuStr = String.valueOf(getCellValue(currentRow.getCell(9))).trim();
                String maVachStr = String.valueOf(getCellValue(currentRow.getCell(10))).trim();

                if (mauSacStr.isEmpty() && sanPhamStr.isEmpty() && tenkichThuoc.isEmpty()
                        && chatLieuStr.isEmpty() && coAoStr.isEmpty() && soLuongTon.isEmpty() && giaBan.isEmpty() && chieuDaiTayStr.isEmpty() && xuatXuStr.isEmpty() && maVachStr.isEmpty()) {
                    continue;
                }
                if (mauSacStr.isEmpty() || sanPhamStr.isEmpty() || tenkichThuoc.isEmpty()
                        && chatLieuStr.isEmpty() || coAoStr.isEmpty() || soLuongTon.isEmpty() || giaBan.isEmpty() || chieuDaiTayStr.isEmpty() || xuatXuStr.isEmpty() || maVachStr.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Không để trống");
                    return;
                }
                try {
                    double soLuongTonSo = 0;
                    soLuongTonSo = Double.parseDouble(soLuongTon);
                    if (soLuongTonSo <= 0) {
                        JOptionPane.showMessageDialog(null, "Số lượng tồn lớn hơn 0");
                        return;
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Số lượng tồn phải là số");
                    return;
                }
                try {
                    double giaBanSo = 0;
                    giaBanSo = Double.parseDouble(giaBan);
                    if (giaBanSo <= 0) {
                        JOptionPane.showMessageDialog(null, "Giá bán lớn hơn 0");
                        return;
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Giá bán phải là số");
                    return;
                }

                SanPham sanPham = new SanPham();
                ChatLieu chatLieu = new ChatLieu();
                ChieuDaiTay chieuDaiTay = new ChieuDaiTay();
                CoAo coAo = new CoAo();
                KichCo kichCo = new KichCo();
                MauSac mauSac = new MauSac();
                XuatXu xuatXu = new XuatXu();

                if (mapSanPham.get(sanPhamStr) == null) {
                    JOptionPane.showMessageDialog(null, "Không tìm thấy sản phẩm");
                    return;
                } else {
                    sanPham = mapSanPham.get(sanPhamStr);
                }

                if (mapMauSac.get(mauSacStr) == null) {
                    JOptionPane.showMessageDialog(null, "Không tìm thấy màu sắc");
                    return;
                } else {
                    mauSac = mapMauSac.get(mauSacStr);
                }

                if (mapKichCo.get(tenkichThuoc) == null) {
                    JOptionPane.showMessageDialog(null, "Không tìm thấy kích thước");
                    return;
                } else {
                    kichCo = mapKichCo.get(tenkichThuoc);
                }

                if (mapChatLieu.get(chatLieuStr) == null) {
                    JOptionPane.showMessageDialog(null, "Không tìm thấy chất liệu");
                    return;
                } else {
                    chatLieu = mapChatLieu.get(chatLieuStr);
                }

                if (mapCoAo.get(coAoStr) == null) {
                    JOptionPane.showMessageDialog(null, "Không tìm thấy cổ áo");
                    return;
                } else {
                    coAo = mapCoAo.get(coAoStr);
                }

                if (mapChieuDaiTay.get(chieuDaiTayStr) == null) {
                    JOptionPane.showMessageDialog(null, "Không tìm thấy chiều dài tay");
                    return;
                } else {
                    chieuDaiTay = mapChieuDaiTay.get(chieuDaiTayStr);
                }

                if (mapXuatXu.get(xuatXuStr) == null) {
                    JOptionPane.showMessageDialog(null, "Không tìm thấy xuất xứ");
                    return;
                } else {
                    xuatXu = mapXuatXu.get(xuatXuStr);
                }

                SanPhamChiTiet chiTietSP = new SanPhamChiTiet();
                SanPhamChiTiet chiTietSPcheck = sanPhamChiTietRepository
                        .findByAtribute(sanPham.getTen(),
                                chatLieu.getTen(), chieuDaiTay.getTen(),
                                coAo.getTen(), kichCo.getTen(), mauSac.getTen(), xuatXu.getTen());
                if (chiTietSPcheck.getMa() == null) {
                    chiTietSP.setMa(generateRandomMaSPCT("SPCT"));
                    chiTietSP.setIdSanPham(sanPham.getId());
                    chiTietSP.setIdMauSac(mauSac.getId());
                    chiTietSP.setIdChatLieu(chatLieu.getId());
                    chiTietSP.setIdKichCo(kichCo.getId());
                    chiTietSP.setIdChieuDaiTay(chieuDaiTay.getId());
                    chiTietSP.setIdCoAo(coAo.getId());
                    chiTietSP.setIdXuatXu(xuatXu.getId());
                    chiTietSP.setMoTa("");
                    chiTietSP.setSoLuong((int) Double.parseDouble(soLuongTon));
                    chiTietSP.setGiaBan(Double.valueOf(giaBan));
                    listSanPhamChiTiet.add(chiTietSP);
                } else {
                    chiTietSPcheck.setGiaBan(Double.valueOf(giaBan));
                    chiTietSPcheck.setMoTa("");
                    if (Integer.valueOf(chiTietSPcheck.getSoLuong()) != null) {
                        chiTietSPcheck.setSoLuong((int) Double.parseDouble(soLuongTon));
                    } else {
                        chiTietSPcheck.setSoLuong((int) Double.parseDouble(soLuongTon));
                    }
                    listSanPhamChiTiet.add(chiTietSPcheck);
                }
            }
            sanPhamChiTietRepository.addAll(listSanPhamChiTiet);
            JOptionPane.showMessageDialog(null, "Import file excel thành công");
            workbook.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addDataInMapSP(ConcurrentMap<String, SanPham> mapSimple) {
        List<SanPham> listSP = sanPhamRepository.getAll();
        getALlPutMapCheckSP(mapSimple, listSP);
    }

    public void addDataInMapMS(ConcurrentMap<String, MauSac> mapSimple) {
        List<MauSac> listSP = mauSacRepository.getAll();
        getALlPutMapCheckMS(mapSimple, listSP);
    }

    public void addDataInMapKT(ConcurrentMap<String, KichCo> mapSimple) {
        List<KichCo> listSP = kichCoRepository.getAll();
        getALlPutMapCheckKT(mapSimple, listSP);
    }

    public void addDataInMapCL(ConcurrentMap<String, ChatLieu> mapSimple) {
        List<ChatLieu> listSP = chatLieuRepository.getAll();
        getALlPutMapCheckCL(mapSimple, listSP);
    }

    public void addDataInMapCA(ConcurrentMap<String, CoAo> mapSimple) {
        List<CoAo> listSP = coAoRepository.getAll();
        getALlPutMapCheckCA(mapSimple, listSP);
    }

    public void addDataInMapCDT(ConcurrentMap<String, ChieuDaiTay> mapSimple) {
        List<ChieuDaiTay> listSP = chieuDaiTayRepository.getAll();
        getALlPutMapCheckCDT(mapSimple, listSP);
    }

    public void addDataInMapXX(ConcurrentMap<String, XuatXu> mapSimple) {
        List<XuatXu> listSP = xuatXuRepository.getAll();
        getALlPutMapCheckXX(mapSimple, listSP);
    }

    public void getALlPutMapCheckSP(ConcurrentMap<String, SanPham> mapSimple, List<SanPham> list) {
        for (SanPham xx : list) {
            mapSimple.put(xx.getTen(), xx);
        }
    }

    public void getALlPutMapCheckKT(ConcurrentMap<String, KichCo> mapSimple, List<KichCo> list) {
        for (KichCo xx : list) {
            mapSimple.put(xx.getTen(), xx);
        }
    }

    public void getALlPutMapCheckMS(ConcurrentMap<String, MauSac> mapSimple, List<MauSac> list) {
        for (MauSac xx : list) {
            mapSimple.put(xx.getTen(), xx);
        }
    }

    public void getALlPutMapCheckCL(ConcurrentMap<String, ChatLieu> mapSimple, List<ChatLieu> list) {
        for (ChatLieu xx : list) {
            mapSimple.put(xx.getTen(), xx);
        }
    }

    public void getALlPutMapCheckCDT(ConcurrentMap<String, ChieuDaiTay> mapSimple, List<ChieuDaiTay> list) {
        for (ChieuDaiTay xx : list) {
            mapSimple.put(xx.getTen(), xx);
        }
    }

    public void getALlPutMapCheckCA(ConcurrentMap<String, CoAo> mapSimple, List<CoAo> list) {
        for (CoAo xx : list) {
            mapSimple.put(xx.getTen(), xx);
        }
    }

    public void getALlPutMapCheckXX(ConcurrentMap<String, XuatXu> mapSimple, List<XuatXu> list) {
        for (XuatXu xx : list) {
            mapSimple.put(xx.getTen(), xx);
        }
    }

    private static Object getCellValue(Cell cell) {
        try {
            switch (cell.getCellType()) {
                case NUMERIC -> {
                    return cell.getNumericCellValue();
                }
                case BOOLEAN -> {
                    return cell.getBooleanCellValue();
                }
                default -> {
                    return cell.getStringCellValue();
                }
            }
        } catch (Exception e) {
            return "";
        }
    }

    private String generateRandomMa(String ma) {
        int randomNumber = (int) ((Math.random() * 90000) + 10000);
        return ma + randomNumber;
    }

    private String generateRandomMaSPCT(String ma) {
        int randomNumber = (int) (Math.random() * 900) + 100;
        return ma + randomNumber;
    }

}
