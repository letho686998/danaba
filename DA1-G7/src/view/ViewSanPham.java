package view;

import app.util.DownloadProductDetailTemplate;
import app.view.swing.EventPagination;
import app.view.swing.PaginationItemRenderStyle1;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.BadLocationException;
import model.ChatLieu;
import model.ChieuDaiTay;
import model.CoAo;
import model.DanhMuc;
import model.KichCo;
import model.MauSac;
import model.SanPham;
import model.SanPhamChiTiet;
import model.ThuongHieu;
import model.XuatXu;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import service.sanphamservice.ISanPhamChiTietService;
import service.sanphamservice.ISanPhamService;
import service.sanphamservice.IThuocTinhService;
import service.sanphamservice.impl.ChatLieuService;
import service.sanphamservice.impl.ChieuDaiTayService;
import service.sanphamservice.impl.CoAoService;
import service.sanphamservice.impl.DanhMucService;
import service.sanphamservice.impl.KichCoService;
import service.sanphamservice.impl.MauSacService;
import service.sanphamservice.impl.SanPhamChiTietService;
import service.sanphamservice.impl.SanPhamService;
import service.sanphamservice.impl.ThuongHieuService;
import service.sanphamservice.impl.XuatXuService;
import util.ImportExcelSanPhamChiTiet;
import static util.ValidateForm.isCheckEmpty;
import static util.XGenerateQRCode.doGenerate;
import static util.XGenerateQRCode.doGenerateQR;
import static util.ValidateForm.isPositiveNumber;
import static util.ValidateForm.isPositiveFloat;

/**
 *
 * @author bcuon
 */
public class ViewSanPham extends javax.swing.JPanel {

    private DefaultComboBoxModel<ThuongHieu> dcbmThuongHieu = new DefaultComboBoxModel<>();
    private DefaultComboBoxModel<DanhMuc> dcbmDanhMuc = new DefaultComboBoxModel<>();
    private DefaultComboBoxModel<ChatLieu> dcbmChatLieu = new DefaultComboBoxModel<>();
    private DefaultComboBoxModel<MauSac> dcbmMauSac = new DefaultComboBoxModel<>();
    private DefaultComboBoxModel<KichCo> dcbmKichCo = new DefaultComboBoxModel<>();
    private DefaultComboBoxModel<CoAo> dcbmCoAo = new DefaultComboBoxModel<>();
    private DefaultComboBoxModel<ChieuDaiTay> dcbmChieuDaiTay = new DefaultComboBoxModel<>();
    private DefaultComboBoxModel<XuatXu> dcbmXuatXu = new DefaultComboBoxModel<>();
    private DefaultComboBoxModel<SanPham> dcbmSanPham = new DefaultComboBoxModel<>();

    private DefaultComboBoxModel<ChatLieu> dcbmChatLieuExport = new DefaultComboBoxModel<>();
    private DefaultComboBoxModel<MauSac> dcbmMauSacExport = new DefaultComboBoxModel<>();
    private DefaultComboBoxModel<KichCo> dcbmKichCoExport = new DefaultComboBoxModel<>();
    private DefaultComboBoxModel<CoAo> dcbmCoAoExport = new DefaultComboBoxModel<>();
    private DefaultComboBoxModel<ChieuDaiTay> dcbmChieuDaiTayExport = new DefaultComboBoxModel<>();
    private DefaultComboBoxModel<XuatXu> dcbmXuatXuExport = new DefaultComboBoxModel<>();

    private DefaultTableModel dtmThuocTinh = new DefaultTableModel();
    private DefaultTableModel dtmSanPham = new DefaultTableModel();
    private DefaultTableModel dtmSanPhamChiTiet = new DefaultTableModel();
    private DefaultTableModel dtmSanPhamChiTietExport = new DefaultTableModel();

    private final IThuocTinhService<ChatLieu> chatLieuService = new ChatLieuService();
    private final IThuocTinhService<ChieuDaiTay> chieuDaiTayService = new ChieuDaiTayService();
    private final IThuocTinhService<CoAo> coAoService = new CoAoService();
    private final IThuocTinhService<DanhMuc> danhMucService = new DanhMucService();
    private final IThuocTinhService<KichCo> kichCoService = new KichCoService();
    private final IThuocTinhService<MauSac> mauSacService = new MauSacService();
    private final IThuocTinhService<ThuongHieu> thuongHieuService = new ThuongHieuService();
    private final XuatXuService xuatXuService = new XuatXuService();
    private final ISanPhamService sanPhamService = new SanPhamService();
    private final ISanPhamChiTietService sanPhamChiTietService = new SanPhamChiTietService();

    private int currentPage = 1;

    public ViewSanPham() {
        initComponents();

    }

    public ViewSanPham(JFrame parentFrame) {
        initComponents();

        dcbmThuongHieu = (DefaultComboBoxModel<ThuongHieu>) cbbThuongHieu.getModel();
        dcbmDanhMuc = (DefaultComboBoxModel<DanhMuc>) cbbDanhMuc.getModel();
        dcbmChatLieu = (DefaultComboBoxModel<ChatLieu>) cbbChatLieu.getModel();
        dcbmMauSac = (DefaultComboBoxModel<MauSac>) cbbMauSac.getModel();
        dcbmKichCo = (DefaultComboBoxModel<KichCo>) cbbKichCo.getModel();
        dcbmCoAo = (DefaultComboBoxModel<CoAo>) cbbCoAo.getModel();
        dcbmChieuDaiTay = (DefaultComboBoxModel<ChieuDaiTay>) cbbChieuDaiTay.getModel();
        dcbmXuatXu = (DefaultComboBoxModel<XuatXu>) cbbXuatXu.getModel();
        dcbmSanPham = (DefaultComboBoxModel<SanPham>) cbbSanPham.getModel();

        dcbmChatLieuExport = (DefaultComboBoxModel<ChatLieu>) cbbChatLieuExport.getModel();
        dcbmMauSacExport = (DefaultComboBoxModel<MauSac>) cbbMauSacExport.getModel();
        dcbmKichCoExport = (DefaultComboBoxModel<KichCo>) cbbKichCoExport.getModel();
        dcbmCoAoExport = (DefaultComboBoxModel<CoAo>) cbbCoAoExport.getModel();
        dcbmChieuDaiTayExport = (DefaultComboBoxModel<ChieuDaiTay>) cbbChieuDaiTayExport.getModel();
        dcbmXuatXuExport = (DefaultComboBoxModel<XuatXu>) cbbXuatXuExport.getModel();

        loadDataComboboxProperties(dcbmThuongHieu, "ThuongHieu");
        loadDataComboboxProperties(dcbmDanhMuc, "DanhMuc");
        loadDataComboboxProperties(dcbmChatLieu, "ChatLieu");
        loadDataComboboxProperties(dcbmMauSac, "MauSac");
        loadDataComboboxProperties(dcbmKichCo, "KichCo");
        loadDataComboboxProperties(dcbmCoAo, "CoAo");
        loadDataComboboxProperties(dcbmChieuDaiTay, "ChieuDaiTay");
        loadDataComboboxProperties(dcbmXuatXu, "XuatXu");

        loadDataComboboxPropertiesExport(dcbmChatLieuExport, "ChatLieu");
        loadDataComboboxPropertiesExport(dcbmMauSacExport, "MauSac");
        loadDataComboboxPropertiesExport(dcbmKichCoExport, "KichCo");
        loadDataComboboxPropertiesExport(dcbmCoAoExport, "CoAo");
        loadDataComboboxPropertiesExport(dcbmChieuDaiTayExport, "ChieuDaiTay");
        loadDataComboboxPropertiesExport(dcbmXuatXuExport, "XuatXu");

        loadDataComboboxSanPham();

        dtmThuocTinh = (DefaultTableModel) tblThuocTinh.getModel();
        dtmSanPham = (DefaultTableModel) tblSanPham.getModel();
        dtmSanPhamChiTiet = (DefaultTableModel) tblSanPhamChiTiet.getModel();
        dtmSanPhamChiTietExport = (DefaultTableModel) tblExportExcel.getModel();

        rdoThuongHieu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showDataProperties();
            }
        });

        rdoDanhMuc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showDataProperties();
            }
        });

        rdoChatLieu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showDataProperties();
            }
        });

        rdoMauSac.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showDataProperties();
            }
        });

        rdoKichCo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showDataProperties();
            }
        });

        rdoCoAo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showDataProperties();
            }
        });

        rdoChieuDaiTay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showDataProperties();
            }
        });

        cbbSanPham.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SanPham sanPhamSelected = (SanPham) cbbSanPham.getSelectedItem();
                loadDataSanPhamChiTietPage(1, sanPhamSelected.getMa());
                paginationSanPhamChiTiet.setPaginationItemRender(new PaginationItemRenderStyle1());
                paginationSanPhamChiTiet.addEventPagination(new EventPagination() {
                    @Override
                    public void pageChanged(int page) {
                        SanPham sanPhamSelected = (SanPham) cbbSanPham.getSelectedItem();
                        loadDataSanPhamChiTietPage(page, sanPhamSelected.getMa());
                    }
                });
            }
        });
        rdoThuongHieu.setSelected(true);
        showDataProperties();

        paginationSanPham.setPaginationItemRender(new PaginationItemRenderStyle1());
        paginationSanPham.addEventPagination(new EventPagination() {
            @Override
            public void pageChanged(int page) {
                loadDataSanPhamPage(page);
            }
        });
        loadDataSanPhamPage(1);

        cbbSanPham.setSelectedIndex(0);

        txtMaSanPham.disable();
        txtMaCTSP.disable();
        txtMaThuocTinh.disable();
        onChangeSearchSanPham();

        cbbMauSacExport.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filterSanPhamChiTietExport();
            }
        });

        cbbKichCoExport.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filterSanPhamChiTietExport();
            }
        });

        cbbChatLieuExport.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filterSanPhamChiTietExport();
            }
        });

        cbbCoAoExport.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filterSanPhamChiTietExport();
            }
        });

        cbbChieuDaiTayExport.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filterSanPhamChiTietExport();
            }
        });

        cbbXuatXuExport.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filterSanPhamChiTietExport();
            }
        });
        cbbMauSacExport.setSelectedIndex(0);

        cbbSanPham.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SanPham sanPham = (SanPham) cbbSanPham.getSelectedItem();
                System.out.println(sanPham.getMa());
                onChangeSearchSanPhamChiTiet(sanPham.getMa());
            }
        });

    }

    private void onChangeSearchSanPham() {
        txtTimKiemSanPham.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                try {
                    if (e.getDocument().getText(0, e.getDocument().getLength()).length() <= 0) {
                        showDataSanPhamWithList(sanPhamService.getAll());
                    } else {
                        String tenSanPham = e.getDocument().getText(0, e.getDocument().getLength());
                        List<SanPham> sanPhamResponse = sanPhamService.getAll();
                        List<SanPham> resultList = new ArrayList<>();
                        if (sanPhamResponse != null) {
                            for (SanPham sanPham : sanPhamResponse) {
                                if (sanPham.getTen().toLowerCase().contains(tenSanPham.toLowerCase())) {
                                    resultList.add(sanPham);
                                }
                            }
                            showDataSanPhamWithList(resultList);
                        }
                    }
                } catch (BadLocationException ex) {
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                showDataSanPhamWithList(sanPhamService.getAll());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                try {
                    if (e.getDocument().getText(0, e.getDocument().getLength()).length() <= 0) {
                        showDataSanPhamWithList(sanPhamService.getAll());
                    } else {
                        String tenSanPham = e.getDocument().getText(0, e.getDocument().getLength());
                        List<SanPham> sanPhamResponse = sanPhamService.getAll();
                        List<SanPham> resultList = new ArrayList<>();
                        if (sanPhamResponse != null) {
                            for (SanPham sanPham : sanPhamResponse) {
                                if (sanPham.getTen().toLowerCase().contains(tenSanPham.toLowerCase())) {
                                    resultList.add(sanPham);
                                }
                            }
                            showDataSanPhamWithList(resultList);
                        }
                    }
                } catch (BadLocationException ex) {
                }
            }
        });
    }

    private <T> void onChangeSearchThuocTinh(List<T> list, Function<T, String> nameExtractor, Function<T, Object[]> mapFunction) {
        txtTimKiemThuocTinh.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                try {
                    if (e.getDocument().getText(0, e.getDocument().getLength()).length() <= 0) {
                        showDataWithList(list, mapFunction);
                    } else {
                        String searchText = e.getDocument().getText(0, e.getDocument().getLength()).toLowerCase();
                        List<T> resultList = new ArrayList<>();
                        for (T item : list) {
                            String itemName = nameExtractor.apply(item).toLowerCase();
                            if (itemName.contains(searchText)) {
                                resultList.add(item);
                            }
                        }
                        showDataWithList(resultList, mapFunction);
                    }
                } catch (BadLocationException ex) {
                    ex.printStackTrace();
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                showDataWithList(list, mapFunction);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                try {
                    if (e.getDocument().getText(0, e.getDocument().getLength()).length() <= 0) {
                        showDataWithList(list, mapFunction);
                    } else {
                        String searchText = e.getDocument().getText(0, e.getDocument().getLength()).toLowerCase();
                        List<T> resultList = new ArrayList<>();
                        for (T item : list) {
                            String itemName = nameExtractor.apply(item).toLowerCase();
                            if (itemName.contains(searchText)) {
                                resultList.add(item);
                            }
                        }
                        showDataWithList(resultList, mapFunction);
                    }
                } catch (BadLocationException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    private <T> void showDataWithList(List<T> list, Function<T, Object[]> mapFunction) {
        dtmThuocTinh.setRowCount(0);

        for (int i = 0; i < list.size(); i++) {
            T item = list.get(i);
            Object[] rowData = mapFunction.apply(item);
            dtmThuocTinh.addRow(new Object[]{
                i + 1,
                rowData[0],
                rowData[1]
            });
        }
    }

    private void onChangeSearchSanPhamChiTiet(String maSanPham) {
        txtTimKiemSanPhamChiTiet.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                try {
                    if (e.getDocument().getText(0, e.getDocument().getLength()).length() <= 0) {
                        List<SanPhamChiTiet> sanPhamChiTiets = sanPhamChiTietService.getAllByIdSanPham(maSanPham);
                        showDataSanPhamChiTietWithList(sanPhamChiTiets);
                    } else {
                        String maCTSP = e.getDocument().getText(0, e.getDocument().getLength());
                        List<SanPhamChiTiet> sanPhamResponse = sanPhamChiTietService.getAllByIdSanPham(maSanPham);
                        List<SanPhamChiTiet> resultList = new ArrayList<>();
                        if (sanPhamResponse != null) {
                            for (SanPhamChiTiet sanPhamChiTiet : sanPhamResponse) {
                                if (sanPhamChiTiet.getMa().toLowerCase().contains(maCTSP.toLowerCase())) {
                                    resultList.add(sanPhamChiTiet);
                                }
                            }
                            showDataSanPhamChiTietWithList(resultList);
                        }
                    }
                } catch (BadLocationException ex) {
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                List<SanPhamChiTiet> sanPhamChiTiets = sanPhamChiTietService.getAllByIdSanPham(maSanPham);
                showDataSanPhamChiTietWithList(sanPhamChiTiets);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                try {
                    if (e.getDocument().getText(0, e.getDocument().getLength()).length() <= 0) {
                        List<SanPhamChiTiet> sanPhamChiTiets = sanPhamChiTietService.getAllByIdSanPham(maSanPham);
                        showDataSanPhamChiTietWithList(sanPhamChiTiets);
                    } else {
                        String maCTSP = e.getDocument().getText(0, e.getDocument().getLength());
                        List<SanPhamChiTiet> sanPhamResponse = sanPhamChiTietService.getAllByIdSanPham(maSanPham);
                        List<SanPhamChiTiet> resultList = new ArrayList<>();
                        if (sanPhamResponse != null) {
                            for (SanPhamChiTiet sanPhamChiTiet : sanPhamResponse) {
                                if (sanPhamChiTiet.getMa().toLowerCase().contains(maCTSP.toLowerCase())) {
                                    resultList.add(sanPhamChiTiet);
                                }
                            }
                            showDataSanPhamChiTietWithList(resultList);
                        }
                    }
                } catch (BadLocationException ex) {
                }
            }
        });
    }

    private void loadDataSanPhamPage(int page) {
        int limit = 10;
        int offset = (page - 1) * limit;
        try {
            int rowCount = sanPhamService.countSanPham();
            int total = (int) Math.ceil((double) rowCount / limit);
            showDataSanPhamWithPage(sanPhamService.getAll(offset, limit));
            paginationSanPham.setPagegination(page, total);
            currentPage = page;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadDataSanPhamChiTietPage(int page, String maSanPham) {
        int limit = 10;
        int offset = (page - 1) * limit;
        try {
            int rowCount = sanPhamChiTietService.countSanPhamChiTiet(maSanPham);
            int total = (int) Math.ceil((double) rowCount / limit);
            showDataSanPhamChiTietWithPage(maSanPham, offset, limit);
            paginationSanPhamChiTiet.setPagegination(page, total);
            currentPage = page;
        } catch (Exception e) {
        }
    }

    private void loadDataComboboxSanPham() {
        dcbmSanPham.removeAllElements();
        for (SanPham sanPham : sanPhamService.getAll()) {
            dcbmSanPham.addElement(sanPham);
        }
    }

    private void loadDataComboboxPropertiesExport(DefaultComboBoxModel<?> model, String object) {
        model.removeAllElements();
        switch (object) {
            case "ChatLieu":
                for (ChatLieu chatLieu : chatLieuService.getAllExport()) {
                    dcbmChatLieuExport.addElement(chatLieu);
                }
                break;
            case "MauSac":
                for (MauSac mauSac : mauSacService.getAllExport()) {
                    dcbmMauSacExport.addElement(mauSac);
                }
                break;
            case "KichCo":
                for (KichCo kichCo : kichCoService.getAllExport()) {
                    dcbmKichCoExport.addElement(kichCo);
                }
                break;
            case "CoAo":
                for (CoAo coAo : coAoService.getAllExport()) {
                    dcbmCoAoExport.addElement(coAo);
                }
                break;
            case "ChieuDaiTay":
                for (ChieuDaiTay chieuDaiTay : chieuDaiTayService.getAllExport()) {
                    dcbmChieuDaiTayExport.addElement(chieuDaiTay);
                }
                break;
            case "XuatXu":
                for (XuatXu xuatXu : xuatXuService.getAllExport()) {
                    dcbmXuatXuExport.addElement(xuatXu);
                }
                break;
            default:
                throw new AssertionError();
        }
    }

    private void filterSanPhamChiTietExport() {
        MauSac mauSac = (MauSac) cbbMauSacExport.getSelectedItem();
        KichCo kickCo = (KichCo) cbbKichCoExport.getSelectedItem();
        ChatLieu chatLieu = (ChatLieu) cbbChatLieuExport.getSelectedItem();
        CoAo coAo = (CoAo) cbbCoAoExport.getSelectedItem();
        ChieuDaiTay chieuDaiTay = (ChieuDaiTay) cbbChieuDaiTayExport.getSelectedItem();
        XuatXu xuatXu = (XuatXu) cbbXuatXuExport.getSelectedItem();
        dtmSanPhamChiTietExport.setRowCount(0);
        for (SanPhamChiTiet sanPhamChiTiet : sanPhamChiTietService.filterSanPhamChiTietExport(mauSac.getId(), kickCo.getId(), chatLieu.getId(), coAo.getId(), chieuDaiTay.getId(), xuatXu.getId())) {
            dtmSanPhamChiTietExport.addRow(new Object[]{
                sanPhamChiTiet.getMa(),
                sanPhamChiTiet.getTenSanPham(),
                sanPhamChiTiet.getSoLuong(),
                sanPhamChiTiet.getGiaBan(),
                sanPhamChiTiet.getTenMauSac(),
                sanPhamChiTiet.getTenKichCo(),
                sanPhamChiTiet.getTenChatLieu(),
                sanPhamChiTiet.getTenCoAo(),
                sanPhamChiTiet.getTenChieuDaiTay(),
                sanPhamChiTiet.getTenXuatXu(),
                sanPhamChiTiet.isTrangThai() ? "Ngừng bán" : "Đang bán"
            });
        }
    }

    private void loadDataComboboxProperties(DefaultComboBoxModel<?> model, String object) {
        model.removeAllElements();
        switch (object) {
            case "ThuongHieu":
                for (ThuongHieu thuongHieu : thuongHieuService.getAll()) {
                    dcbmThuongHieu.addElement(thuongHieu);
                }
                break;
            case "DanhMuc":
                for (DanhMuc danhMuc : danhMucService.getAll()) {
                    dcbmDanhMuc.addElement(danhMuc);
                }
                break;
            case "ChatLieu":
                for (ChatLieu chatLieu : chatLieuService.getAll()) {
                    dcbmChatLieu.addElement(chatLieu);
                }
                break;
            case "MauSac":
                for (MauSac mauSac : mauSacService.getAll()) {
                    dcbmMauSac.addElement(mauSac);
                }
                break;
            case "KichCo":
                for (KichCo kichCo : kichCoService.getAll()) {
                    dcbmKichCo.addElement(kichCo);
                }
                break;
            case "CoAo":
                for (CoAo coAo : coAoService.getAll()) {
                    dcbmCoAo.addElement(coAo);
                }
                break;
            case "ChieuDaiTay":
                for (ChieuDaiTay chieuDaiTay : chieuDaiTayService.getAll()) {
                    dcbmChieuDaiTay.addElement(chieuDaiTay);
                }
                break;
            case "XuatXu":
                for (XuatXu xuatXu : xuatXuService.getAll()) {
                    dcbmXuatXu.addElement(xuatXu);
                }
                break;
            default:
                throw new AssertionError();
        }
    }

    private void showDataProperties() {
        if (rdoThuongHieu.isSelected()) {
            txtMaThuocTinh.setText("");
            txtTenThuocTinh.setText("");
            dtmThuocTinh.setRowCount(0);
            for (int i = 0; i < thuongHieuService.getAll().size(); i++) {
                ThuongHieu thuongHieu = thuongHieuService.getAll().get(i);
                dtmThuocTinh.addRow(new Object[]{
                    i + 1,
                    thuongHieu.getMa(),
                    thuongHieu.getTen()
                });
            }
        } else if (rdoDanhMuc.isSelected()) {
            txtMaThuocTinh.setText("");
            txtTenThuocTinh.setText("");
            dtmThuocTinh.setRowCount(0);
            for (int i = 0; i < danhMucService.getAll().size(); i++) {
                DanhMuc danhMuc = danhMucService.getAll().get(i);
                dtmThuocTinh.addRow(new Object[]{
                    i + 1,
                    danhMuc.getMa(),
                    danhMuc.getTen()
                });
            }
        } else if (rdoChatLieu.isSelected()) {
            txtMaThuocTinh.setText("");
            txtTenThuocTinh.setText("");
            dtmThuocTinh.setRowCount(0);
            for (int i = 0; i < chatLieuService.getAll().size(); i++) {
                ChatLieu chatLieu = chatLieuService.getAll().get(i);
                dtmThuocTinh.addRow(new Object[]{
                    i + 1,
                    chatLieu.getMa(),
                    chatLieu.getTen()
                });
            }
        } else if (rdoMauSac.isSelected()) {
            txtMaThuocTinh.setText("");
            txtTenThuocTinh.setText("");
            dtmThuocTinh.setRowCount(0);
            for (int i = 0; i < mauSacService.getAll().size(); i++) {
                MauSac mauSac = mauSacService.getAll().get(i);
                dtmThuocTinh.addRow(new Object[]{
                    i + 1,
                    mauSac.getMa(),
                    mauSac.getTen()
                });
            }
        } else if (rdoKichCo.isSelected()) {
            txtMaThuocTinh.setText("");
            txtTenThuocTinh.setText("");
            dtmThuocTinh.setRowCount(0);
            for (int i = 0; i < kichCoService.getAll().size(); i++) {
                KichCo kichCo = kichCoService.getAll().get(i);
                dtmThuocTinh.addRow(new Object[]{
                    i + 1,
                    kichCo.getMa(),
                    kichCo.getTen()
                });
            }
        } else if (rdoCoAo.isSelected()) {
            txtMaThuocTinh.setText("");
            txtTenThuocTinh.setText("");
            dtmThuocTinh.setRowCount(0);
            for (int i = 0; i < coAoService.getAll().size(); i++) {
                CoAo coAo = coAoService.getAll().get(i);
                dtmThuocTinh.addRow(new Object[]{
                    i + 1,
                    coAo.getMa(),
                    coAo.getTen()
                });
            }
        } else if (rdoChieuDaiTay.isSelected()) {
            txtMaThuocTinh.setText("");
            txtTenThuocTinh.setText("");
            dtmThuocTinh.setRowCount(0);
            for (int i = 0; i < chieuDaiTayService.getAll().size(); i++) {
                ChieuDaiTay chieuDaiTay = chieuDaiTayService.getAll().get(i);
                dtmThuocTinh.addRow(new Object[]{
                    i + 1,
                    chieuDaiTay.getMa(),
                    chieuDaiTay.getTen()
                });
            }
        }
    }

    private void showDataSanPhamWithList(List<SanPham> listSanPham) {
        dtmSanPham.setRowCount(0);
        for (SanPham sanPham : listSanPham) {
            dtmSanPham.addRow(new Object[]{
                sanPham.getMa(),
                sanPham.getTen(),
                sanPham.getSoLuong(),
                sanPham.getTenThuongHieu(),
                sanPham.getTenDanhMuc()
            });
        }
    }

    private void showDataSanPhamWithPage(List<SanPham> listSanPham) {
        dtmSanPham.setRowCount(0);
        for (SanPham sanPham : listSanPham) {
            dtmSanPham.addRow(new Object[]{
                sanPham.getMa(),
                sanPham.getTen(),
                sanPham.getSoLuong(),
                sanPham.getTenThuongHieu(),
                sanPham.getTenDanhMuc()
            });
        }
    }

    private void showDataSanPhamChiTietWithList(List<SanPhamChiTiet> sanPhamChiTietList) {
        dtmSanPhamChiTiet.setRowCount(0);

        for (SanPhamChiTiet sanPhamChiTiet : sanPhamChiTietList) {
            dtmSanPhamChiTiet.addRow(new Object[]{
                sanPhamChiTiet.getMa(),
                sanPhamChiTiet.getTenSanPham(),
                sanPhamChiTiet.getSoLuong(),
                sanPhamChiTiet.getGiaBan(),
                sanPhamChiTiet.getTenMauSac(),
                sanPhamChiTiet.getTenKichCo(),
                sanPhamChiTiet.getTenChatLieu(),
                sanPhamChiTiet.getTenCoAo(),
                sanPhamChiTiet.getTenChieuDaiTay(),
                sanPhamChiTiet.getTenXuatXu()
            });
        }
    }

//    private void showDataSanPhamChiTietExport() {
//        dtmSanPhamChiTietExport.setRowCount(0);
//        for (SanPhamChiTiet sanPhamChiTiet : sanPhamChiTietService.getAllExportExcel()) {
//            dtmSanPhamChiTietExport.addRow(new Object[]{
//                sanPhamChiTiet.getMa(),
//                sanPhamChiTiet.getTenSanPham(),
//                sanPhamChiTiet.getSoLuong(),
//                sanPhamChiTiet.getGiaBan(),
//                sanPhamChiTiet.getTenMauSac(),
//                sanPhamChiTiet.getTenKichCo(),
//                sanPhamChiTiet.getTenChatLieu(),
//                sanPhamChiTiet.getTenCoAo(),
//                sanPhamChiTiet.getTenChieuDaiTay(),
//                sanPhamChiTiet.getTenXuatXu(),
//                sanPhamChiTiet.isTrangThai() ? "Đang bán" : "Ngừng bán"
//            });
//        }
//    }
    private void showDataSanPhamChiTiet(String maSanPham) {
        dtmSanPhamChiTiet.setRowCount(0);
        for (SanPhamChiTiet sanPhamChiTiet : sanPhamChiTietService.getAllByIdSanPham(maSanPham)) {
            dtmSanPhamChiTiet.addRow(new Object[]{
                sanPhamChiTiet.getMa(),
                sanPhamChiTiet.getTenSanPham(),
                sanPhamChiTiet.getSoLuong(),
                sanPhamChiTiet.getGiaBan(),
                sanPhamChiTiet.getTenMauSac(),
                sanPhamChiTiet.getTenKichCo(),
                sanPhamChiTiet.getTenChatLieu(),
                sanPhamChiTiet.getTenCoAo(),
                sanPhamChiTiet.getTenChieuDaiTay(),
                sanPhamChiTiet.getTenXuatXu()
            });
        }
    }

    private void showDataSanPhamChiTietWithPage(String maSanPham, int offset, int limit) {
        dtmSanPhamChiTiet.setRowCount(0);
        for (SanPhamChiTiet sanPhamChiTiet : sanPhamChiTietService.getAllByIdSanPham(maSanPham, offset, limit)) {
            dtmSanPhamChiTiet.addRow(new Object[]{
                sanPhamChiTiet.getMa(),
                sanPhamChiTiet.getTenSanPham(),
                sanPhamChiTiet.getSoLuong(),
                sanPhamChiTiet.getGiaBan(),
                sanPhamChiTiet.getTenMauSac(),
                sanPhamChiTiet.getTenKichCo(),
                sanPhamChiTiet.getTenChatLieu(),
                sanPhamChiTiet.getTenCoAo(),
                sanPhamChiTiet.getTenChieuDaiTay(),
                sanPhamChiTiet.getTenXuatXu()
            });
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

    private ThuongHieu addThuongHieu() {
        ThuongHieu thuongHieu = null;
        String maThuocTinh = generateRandomMa("TT");
        String tenThuocTinh = txtTenThuocTinh.getText();
        if (isCheckEmpty(tenThuocTinh)) {
            JOptionPane.showMessageDialog(this, "Tên thương hiệu không được để trống!");
            return null;
        }
        if (thuongHieuService.isThuocTinhExists(tenThuocTinh)) {
            JOptionPane.showMessageDialog(this, "Thương hiệu đã tồn tại!");
            return null;
        }
        thuongHieu = new ThuongHieu(maThuocTinh, tenThuocTinh);
        return thuongHieu;
    }

    private ThuongHieu addNhanhThuongHieu() {
        ThuongHieu thuongHieu = null;
        String maThuocTinh = generateRandomMa("TT");
        String tenThuocTinh = txtTenThuongHieuThemNhanh.getText();
        if (isCheckEmpty(tenThuocTinh)) {
            JOptionPane.showMessageDialog(this, "Tên thương hiệu không được để trống!");
            return null;
        }
        if (thuongHieuService.isThuocTinhExists(tenThuocTinh)) {
            JOptionPane.showMessageDialog(this, "Thương hiệu đã tồn tại!");
            return null;
        }
        thuongHieu = new ThuongHieu(maThuocTinh, tenThuocTinh);
        return thuongHieu;
    }

    private ThuongHieu updateThuongHieu() {
        String ma = txtMaThuocTinh.getText();
        String ten = txtTenThuocTinh.getText().trim();
        if (isCheckEmpty(ten)) {
            JOptionPane.showMessageDialog(this, "Tên thương hiệu không được để trống!");
            return null;
        }
        if (thuongHieuService.isThuocTinhExists(ten)) {
            JOptionPane.showMessageDialog(this, "Thương hiệu đã tồn tại!");
            return null;
        }
        return new ThuongHieu(ma, ten);
    }

    private DanhMuc addDanhMuc() {
        DanhMuc danhMuc = null;
        String maThuocTinh = generateRandomMa("TT");
        String tenThuocTinh = txtTenThuocTinh.getText();
        if (isCheckEmpty(tenThuocTinh)) {
            JOptionPane.showMessageDialog(this, "Tên danh mục không được để trống!");
            return null;
        }
        if (danhMucService.isThuocTinhExists(tenThuocTinh)) {
            JOptionPane.showMessageDialog(this, "Danh mục đã tồn tại!");
            return null;
        }
        danhMuc = new DanhMuc(maThuocTinh, tenThuocTinh);
        return danhMuc;
    }

    private DanhMuc addNhanhDanhMuc() {
        DanhMuc danhMuc = null;
        String maThuocTinh = generateRandomMa("TT");
        String tenThuocTinh = txtTenDanhMucThemNhanh.getText();
        if (isCheckEmpty(tenThuocTinh)) {
            JOptionPane.showMessageDialog(this, "Tên danh mục không được để trống!");
            return null;
        }
        if (danhMucService.isThuocTinhExists(tenThuocTinh)) {
            JOptionPane.showMessageDialog(this, "Danh mục đã tồn tại!");
            return null;
        }
        danhMuc = new DanhMuc(maThuocTinh, tenThuocTinh);
        return danhMuc;
    }

    private DanhMuc updateDanhMuc() {
        String ma = txtMaThuocTinh.getText();
        String ten = txtTenThuocTinh.getText().trim();
        if (isCheckEmpty(ten)) {
            JOptionPane.showMessageDialog(this, "Tên danh mục không được để trống!");
            return null;
        }
        if (danhMucService.isThuocTinhExists(ten)) {
            JOptionPane.showMessageDialog(this, "Danh mục đã tồn tại!");
            return null;
        }
        return new DanhMuc(ma, ten);
    }

    private ChatLieu addChatLieu() {
        ChatLieu chatLieu = null;
        String maThuocTinh = generateRandomMa("TT");
        String tenThuocTinh = txtTenThuocTinh.getText();
        if (isCheckEmpty(tenThuocTinh)) {
            JOptionPane.showMessageDialog(this, "Tên chất liệu không được để trống!");
            return null;
        }
        if (chatLieuService.isThuocTinhExists(tenThuocTinh)) {
            JOptionPane.showMessageDialog(this, "Chất liệu đã tồn tại!");
            return null;
        }
        chatLieu = new ChatLieu(maThuocTinh, tenThuocTinh);
        return chatLieu;
    }

    private ChatLieu addNhanhChatLieu() {
        ChatLieu chatLieu = null;
        String maThuocTinh = generateRandomMa("TT");
        String tenThuocTinh = txtTenChatLieuThemNhanh.getText();
        if (isCheckEmpty(tenThuocTinh)) {
            JOptionPane.showMessageDialog(this, "Tên chất liệu không được để trống!");
            return null;
        }
        if (chatLieuService.isThuocTinhExists(tenThuocTinh)) {
            JOptionPane.showMessageDialog(this, "Chất liệu đã tồn tại!");
            return null;
        }
        chatLieu = new ChatLieu(maThuocTinh, tenThuocTinh);
        return chatLieu;
    }

    private ChatLieu updateChatLieu() {
        String ma = txtMaThuocTinh.getText();
        String ten = txtTenThuocTinh.getText().trim();
        if (isCheckEmpty(ten)) {
            JOptionPane.showMessageDialog(this, "Tên chất liệu không được để trống!");
            return null;
        }
        if (chatLieuService.isThuocTinhExists(ten)) {
            JOptionPane.showMessageDialog(this, "Chất liệu đã tồn tại!");
            return null;
        }
        return new ChatLieu(ma, ten);
    }

    private MauSac addMauSac() {
        MauSac mauSac = null;
        String maThuocTinh = generateRandomMa("TT");
        String tenThuocTinh = txtTenThuocTinh.getText();
        if (isCheckEmpty(tenThuocTinh)) {
            JOptionPane.showMessageDialog(this, "Tên màu sắc không được để trống!");
            return null;
        }
        if (mauSacService.isThuocTinhExists(tenThuocTinh)) {
            JOptionPane.showMessageDialog(this, "Màu sắc đã tồn tại!");
            return null;
        }
        mauSac = new MauSac(maThuocTinh, tenThuocTinh);
        return mauSac;
    }

    private MauSac addNhanhMauSac() {
        MauSac mauSac = null;
        String maThuocTinh = generateRandomMa("TT");
        String tenThuocTinh = txtTenMauSacThemNhanh.getText();
        if (isCheckEmpty(tenThuocTinh)) {
            JOptionPane.showMessageDialog(this, "Tên màu sắc không được để trống!");
            return null;
        }
        if (mauSacService.isThuocTinhExists(tenThuocTinh)) {
            JOptionPane.showMessageDialog(this, "Màu sắc đã tồn tại!");
            return null;
        }
        mauSac = new MauSac(maThuocTinh, tenThuocTinh);
        return mauSac;
    }

    private MauSac updateMauSac() {
        String ma = txtMaThuocTinh.getText();
        String ten = txtTenThuocTinh.getText().trim();
        if (isCheckEmpty(ten)) {
            JOptionPane.showMessageDialog(this, "Tên màu sắc không được để trống!");
            return null;
        }
        if (mauSacService.isThuocTinhExists(ten)) {
            JOptionPane.showMessageDialog(this, "Màu sắc đã tồn tại!");
            return null;
        }
        return new MauSac(ma, ten);
    }

    private KichCo addKichCo() {
        KichCo kichCo = null;
        String maThuocTinh = generateRandomMa("TT");
        String tenThuocTinh = txtTenThuocTinh.getText();
        if (isCheckEmpty(tenThuocTinh)) {
            JOptionPane.showMessageDialog(this, "Tên kích cỡ không được để trống!");
            return null;
        }
        if (kichCoService.isThuocTinhExists(tenThuocTinh)) {
            JOptionPane.showMessageDialog(this, "Kích cỡ đã tồn tại!");
            return null;
        }
        kichCo = new KichCo(maThuocTinh, tenThuocTinh);
        return kichCo;
    }

    private KichCo addNhanhKichCo() {
        KichCo kichCo = null;
        String maThuocTinh = generateRandomMa("TT");
        String tenThuocTinh = txtTenKichCoThemNhanh.getText();
        if (isCheckEmpty(tenThuocTinh)) {
            JOptionPane.showMessageDialog(this, "Tên kích cỡ không được để trống!");
            return null;
        }
        if (kichCoService.isThuocTinhExists(tenThuocTinh)) {
            JOptionPane.showMessageDialog(this, "Kích cỡ đã tồn tại!");
            return null;
        }
        kichCo = new KichCo(maThuocTinh, tenThuocTinh);
        return kichCo;
    }

    private KichCo updateKichCo() {
        String ma = txtMaThuocTinh.getText();
        String ten = txtTenThuocTinh.getText().trim();
        if (isCheckEmpty(ten)) {
            JOptionPane.showMessageDialog(this, "Tên kích cỡ không được để trống!");
            return null;
        }
        if (kichCoService.isThuocTinhExists(ten)) {
            JOptionPane.showMessageDialog(this, "Kích cỡ đã tồn tại!");
            return null;
        }
        return new KichCo(ma, ten);
    }

    private CoAo addCoAo() {
        CoAo coAo = null;
        String maThuocTinh = generateRandomMa("TT");
        String tenThuocTinh = txtTenThuocTinh.getText();
        if (isCheckEmpty(tenThuocTinh)) {
            JOptionPane.showMessageDialog(this, "Tên cổ áo không được để trống!");
            return null;
        }
        if (coAoService.isThuocTinhExists(tenThuocTinh)) {
            JOptionPane.showMessageDialog(this, "Cổ áo đã tồn tại!");
            return null;
        }
        coAo = new CoAo(maThuocTinh, tenThuocTinh);
        return coAo;
    }

    private CoAo addNhanhCoAo() {
        CoAo coAo = null;
        String maThuocTinh = generateRandomMa("TT");
        String tenThuocTinh = txtTenCoAoThemNhanh.getText();
        if (isCheckEmpty(tenThuocTinh)) {
            JOptionPane.showMessageDialog(this, "Tên cổ áo không được để trống!");
            return null;
        }
        if (coAoService.isThuocTinhExists(tenThuocTinh)) {
            JOptionPane.showMessageDialog(this, "Cổ áo đã tồn tại!");
            return null;
        }
        coAo = new CoAo(maThuocTinh, tenThuocTinh);
        return coAo;
    }

    private CoAo updateCoAo() {
        String ma = txtMaThuocTinh.getText();
        String ten = txtTenThuocTinh.getText().trim();
        if (isCheckEmpty(ten)) {
            JOptionPane.showMessageDialog(this, "Tên cổ áo không được để trống!");
            return null;
        }
        if (coAoService.isThuocTinhExists(ten)) {
            JOptionPane.showMessageDialog(this, "Cổ áo đã tồn tại!");
            return null;
        }
        return new CoAo(ma, ten);
    }

    private ChieuDaiTay addChieuDaiTay() {
        ChieuDaiTay chieuDaiTay = null;
        String maThuocTinh = generateRandomMa("TT");
        String tenThuocTinh = txtTenThuocTinh.getText();
        if (isCheckEmpty(tenThuocTinh)) {
            JOptionPane.showMessageDialog(this, "Tên chiều dài tay không được để trống!");
            return null;
        }
        if (chieuDaiTayService.isThuocTinhExists(tenThuocTinh)) {
            JOptionPane.showMessageDialog(this, "Chiều dài tay đã tồn tại!");
            return null;
        }
        chieuDaiTay = new ChieuDaiTay(maThuocTinh, tenThuocTinh);
        return chieuDaiTay;
    }

    private ChieuDaiTay addNhanhChieuDaiTay() {
        ChieuDaiTay chieuDaiTay = null;
        String maThuocTinh = generateRandomMa("TT");
        String tenThuocTinh = txtTenChieuDaiTayThemNhanh.getText();
        if (isCheckEmpty(tenThuocTinh)) {
            JOptionPane.showMessageDialog(this, "Tên chiều dài tay không được để trống!");
            return null;
        }
        if (chieuDaiTayService.isThuocTinhExists(tenThuocTinh)) {
            JOptionPane.showMessageDialog(this, "Chiều dài tay đã tồn tại!");
            return null;
        }
        chieuDaiTay = new ChieuDaiTay(maThuocTinh, tenThuocTinh);
        return chieuDaiTay;
    }

    private ChieuDaiTay updateChieuDaiTay() {
        String ma = txtMaThuocTinh.getText();
        String ten = txtTenThuocTinh.getText().trim();
        if (isCheckEmpty(ten)) {
            JOptionPane.showMessageDialog(this, "Tên chiều dài tay không được để trống!");
            return null;
        }
        if (chieuDaiTayService.isThuocTinhExists(ten)) {
            JOptionPane.showMessageDialog(this, "Chiều dài tay đã tồn tại!");
            return null;
        }
        return new ChieuDaiTay(ma, ten);
    }

    private SanPham addSanPham() {
        String ten = txtTenSanPham.getText().trim();
        ThuongHieu thuongHieuSelected = (ThuongHieu) dcbmThuongHieu.getSelectedItem();
        DanhMuc danhMucSelected = (DanhMuc) dcbmDanhMuc.getSelectedItem();
        if (isCheckEmpty(ten)) {
            JOptionPane.showMessageDialog(this, "Tên sản phẩm không được để trống!");
            return null;
        }
        if (sanPhamService.isSanPhamExists(ten, thuongHieuSelected.getId(), danhMucSelected.getId())) {
            JOptionPane.showMessageDialog(this, "Sản phẩm đã tồn tại!");
            return null;
        }
        SanPham sanPham = null;
        String ma = generateRandomMa("SP");
        sanPham = new SanPham(ma, ten, thuongHieuSelected.getId(), danhMucSelected.getId());
        return sanPham;
    }

    private SanPham updateSanPham() {
        String ma = txtMaSanPham.getText();
        String ten = txtTenSanPham.getText().trim();
        ThuongHieu thuongHieuSelected = (ThuongHieu) dcbmThuongHieu.getSelectedItem();
        DanhMuc danhMucSelected = (DanhMuc) dcbmDanhMuc.getSelectedItem();
        if (isCheckEmpty(ten)) {
            JOptionPane.showMessageDialog(this, "Tên sản phẩm không được để trống!");
            return null;
        }
        if (sanPhamService.isSanPhamExists(ten, thuongHieuSelected.getId(), danhMucSelected.getId())) {
            JOptionPane.showMessageDialog(this, "Sản phẩm đã tồn tại!");
            return null;
        }
        SanPham sanPham = null;
        sanPham = new SanPham(ma, ten, thuongHieuSelected.getId(), danhMucSelected.getId());

        return sanPham;
    }

    private SanPhamChiTiet addSanPhamChiTiet() {
        SanPhamChiTiet sanPhamChiTiet = null;
        SanPham sanPhamSelected = (SanPham) dcbmSanPham.getSelectedItem();
        ChatLieu chatLieuSelected = (ChatLieu) dcbmChatLieu.getSelectedItem();
        CoAo coAoSelected = (CoAo) dcbmCoAo.getSelectedItem();
        MauSac mauSacSelected = (MauSac) dcbmMauSac.getSelectedItem();
        KichCo kichCoSelected = (KichCo) dcbmKichCo.getSelectedItem();
        ChieuDaiTay chieuDaiTaySelected = (ChieuDaiTay) dcbmChieuDaiTay.getSelectedItem();
        XuatXu xuatXu = (XuatXu) dcbmXuatXu.getSelectedItem();
        String maCTSP = generateRandomMaSPCT("SPCT");
        String soLuong = txtSoLuong.getText();
        String giaBan = txtGiaBan.getText();
        String moTa = txtMoTa.getText();
        if (isCheckEmpty(soLuong)) {
            JOptionPane.showMessageDialog(this, "Số lượng không được để trống");
            return null;
        }
        if (isCheckEmpty(giaBan)) {
            JOptionPane.showMessageDialog(this, "Giá bán không được để trống");
            return null;
        }
        if (!isPositiveNumber(soLuong)) {
            JOptionPane.showMessageDialog(this, "Số lượng nhập vào sai định dạng!");
            return null;
        }
        if (!isPositiveFloat(giaBan)) {
            JOptionPane.showMessageDialog(this, "Giá bán nhập vào sai định dạng!");
            return null;
        }
        if (sanPhamChiTietService.isSanPhamChiTietExists(sanPhamSelected.getId(), kichCoSelected.getId(),
                mauSacSelected.getId(), chatLieuSelected.getId(), coAoSelected.getId(),
                chieuDaiTaySelected.getId(), xuatXu.getId())) {
            JOptionPane.showMessageDialog(this, "Sản phẩm đã tồn tại!");
            return null;
        }
        sanPhamChiTiet = new SanPhamChiTiet(maCTSP,
                sanPhamSelected.getId(),
                chatLieuSelected.getId(),
                chieuDaiTaySelected.getId(),
                kichCoSelected.getId(),
                mauSacSelected.getId(),
                coAoSelected.getId(),
                xuatXu.getId(),
                Integer.parseInt(soLuong),
                Double.parseDouble(giaBan),
                moTa);
        doGenerate(maCTSP, sanPhamSelected.getTen());
        return sanPhamChiTiet;
    }

    private SanPhamChiTiet updateSanPhamChiTiet() {
        SanPhamChiTiet sanPhamChiTiet = null;
        String ma = txtMaCTSP.getText();
        String soLuong = txtSoLuong.getText();
        String giaBan = txtGiaBan.getText();
        String moTa = txtMoTa.getText();
        if (isCheckEmpty(soLuong)) {
            JOptionPane.showMessageDialog(this, "Số lượng không được để trống");
            return null;
        }
        if (isCheckEmpty(giaBan)) {
            JOptionPane.showMessageDialog(this, "Giá bán không được để trống");
            return null;
        }
        if (!isPositiveNumber(soLuong)) {
            JOptionPane.showMessageDialog(this, "Số lượng nhập vào sai định dạng!");
            return null;
        }
        if (!isPositiveFloat(giaBan)) {
            JOptionPane.showMessageDialog(this, "Giá bán nhập vào sai định dạng!");
            return null;
        }
        sanPhamChiTiet = new SanPhamChiTiet(ma, Integer.parseInt(soLuong), Double.parseDouble(giaBan), moTa);
        return sanPhamChiTiet;
    }

    private void fillDataSanPham(SanPham sanPham) {
        txtMaSanPham.setText(sanPham.getMa());
        txtTenSanPham.setText(sanPham.getTen());
        dcbmDanhMuc.setSelectedItem(sanPham.getTenDanhMuc());
        dcbmThuongHieu.setSelectedItem(sanPham.getTenThuongHieu());
    }

    private void fillDataThuongHieu(ThuongHieu thuongHieu) {
        txtMaThuocTinh.setText(thuongHieu.getMa());
        txtTenThuocTinh.setText(thuongHieu.getTen());
    }

    private void fillDataChatLieu(ChatLieu chatLieu) {
        txtMaThuocTinh.setText(chatLieu.getMa());
        txtTenThuocTinh.setText(chatLieu.getTen());
    }

    private void fillDataChieuDaiTay(ChieuDaiTay chieuDaiTay) {
        txtMaThuocTinh.setText(chieuDaiTay.getMa());
        txtTenThuocTinh.setText(chieuDaiTay.getTen());
    }

    private void fillDataCoAo(CoAo coAo) {
        txtMaThuocTinh.setText(coAo.getMa());
        txtTenThuocTinh.setText(coAo.getTen());
    }

    private void fillDataDanhMuc(DanhMuc danhMuc) {
        txtMaThuocTinh.setText(danhMuc.getMa());
        txtTenThuocTinh.setText(danhMuc.getTen());
    }

    private void fillDataKichCo(KichCo kichCo) {
        txtMaThuocTinh.setText(kichCo.getMa());
        txtTenThuocTinh.setText(kichCo.getTen());
    }

    private void fillDataMauSac(MauSac mauSac) {
        txtMaThuocTinh.setText(mauSac.getMa());
        txtTenThuocTinh.setText(mauSac.getTen());
    }

    public <T> T searchPropertiesById(int id, List<T> list) {
        for (T item : list) {
            if (item instanceof SanPham) {
                if (((SanPham) item).getId() == id) {
                    return item;
                }
            } else if (item instanceof ChatLieu) {
                if (((ChatLieu) item).getId() == id) {
                    return item;
                }
            } else if (item instanceof CoAo) {
                if (((CoAo) item).getId() == id) {
                    return item;
                }
            } else if (item instanceof MauSac) {
                if (((MauSac) item).getId() == id) {
                    return item;
                }
            } else if (item instanceof ChieuDaiTay) {
                if (((ChieuDaiTay) item).getId() == id) {
                    return item;
                }
            } else if (item instanceof XuatXu) {
                if (((XuatXu) item).getId() == id) {
                    return item;
                }
            } else if (item instanceof KichCo) {
                if (((KichCo) item).getId() == id) {
                    return item;
                }
            }
        }
        return null;
    }

    public void fillDataSanPhamChiTiet(SanPhamChiTiet sanPhamChiTiet) {
        cbbSanPham.setSelectedItem(searchPropertiesById(sanPhamChiTiet.getIdSanPham(), sanPhamService.getAll()));
        cbbChatLieu.setSelectedItem(searchPropertiesById(sanPhamChiTiet.getIdChatLieu(), chatLieuService.getAll()));
        cbbCoAo.setSelectedItem(searchPropertiesById(sanPhamChiTiet.getIdCoAo(), coAoService.getAll()));
        cbbMauSac.setSelectedItem(searchPropertiesById(sanPhamChiTiet.getIdMauSac(), mauSacService.getAll()));
        cbbKichCo.setSelectedItem(searchPropertiesById(sanPhamChiTiet.getIdKichCo(), kichCoService.getAll()));
        cbbChieuDaiTay.setSelectedItem(searchPropertiesById(sanPhamChiTiet.getIdChieuDaiTay(), chieuDaiTayService.getAll()));
        cbbXuatXu.setSelectedItem(searchPropertiesById(sanPhamChiTiet.getIdXuatXu(), xuatXuService.getAll()));
        txtMaCTSP.setText(sanPhamChiTiet.getMa());
        txtSoLuong.setText(sanPhamChiTiet.getSoLuong() + "");
        txtGiaBan.setText(sanPhamChiTiet.getGiaBan() + "");
        txtMoTa.setText(sanPhamChiTiet.getMoTa());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        rdoThuocTinh = new javax.swing.ButtonGroup();
        jDialogThemNhanhKichCo = new javax.swing.JDialog();
        jPanel25 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        txtTenKichCoThemNhanh = new textfield.TextField2();
        btnKichCoThemNhanh = new app.view.swing.ButtonGradient();
        btnKichCoLamMoiThemNhanh = new app.view.swing.ButtonGradient();
        jDialogThemNhanhMauSac = new javax.swing.JDialog();
        jPanel26 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        txtTenMauSacThemNhanh = new textfield.TextField2();
        btnMauSacThemNhanh = new app.view.swing.ButtonGradient();
        btnMauSacLamMoiThemNhanh = new app.view.swing.ButtonGradient();
        jDialogThemNhanhChatLieu = new javax.swing.JDialog();
        jPanel27 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        txtTenChatLieuThemNhanh = new textfield.TextField2();
        btnChatLieuThemNhanh = new app.view.swing.ButtonGradient();
        btnChatLieuLamMoiThemNhanh = new app.view.swing.ButtonGradient();
        jDialogThemNhanhCoAo = new javax.swing.JDialog();
        jPanel28 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        txtTenCoAoThemNhanh = new textfield.TextField2();
        btnCoAoThemNhanh = new app.view.swing.ButtonGradient();
        btnCoAoLamMoiThemNhanh = new app.view.swing.ButtonGradient();
        jDialogThemNhanhChieuDaiTay = new javax.swing.JDialog();
        jPanel29 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        txtTenChieuDaiTayThemNhanh = new textfield.TextField2();
        btnChieuDaiTayThemNhanh = new app.view.swing.ButtonGradient();
        btnChieuDaiTayLamMoiThemNhanh = new app.view.swing.ButtonGradient();
        jDialogThemNhanhThuongHieu = new javax.swing.JDialog();
        jPanel20 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        txtTenThuongHieuThemNhanh = new textfield.TextField2();
        btnThuongHieuThemNhanh = new app.view.swing.ButtonGradient();
        btnThuongHieuLamMoiThemNhanh = new app.view.swing.ButtonGradient();
        jDialogThemNhanhDanhMuc = new javax.swing.JDialog();
        jPanel30 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        txtTenDanhMucThemNhanh = new textfield.TextField2();
        btnDanhMucThemNhanh = new app.view.swing.ButtonGradient();
        btnDanhMucLamMoiThemNhanh = new app.view.swing.ButtonGradient();
        jDialogExportExcel = new javax.swing.JDialog();
        jPanel31 = new javax.swing.JPanel();
        tabbedPaneCustom2 = new app.view.swing.TabbedPaneCustom();
        jPanel32 = new javax.swing.JPanel();
        jPanel33 = new javax.swing.JPanel();
        jPanel34 = new javax.swing.JPanel();
        scrollPaneWin114 = new custome_ui.swing.ScrollPaneWin11();
        tblExportExcel = new rojeru_san.complementos.RSTableMetro();
        jLabel13 = new javax.swing.JLabel();
        textField26 = new textfield.TextField2();
        jLabel27 = new javax.swing.JLabel();
        cbbMauSacExport = new app.view.swing.ComboBoxSuggestion();
        jLabel28 = new javax.swing.JLabel();
        cbbKichCoExport = new app.view.swing.ComboBoxSuggestion();
        jLabel31 = new javax.swing.JLabel();
        cbbChatLieuExport = new app.view.swing.ComboBoxSuggestion();
        jLabel33 = new javax.swing.JLabel();
        cbbCoAoExport = new app.view.swing.ComboBoxSuggestion();
        jLabel34 = new javax.swing.JLabel();
        cbbChieuDaiTayExport = new app.view.swing.ComboBoxSuggestion();
        btnExport = new app.view.swing.ButtonGradient();
        jLabel35 = new javax.swing.JLabel();
        cbbXuatXuExport = new app.view.swing.ComboBoxSuggestion();
        jPanel1 = new javax.swing.JPanel();
        tabbedPaneCustom1 = new app.view.swing.TabbedPaneCustom();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        txtMaSanPham = new textfield.TextField2();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtTenSanPham = new textfield.TextField2();
        jPanel6 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        btnThemNhanhDanhMuc = new app.view.swing.Button();
        btnThemNhanhThuongHieu = new app.view.swing.Button();
        cbbThuongHieu = new app.view.swing.ComboBoxSuggestion();
        cbbDanhMuc = new app.view.swing.ComboBoxSuggestion();
        jPanel7 = new javax.swing.JPanel();
        btnThemSanPham = new app.view.swing.ButtonGradient();
        btnSuaSanPham = new app.view.swing.ButtonGradient();
        btnLamMoiSanPham = new app.view.swing.ButtonGradient();
        jPanel4 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        scrollPaneWin111 = new custome_ui.swing.ScrollPaneWin11();
        tblSanPham = new rojeru_san.complementos.RSTableMetro();
        paginationSanPham = new app.view.swing.Pagination();
        jLabel3 = new javax.swing.JLabel();
        txtTimKiemSanPham = new textfield.TextField2();
        jPanel18 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        btnSuaSanPhamChiTiet = new app.view.swing.ButtonGradient();
        btnLamMoiSanPhamChiTiet = new app.view.swing.ButtonGradient();
        btnImportExcel = new app.view.swing.ButtonGradient();
        btnTaiExcel = new app.view.swing.ButtonGradient();
        jPanel24 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        cbbSanPham = new app.view.swing.ComboBoxSuggestion();
        txtSoLuong = new textfield.TextField2();
        jLabel20 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        cbbXuatXu = new app.view.swing.ComboBoxSuggestion();
        jLabel30 = new javax.swing.JLabel();
        txtMaCTSP = new textfield.TextField2();
        jLabel26 = new javax.swing.JLabel();
        cbbKichCo = new app.view.swing.ComboBoxSuggestion();
        jLabel22 = new javax.swing.JLabel();
        cbbMauSac = new app.view.swing.ComboBoxSuggestion();
        btnThemNhanhMauSac = new app.view.swing.Button();
        btnThemNhanhKichCo = new app.view.swing.Button();
        jLabel21 = new javax.swing.JLabel();
        cbbChatLieu = new app.view.swing.ComboBoxSuggestion();
        jLabel24 = new javax.swing.JLabel();
        cbbChieuDaiTay = new app.view.swing.ComboBoxSuggestion();
        jLabel29 = new javax.swing.JLabel();
        txtGiaBan = new textfield.TextField2();
        jLabel23 = new javax.swing.JLabel();
        cbbCoAo = new app.view.swing.ComboBoxSuggestion();
        btnThemNhanhChatLieu = new app.view.swing.Button();
        btnThemNhanhCoAo = new app.view.swing.Button();
        btnThemNhanhChieuDaiTay = new app.view.swing.Button();
        jLabel32 = new javax.swing.JLabel();
        txtMoTa = new textfield.TextField2();
        btnThemSanPhamChiTiet = new app.view.swing.ButtonGradient();
        btnExportExcel = new app.view.swing.ButtonGradient();
        btnTaiQR = new app.view.swing.ButtonGradient();
        jPanel16 = new javax.swing.JPanel();
        jPanel17 = new javax.swing.JPanel();
        scrollPaneWin113 = new custome_ui.swing.ScrollPaneWin11();
        tblSanPhamChiTiet = new rojeru_san.complementos.RSTableMetro();
        paginationSanPhamChiTiet = new app.view.swing.Pagination();
        txtTimKiemSanPhamChiTiet = new textfield.TextField2();
        jLabel6 = new javax.swing.JLabel();
        jPanel19 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jPanel21 = new javax.swing.JPanel();
        txtMaThuocTinh = new textfield.TextField2();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtTenThuocTinh = new textfield.TextField2();
        jPanel22 = new javax.swing.JPanel();
        rdoThuongHieu = new radio_button.RadioButtonCustom();
        rdoDanhMuc = new radio_button.RadioButtonCustom();
        rdoChatLieu = new radio_button.RadioButtonCustom();
        rdoMauSac = new radio_button.RadioButtonCustom();
        rdoKichCo = new radio_button.RadioButtonCustom();
        rdoCoAo = new radio_button.RadioButtonCustom();
        rdoChieuDaiTay = new radio_button.RadioButtonCustom();
        jPanel23 = new javax.swing.JPanel();
        btnLamMoiThuocTinh = new app.view.swing.ButtonGradient();
        btnSuaThuocTinh = new app.view.swing.ButtonGradient();
        btnThemThuocTinh = new app.view.swing.ButtonGradient();
        jPanel12 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        tbl = new custome_ui.swing.ScrollPaneWin11();
        tblThuocTinh = new rojeru_san.complementos.RSTableMetro();
        jLabel9 = new javax.swing.JLabel();
        txtTimKiemThuocTinh = new textfield.TextField2();

        jPanel25.setBackground(new java.awt.Color(38, 28, 73));

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Tên thuộc tính");

        btnKichCoThemNhanh.setForeground(new java.awt.Color(0, 0, 0));
        btnKichCoThemNhanh.setText("Thêm");
        btnKichCoThemNhanh.setColor1(new java.awt.Color(255, 255, 255));
        btnKichCoThemNhanh.setColor2(new java.awt.Color(102, 102, 102));
        btnKichCoThemNhanh.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnKichCoThemNhanh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKichCoThemNhanhActionPerformed(evt);
            }
        });

        btnKichCoLamMoiThemNhanh.setForeground(new java.awt.Color(0, 0, 0));
        btnKichCoLamMoiThemNhanh.setText("Làm mới");
        btnKichCoLamMoiThemNhanh.setColor1(new java.awt.Color(255, 255, 255));
        btnKichCoLamMoiThemNhanh.setColor2(new java.awt.Color(102, 102, 102));
        btnKichCoLamMoiThemNhanh.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnKichCoLamMoiThemNhanh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKichCoLamMoiThemNhanhActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel25Layout = new javax.swing.GroupLayout(jPanel25);
        jPanel25.setLayout(jPanel25Layout);
        jPanel25Layout.setHorizontalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnKichCoThemNhanh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel25Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(txtTenKichCoThemNhanh, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel25Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(btnKichCoLamMoiThemNhanh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(60, Short.MAX_VALUE))
        );
        jPanel25Layout.setVerticalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(txtTenKichCoThemNhanh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnKichCoThemNhanh, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnKichCoLamMoiThemNhanh, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(50, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jDialogThemNhanhKichCoLayout = new javax.swing.GroupLayout(jDialogThemNhanhKichCo.getContentPane());
        jDialogThemNhanhKichCo.getContentPane().setLayout(jDialogThemNhanhKichCoLayout);
        jDialogThemNhanhKichCoLayout.setHorizontalGroup(
            jDialogThemNhanhKichCoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jDialogThemNhanhKichCoLayout.setVerticalGroup(
            jDialogThemNhanhKichCoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel26.setBackground(new java.awt.Color(38, 28, 73));

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Tên thuộc tính");

        btnMauSacThemNhanh.setForeground(new java.awt.Color(0, 0, 0));
        btnMauSacThemNhanh.setText("Thêm");
        btnMauSacThemNhanh.setColor1(new java.awt.Color(255, 255, 255));
        btnMauSacThemNhanh.setColor2(new java.awt.Color(102, 102, 102));
        btnMauSacThemNhanh.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnMauSacThemNhanh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMauSacThemNhanhActionPerformed(evt);
            }
        });

        btnMauSacLamMoiThemNhanh.setForeground(new java.awt.Color(0, 0, 0));
        btnMauSacLamMoiThemNhanh.setText("Làm mới");
        btnMauSacLamMoiThemNhanh.setColor1(new java.awt.Color(255, 255, 255));
        btnMauSacLamMoiThemNhanh.setColor2(new java.awt.Color(102, 102, 102));
        btnMauSacLamMoiThemNhanh.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnMauSacLamMoiThemNhanh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMauSacLamMoiThemNhanhActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel26Layout = new javax.swing.GroupLayout(jPanel26);
        jPanel26.setLayout(jPanel26Layout);
        jPanel26Layout.setHorizontalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnMauSacThemNhanh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel26Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(txtTenMauSacThemNhanh, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel26Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(btnMauSacLamMoiThemNhanh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(60, Short.MAX_VALUE))
        );
        jPanel26Layout.setVerticalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(txtTenMauSacThemNhanh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnMauSacThemNhanh, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnMauSacLamMoiThemNhanh, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(50, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jDialogThemNhanhMauSacLayout = new javax.swing.GroupLayout(jDialogThemNhanhMauSac.getContentPane());
        jDialogThemNhanhMauSac.getContentPane().setLayout(jDialogThemNhanhMauSacLayout);
        jDialogThemNhanhMauSacLayout.setHorizontalGroup(
            jDialogThemNhanhMauSacLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jDialogThemNhanhMauSacLayout.setVerticalGroup(
            jDialogThemNhanhMauSacLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel27.setBackground(new java.awt.Color(38, 28, 73));

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("Tên thuộc tính");

        btnChatLieuThemNhanh.setForeground(new java.awt.Color(0, 0, 0));
        btnChatLieuThemNhanh.setText("Thêm");
        btnChatLieuThemNhanh.setColor1(new java.awt.Color(255, 255, 255));
        btnChatLieuThemNhanh.setColor2(new java.awt.Color(102, 102, 102));
        btnChatLieuThemNhanh.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnChatLieuThemNhanh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChatLieuThemNhanhActionPerformed(evt);
            }
        });

        btnChatLieuLamMoiThemNhanh.setForeground(new java.awt.Color(0, 0, 0));
        btnChatLieuLamMoiThemNhanh.setText("Làm mới");
        btnChatLieuLamMoiThemNhanh.setColor1(new java.awt.Color(255, 255, 255));
        btnChatLieuLamMoiThemNhanh.setColor2(new java.awt.Color(102, 102, 102));
        btnChatLieuLamMoiThemNhanh.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnChatLieuLamMoiThemNhanh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChatLieuLamMoiThemNhanhActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel27Layout = new javax.swing.GroupLayout(jPanel27);
        jPanel27.setLayout(jPanel27Layout);
        jPanel27Layout.setHorizontalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnChatLieuThemNhanh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel27Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(txtTenChatLieuThemNhanh, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel27Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(btnChatLieuLamMoiThemNhanh, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(60, Short.MAX_VALUE))
        );
        jPanel27Layout.setVerticalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(txtTenChatLieuThemNhanh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnChatLieuThemNhanh, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnChatLieuLamMoiThemNhanh, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(50, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jDialogThemNhanhChatLieuLayout = new javax.swing.GroupLayout(jDialogThemNhanhChatLieu.getContentPane());
        jDialogThemNhanhChatLieu.getContentPane().setLayout(jDialogThemNhanhChatLieuLayout);
        jDialogThemNhanhChatLieuLayout.setHorizontalGroup(
            jDialogThemNhanhChatLieuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jDialogThemNhanhChatLieuLayout.setVerticalGroup(
            jDialogThemNhanhChatLieuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel28.setBackground(new java.awt.Color(38, 28, 73));

        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("Tên thuộc tính");

        btnCoAoThemNhanh.setForeground(new java.awt.Color(0, 0, 0));
        btnCoAoThemNhanh.setText("Thêm");
        btnCoAoThemNhanh.setColor1(new java.awt.Color(255, 255, 255));
        btnCoAoThemNhanh.setColor2(new java.awt.Color(102, 102, 102));
        btnCoAoThemNhanh.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnCoAoThemNhanh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCoAoThemNhanhActionPerformed(evt);
            }
        });

        btnCoAoLamMoiThemNhanh.setForeground(new java.awt.Color(0, 0, 0));
        btnCoAoLamMoiThemNhanh.setText("Làm mới");
        btnCoAoLamMoiThemNhanh.setColor1(new java.awt.Color(255, 255, 255));
        btnCoAoLamMoiThemNhanh.setColor2(new java.awt.Color(102, 102, 102));
        btnCoAoLamMoiThemNhanh.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnCoAoLamMoiThemNhanh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCoAoLamMoiThemNhanhActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel28Layout = new javax.swing.GroupLayout(jPanel28);
        jPanel28.setLayout(jPanel28Layout);
        jPanel28Layout.setHorizontalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel28Layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnCoAoThemNhanh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel28Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(txtTenCoAoThemNhanh, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel28Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(btnCoAoLamMoiThemNhanh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(60, Short.MAX_VALUE))
        );
        jPanel28Layout.setVerticalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel28Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(txtTenCoAoThemNhanh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCoAoThemNhanh, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCoAoLamMoiThemNhanh, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(50, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jDialogThemNhanhCoAoLayout = new javax.swing.GroupLayout(jDialogThemNhanhCoAo.getContentPane());
        jDialogThemNhanhCoAo.getContentPane().setLayout(jDialogThemNhanhCoAoLayout);
        jDialogThemNhanhCoAoLayout.setHorizontalGroup(
            jDialogThemNhanhCoAoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jDialogThemNhanhCoAoLayout.setVerticalGroup(
            jDialogThemNhanhCoAoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel29.setBackground(new java.awt.Color(38, 28, 73));

        jLabel19.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("Tên thuộc tính");

        btnChieuDaiTayThemNhanh.setForeground(new java.awt.Color(0, 0, 0));
        btnChieuDaiTayThemNhanh.setText("Thêm");
        btnChieuDaiTayThemNhanh.setColor1(new java.awt.Color(255, 255, 255));
        btnChieuDaiTayThemNhanh.setColor2(new java.awt.Color(102, 102, 102));
        btnChieuDaiTayThemNhanh.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnChieuDaiTayThemNhanh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChieuDaiTayThemNhanhActionPerformed(evt);
            }
        });

        btnChieuDaiTayLamMoiThemNhanh.setForeground(new java.awt.Color(0, 0, 0));
        btnChieuDaiTayLamMoiThemNhanh.setText("Làm mới");
        btnChieuDaiTayLamMoiThemNhanh.setColor1(new java.awt.Color(255, 255, 255));
        btnChieuDaiTayLamMoiThemNhanh.setColor2(new java.awt.Color(102, 102, 102));
        btnChieuDaiTayLamMoiThemNhanh.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnChieuDaiTayLamMoiThemNhanh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChieuDaiTayLamMoiThemNhanhActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel29Layout = new javax.swing.GroupLayout(jPanel29);
        jPanel29.setLayout(jPanel29Layout);
        jPanel29Layout.setHorizontalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel29Layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnChieuDaiTayThemNhanh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel29Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(txtTenChieuDaiTayThemNhanh, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel29Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(btnChieuDaiTayLamMoiThemNhanh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(60, Short.MAX_VALUE))
        );
        jPanel29Layout.setVerticalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel29Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(txtTenChieuDaiTayThemNhanh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnChieuDaiTayThemNhanh, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnChieuDaiTayLamMoiThemNhanh, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(50, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jDialogThemNhanhChieuDaiTayLayout = new javax.swing.GroupLayout(jDialogThemNhanhChieuDaiTay.getContentPane());
        jDialogThemNhanhChieuDaiTay.getContentPane().setLayout(jDialogThemNhanhChieuDaiTayLayout);
        jDialogThemNhanhChieuDaiTayLayout.setHorizontalGroup(
            jDialogThemNhanhChieuDaiTayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jDialogThemNhanhChieuDaiTayLayout.setVerticalGroup(
            jDialogThemNhanhChieuDaiTayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel20.setBackground(new java.awt.Color(38, 28, 73));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Tên thuộc tính");

        btnThuongHieuThemNhanh.setForeground(new java.awt.Color(0, 0, 0));
        btnThuongHieuThemNhanh.setText("Thêm");
        btnThuongHieuThemNhanh.setColor1(new java.awt.Color(255, 255, 255));
        btnThuongHieuThemNhanh.setColor2(new java.awt.Color(102, 102, 102));
        btnThuongHieuThemNhanh.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnThuongHieuThemNhanh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThuongHieuThemNhanhActionPerformed(evt);
            }
        });

        btnThuongHieuLamMoiThemNhanh.setForeground(new java.awt.Color(0, 0, 0));
        btnThuongHieuLamMoiThemNhanh.setText("Làm mới");
        btnThuongHieuLamMoiThemNhanh.setColor1(new java.awt.Color(255, 255, 255));
        btnThuongHieuLamMoiThemNhanh.setColor2(new java.awt.Color(102, 102, 102));
        btnThuongHieuLamMoiThemNhanh.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnThuongHieuLamMoiThemNhanh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThuongHieuLamMoiThemNhanhActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnThuongHieuThemNhanh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(txtTenThuongHieuThemNhanh, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(btnThuongHieuLamMoiThemNhanh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(60, Short.MAX_VALUE))
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtTenThuongHieuThemNhanh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThuongHieuThemNhanh, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThuongHieuLamMoiThemNhanh, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(50, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jDialogThemNhanhThuongHieuLayout = new javax.swing.GroupLayout(jDialogThemNhanhThuongHieu.getContentPane());
        jDialogThemNhanhThuongHieu.getContentPane().setLayout(jDialogThemNhanhThuongHieuLayout);
        jDialogThemNhanhThuongHieuLayout.setHorizontalGroup(
            jDialogThemNhanhThuongHieuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jDialogThemNhanhThuongHieuLayout.setVerticalGroup(
            jDialogThemNhanhThuongHieuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel30.setBackground(new java.awt.Color(38, 28, 73));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Tên thuộc tính");

        btnDanhMucThemNhanh.setForeground(new java.awt.Color(0, 0, 0));
        btnDanhMucThemNhanh.setText("Thêm");
        btnDanhMucThemNhanh.setColor1(new java.awt.Color(255, 255, 255));
        btnDanhMucThemNhanh.setColor2(new java.awt.Color(102, 102, 102));
        btnDanhMucThemNhanh.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnDanhMucThemNhanh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDanhMucThemNhanhActionPerformed(evt);
            }
        });

        btnDanhMucLamMoiThemNhanh.setForeground(new java.awt.Color(0, 0, 0));
        btnDanhMucLamMoiThemNhanh.setText("Làm mới");
        btnDanhMucLamMoiThemNhanh.setColor1(new java.awt.Color(255, 255, 255));
        btnDanhMucLamMoiThemNhanh.setColor2(new java.awt.Color(102, 102, 102));
        btnDanhMucLamMoiThemNhanh.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnDanhMucLamMoiThemNhanh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDanhMucLamMoiThemNhanhActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel30Layout = new javax.swing.GroupLayout(jPanel30);
        jPanel30.setLayout(jPanel30Layout);
        jPanel30Layout.setHorizontalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel30Layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnDanhMucThemNhanh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel30Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(txtTenDanhMucThemNhanh, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel30Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(btnDanhMucLamMoiThemNhanh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(60, Short.MAX_VALUE))
        );
        jPanel30Layout.setVerticalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel30Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtTenDanhMucThemNhanh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDanhMucThemNhanh, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDanhMucLamMoiThemNhanh, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(50, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jDialogThemNhanhDanhMucLayout = new javax.swing.GroupLayout(jDialogThemNhanhDanhMuc.getContentPane());
        jDialogThemNhanhDanhMuc.getContentPane().setLayout(jDialogThemNhanhDanhMucLayout);
        jDialogThemNhanhDanhMucLayout.setHorizontalGroup(
            jDialogThemNhanhDanhMucLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel30, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jDialogThemNhanhDanhMucLayout.setVerticalGroup(
            jDialogThemNhanhDanhMucLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel30, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel31.setBackground(new java.awt.Color(38, 28, 73));
        jPanel31.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tabbedPaneCustom2.setBackground(new java.awt.Color(38, 28, 73));
        tabbedPaneCustom2.setForeground(new java.awt.Color(255, 255, 255));
        tabbedPaneCustom2.setSelectedColor(new java.awt.Color(51, 0, 102));
        tabbedPaneCustom2.setUnselectedColor(new java.awt.Color(38, 32, 78));

        jPanel32.setBackground(new java.awt.Color(38, 28, 73));

        jPanel33.setBackground(new java.awt.Color(38, 28, 73));
        jPanel33.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh sách sản phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 16), new java.awt.Color(255, 255, 255))); // NOI18N

        jPanel34.setBackground(new java.awt.Color(38, 28, 73));
        jPanel34.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        tblExportExcel.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã SPCT", "Tên sản phẩm", "Số lượng", "Giá bán", "Màu sắc", "Kích cỡ", "Chất liệu", "Cổ áo", "Chiều dài tay", "Xuất xứ", "Trạng thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblExportExcel.setColorBackgoundHead(new java.awt.Color(102, 102, 102));
        tblExportExcel.setColorBordeFilas(new java.awt.Color(153, 153, 153));
        tblExportExcel.setColorBordeHead(new java.awt.Color(153, 153, 153));
        tblExportExcel.setColorFilasBackgound1(new java.awt.Color(204, 204, 204));
        tblExportExcel.setColorFilasForeground1(new java.awt.Color(0, 0, 0));
        tblExportExcel.setColorFilasForeground2(new java.awt.Color(0, 0, 0));
        tblExportExcel.setColorSelBackgound(new java.awt.Color(38, 28, 73));
        tblExportExcel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tblExportExcel.setFuenteFilas(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tblExportExcel.setFuenteFilasSelect(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        tblExportExcel.setFuenteHead(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        tblExportExcel.setRowHeight(40);
        tblExportExcel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblExportExcelMouseClicked(evt);
            }
        });
        scrollPaneWin114.setViewportView(tblExportExcel);
        if (tblExportExcel.getColumnModel().getColumnCount() > 0) {
            tblExportExcel.getColumnModel().getColumn(0).setResizable(false);
            tblExportExcel.getColumnModel().getColumn(1).setResizable(false);
            tblExportExcel.getColumnModel().getColumn(2).setResizable(false);
            tblExportExcel.getColumnModel().getColumn(3).setResizable(false);
            tblExportExcel.getColumnModel().getColumn(4).setResizable(false);
            tblExportExcel.getColumnModel().getColumn(5).setResizable(false);
            tblExportExcel.getColumnModel().getColumn(6).setResizable(false);
            tblExportExcel.getColumnModel().getColumn(7).setResizable(false);
            tblExportExcel.getColumnModel().getColumn(8).setResizable(false);
            tblExportExcel.getColumnModel().getColumn(9).setResizable(false);
            tblExportExcel.getColumnModel().getColumn(10).setResizable(false);
        }

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Tìm kiếm");

        textField26.setForeground(new java.awt.Color(0, 0, 0));
        textField26.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel27.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(255, 255, 255));
        jLabel27.setText("Màu sắc");

        cbbMauSacExport.setBackground(new java.awt.Color(204, 204, 204));
        cbbMauSacExport.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        cbbMauSacExport.setForeground(new java.awt.Color(255, 255, 255));
        cbbMauSacExport.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel28.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(255, 255, 255));
        jLabel28.setText("Kích cỡ");

        cbbKichCoExport.setBackground(new java.awt.Color(204, 204, 204));
        cbbKichCoExport.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        cbbKichCoExport.setForeground(new java.awt.Color(255, 255, 255));
        cbbKichCoExport.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel31.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(255, 255, 255));
        jLabel31.setText("Chất liệu");

        cbbChatLieuExport.setBackground(new java.awt.Color(204, 204, 204));
        cbbChatLieuExport.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        cbbChatLieuExport.setForeground(new java.awt.Color(255, 255, 255));
        cbbChatLieuExport.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel33.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(255, 255, 255));
        jLabel33.setText("Cổ áo");

        cbbCoAoExport.setBackground(new java.awt.Color(204, 204, 204));
        cbbCoAoExport.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        cbbCoAoExport.setForeground(new java.awt.Color(255, 255, 255));
        cbbCoAoExport.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel34.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(255, 255, 255));
        jLabel34.setText("Chiều dài tay");

        cbbChieuDaiTayExport.setBackground(new java.awt.Color(204, 204, 204));
        cbbChieuDaiTayExport.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        cbbChieuDaiTayExport.setForeground(new java.awt.Color(255, 255, 255));
        cbbChieuDaiTayExport.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        btnExport.setForeground(new java.awt.Color(0, 0, 0));
        btnExport.setText("Export");
        btnExport.setColor1(new java.awt.Color(255, 255, 255));
        btnExport.setColor2(new java.awt.Color(102, 102, 102));
        btnExport.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnExport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExportActionPerformed(evt);
            }
        });

        jLabel35.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(255, 255, 255));
        jLabel35.setText("Xuất xứ");

        cbbXuatXuExport.setBackground(new java.awt.Color(204, 204, 204));
        cbbXuatXuExport.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        cbbXuatXuExport.setForeground(new java.awt.Color(255, 255, 255));
        cbbXuatXuExport.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel34Layout = new javax.swing.GroupLayout(jPanel34);
        jPanel34.setLayout(jPanel34Layout);
        jPanel34Layout.setHorizontalGroup(
            jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel34Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(scrollPaneWin114, javax.swing.GroupLayout.PREFERRED_SIZE, 1084, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(391, 391, 391))
            .addGroup(jPanel34Layout.createSequentialGroup()
                .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel34Layout.createSequentialGroup()
                        .addGap(501, 501, 501)
                        .addComponent(btnExport, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel34Layout.createSequentialGroup()
                        .addGap(103, 103, 103)
                        .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel34Layout.createSequentialGroup()
                                .addGap(51, 51, 51)
                                .addComponent(jLabel13)
                                .addGap(18, 18, 18)
                                .addComponent(textField26, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel27)
                                .addGap(18, 18, 18)
                                .addComponent(cbbMauSacExport, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel28)
                                .addGap(18, 18, 18)
                                .addComponent(cbbKichCoExport, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel34Layout.createSequentialGroup()
                                .addComponent(jLabel31)
                                .addGap(18, 18, 18)
                                .addComponent(cbbChatLieuExport, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel33)
                                .addGap(18, 18, 18)
                                .addComponent(cbbCoAoExport, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel34)
                                .addGap(18, 18, 18)
                                .addComponent(cbbChieuDaiTayExport, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel35)
                                .addGap(18, 18, 18)
                                .addComponent(cbbXuatXuExport, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel34Layout.setVerticalGroup(
            jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel34Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(textField26, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbbMauSacExport, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbbKichCoExport, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cbbXuatXuExport, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cbbChatLieuExport, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cbbCoAoExport, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cbbChieuDaiTayExport, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(scrollPaneWin114, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnExport, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(7, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel33Layout = new javax.swing.GroupLayout(jPanel33);
        jPanel33.setLayout(jPanel33Layout);
        jPanel33Layout.setHorizontalGroup(
            jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel33Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel34, javax.swing.GroupLayout.DEFAULT_SIZE, 1103, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel33Layout.setVerticalGroup(
            jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel33Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel34, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel32Layout = new javax.swing.GroupLayout(jPanel32);
        jPanel32.setLayout(jPanel32Layout);
        jPanel32Layout.setHorizontalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel32Layout.createSequentialGroup()
                .addComponent(jPanel33, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 40, Short.MAX_VALUE))
        );
        jPanel32Layout.setVerticalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel32Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel33, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(33, Short.MAX_VALUE))
        );

        tabbedPaneCustom2.addTab("Danh sách sản phẩm", jPanel32);

        jPanel31.add(tabbedPaneCustom2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 1170, 740));

        javax.swing.GroupLayout jDialogExportExcelLayout = new javax.swing.GroupLayout(jDialogExportExcel.getContentPane());
        jDialogExportExcel.getContentPane().setLayout(jDialogExportExcelLayout);
        jDialogExportExcelLayout.setHorizontalGroup(
            jDialogExportExcelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel31, javax.swing.GroupLayout.PREFERRED_SIZE, 1200, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jDialogExportExcelLayout.setVerticalGroup(
            jDialogExportExcelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialogExportExcelLayout.createSequentialGroup()
                .addComponent(jPanel31, javax.swing.GroupLayout.PREFERRED_SIZE, 850, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        setMinimumSize(new java.awt.Dimension(1344, 790));
        setPreferredSize(new java.awt.Dimension(1344, 790));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(38, 28, 73));
        jPanel1.setPreferredSize(new java.awt.Dimension(1330, 790));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tabbedPaneCustom1.setBackground(new java.awt.Color(38, 28, 73));
        tabbedPaneCustom1.setForeground(new java.awt.Color(255, 255, 255));
        tabbedPaneCustom1.setSelectedColor(new java.awt.Color(51, 0, 102));
        tabbedPaneCustom1.setUnselectedColor(new java.awt.Color(38, 32, 78));

        jPanel2.setBackground(new java.awt.Color(38, 28, 73));

        jPanel3.setBackground(new java.awt.Color(38, 28, 73));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin sản phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 16), new java.awt.Color(255, 255, 255))); // NOI18N

        jPanel9.setBackground(new java.awt.Color(38, 28, 73));
        jPanel9.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel9.setForeground(new java.awt.Color(255, 255, 255));
        jPanel9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jPanel5.setBackground(new java.awt.Color(38, 28, 73));

        txtMaSanPham.setBackground(new java.awt.Color(153, 153, 153));
        txtMaSanPham.setForeground(new java.awt.Color(0, 0, 0));
        txtMaSanPham.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Mã sản phẩm");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Tên sản phẩm");

        txtTenSanPham.setForeground(new java.awt.Color(0, 0, 0));
        txtTenSanPham.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(txtMaSanPham, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(txtTenSanPham, javax.swing.GroupLayout.DEFAULT_SIZE, 229, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtMaSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTenSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel6.setBackground(new java.awt.Color(38, 28, 73));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Danh mục");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Thương hiệu");

        btnThemNhanhDanhMuc.setText("+");
        btnThemNhanhDanhMuc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemNhanhDanhMucActionPerformed(evt);
            }
        });

        btnThemNhanhThuongHieu.setText("+");
        btnThemNhanhThuongHieu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemNhanhThuongHieuActionPerformed(evt);
            }
        });

        cbbThuongHieu.setBackground(new java.awt.Color(204, 204, 204));
        cbbThuongHieu.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        cbbThuongHieu.setForeground(new java.awt.Color(255, 255, 255));
        cbbThuongHieu.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cbbThuongHieu.setPreferredSize(new java.awt.Dimension(150, 28));
        cbbThuongHieu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbThuongHieuActionPerformed(evt);
            }
        });

        cbbDanhMuc.setBackground(new java.awt.Color(204, 204, 204));
        cbbDanhMuc.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        cbbDanhMuc.setForeground(new java.awt.Color(255, 255, 255));
        cbbDanhMuc.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tiền mặt", "Chuyển khoản", "Kết hợp" }));
        cbbDanhMuc.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cbbDanhMuc.setPreferredSize(new java.awt.Dimension(150, 28));
        cbbDanhMuc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbDanhMucActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(21, 21, 21))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(38, 38, 38)))
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbbDanhMuc, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbbThuongHieu, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnThemNhanhThuongHieu, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThemNhanhDanhMuc, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(44, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbbThuongHieu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThemNhanhThuongHieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbbDanhMuc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThemNhanhDanhMuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12))
        );

        jPanel7.setBackground(new java.awt.Color(38, 28, 73));

        btnThemSanPham.setForeground(new java.awt.Color(0, 0, 0));
        btnThemSanPham.setText("Thêm");
        btnThemSanPham.setColor1(new java.awt.Color(255, 255, 255));
        btnThemSanPham.setColor2(new java.awt.Color(102, 102, 102));
        btnThemSanPham.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnThemSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemSanPhamActionPerformed(evt);
            }
        });

        btnSuaSanPham.setForeground(new java.awt.Color(0, 0, 0));
        btnSuaSanPham.setText("Cập nhật");
        btnSuaSanPham.setColor1(new java.awt.Color(255, 255, 255));
        btnSuaSanPham.setColor2(new java.awt.Color(102, 102, 102));
        btnSuaSanPham.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnSuaSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaSanPhamActionPerformed(evt);
            }
        });

        btnLamMoiSanPham.setForeground(new java.awt.Color(0, 0, 0));
        btnLamMoiSanPham.setText("Làm mới");
        btnLamMoiSanPham.setColor1(new java.awt.Color(255, 255, 255));
        btnLamMoiSanPham.setColor2(new java.awt.Color(102, 102, 102));
        btnLamMoiSanPham.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnLamMoiSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiSanPhamActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap(40, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnLamMoiSanPham, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnThemSanPham, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSuaSanPham, javax.swing.GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnThemSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnSuaSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnLamMoiSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(9, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap(141, Short.MAX_VALUE)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(62, 62, 62)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(118, 118, 118))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap(34, Short.MAX_VALUE)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(52, 52, 52))))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel4.setBackground(new java.awt.Color(38, 28, 73));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh sách sản phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 16), new java.awt.Color(255, 255, 255))); // NOI18N

        jPanel8.setBackground(new java.awt.Color(38, 28, 73));
        jPanel8.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        tblSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Mã sản phẩm", "Tên sản phẩm", "Số lượng", "Thương hiệu", "Danh mục"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblSanPham.setColorBackgoundHead(new java.awt.Color(102, 102, 102));
        tblSanPham.setColorBordeFilas(new java.awt.Color(153, 153, 153));
        tblSanPham.setColorBordeHead(new java.awt.Color(153, 153, 153));
        tblSanPham.setColorFilasBackgound1(new java.awt.Color(204, 204, 204));
        tblSanPham.setColorFilasForeground1(new java.awt.Color(0, 0, 0));
        tblSanPham.setColorFilasForeground2(new java.awt.Color(0, 0, 0));
        tblSanPham.setColorSelBackgound(new java.awt.Color(38, 28, 73));
        tblSanPham.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tblSanPham.setFuenteFilas(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tblSanPham.setFuenteFilasSelect(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        tblSanPham.setFuenteHead(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        tblSanPham.setRowHeight(40);
        tblSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSanPhamMouseClicked(evt);
            }
        });
        scrollPaneWin111.setViewportView(tblSanPham);
        if (tblSanPham.getColumnModel().getColumnCount() > 0) {
            tblSanPham.getColumnModel().getColumn(0).setResizable(false);
            tblSanPham.getColumnModel().getColumn(1).setResizable(false);
            tblSanPham.getColumnModel().getColumn(2).setResizable(false);
            tblSanPham.getColumnModel().getColumn(3).setResizable(false);
            tblSanPham.getColumnModel().getColumn(4).setResizable(false);
        }

        paginationSanPham.setBackground(new java.awt.Color(38, 28, 73));
        paginationSanPham.setForeground(new java.awt.Color(38, 28, 73));

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scrollPaneWin111, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(paginationSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(545, 545, 545))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scrollPaneWin111, javax.swing.GroupLayout.DEFAULT_SIZE, 296, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(paginationSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Tìm kiếm");

        txtTimKiemSanPham.setForeground(new java.awt.Color(0, 0, 0));
        txtTimKiemSanPham.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(txtTimKiemSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtTimKiemSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(39, Short.MAX_VALUE))
        );

        tabbedPaneCustom1.addTab("Sản phẩm", jPanel2);

        jPanel18.setBackground(new java.awt.Color(38, 28, 73));

        jPanel14.setBackground(new java.awt.Color(38, 28, 73));
        jPanel14.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin sản phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 16), new java.awt.Color(255, 255, 255))); // NOI18N

        jPanel15.setBackground(new java.awt.Color(38, 28, 73));
        jPanel15.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnSuaSanPhamChiTiet.setForeground(new java.awt.Color(0, 0, 0));
        btnSuaSanPhamChiTiet.setText("Cập nhật");
        btnSuaSanPhamChiTiet.setColor1(new java.awt.Color(255, 255, 255));
        btnSuaSanPhamChiTiet.setColor2(new java.awt.Color(102, 102, 102));
        btnSuaSanPhamChiTiet.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnSuaSanPhamChiTiet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaSanPhamChiTietActionPerformed(evt);
            }
        });

        btnLamMoiSanPhamChiTiet.setForeground(new java.awt.Color(0, 0, 0));
        btnLamMoiSanPhamChiTiet.setText("Làm mới");
        btnLamMoiSanPhamChiTiet.setColor1(new java.awt.Color(255, 255, 255));
        btnLamMoiSanPhamChiTiet.setColor2(new java.awt.Color(102, 102, 102));
        btnLamMoiSanPhamChiTiet.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnLamMoiSanPhamChiTiet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiSanPhamChiTietActionPerformed(evt);
            }
        });

        btnImportExcel.setForeground(new java.awt.Color(0, 0, 0));
        btnImportExcel.setText("Import Excel");
        btnImportExcel.setColor1(new java.awt.Color(255, 255, 255));
        btnImportExcel.setColor2(new java.awt.Color(102, 102, 102));
        btnImportExcel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnImportExcel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImportExcelActionPerformed(evt);
            }
        });

        btnTaiExcel.setForeground(new java.awt.Color(0, 0, 0));
        btnTaiExcel.setText("Tải Excel");
        btnTaiExcel.setColor1(new java.awt.Color(255, 255, 255));
        btnTaiExcel.setColor2(new java.awt.Color(102, 102, 102));
        btnTaiExcel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnTaiExcel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTaiExcelActionPerformed(evt);
            }
        });

        jPanel24.setBackground(new java.awt.Color(38, 28, 73));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Tên sản phẩm");

        cbbSanPham.setBackground(new java.awt.Color(204, 204, 204));
        cbbSanPham.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        cbbSanPham.setForeground(new java.awt.Color(255, 255, 255));
        cbbSanPham.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        txtSoLuong.setForeground(new java.awt.Color(0, 0, 0));
        txtSoLuong.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtSoLuong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSoLuongActionPerformed(evt);
            }
        });

        jLabel20.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("Số lượng");

        jLabel25.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(255, 255, 255));
        jLabel25.setText("Xuất xứ");

        cbbXuatXu.setBackground(new java.awt.Color(204, 204, 204));
        cbbXuatXu.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        cbbXuatXu.setForeground(new java.awt.Color(255, 255, 255));
        cbbXuatXu.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel30.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(255, 255, 255));
        jLabel30.setText("Mã SPCT");

        txtMaCTSP.setBackground(new java.awt.Color(153, 153, 153));
        txtMaCTSP.setForeground(new java.awt.Color(0, 0, 0));
        txtMaCTSP.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtMaCTSP.setName(""); // NOI18N
        txtMaCTSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaCTSPActionPerformed(evt);
            }
        });

        jLabel26.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(255, 255, 255));
        jLabel26.setText("Kích cỡ");

        cbbKichCo.setBackground(new java.awt.Color(204, 204, 204));
        cbbKichCo.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        cbbKichCo.setForeground(new java.awt.Color(255, 255, 255));
        cbbKichCo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel22.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setText("Màu sắc");

        cbbMauSac.setBackground(new java.awt.Color(204, 204, 204));
        cbbMauSac.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        cbbMauSac.setForeground(new java.awt.Color(255, 255, 255));
        cbbMauSac.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        btnThemNhanhMauSac.setText("+");
        btnThemNhanhMauSac.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemNhanhMauSacActionPerformed(evt);
            }
        });

        btnThemNhanhKichCo.setText("+");
        btnThemNhanhKichCo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemNhanhKichCoActionPerformed(evt);
            }
        });

        jLabel21.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText("Chất liệu");

        cbbChatLieu.setBackground(new java.awt.Color(204, 204, 204));
        cbbChatLieu.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        cbbChatLieu.setForeground(new java.awt.Color(255, 255, 255));
        cbbChatLieu.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel24.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(255, 255, 255));
        jLabel24.setText("Chiều dài tay");

        cbbChieuDaiTay.setBackground(new java.awt.Color(204, 204, 204));
        cbbChieuDaiTay.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        cbbChieuDaiTay.setForeground(new java.awt.Color(255, 255, 255));
        cbbChieuDaiTay.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel29.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(255, 255, 255));
        jLabel29.setText("Giá bán");

        txtGiaBan.setForeground(new java.awt.Color(0, 0, 0));
        txtGiaBan.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel23.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setText("Cổ áo");

        cbbCoAo.setBackground(new java.awt.Color(204, 204, 204));
        cbbCoAo.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        cbbCoAo.setForeground(new java.awt.Color(255, 255, 255));
        cbbCoAo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        btnThemNhanhChatLieu.setText("+");
        btnThemNhanhChatLieu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemNhanhChatLieuActionPerformed(evt);
            }
        });

        btnThemNhanhCoAo.setText("+");
        btnThemNhanhCoAo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemNhanhCoAoActionPerformed(evt);
            }
        });

        btnThemNhanhChieuDaiTay.setText("+");
        btnThemNhanhChieuDaiTay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemNhanhChieuDaiTayActionPerformed(evt);
            }
        });

        jLabel32.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(255, 255, 255));
        jLabel32.setText("Mô tả");

        txtMoTa.setForeground(new java.awt.Color(0, 0, 0));
        txtMoTa.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtMoTa.setName(""); // NOI18N
        txtMoTa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMoTaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel24Layout.createSequentialGroup()
                        .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel30)
                            .addComponent(jLabel20)
                            .addComponent(jLabel29))
                        .addGap(44, 44, 44)
                        .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtMaCTSP, javax.swing.GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE)
                            .addComponent(txtSoLuong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtGiaBan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel24Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(18, 18, 18)
                        .addComponent(cbbSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(80, 80, 80)
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel24Layout.createSequentialGroup()
                        .addComponent(jLabel26)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cbbKichCo, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel24Layout.createSequentialGroup()
                        .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel21)
                            .addComponent(jLabel23))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbbCoAo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbbChatLieu, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel24Layout.createSequentialGroup()
                        .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(cbbMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(12, 12, 12)
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnThemNhanhCoAo, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThemNhanhKichCo, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThemNhanhMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThemNhanhChatLieu, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(80, 80, 80)
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel24Layout.createSequentialGroup()
                        .addComponent(jLabel24)
                        .addGap(18, 18, 18)
                        .addComponent(cbbChieuDaiTay, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnThemNhanhChieuDaiTay, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel24Layout.createSequentialGroup()
                        .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel25)
                            .addComponent(jLabel32))
                        .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel24Layout.createSequentialGroup()
                                .addGap(54, 54, 54)
                                .addComponent(cbbXuatXu, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel24Layout.createSequentialGroup()
                                .addGap(46, 46, 46)
                                .addComponent(txtMoTa, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addContainerGap(35, Short.MAX_VALUE)
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbbSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThemNhanhKichCo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbbKichCo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbbChieuDaiTay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThemNhanhChieuDaiTay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel24Layout.createSequentialGroup()
                        .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbbMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtMaCTSP, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel24Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel24Layout.createSequentialGroup()
                                        .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(cbbChatLieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(btnThemNhanhChatLieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel24Layout.createSequentialGroup()
                                                .addGap(1, 1, 1)
                                                .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(btnThemNhanhCoAo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(cbbCoAo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPanel24Layout.createSequentialGroup()
                                        .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(txtGiaBan, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addGroup(jPanel24Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel24Layout.createSequentialGroup()
                        .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnThemNhanhMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbbXuatXu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtMoTa, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(9, 9, 9))
        );

        btnThemSanPhamChiTiet.setForeground(new java.awt.Color(0, 0, 0));
        btnThemSanPhamChiTiet.setText("Thêm");
        btnThemSanPhamChiTiet.setColor1(new java.awt.Color(255, 255, 255));
        btnThemSanPhamChiTiet.setColor2(new java.awt.Color(102, 102, 102));
        btnThemSanPhamChiTiet.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnThemSanPhamChiTiet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemSanPhamChiTietActionPerformed(evt);
            }
        });

        btnExportExcel.setForeground(new java.awt.Color(0, 0, 0));
        btnExportExcel.setText("Export Excel");
        btnExportExcel.setColor1(new java.awt.Color(255, 255, 255));
        btnExportExcel.setColor2(new java.awt.Color(102, 102, 102));
        btnExportExcel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnExportExcel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExportExcelActionPerformed(evt);
            }
        });

        btnTaiQR.setForeground(new java.awt.Color(0, 0, 0));
        btnTaiQR.setText("Tải QR");
        btnTaiQR.setColor1(new java.awt.Color(255, 255, 255));
        btnTaiQR.setColor2(new java.awt.Color(102, 102, 102));
        btnTaiQR.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnTaiQR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTaiQRActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(jPanel24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGap(63, 63, 63)
                        .addComponent(btnThemSanPhamChiTiet, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnSuaSanPhamChiTiet, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnLamMoiSanPhamChiTiet, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnImportExcel, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnTaiExcel, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnExportExcel, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnTaiQR, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(36, Short.MAX_VALUE))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThemSanPhamChiTiet, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSuaSanPhamChiTiet, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLamMoiSanPhamChiTiet, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnImportExcel, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTaiExcel, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnExportExcel, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTaiQR, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(37, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel16.setBackground(new java.awt.Color(38, 28, 73));
        jPanel16.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh sách sản phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 16), new java.awt.Color(255, 255, 255))); // NOI18N

        jPanel17.setBackground(new java.awt.Color(38, 28, 73));
        jPanel17.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        tblSanPhamChiTiet.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã SPCT", "Tên sản phẩm", "Số lượng", "Giá bán", "Màu sắc", "Kích cỡ", "Chất liệu", "Cổ áo", "Chiều dài tay", "Xuất xứ"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblSanPhamChiTiet.setColorBackgoundHead(new java.awt.Color(102, 102, 102));
        tblSanPhamChiTiet.setColorBordeFilas(new java.awt.Color(153, 153, 153));
        tblSanPhamChiTiet.setColorBordeHead(new java.awt.Color(153, 153, 153));
        tblSanPhamChiTiet.setColorFilasBackgound1(new java.awt.Color(204, 204, 204));
        tblSanPhamChiTiet.setColorFilasForeground1(new java.awt.Color(0, 0, 0));
        tblSanPhamChiTiet.setColorFilasForeground2(new java.awt.Color(0, 0, 0));
        tblSanPhamChiTiet.setColorSelBackgound(new java.awt.Color(38, 28, 73));
        tblSanPhamChiTiet.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tblSanPhamChiTiet.setFuenteFilas(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tblSanPhamChiTiet.setFuenteFilasSelect(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        tblSanPhamChiTiet.setFuenteHead(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        tblSanPhamChiTiet.setRowHeight(40);
        tblSanPhamChiTiet.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSanPhamChiTietMouseClicked(evt);
            }
        });
        scrollPaneWin113.setViewportView(tblSanPhamChiTiet);
        if (tblSanPhamChiTiet.getColumnModel().getColumnCount() > 0) {
            tblSanPhamChiTiet.getColumnModel().getColumn(0).setResizable(false);
            tblSanPhamChiTiet.getColumnModel().getColumn(1).setResizable(false);
            tblSanPhamChiTiet.getColumnModel().getColumn(2).setResizable(false);
            tblSanPhamChiTiet.getColumnModel().getColumn(3).setResizable(false);
            tblSanPhamChiTiet.getColumnModel().getColumn(4).setResizable(false);
            tblSanPhamChiTiet.getColumnModel().getColumn(5).setResizable(false);
            tblSanPhamChiTiet.getColumnModel().getColumn(6).setResizable(false);
            tblSanPhamChiTiet.getColumnModel().getColumn(7).setResizable(false);
            tblSanPhamChiTiet.getColumnModel().getColumn(8).setResizable(false);
            tblSanPhamChiTiet.getColumnModel().getColumn(9).setResizable(false);
        }

        paginationSanPhamChiTiet.setBackground(new java.awt.Color(38, 28, 73));
        paginationSanPhamChiTiet.setForeground(new java.awt.Color(38, 28, 73));

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scrollPaneWin113, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(paginationSanPhamChiTiet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(566, 566, 566))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scrollPaneWin113, javax.swing.GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(paginationSanPhamChiTiet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        txtTimKiemSanPhamChiTiet.setForeground(new java.awt.Color(0, 0, 0));
        txtTimKiemSanPhamChiTiet.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Tìm kiếm");

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(18, 18, 18)
                        .addComponent(txtTimKiemSanPhamChiTiet, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtTimKiemSanPhamChiTiet, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 28, Short.MAX_VALUE))
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(48, Short.MAX_VALUE))
        );

        tabbedPaneCustom1.addTab("Sản phẩm chi tiết", jPanel18);

        jPanel19.setBackground(new java.awt.Color(38, 28, 73));

        jPanel10.setBackground(new java.awt.Color(38, 28, 73));
        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thiết lập thuộc tính", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 16), new java.awt.Color(255, 255, 255))); // NOI18N

        jPanel11.setBackground(new java.awt.Color(38, 28, 73));
        jPanel11.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel21.setBackground(new java.awt.Color(38, 28, 73));

        txtMaThuocTinh.setBackground(new java.awt.Color(153, 153, 153));
        txtMaThuocTinh.setForeground(new java.awt.Color(0, 0, 0));
        txtMaThuocTinh.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Mã thuộc tính");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Tên thuộc tính");

        txtTenThuocTinh.setForeground(new java.awt.Color(0, 0, 0));
        txtTenThuocTinh.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel21Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addGap(18, 18, 18))
                    .addGroup(jPanel21Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addGap(21, 21, 21)))
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtMaThuocTinh, javax.swing.GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE)
                    .addComponent(txtTenThuocTinh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(12, 12, 12))
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMaThuocTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTenThuocTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(8, Short.MAX_VALUE))
        );

        jPanel22.setBackground(new java.awt.Color(38, 28, 73));

        rdoThuongHieu.setBackground(new java.awt.Color(38, 28, 73));
        rdoThuocTinh.add(rdoThuongHieu);
        rdoThuongHieu.setForeground(new java.awt.Color(255, 255, 255));
        rdoThuongHieu.setText("Thương hiệu");
        rdoThuongHieu.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        rdoDanhMuc.setBackground(new java.awt.Color(38, 28, 73));
        rdoThuocTinh.add(rdoDanhMuc);
        rdoDanhMuc.setForeground(new java.awt.Color(255, 255, 255));
        rdoDanhMuc.setText("Danh mục");
        rdoDanhMuc.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        rdoChatLieu.setBackground(new java.awt.Color(38, 28, 73));
        rdoThuocTinh.add(rdoChatLieu);
        rdoChatLieu.setForeground(new java.awt.Color(255, 255, 255));
        rdoChatLieu.setText("Chất liệu");
        rdoChatLieu.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        rdoMauSac.setBackground(new java.awt.Color(38, 28, 73));
        rdoThuocTinh.add(rdoMauSac);
        rdoMauSac.setForeground(new java.awt.Color(255, 255, 255));
        rdoMauSac.setText("Màu sắc");
        rdoMauSac.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        rdoKichCo.setBackground(new java.awt.Color(38, 28, 73));
        rdoThuocTinh.add(rdoKichCo);
        rdoKichCo.setForeground(new java.awt.Color(255, 255, 255));
        rdoKichCo.setText("Kích cỡ");
        rdoKichCo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        rdoCoAo.setBackground(new java.awt.Color(38, 28, 73));
        rdoThuocTinh.add(rdoCoAo);
        rdoCoAo.setForeground(new java.awt.Color(255, 255, 255));
        rdoCoAo.setText("Cổ áo");
        rdoCoAo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        rdoChieuDaiTay.setBackground(new java.awt.Color(38, 28, 73));
        rdoThuocTinh.add(rdoChieuDaiTay);
        rdoChieuDaiTay.setForeground(new java.awt.Color(255, 255, 255));
        rdoChieuDaiTay.setText("Chiều dài tay");
        rdoChieuDaiTay.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rdoThuongHieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rdoKichCo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(49, 49, 49)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rdoDanhMuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rdoCoAo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 53, Short.MAX_VALUE)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel22Layout.createSequentialGroup()
                        .addComponent(rdoChatLieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(55, 55, 55)
                        .addComponent(rdoMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(rdoChieuDaiTay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdoThuongHieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rdoDanhMuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rdoChatLieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rdoMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdoKichCo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rdoCoAo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rdoChieuDaiTay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel23.setBackground(new java.awt.Color(38, 28, 73));

        btnLamMoiThuocTinh.setForeground(new java.awt.Color(0, 0, 0));
        btnLamMoiThuocTinh.setText("Làm mới");
        btnLamMoiThuocTinh.setColor1(new java.awt.Color(255, 255, 255));
        btnLamMoiThuocTinh.setColor2(new java.awt.Color(102, 102, 102));
        btnLamMoiThuocTinh.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnLamMoiThuocTinh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiThuocTinhActionPerformed(evt);
            }
        });

        btnSuaThuocTinh.setForeground(new java.awt.Color(0, 0, 0));
        btnSuaThuocTinh.setText("Cập nhật");
        btnSuaThuocTinh.setColor1(new java.awt.Color(255, 255, 255));
        btnSuaThuocTinh.setColor2(new java.awt.Color(102, 102, 102));
        btnSuaThuocTinh.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnSuaThuocTinh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaThuocTinhActionPerformed(evt);
            }
        });

        btnThemThuocTinh.setForeground(new java.awt.Color(0, 0, 0));
        btnThemThuocTinh.setText("Thêm");
        btnThemThuocTinh.setColor1(new java.awt.Color(255, 255, 255));
        btnThemThuocTinh.setColor2(new java.awt.Color(102, 102, 102));
        btnThemThuocTinh.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnThemThuocTinh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemThuocTinhActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnSuaThuocTinh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnLamMoiThuocTinh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnThemThuocTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel23Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnThemThuocTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnSuaThuocTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnLamMoiThuocTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addContainerGap(107, Short.MAX_VALUE)
                .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(94, 94, 94))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addContainerGap(23, Short.MAX_VALUE)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21))
        );

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel12.setBackground(new java.awt.Color(38, 28, 73));
        jPanel12.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh sách thuộc tính", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 16), new java.awt.Color(255, 255, 255))); // NOI18N

        jPanel13.setBackground(new java.awt.Color(38, 28, 73));
        jPanel13.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        tblThuocTinh.setAutoCreateRowSorter(true);
        tblThuocTinh.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "STT", "Mã thuộc tính", "Tên thuộc tính"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblThuocTinh.setColorBackgoundHead(new java.awt.Color(102, 102, 102));
        tblThuocTinh.setColorBordeFilas(new java.awt.Color(153, 153, 153));
        tblThuocTinh.setColorBordeHead(new java.awt.Color(153, 153, 153));
        tblThuocTinh.setColorFilasBackgound1(new java.awt.Color(204, 204, 204));
        tblThuocTinh.setColorFilasForeground1(new java.awt.Color(0, 0, 0));
        tblThuocTinh.setColorFilasForeground2(new java.awt.Color(0, 0, 0));
        tblThuocTinh.setColorSelBackgound(new java.awt.Color(38, 28, 73));
        tblThuocTinh.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tblThuocTinh.setFuenteFilas(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tblThuocTinh.setFuenteFilasSelect(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        tblThuocTinh.setFuenteHead(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        tblThuocTinh.setRowHeight(40);
        tblThuocTinh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblThuocTinhMouseClicked(evt);
            }
        });
        tbl.setViewportView(tblThuocTinh);
        if (tblThuocTinh.getColumnModel().getColumnCount() > 0) {
            tblThuocTinh.getColumnModel().getColumn(0).setMaxWidth(100);
            tblThuocTinh.getColumnModel().getColumn(1).setResizable(false);
            tblThuocTinh.getColumnModel().getColumn(2).setResizable(false);
        }

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(tbl, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(130, 130, 130))
        );

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Tìm kiếm");

        txtTimKiemThuocTinh.setForeground(new java.awt.Color(0, 0, 0));
        txtTimKiemThuocTinh.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(18, 18, 18)
                        .addComponent(txtTimKiemThuocTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtTimKiemThuocTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(126, 126, 126))
        );

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(23, Short.MAX_VALUE))
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, 441, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(36, Short.MAX_VALUE))
        );

        tabbedPaneCustom1.addTab("Thuộc tính", jPanel19);

        jPanel1.add(tabbedPaneCustom1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 1310, 770));

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1330, 790));
    }// </editor-fold>//GEN-END:initComponents

    private void btnLamMoiThuocTinhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiThuocTinhActionPerformed
        txtMaThuocTinh.setText("");
        txtTenThuocTinh.setText("");
    }//GEN-LAST:event_btnLamMoiThuocTinhActionPerformed

    private void btnSuaThuocTinhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaThuocTinhActionPerformed
        if (rdoThuongHieu.isSelected()) {
            int confirm = JOptionPane.showConfirmDialog(this, "Xác nhận!", "Chỉnh sửa thương hiệu!", JOptionPane.YES_NO_OPTION);
            if (confirm == 0) {
                JOptionPane.showMessageDialog(this, thuongHieuService.update(updateThuongHieu()));
                ThuongHieu thuongHieuMoi = thuongHieuService.getAll().get(0);
                cbbThuongHieu.insertItemAt(thuongHieuMoi, 0);
                cbbThuongHieu.setSelectedItem(thuongHieuMoi);
                loadDataComboboxProperties(dcbmThuongHieu, "ThuongHieu");
                showDataProperties();
            }
        } else if (rdoDanhMuc.isSelected()) {
            int confirm = JOptionPane.showConfirmDialog(this, "Xác nhận!", "Chỉnh sửa danh mục!", JOptionPane.YES_NO_OPTION);
            if (confirm == 0) {
                JOptionPane.showMessageDialog(this, danhMucService.update(updateDanhMuc()));
                DanhMuc danhMucMoi = danhMucService.getAll().get(0);
                cbbDanhMuc.insertItemAt(danhMucMoi, 0);
                cbbDanhMuc.setSelectedItem(danhMucMoi);
                loadDataComboboxProperties(dcbmDanhMuc, "DanhMuc");
                showDataProperties();
            }
        } else if (rdoChatLieu.isSelected()) {
            int confirm = JOptionPane.showConfirmDialog(this, "Xác nhận!", "Chỉnh sửa chất liệu!", JOptionPane.YES_NO_OPTION);
            if (confirm == 0) {
                JOptionPane.showMessageDialog(this, chatLieuService.update(updateChatLieu()));
                ChatLieu chatLieuMoi = chatLieuService.getAll().get(0);
                cbbChatLieu.insertItemAt(chatLieuMoi, 0);
                cbbChatLieu.setSelectedItem(chatLieuMoi);
                loadDataComboboxProperties(dcbmChatLieu, "ChatLieu");
                showDataProperties();
            }
        } else if (rdoMauSac.isSelected()) {
            int confirm = JOptionPane.showConfirmDialog(this, "Xác nhận!", "Chỉnh sửa màu sắc!", JOptionPane.YES_NO_OPTION);
            if (confirm == 0) {
                JOptionPane.showMessageDialog(this, mauSacService.update(updateMauSac()));
                MauSac mauSacMoi = mauSacService.getAll().get(0);
                cbbMauSac.insertItemAt(mauSacMoi, 0);
                cbbMauSac.setSelectedItem(mauSacMoi);
                loadDataComboboxProperties(dcbmMauSac, "MauSac");
                showDataProperties();
            }
        } else if (rdoKichCo.isSelected()) {
            int confirm = JOptionPane.showConfirmDialog(this, "Xác nhận!", "Chỉnh sửa kích cỡ!", JOptionPane.YES_NO_OPTION);
            if (confirm == 0) {
                JOptionPane.showMessageDialog(this, kichCoService.update(updateKichCo()));
                KichCo kichCoMoi = kichCoService.getAll().get(0);
                cbbKichCo.insertItemAt(kichCoMoi, 0);
                cbbKichCo.setSelectedItem(kichCoMoi);
                loadDataComboboxProperties(dcbmKichCo, "KichCo");
                showDataProperties();
            }
        } else if (rdoCoAo.isSelected()) {
            int confirm = JOptionPane.showConfirmDialog(this, "Xác nhận!", "Chỉnh sửa cổ áo!", JOptionPane.YES_NO_OPTION);
            if (confirm == 0) {
                JOptionPane.showMessageDialog(this, coAoService.update(updateCoAo()));
                CoAo coAoMoi = coAoService.getAll().get(0);
                cbbCoAo.insertItemAt(coAoMoi, 0);
                cbbCoAo.setSelectedItem(coAoMoi);
                loadDataComboboxProperties(dcbmCoAo, "CoAo");
                showDataProperties();
            }
        } else if (rdoChieuDaiTay.isSelected()) {
            int confirm = JOptionPane.showConfirmDialog(this, "Xác nhận!", "Chỉnh sửa chiều dài tay!", JOptionPane.YES_NO_OPTION);
            if (confirm == 0) {
                JOptionPane.showMessageDialog(this, chieuDaiTayService.update(updateChieuDaiTay()));
                ChieuDaiTay chieuDaiTayMoi = chieuDaiTayService.getAll().get(0);
                cbbChieuDaiTay.insertItemAt(chieuDaiTayMoi, 0);
                cbbChieuDaiTay.setSelectedItem(chieuDaiTayMoi);
                loadDataComboboxProperties(dcbmChieuDaiTay, "ChieuDaiTay");
                showDataProperties();
            }
        }
    }//GEN-LAST:event_btnSuaThuocTinhActionPerformed

    private void btnThemThuocTinhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemThuocTinhActionPerformed
        if (rdoThuongHieu.isSelected()) {
            int confirm = JOptionPane.showConfirmDialog(this, "Xác nhận thêm!", "Thêm thương hiệu!", JOptionPane.YES_NO_OPTION);
            if (confirm == 0) {
                JOptionPane.showMessageDialog(this, thuongHieuService.add(addThuongHieu()));
                ThuongHieu thuongHieuMoi = thuongHieuService.getAll().get(0);
                cbbThuongHieu.insertItemAt(thuongHieuMoi, 0);
                cbbThuongHieu.setSelectedItem(thuongHieuMoi);
                loadDataComboboxProperties(dcbmThuongHieu, "ThuongHieu");
                showDataProperties();
            }
        } else if (rdoDanhMuc.isSelected()) {
            int confirm = JOptionPane.showConfirmDialog(this, "Xác nhận thêm!", "Thêm danh mục!", JOptionPane.YES_NO_OPTION);
            if (confirm == 0) {
                JOptionPane.showMessageDialog(this, danhMucService.add(addDanhMuc()));
                DanhMuc danhMucMoi = danhMucService.getAll().get(0);
                cbbDanhMuc.insertItemAt(danhMucMoi, 0);
                cbbDanhMuc.setSelectedItem(danhMucMoi);
                loadDataComboboxProperties(dcbmDanhMuc, "DanhMuc");
                showDataProperties();
            }
        } else if (rdoChatLieu.isSelected()) {
            int confirm = JOptionPane.showConfirmDialog(this, "Xác nhận thêm!", "Thêm chất liệu!", JOptionPane.YES_NO_OPTION);
            if (confirm == 0) {
                JOptionPane.showMessageDialog(this, chatLieuService.add(addChatLieu()));
                ChatLieu chatLieuMoi = chatLieuService.getAll().get(0);
                cbbChatLieu.insertItemAt(chatLieuMoi, 0);
                cbbChatLieu.setSelectedItem(chatLieuMoi);
                loadDataComboboxProperties(dcbmChatLieu, "ChatLieu");
                showDataProperties();
            }
        } else if (rdoMauSac.isSelected()) {
            int confirm = JOptionPane.showConfirmDialog(this, "Xác nhận thêm!", "Thêm màu sắc!", JOptionPane.YES_NO_OPTION);
            if (confirm == 0) {
                JOptionPane.showMessageDialog(this, mauSacService.add(addMauSac()));
                MauSac mauSacMoi = mauSacService.getAll().get(0);
                cbbMauSac.insertItemAt(mauSacMoi, 0);
                cbbMauSac.setSelectedItem(mauSacMoi);
                loadDataComboboxProperties(dcbmMauSac, "MauSac");
                showDataProperties();
            }
        } else if (rdoKichCo.isSelected()) {
            int confirm = JOptionPane.showConfirmDialog(this, "Xác nhận thêm!", "Thêm kích cỡ!", JOptionPane.YES_NO_OPTION);
            if (confirm == 0) {
                JOptionPane.showMessageDialog(this, kichCoService.add(addKichCo()));
                KichCo kichCoMoi = kichCoService.getAll().get(0);
                cbbKichCo.insertItemAt(kichCoMoi, 0);
                cbbKichCo.setSelectedItem(kichCoMoi);
                loadDataComboboxProperties(dcbmKichCo, "KichCo");
                showDataProperties();
            }
        } else if (rdoCoAo.isSelected()) {
            int confirm = JOptionPane.showConfirmDialog(this, "Xác nhận thêm!", "Thêm cổ áo!", JOptionPane.YES_NO_OPTION);
            if (confirm == 0) {
                JOptionPane.showMessageDialog(this, coAoService.add(addCoAo()));
                CoAo coAoMoi = coAoService.getAll().get(0);
                cbbCoAo.insertItemAt(coAoMoi, 0);
                cbbCoAo.setSelectedItem(coAoMoi);
                loadDataComboboxProperties(dcbmCoAo, "CoAo");
                showDataProperties();
            }
        } else if (rdoChieuDaiTay.isSelected()) {
            int confirm = JOptionPane.showConfirmDialog(this, "Xác nhận thêm!", "Thêm chiều dài tay!", JOptionPane.YES_NO_OPTION);
            if (confirm == 0) {
                JOptionPane.showMessageDialog(this, chieuDaiTayService.add(addChieuDaiTay()));
                ChieuDaiTay chieuDaiTayMoi = chieuDaiTayService.getAll().get(0);
                cbbChieuDaiTay.insertItemAt(chieuDaiTayMoi, 0);
                cbbChieuDaiTay.setSelectedItem(chieuDaiTayMoi);
                loadDataComboboxProperties(dcbmChieuDaiTay, "ChieuDaiTay");
                showDataProperties();
            }
        }
    }//GEN-LAST:event_btnThemThuocTinhActionPerformed

    private void tblSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSanPhamMouseClicked
        int index = tblSanPham.getSelectedRow();
        int limit = 10;
        int offset = (currentPage - 1) * limit + index;
        if (index >= 0) {
            SanPham selectedSanPham = sanPhamService.getAll(offset, 1).get(0);
            fillDataSanPham(selectedSanPham);
        }
    }//GEN-LAST:event_tblSanPhamMouseClicked

    private void btnThemNhanhChatLieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemNhanhChatLieuActionPerformed
        jDialogThemNhanhChatLieu.setVisible(true);
        jDialogThemNhanhChatLieu.setSize(415, 250);
        jDialogThemNhanhChatLieu.setLocation(800, 350);
    }//GEN-LAST:event_btnThemNhanhChatLieuActionPerformed

    private void btnThemNhanhCoAoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemNhanhCoAoActionPerformed
        jDialogThemNhanhCoAo.setVisible(true);
        jDialogThemNhanhCoAo.setSize(415, 250);
        jDialogThemNhanhCoAo.setLocation(800, 350);
    }//GEN-LAST:event_btnThemNhanhCoAoActionPerformed

    private void btnThemNhanhMauSacActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemNhanhMauSacActionPerformed
        jDialogThemNhanhMauSac.setVisible(true);
        jDialogThemNhanhMauSac.setSize(415, 250);
        jDialogThemNhanhMauSac.setLocation(800, 350);
    }//GEN-LAST:event_btnThemNhanhMauSacActionPerformed

    private void btnThemNhanhKichCoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemNhanhKichCoActionPerformed
        jDialogThemNhanhKichCo.setVisible(true);
        jDialogThemNhanhKichCo.setSize(415, 250);
        jDialogThemNhanhKichCo.setLocation(800, 350);
    }//GEN-LAST:event_btnThemNhanhKichCoActionPerformed

    private void btnThemNhanhChieuDaiTayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemNhanhChieuDaiTayActionPerformed
        jDialogThemNhanhChieuDaiTay.setVisible(true);
        jDialogThemNhanhChieuDaiTay.setSize(415, 250);
        jDialogThemNhanhChieuDaiTay.setLocation(800, 350);
    }//GEN-LAST:event_btnThemNhanhChieuDaiTayActionPerformed

    private void btnThemNhanhThuongHieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemNhanhThuongHieuActionPerformed
        jDialogThemNhanhThuongHieu.setVisible(true);
        jDialogThemNhanhThuongHieu.setSize(415, 250);
        jDialogThemNhanhThuongHieu.setLocation(800, 350);
    }//GEN-LAST:event_btnThemNhanhThuongHieuActionPerformed

    private void btnThemNhanhDanhMucActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemNhanhDanhMucActionPerformed
        jDialogThemNhanhDanhMuc.setVisible(true);
        jDialogThemNhanhDanhMuc.setSize(415, 250);
        jDialogThemNhanhDanhMuc.setLocation(800, 350);
    }//GEN-LAST:event_btnThemNhanhDanhMucActionPerformed

    private void btnThemSanPhamChiTietActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemSanPhamChiTietActionPerformed
        int confirm = JOptionPane.showConfirmDialog(this, "Xác nhận thêm!", "Thêm sản phẩm!", JOptionPane.YES_NO_OPTION);
        SanPham sanPham = (SanPham) cbbSanPham.getSelectedItem();
        if (confirm == 0) {
            JOptionPane.showMessageDialog(this, sanPhamChiTietService.add(addSanPhamChiTiet()));
        }
        showDataSanPhamChiTiet(sanPham.getMa());

    }//GEN-LAST:event_btnThemSanPhamChiTietActionPerformed

    private void tblSanPhamChiTietMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSanPhamChiTietMouseClicked
        int index = tblSanPhamChiTiet.getSelectedRow();
        int limit = 10;
        int offset = (currentPage - 1) * limit + index;
        if (index >= 0) {
            SanPham selectedSanPham = (SanPham) cbbSanPham.getSelectedItem();
            SanPhamChiTiet selectedSanPhamChiTiet = (SanPhamChiTiet) sanPhamChiTietService.getAllByIdSanPham(selectedSanPham.getMa(), offset, 1).get(0);
            cbbKichCo.disable();
            cbbMauSac.disable();
            cbbChatLieu.disable();
            cbbCoAo.disable();
            cbbChieuDaiTay.disable();
            cbbXuatXu.disable();
            fillDataSanPhamChiTiet(selectedSanPhamChiTiet);
        }
    }//GEN-LAST:event_tblSanPhamChiTietMouseClicked

    private void btnSuaSanPhamChiTietActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaSanPhamChiTietActionPerformed
        int confirm = JOptionPane.showConfirmDialog(this, "Xác nhận cập nhật!", "Cập nhật sản phẩm!", JOptionPane.YES_NO_OPTION);
        if (confirm == 0) {
            JOptionPane.showMessageDialog(this, sanPhamChiTietService.update(updateSanPhamChiTiet()));
            SanPham sanPham = (SanPham) cbbSanPham.getSelectedItem();
            loadDataSanPhamChiTietPage(1, sanPham.getMa());
        }
    }//GEN-LAST:event_btnSuaSanPhamChiTietActionPerformed

    private void btnLamMoiSanPhamChiTietActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiSanPhamChiTietActionPerformed
        txtMaCTSP.setText("");
        txtSoLuong.setText("");
        txtGiaBan.setText("");
        txtMoTa.setText("");
        cbbChatLieu.enable();
        cbbChieuDaiTay.enable();
        cbbCoAo.enable();
        cbbKichCo.enable();
        cbbMauSac.enable();
        cbbXuatXu.enable();
    }//GEN-LAST:event_btnLamMoiSanPhamChiTietActionPerformed

    private void btnImportExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImportExcelActionPerformed
        try {
            JFileChooser fc = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Exel File", "xlsx");
            fc.setFileFilter(filter);
            int check = fc.showOpenDialog(null);
            File file = null;
            if (check == JFileChooser.APPROVE_OPTION) {
                file = fc.getSelectedFile();
            }
            if (file == null) {
                return;
            }
            ImportExcelSanPhamChiTiet excelCTSP = new ImportExcelSanPhamChiTiet();
            excelCTSP.ImportFile(file.getAbsolutePath());
            SanPham sanPham = (SanPham) cbbSanPham.getSelectedItem();
            loadDataSanPhamChiTietPage(1, sanPham.getMa());
            loadDataSanPhamPage(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnImportExcelActionPerformed

    private void btnThemSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemSanPhamActionPerformed
        int confirm = JOptionPane.showConfirmDialog(this, "Xác nhận thêm!", "Thêm sản phẩm!", JOptionPane.YES_NO_OPTION);
        if (confirm == 0) {
            JOptionPane.showMessageDialog(this, sanPhamService.add(addSanPham()));
            SanPham sanPhamMoi = sanPhamService.getAll().get(0);
            cbbSanPham.insertItemAt(sanPhamMoi, 0);
            cbbSanPham.setSelectedItem(sanPhamMoi);
            loadDataSanPhamPage(1);
        }
    }//GEN-LAST:event_btnThemSanPhamActionPerformed

    private void txtMaCTSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaCTSPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaCTSPActionPerformed

    private void btnLamMoiSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiSanPhamActionPerformed
        txtMaSanPham.setText("");
        txtTenSanPham.setText("");
        cbbDanhMuc.setSelectedIndex(0);
        cbbThuongHieu.setSelectedIndex(0);
    }//GEN-LAST:event_btnLamMoiSanPhamActionPerformed

    private void btnSuaSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaSanPhamActionPerformed
        int confirm = JOptionPane.showConfirmDialog(this, "Xác nhận cập nhật!", "Cập nhật sản phẩm!", JOptionPane.YES_NO_OPTION);
        if (confirm == 0) {
            JOptionPane.showMessageDialog(this, sanPhamService.update(updateSanPham()));
        }
    }//GEN-LAST:event_btnSuaSanPhamActionPerformed

    private void btnKichCoThemNhanhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKichCoThemNhanhActionPerformed
        int confirm = JOptionPane.showConfirmDialog(this, "Xác nhận thêm!", "Thêm kích cỡ!", JOptionPane.YES_NO_OPTION);
        if (confirm == 0) {
            JOptionPane.showMessageDialog(this, kichCoService.add(addNhanhKichCo()));
            KichCo kichCoMoi = kichCoService.getAll().get(0);
            cbbKichCo.insertItemAt(kichCoMoi, 0);
            cbbKichCo.setSelectedItem(kichCoMoi);
            loadDataComboboxProperties(dcbmKichCo, "KichCo");
            showDataProperties();
        }
        txtTenKichCoThemNhanh.setText("");
    }//GEN-LAST:event_btnKichCoThemNhanhActionPerformed

    private void btnMauSacThemNhanhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMauSacThemNhanhActionPerformed
        int confirm = JOptionPane.showConfirmDialog(this, "Xác nhận thêm!", "Thêm màu sắc!", JOptionPane.YES_NO_OPTION);
        if (confirm == 0) {
            JOptionPane.showMessageDialog(this, mauSacService.add(addNhanhMauSac()));
            MauSac mauSacMoi = mauSacService.getAll().get(0);
            cbbMauSac.insertItemAt(mauSacMoi, 0);
            cbbMauSac.setSelectedItem(mauSacMoi);
            loadDataComboboxProperties(dcbmMauSac, "MauSac");
            showDataProperties();
        }
        txtTenMauSacThemNhanh.setText("");
    }//GEN-LAST:event_btnMauSacThemNhanhActionPerformed

    private void btnChatLieuThemNhanhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChatLieuThemNhanhActionPerformed
        int confirm = JOptionPane.showConfirmDialog(this, "Xác nhận thêm!", "Thêm chất liệu!", JOptionPane.YES_NO_OPTION);
        if (confirm == 0) {
            JOptionPane.showMessageDialog(this, chatLieuService.add(addNhanhChatLieu()));
            ChatLieu chatLieuMoi = chatLieuService.getAll().get(0);
            cbbChatLieu.insertItemAt(chatLieuMoi, 0);
            cbbChatLieu.setSelectedItem(chatLieuMoi);
            loadDataComboboxProperties(dcbmChatLieu, "ChatLieu");
            showDataProperties();
        }
        txtTenChatLieuThemNhanh.setText("");
    }//GEN-LAST:event_btnChatLieuThemNhanhActionPerformed

    private void btnChatLieuLamMoiThemNhanhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChatLieuLamMoiThemNhanhActionPerformed
        txtTenChatLieuThemNhanh.setText("");
    }//GEN-LAST:event_btnChatLieuLamMoiThemNhanhActionPerformed

    private void btnCoAoThemNhanhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCoAoThemNhanhActionPerformed
        int confirm = JOptionPane.showConfirmDialog(this, "Xác nhận thêm!", "Thêm cổ áo!", JOptionPane.YES_NO_OPTION);
        if (confirm == 0) {
            JOptionPane.showMessageDialog(this, coAoService.add(addNhanhCoAo()));
            CoAo coAoMoi = coAoService.getAll().get(0);
            cbbCoAo.insertItemAt(coAoMoi, 0);
            cbbCoAo.setSelectedItem(coAoMoi);
            loadDataComboboxProperties(dcbmCoAo, "CoAo");
            showDataProperties();
        }
        txtTenCoAoThemNhanh.setText("");
    }//GEN-LAST:event_btnCoAoThemNhanhActionPerformed

    private void btnCoAoLamMoiThemNhanhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCoAoLamMoiThemNhanhActionPerformed
        txtTenCoAoThemNhanh.setText("");
    }//GEN-LAST:event_btnCoAoLamMoiThemNhanhActionPerformed

    private void btnChieuDaiTayThemNhanhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChieuDaiTayThemNhanhActionPerformed
        int confirm = JOptionPane.showConfirmDialog(this, "Xác nhận thêm!", "Thêm chiều dài tay!", JOptionPane.YES_NO_OPTION);
        if (confirm == 0) {
            JOptionPane.showMessageDialog(this, chieuDaiTayService.add(addNhanhChieuDaiTay()));
            ChieuDaiTay chieuDaiTayMoi = chieuDaiTayService.getAll().get(0);
            cbbChieuDaiTay.insertItemAt(chieuDaiTayMoi, 0);
            cbbChieuDaiTay.setSelectedItem(chieuDaiTayMoi);
            loadDataComboboxProperties(dcbmChieuDaiTay, "ChieuDaiTay");
            showDataProperties();
        }
        txtTenChieuDaiTayThemNhanh.setText("");
    }//GEN-LAST:event_btnChieuDaiTayThemNhanhActionPerformed

    private void btnChieuDaiTayLamMoiThemNhanhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChieuDaiTayLamMoiThemNhanhActionPerformed
        txtTenChieuDaiTayThemNhanh.setText("");
    }//GEN-LAST:event_btnChieuDaiTayLamMoiThemNhanhActionPerformed

    private void btnKichCoLamMoiThemNhanhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKichCoLamMoiThemNhanhActionPerformed
        txtTenKichCoThemNhanh.setText("");
    }//GEN-LAST:event_btnKichCoLamMoiThemNhanhActionPerformed

    private void btnMauSacLamMoiThemNhanhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMauSacLamMoiThemNhanhActionPerformed
        txtTenMauSacThemNhanh.setText("");
    }//GEN-LAST:event_btnMauSacLamMoiThemNhanhActionPerformed

    private void btnThuongHieuThemNhanhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThuongHieuThemNhanhActionPerformed
        int confirm = JOptionPane.showConfirmDialog(this, "Xác nhận thêm!", "Thêm thương hiệu!", JOptionPane.YES_NO_OPTION);
        if (confirm == 0) {
            JOptionPane.showMessageDialog(this, thuongHieuService.add(addNhanhThuongHieu()));
            ThuongHieu thuongHieuMoi = thuongHieuService.getAll().get(0);
            cbbThuongHieu.insertItemAt(thuongHieuMoi, 0);
            cbbThuongHieu.setSelectedItem(thuongHieuMoi);
            loadDataComboboxProperties(dcbmThuongHieu, "ThuongHieu");
            showDataProperties();
        }
        txtTenThuongHieuThemNhanh.setText("");
    }//GEN-LAST:event_btnThuongHieuThemNhanhActionPerformed

    private void btnDanhMucThemNhanhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDanhMucThemNhanhActionPerformed
        int confirm = JOptionPane.showConfirmDialog(this, "Xác nhận thêm!", "Thêm danh mục!", JOptionPane.YES_NO_OPTION);
        if (confirm == 0) {
            JOptionPane.showMessageDialog(this, danhMucService.add(addNhanhDanhMuc()));
            DanhMuc danhMucMoi = danhMucService.getAll().get(0);
            cbbDanhMuc.insertItemAt(danhMucMoi, 0);
            cbbDanhMuc.setSelectedItem(danhMucMoi);
            loadDataComboboxProperties(dcbmDanhMuc, "DanhMuc");
            showDataProperties();
        }
        txtTenDanhMucThemNhanh.setText("");
    }//GEN-LAST:event_btnDanhMucThemNhanhActionPerformed

    private void btnThuongHieuLamMoiThemNhanhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThuongHieuLamMoiThemNhanhActionPerformed
        txtTenThuongHieuThemNhanh.setText("");
    }//GEN-LAST:event_btnThuongHieuLamMoiThemNhanhActionPerformed

    private void btnDanhMucLamMoiThemNhanhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDanhMucLamMoiThemNhanhActionPerformed
        txtTenDanhMucThemNhanh.setText("");
    }//GEN-LAST:event_btnDanhMucLamMoiThemNhanhActionPerformed

    private void btnTaiExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaiExcelActionPerformed
        DownloadProductDetailTemplate.ImportExcel();
    }//GEN-LAST:event_btnTaiExcelActionPerformed

    private void cbbThuongHieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbThuongHieuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbThuongHieuActionPerformed

    private void cbbDanhMucActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbDanhMucActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbDanhMucActionPerformed

    private void txtMoTaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMoTaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMoTaActionPerformed

    private void tblThuocTinhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblThuocTinhMouseClicked
        int index = tblThuocTinh.getSelectedRow();
        if (rdoThuongHieu.isSelected()) {
            fillDataThuongHieu(thuongHieuService.getAll().get(index));
        } else if (rdoDanhMuc.isSelected()) {
            fillDataDanhMuc(danhMucService.getAll().get(index));
        } else if (rdoChatLieu.isSelected()) {
            fillDataChatLieu(chatLieuService.getAll().get(index));
        } else if (rdoMauSac.isSelected()) {
            fillDataMauSac(mauSacService.getAll().get(index));
        } else if (rdoKichCo.isSelected()) {
            fillDataKichCo(kichCoService.getAll().get(index));
        } else if (rdoCoAo.isSelected()) {
            fillDataCoAo(coAoService.getAll().get(index));
        } else if (rdoChieuDaiTay.isSelected()) {
            fillDataChieuDaiTay(chieuDaiTayService.getAll().get(index));
        }
    }//GEN-LAST:event_tblThuocTinhMouseClicked

    private void tblExportExcelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblExportExcelMouseClicked

    }//GEN-LAST:event_tblExportExcelMouseClicked

    private void btnExportExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportExcelActionPerformed
        jDialogExportExcel.setVisible(true);
        jDialogExportExcel.setSize(1200, 800);
        jDialogExportExcel.setLocation(400, 130);
    }//GEN-LAST:event_btnExportExcelActionPerformed

    private void openFile(String file) {
        try {
            File path = new File(file);
            Desktop.getDesktop().open(path);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    private void btnExportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportActionPerformed
        try {
            JFileChooser jFileChooser = new JFileChooser();
            jFileChooser.showSaveDialog(this);
            File saveFile = jFileChooser.getSelectedFile();
            if (saveFile != null) {
                saveFile = new File(saveFile.toString() + ".xlsx");
                Workbook wb = new XSSFWorkbook();
                Sheet sheet = wb.createSheet("Danh sách sản phẩm");

                Row rowCol = sheet.createRow(0);
                for (int i = 0; i < tblExportExcel.getColumnCount(); i++) {
                    Cell cell = rowCol.createCell(i);
                    cell.setCellValue(tblExportExcel.getColumnName(i));
                }

                for (int j = 0; j < tblExportExcel.getRowCount(); j++) {
                    Row row = sheet.createRow(j + 1);
                    for (int k = 0; k < tblExportExcel.getColumnCount(); k++) {
                        Cell cell = row.createCell(k);
                        if (tblExportExcel.getValueAt(j, k) != null) {
                            cell.setCellValue(tblExportExcel.getValueAt(j, k).toString());
                        }
                    }
                }
                FileOutputStream out = new FileOutputStream(new File(saveFile.toString()));
                wb.write(out);
                wb.close();
                out.close();
                openFile(saveFile.toString());
            } else {
                JOptionPane.showMessageDialog(this, "Export file Excel không thành công!");
            }
        } catch (FileNotFoundException e) {

        } catch (IOException io) {
            io.printStackTrace();
        }
    }//GEN-LAST:event_btnExportActionPerformed

    private void openQRCodeFile(String filePath) {
        try {
            File file = new File(filePath);
            if (file.exists() && Desktop.isDesktopSupported()) {
                Desktop.getDesktop().open(file);
            } else {
                JOptionPane.showMessageDialog(this, "Không thể mở QR");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void btnTaiQRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaiQRActionPerformed
        int index = tblSanPhamChiTiet.getSelectedRow();
        if (index != -1) {
            String maSPCT = (String) tblSanPhamChiTiet.getValueAt(index, 0);
            SanPham sanPham = (SanPham) cbbSanPham.getSelectedItem();

            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            fileChooser.setDialogTitle("Chọn thư mục để lưu QR");

            int result = fileChooser.showOpenDialog(this);

            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedDirectory = fileChooser.getSelectedFile();

                boolean success = doGenerateQR(maSPCT, sanPham.getTen(), selectedDirectory.getAbsolutePath());

                if (success) {
                    JOptionPane.showMessageDialog(this, "Mã QR đã được tạo!");
                    openQRCodeFile(selectedDirectory.getAbsolutePath() + File.separator + maSPCT + "-QR.png");
                } else {
                    JOptionPane.showMessageDialog(this, "Không thể tạo mã QR!");
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Mời chọn sản phẩm chi tiết muốn tạo QR!");
        }
    }//GEN-LAST:event_btnTaiQRActionPerformed

    private void txtSoLuongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSoLuongActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSoLuongActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private app.view.swing.ButtonGradient btnChatLieuLamMoiThemNhanh;
    private app.view.swing.ButtonGradient btnChatLieuThemNhanh;
    private app.view.swing.ButtonGradient btnChieuDaiTayLamMoiThemNhanh;
    private app.view.swing.ButtonGradient btnChieuDaiTayThemNhanh;
    private app.view.swing.ButtonGradient btnCoAoLamMoiThemNhanh;
    private app.view.swing.ButtonGradient btnCoAoThemNhanh;
    private app.view.swing.ButtonGradient btnDanhMucLamMoiThemNhanh;
    private app.view.swing.ButtonGradient btnDanhMucThemNhanh;
    private app.view.swing.ButtonGradient btnExport;
    private app.view.swing.ButtonGradient btnExportExcel;
    private app.view.swing.ButtonGradient btnImportExcel;
    private app.view.swing.ButtonGradient btnKichCoLamMoiThemNhanh;
    private app.view.swing.ButtonGradient btnKichCoThemNhanh;
    private app.view.swing.ButtonGradient btnLamMoiSanPham;
    private app.view.swing.ButtonGradient btnLamMoiSanPhamChiTiet;
    private app.view.swing.ButtonGradient btnLamMoiThuocTinh;
    private app.view.swing.ButtonGradient btnMauSacLamMoiThemNhanh;
    private app.view.swing.ButtonGradient btnMauSacThemNhanh;
    private app.view.swing.ButtonGradient btnSuaSanPham;
    private app.view.swing.ButtonGradient btnSuaSanPhamChiTiet;
    private app.view.swing.ButtonGradient btnSuaThuocTinh;
    private app.view.swing.ButtonGradient btnTaiExcel;
    private app.view.swing.ButtonGradient btnTaiQR;
    private app.view.swing.Button btnThemNhanhChatLieu;
    private app.view.swing.Button btnThemNhanhChieuDaiTay;
    private app.view.swing.Button btnThemNhanhCoAo;
    private app.view.swing.Button btnThemNhanhDanhMuc;
    private app.view.swing.Button btnThemNhanhKichCo;
    private app.view.swing.Button btnThemNhanhMauSac;
    private app.view.swing.Button btnThemNhanhThuongHieu;
    private app.view.swing.ButtonGradient btnThemSanPham;
    private app.view.swing.ButtonGradient btnThemSanPhamChiTiet;
    private app.view.swing.ButtonGradient btnThemThuocTinh;
    private app.view.swing.ButtonGradient btnThuongHieuLamMoiThemNhanh;
    private app.view.swing.ButtonGradient btnThuongHieuThemNhanh;
    private app.view.swing.ComboBoxSuggestion cbbChatLieu;
    private app.view.swing.ComboBoxSuggestion cbbChatLieuExport;
    private app.view.swing.ComboBoxSuggestion cbbChieuDaiTay;
    private app.view.swing.ComboBoxSuggestion cbbChieuDaiTayExport;
    private app.view.swing.ComboBoxSuggestion cbbCoAo;
    private app.view.swing.ComboBoxSuggestion cbbCoAoExport;
    private app.view.swing.ComboBoxSuggestion cbbDanhMuc;
    private app.view.swing.ComboBoxSuggestion cbbKichCo;
    private app.view.swing.ComboBoxSuggestion cbbKichCoExport;
    private app.view.swing.ComboBoxSuggestion cbbMauSac;
    private app.view.swing.ComboBoxSuggestion cbbMauSacExport;
    private app.view.swing.ComboBoxSuggestion cbbSanPham;
    private app.view.swing.ComboBoxSuggestion cbbThuongHieu;
    private app.view.swing.ComboBoxSuggestion cbbXuatXu;
    private app.view.swing.ComboBoxSuggestion cbbXuatXuExport;
    private javax.swing.JDialog jDialogExportExcel;
    private javax.swing.JDialog jDialogThemNhanhChatLieu;
    private javax.swing.JDialog jDialogThemNhanhChieuDaiTay;
    private javax.swing.JDialog jDialogThemNhanhCoAo;
    private javax.swing.JDialog jDialogThemNhanhDanhMuc;
    private javax.swing.JDialog jDialogThemNhanhKichCo;
    private javax.swing.JDialog jDialogThemNhanhMauSac;
    private javax.swing.JDialog jDialogThemNhanhThuongHieu;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel30;
    private javax.swing.JPanel jPanel31;
    private javax.swing.JPanel jPanel32;
    private javax.swing.JPanel jPanel33;
    private javax.swing.JPanel jPanel34;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private app.view.swing.Pagination paginationSanPham;
    private app.view.swing.Pagination paginationSanPhamChiTiet;
    private radio_button.RadioButtonCustom rdoChatLieu;
    private radio_button.RadioButtonCustom rdoChieuDaiTay;
    private radio_button.RadioButtonCustom rdoCoAo;
    private radio_button.RadioButtonCustom rdoDanhMuc;
    private radio_button.RadioButtonCustom rdoKichCo;
    private radio_button.RadioButtonCustom rdoMauSac;
    private javax.swing.ButtonGroup rdoThuocTinh;
    private radio_button.RadioButtonCustom rdoThuongHieu;
    private custome_ui.swing.ScrollPaneWin11 scrollPaneWin111;
    private custome_ui.swing.ScrollPaneWin11 scrollPaneWin113;
    private custome_ui.swing.ScrollPaneWin11 scrollPaneWin114;
    private app.view.swing.TabbedPaneCustom tabbedPaneCustom1;
    private app.view.swing.TabbedPaneCustom tabbedPaneCustom2;
    private custome_ui.swing.ScrollPaneWin11 tbl;
    private rojeru_san.complementos.RSTableMetro tblExportExcel;
    private rojeru_san.complementos.RSTableMetro tblSanPham;
    private rojeru_san.complementos.RSTableMetro tblSanPhamChiTiet;
    private rojeru_san.complementos.RSTableMetro tblThuocTinh;
    private textfield.TextField2 textField26;
    private textfield.TextField2 txtGiaBan;
    private textfield.TextField2 txtMaCTSP;
    private textfield.TextField2 txtMaSanPham;
    private textfield.TextField2 txtMaThuocTinh;
    private textfield.TextField2 txtMoTa;
    private textfield.TextField2 txtSoLuong;
    private textfield.TextField2 txtTenChatLieuThemNhanh;
    private textfield.TextField2 txtTenChieuDaiTayThemNhanh;
    private textfield.TextField2 txtTenCoAoThemNhanh;
    private textfield.TextField2 txtTenDanhMucThemNhanh;
    private textfield.TextField2 txtTenKichCoThemNhanh;
    private textfield.TextField2 txtTenMauSacThemNhanh;
    private textfield.TextField2 txtTenSanPham;
    private textfield.TextField2 txtTenThuocTinh;
    private textfield.TextField2 txtTenThuongHieuThemNhanh;
    private textfield.TextField2 txtTimKiemSanPham;
    private textfield.TextField2 txtTimKiemSanPhamChiTiet;
    private textfield.TextField2 txtTimKiemThuocTinh;
    // End of variables declaration//GEN-END:variables

}
