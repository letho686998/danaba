/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamException;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.HoaDonBackup;
import model.HoaDonChiTietBackup;
import model.KhachHang;
import model.SanPhamChiTiet;
import service.hoadonservice.IHoaDonServices;
import service.hoadonservice.impl.HoaDonService;
import service.khachhangservice.IKhachHangServise;
import service.khachhangservice.impl.KhachHangServise;
import service.sanphamservice.ISanPhamChiTietService;
import service.sanphamservice.impl.SanPhamChiTietService;
import util.TinhTien;
import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import model.ChatLieu;
import model.ChieuDaiTay;
import model.CoAo;
import model.KichCo;
import model.MauSac;
import model.VoucherBackup;
import modelViews.QlKhachHang;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;
import service.sanphamservice.IThuocTinhService;
import service.sanphamservice.impl.ChatLieuService;
import service.sanphamservice.impl.ChieuDaiTayService;
import service.sanphamservice.impl.CoAoService;
import service.sanphamservice.impl.KichCoService;
import service.sanphamservice.impl.MauSacService;
import service.voucherservice.IVoucherServise;
import service.voucherservice.impl.VoucherService;
import util.DBConnect;
import util.Uhelper;
import static util.ValidateForm.isPositiveNumber;
import static util.ValidateForm.isCheckEmpty;

/**
 *
 * @author bcuon
 */
public class ViewBanHang extends javax.swing.JPanel {

    private DefaultTableModel dtmSanPhamChiTiet = new DefaultTableModel();
    private DefaultTableModel dtmKhachHang = new DefaultTableModel();
    private DefaultTableModel dtmHoaDon = new DefaultTableModel();
    private DefaultTableModel dtmHoaDonChiTiet = new DefaultTableModel();
    private DefaultTableModel dtmVoucher = new DefaultTableModel();

    private DefaultComboBoxModel<ChatLieu> dcbmChatLieuBanHang = new DefaultComboBoxModel<>();
    private DefaultComboBoxModel<MauSac> dcbmMauSacBanHang = new DefaultComboBoxModel<>();
    private DefaultComboBoxModel<KichCo> dcbmKichCoBanHang = new DefaultComboBoxModel<>();
    private DefaultComboBoxModel<CoAo> dcbmCoAoBanHang = new DefaultComboBoxModel<>();
    private DefaultComboBoxModel<ChieuDaiTay> dcbmChieuDaiTayBanHang = new DefaultComboBoxModel<>();

    private final ISanPhamChiTietService sanPhamChiTietService = new SanPhamChiTietService();
    private final IKhachHangServise khachHangService = new KhachHangServise();
    private final IHoaDonServices hoaDonService = new HoaDonService();
    private final IVoucherServise voucherService = new VoucherService();

    private final IThuocTinhService<ChatLieu> chatLieuService = new ChatLieuService();
    private final IThuocTinhService<ChieuDaiTay> chieuDaiTayService = new ChieuDaiTayService();
    private final IThuocTinhService<CoAo> coAoService = new CoAoService();
    private final IThuocTinhService<KichCo> kichCoService = new KichCoService();
    private final IThuocTinhService<MauSac> mauSacService = new MauSacService();

    private WebcamPanel panel = null;
    private Webcam webcam = null;

    private KhachHang selectedKhachHang;
    private VoucherBackup selectedVoucher;

    private Result resultWebcam;
    int idNhanvien;

    public ViewBanHang(String IdNhanVienBanHang) {
        initComponents();
        this.idNhanvien = Integer.parseInt(IdNhanVienBanHang);
        dtmSanPhamChiTiet = (DefaultTableModel) tblSanPhamChiTiet.getModel();
        dtmKhachHang = (DefaultTableModel) tblKhachHang.getModel();
        dtmHoaDon = (DefaultTableModel) tblHoaDon.getModel();
        dtmHoaDonChiTiet = (DefaultTableModel) tblHoaDonChiTiet.getModel();
        dtmVoucher = (DefaultTableModel) tblVoucher.getModel();

        dcbmChatLieuBanHang = (DefaultComboBoxModel<ChatLieu>) cbbChatLieuBanHang.getModel();
        dcbmMauSacBanHang = (DefaultComboBoxModel<MauSac>) cbbMauSacBanHang.getModel();
        dcbmKichCoBanHang = (DefaultComboBoxModel<KichCo>) cbbKichCoBanHang.getModel();
        dcbmCoAoBanHang = (DefaultComboBoxModel<CoAo>) cbbCoAoBanHang.getModel();
        dcbmChieuDaiTayBanHang = (DefaultComboBoxModel<ChieuDaiTay>) cbbChieuDaiTayBanHang.getModel();

        loadDataComboboxPropertiesExport(dcbmChatLieuBanHang, "ChatLieu");
        loadDataComboboxPropertiesExport(dcbmMauSacBanHang, "MauSac");
        loadDataComboboxPropertiesExport(dcbmKichCoBanHang, "KichCo");
        loadDataComboboxPropertiesExport(dcbmCoAoBanHang, "CoAo");
        loadDataComboboxPropertiesExport(dcbmChieuDaiTayBanHang, "ChieuDaiTay");

        showDataSanPhamChiTiet();
        loadlistKhachHang(khachHangService.selectAll());
        showDataKhachHang();
        showDataHoaDonChuaThanhToan();
        txtMaKhachHang.setText("KH000");
        txtTenKhachHang.setText("Khách lẻ");
        txtMaKhachHang.disable();
        txtTenKhachHang.disable();

        resetTien();

        Thread threadCam = new Thread(() -> {
            initWebcam();
        });
        threadCam.start();
        Thread threadScan = new Thread(() -> {
            try {
                Thread.sleep(5000);
                scan();
            } catch (InterruptedException ex) {

            }
        });
        threadScan.start();

        TinhTien tien = new TinhTien(txtTienThanhToan, txtTienKhachDua, txtTienThua, txtTienKhachChuyenKhoan, cbbHinhThucThanhToan);
        tien.start();
        onChangeSearchSanPham();

        cbbMauSacBanHang.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filterSanPhamChiTietExport();
            }
        });

        cbbKichCoBanHang.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filterSanPhamChiTietExport();
            }
        });

        cbbChatLieuBanHang.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filterSanPhamChiTietExport();
            }
        });

        cbbCoAoBanHang.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filterSanPhamChiTietExport();
            }
        });

        cbbChieuDaiTayBanHang.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filterSanPhamChiTietExport();
            }
        });
    }

    private void loadDataComboboxPropertiesExport(DefaultComboBoxModel<?> model, String object) {
        model.removeAllElements();
        switch (object) {
            case "ChatLieu":
                for (ChatLieu chatLieu : chatLieuService.getAllExport()) {
                    dcbmChatLieuBanHang.addElement(chatLieu);
                }
                break;
            case "MauSac":
                for (MauSac mauSac : mauSacService.getAllExport()) {
                    dcbmMauSacBanHang.addElement(mauSac);
                }
                break;
            case "KichCo":
                for (KichCo kichCo : kichCoService.getAllExport()) {
                    dcbmKichCoBanHang.addElement(kichCo);
                }
                break;
            case "CoAo":
                for (CoAo coAo : coAoService.getAllExport()) {
                    dcbmCoAoBanHang.addElement(coAo);
                }
                break;
            case "ChieuDaiTay":
                for (ChieuDaiTay chieuDaiTay : chieuDaiTayService.getAllExport()) {
                    dcbmChieuDaiTayBanHang.addElement(chieuDaiTay);
                }
                break;
            default:
                throw new AssertionError();
        }
    }

    private void filterSanPhamChiTietExport() {
        MauSac mauSac = (MauSac) cbbMauSacBanHang.getSelectedItem();
        KichCo kickCo = (KichCo) cbbKichCoBanHang.getSelectedItem();
        ChatLieu chatLieu = (ChatLieu) cbbChatLieuBanHang.getSelectedItem();
        CoAo coAo = (CoAo) cbbCoAoBanHang.getSelectedItem();
        ChieuDaiTay chieuDaiTay = (ChieuDaiTay) cbbChieuDaiTayBanHang.getSelectedItem();
        dtmSanPhamChiTiet.setRowCount(0);
        for (SanPhamChiTiet sanPhamChiTiet : sanPhamChiTietService.filterSanPhamChiTietBanHang(mauSac.getId(), kickCo.getId(), chatLieu.getId(), coAo.getId(), chieuDaiTay.getId())) {
            dtmSanPhamChiTiet.addRow(new Object[]{
                sanPhamChiTiet.getMa(),
                sanPhamChiTiet.getTenSanPham(),
                sanPhamChiTiet.getSoLuong(),
                sanPhamChiTiet.getGiaBan(),
                sanPhamChiTiet.getTenMauSac(),
                sanPhamChiTiet.getTenKichCo(),
                sanPhamChiTiet.getTenChatLieu(),
                sanPhamChiTiet.getTenCoAo(),
                sanPhamChiTiet.getTenChieuDaiTay()
            });
        }
    }

    private void resetTien() {
        txtMaKhachHang.setText("KH000");
        txtTenKhachHang.setText("Khách lẻ");
        txtMaHoaDon.setText("");
        txtTenNhanVien.setText("");
        txtTongTien.setText("0");
        txtTienThanhToan.setText("0");
        txtTienKhachDua.setText("0");
        txtTienKhachChuyenKhoan.setText("0");
        txtTienThua.setText("0");
    }

    private void onChangeSearchSanPham() {
        txtTimKiemSanPham.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                try {
                    if (e.getDocument().getText(0, e.getDocument().getLength()).length() <= 0) {
                        showDataSanPhamChiTietWithList(sanPhamChiTietService.getAll());
                    } else {
                        String maSPCT = e.getDocument().getText(0, e.getDocument().getLength());
                        List<SanPhamChiTiet> sanPhamChiTietResponse = sanPhamChiTietService.getAll();
                        List<SanPhamChiTiet> resultList = new ArrayList<>();
                        if (sanPhamChiTietResponse != null) {
                            for (SanPhamChiTiet sanPhamChiTiet : sanPhamChiTietResponse) {
                                if (sanPhamChiTiet.getMa().toLowerCase().contains(maSPCT.toLowerCase())) {
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
                showDataSanPhamChiTietWithList(sanPhamChiTietService.getAll());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                try {
                    if (e.getDocument().getText(0, e.getDocument().getLength()).length() <= 0) {
                        showDataSanPhamChiTietWithList(sanPhamChiTietService.getAll());
                    } else {
                        String maSPCT = e.getDocument().getText(0, e.getDocument().getLength());
                        List<SanPhamChiTiet> sanPhamChiTietResponse = sanPhamChiTietService.getAll();
                        List<SanPhamChiTiet> resultList = new ArrayList<>();
                        if (sanPhamChiTietResponse != null) {
                            for (SanPhamChiTiet sanPhamChiTiet : sanPhamChiTietResponse) {
                                if (sanPhamChiTiet.getMa().toLowerCase().contains(maSPCT.toLowerCase())) {
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

    private void loadlistKhachHang(ArrayList<QlKhachHang> ds) {
        DefaultTableModel model = (DefaultTableModel) tblKhachHang.getModel();
        model.setRowCount(0);
        for (QlKhachHang kh : ds) {
            Object[] row = {
                kh.getMaKh(),
                kh.getTenKh(),
                kh.getGioiTinh() ? "Nam" : "Nữ",
                kh.getSdt(),
                kh.getEmail(),
                kh.getDiaChi(),
                kh.getNgaySinh()
            };
            model.addRow(row);
        }

    }

    private void showDataVoucher(double tongTien) {
        dtmVoucher.setRowCount(0);
        for (VoucherBackup voucherBackup : voucherService.getAllByTongTien(tongTien)) {
            dtmVoucher.addRow(new Object[]{
                voucherBackup.getMa(),
                voucherBackup.getTen(),
                voucherBackup.isLoaiGiamGia() ? "%" : "VND",
                voucherBackup.getGiaTriGiam(),
                voucherBackup.getSoLuong(),
                voucherBackup.getGiaTriDonHangToiThieu()
            });
        }
    }

    private void showDataSanPhamChiTiet() {
        dtmSanPhamChiTiet.setRowCount(0);
        for (SanPhamChiTiet sanPhamChiTiet : sanPhamChiTietService.getAll()) {
            dtmSanPhamChiTiet.addRow(new Object[]{
                sanPhamChiTiet.getMa(),
                sanPhamChiTiet.getTenSanPham(),
                sanPhamChiTiet.getSoLuong(),
                sanPhamChiTiet.getGiaBan(),
                sanPhamChiTiet.getTenMauSac(),
                sanPhamChiTiet.getTenKichCo(),
                sanPhamChiTiet.getTenChatLieu(),
                sanPhamChiTiet.getTenCoAo(),
                sanPhamChiTiet.getTenChieuDaiTay()
            });
        }
    }

    private void showDataSanPhamChiTietWithList(List<SanPhamChiTiet> listSPCT) {
        dtmSanPhamChiTiet.setRowCount(0);
        for (SanPhamChiTiet sanPhamChiTiet : listSPCT) {
            dtmSanPhamChiTiet.addRow(new Object[]{
                sanPhamChiTiet.getMa(),
                sanPhamChiTiet.getTenSanPham(),
                sanPhamChiTiet.getSoLuong(),
                sanPhamChiTiet.getGiaBan(),
                sanPhamChiTiet.getTenMauSac(),
                sanPhamChiTiet.getTenKichCo(),
                sanPhamChiTiet.getTenChatLieu(),
                sanPhamChiTiet.getTenCoAo(),
                sanPhamChiTiet.getTenChieuDaiTay()
            });
        }
    }

    private void showDataKhachHang() {
        dtmKhachHang.setRowCount(0);
        for (KhachHang khachHang : khachHangService.getAllKhachHang()) {
            dtmKhachHang.addRow(new Object[]{
                khachHang.getMaKh(),
                khachHang.getTenKh(),
                khachHang.getSdt(),
                khachHang.getEmail(),
                khachHang.getGioiTinh() ? "Nam" : "Nữ",
                khachHang.getDiaChi()
            });
        }
    }

    private void showDataHoaDonChuaThanhToan() {
        dtmHoaDon.setRowCount(0);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        for (HoaDonBackup hoaDonBackup : hoaDonService.getAllHoaDonChuaThanhToan()) {
            LocalDateTime ngayTao = hoaDonBackup.getNgayTao();
            dtmHoaDon.addRow(new Object[]{
                hoaDonBackup.getMaHoaDon(),
                ngayTao.format(formatter),
                hoaDonBackup.getTenNhanVien(),
                hoaDonBackup.getTenKhachHang(),
                hoaDonBackup.isTrangThai() ? "Đã thanh toán" : "Chưa thanh toán"
            });
        }
    }

    private void showDataHoaDonChiTiet(int idHoaDon) {
        dtmHoaDonChiTiet.setRowCount(0);
        for (HoaDonChiTietBackup hoaDonChiTietBackup : hoaDonService.getAllByIdHoaDon(idHoaDon)) {
            dtmHoaDonChiTiet.addRow(new Object[]{
                hoaDonChiTietBackup.getMaSanPhamChiTiet(),
                hoaDonChiTietBackup.getTenSanPham(),
                hoaDonChiTietBackup.getSoLuong(),
                hoaDonChiTietBackup.getGiaTien(),
                hoaDonChiTietBackup.getThanhTien()
            });
        }
    }

    private String generateRandomMa(String ma) {
        int randomNumber = (int) ((Math.random() * 90000) + 10000);
        return ma + randomNumber;
    }

    private HoaDonBackup addHoaDon() {
        KhachHang khachHang = khachHangService.findKhachHangByMa(txtMaKhachHang.getText());
        HoaDonBackup hoaDonBackup = null;
        String ma = generateRandomMa("HD");
        //nhân viên
        int idKhachHang = khachHang.getId();
        hoaDonBackup = new HoaDonBackup(ma, idNhanvien, idKhachHang);
        return hoaDonBackup;
    }

    private void initWebcam() {
        try {

            Dimension size = WebcamResolution.QVGA.getSize();
//            webcam.open(true);
            webcam = Webcam.getWebcams().get(0);
            webcam.setViewSize(size);
            panel = new WebcamPanel(webcam);
            panel.setPreferredSize(size);

            pnWebcam.add(panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 470, 300));
            pnWebcam.revalidate();
        } catch (WebcamException e) {
            if (this.isEnabled()) {
                JOptionPane.showMessageDialog(this, "Lỗi Cam");
            } else {
                JOptionPane.showMessageDialog(this, "Chưa Kết Nối Được Camera");
            }
        }
    }

    private void scan() {
        try {
            do {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ex) {
                    System.out.println(ex.getMessage());
                }

                Result result = null;
                BufferedImage image = null;

                if (webcam.isOpen()) {
                    if ((image = webcam.getImage()) == null) {
                        continue;
                    }
                }

                LuminanceSource source = new BufferedImageLuminanceSource(image);
                BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(source));

                try {
                    result = new MultiFormatReader().decode(binaryBitmap);
                } catch (NotFoundException ex) {
                }

                if (result != null) {
                    resultWebcam = result;
                    jDialogNhapSoLuongWebcam.setVisible(true);
                    jDialogNhapSoLuongWebcam.setSize(415, 240);
                    jDialogNhapSoLuongWebcam.setLocation(800, 350);
                }
            } while (true);
        } catch (Exception e) {
            System.out.println("Tắt Webcam");
        }
    }

    private void clearFormHoaDon() {
        txtMaHoaDon.setText("");
        txtTenNhanVien.setText("");
        txtTongTien.setText("");
        txtTienThanhToan.setText("");
        txtTienKhachDua.setText("");
        txtTienKhachChuyenKhoan.setText("");
        txtTienThua.setText("");
    }

    private Boolean validateFormKhachHang() {

        if (Uhelper.checkNull(txtTenKH, "Nhập tên khách hàng")) {
            return true;
        }
        if (Uhelper.checkNull(txtNgaysinh, "Nhập ngày sinh")) {
            return true;
        }
        if (Uhelper.checkNull(txtdiachi, "Nhập địa chỉ")) {
            return true;
        }
        if (Uhelper.checkNull(txtEmail, "Nhập email")) {
            return true;
        }
        if (Uhelper.checkNull(txtsdt, "Nhập số điện thoại")) {
            return true;
        }
        if (Uhelper.checkSDT(txtsdt, "Nhập số điện thoại đúng định dạng 9-10 số")) {
            return true;
        }
        if (Uhelper.checkEmail(txtEmail, "Nhập email đúng định dạng ")) {
            return true;
        }

        return false;
    }

    private QlKhachHang getForm() {
        try {
            QlKhachHang kh = new QlKhachHang();
            kh.setMaKh(generateRandomMa("KH"));
            kh.setTenKh(txtTenKH.getText());
            kh.setGioiTinh(rdoNam.isSelected());
            kh.setSdt(txtsdt.getText());
            kh.setEmail(txtEmail.getText());
            kh.setDiaChi(txtdiachi.getText());
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

            Date ngaySinh;
            ngaySinh = dateFormat.parse(txtNgaysinh.getText());

            kh.setNgaySinh(ngaySinh);

            return kh;

        } catch (ParseException ex) {
            Logger.getLogger(ViewKhachHang.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }

    private void clearFormKhachHang() {
        txtTenKH.setText("");
        txtEmail.setText("");
        txtNgaysinh.setText("");
        txtsdt.setText("");
        txtdiachi.setText("");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDialogKhachHang = new javax.swing.JDialog();
        jPanel17 = new javax.swing.JPanel();
        tabbedPaneCustom2 = new app.view.swing.TabbedPaneCustom();
        jPanel20 = new javax.swing.JPanel();
        jPanel26 = new javax.swing.JPanel();
        jPanel27 = new javax.swing.JPanel();
        scrollPaneWin114 = new custome_ui.swing.ScrollPaneWin11();
        tblKhachHang = new rojeru_san.complementos.RSTableMetro();
        btnChonKhachHang = new app.view.swing.ButtonGradient();
        jLabel16 = new javax.swing.JLabel();
        txtTimkiem = new textfield.TextField2();
        btnTimKiemKhachHang = new app.view.swing.ButtonGradient();
        btnLoadKhachHang = new app.view.swing.ButtonGradient();
        jPanel34 = new javax.swing.JPanel();
        jPanel35 = new javax.swing.JPanel();
        jPanel36 = new javax.swing.JPanel();
        jPanel37 = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        txtTenKH = new textfield.TextField2();
        jLabel28 = new javax.swing.JLabel();
        txtEmail = new textfield.TextField2();
        jLabel29 = new javax.swing.JLabel();
        txtsdt = new textfield.TextField2();
        jLabel30 = new javax.swing.JLabel();
        txtNgaysinh = new textfield.TextField2();
        jLabel31 = new javax.swing.JLabel();
        rdoNam = new radio_button.RadioButtonCustom();
        rdoNu = new radio_button.RadioButtonCustom();
        jLabel32 = new javax.swing.JLabel();
        txtdiachi = new textfield.TextField2();
        btnThemKhachHang = new app.view.swing.ButtonGradient();
        jDialogNhapSoLuong = new javax.swing.JDialog();
        jPanel29 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        txtNhapSoLuong = new textfield.TextField2();
        btnXacNhanSoLuong = new app.view.swing.ButtonGradient();
        btnGioiTinh = new javax.swing.ButtonGroup();
        jDialogChangeSoLuong = new javax.swing.JDialog();
        jPanel30 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        txtChangeSoLuong = new textfield.TextField2();
        btnChangeSoLuong = new app.view.swing.ButtonGradient();
        jDialogNhapSoLuongWebcam = new javax.swing.JDialog();
        jPanel31 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        txtNhapSoLuongWebcam = new textfield.TextField2();
        btnNhapSoLuongWebcam = new app.view.swing.ButtonGradient();
        jDialogVoucher = new javax.swing.JDialog();
        jPanel21 = new javax.swing.JPanel();
        tabbedPaneCustom3 = new app.view.swing.TabbedPaneCustom();
        jPanel22 = new javax.swing.JPanel();
        jPanel28 = new javax.swing.JPanel();
        jPanel32 = new javax.swing.JPanel();
        scrollPaneWin115 = new custome_ui.swing.ScrollPaneWin11();
        tblVoucher = new rojeru_san.complementos.RSTableMetro();
        btnChonVoucher = new app.view.swing.ButtonGradient();
        jLabel17 = new javax.swing.JLabel();
        txtTimkiem1 = new textfield.TextField2();
        btnTimKiemKhachHang1 = new app.view.swing.ButtonGradient();
        btnLoadKhachHang1 = new app.view.swing.ButtonGradient();
        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        scrollPaneWin113 = new custome_ui.swing.ScrollPaneWin11();
        tblHoaDon = new rojeru_san.complementos.RSTableMetro();
        btnTaoHoaDon = new app.view.swing.ButtonGradient();
        btnXoaAllHoaDon = new app.view.swing.ButtonGradient();
        jPanel5 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        scrollPaneWin112 = new custome_ui.swing.ScrollPaneWin11();
        tblSanPhamChiTiet = new rojeru_san.complementos.RSTableMetro();
        jLabel10 = new javax.swing.JLabel();
        txtTimKiemSanPham = new textfield.TextField2();
        btnShowSanPhamChiTiet = new app.view.swing.ButtonGradient();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        cbbKichCoBanHang = new app.view.swing.ComboBoxSuggestion();
        cbbMauSacBanHang = new app.view.swing.ComboBoxSuggestion();
        cbbChatLieuBanHang = new app.view.swing.ComboBoxSuggestion();
        cbbCoAoBanHang = new app.view.swing.ComboBoxSuggestion();
        cbbChieuDaiTayBanHang = new app.view.swing.ComboBoxSuggestion();
        jPanel7 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        scrollPaneWin111 = new custome_ui.swing.ScrollPaneWin11();
        tblHoaDonChiTiet = new rojeru_san.complementos.RSTableMetro();
        btnXoaAllHoaDonChiTiet = new app.view.swing.ButtonGradient();
        jPanel19 = new javax.swing.JPanel();
        tabbedPaneCustom1 = new app.view.swing.TabbedPaneCustom();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtMaKhachHang = new textfield.TextField2();
        txtTenKhachHang = new textfield.TextField2();
        btnChonNhanVien = new app.view.swing.ButtonGradient();
        jPanel13 = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        txtMaHoaDon = new textfield.TextField2();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtTenNhanVien = new textfield.TextField2();
        txtTongTien = new textfield.TextField2();
        btnPhieuGiamGia = new app.view.swing.ButtonGradient();
        txtTienThanhToan = new textfield.TextField2();
        jLabel11 = new javax.swing.JLabel();
        cbbHinhThucThanhToan = new app.view.swing.ComboBoxSuggestion();
        jLabel12 = new javax.swing.JLabel();
        txtTienKhachDua = new textfield.TextField2();
        jLabel13 = new javax.swing.JLabel();
        txtTienKhachChuyenKhoan = new textfield.TextField2();
        jLabel14 = new javax.swing.JLabel();
        txtTienThua = new textfield.TextField2();
        btnThanhToan = new app.view.swing.ButtonGradient();
        jPanel6 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        pnWebcam = new javax.swing.JPanel();

        jPanel17.setBackground(new java.awt.Color(38, 28, 73));
        jPanel17.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tabbedPaneCustom2.setBackground(new java.awt.Color(38, 28, 73));
        tabbedPaneCustom2.setForeground(new java.awt.Color(255, 255, 255));
        tabbedPaneCustom2.setSelectedColor(new java.awt.Color(51, 0, 102));
        tabbedPaneCustom2.setUnselectedColor(new java.awt.Color(38, 32, 78));

        jPanel20.setBackground(new java.awt.Color(38, 28, 73));

        jPanel26.setBackground(new java.awt.Color(38, 28, 73));
        jPanel26.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh sách sản phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 16), new java.awt.Color(255, 255, 255))); // NOI18N

        jPanel27.setBackground(new java.awt.Color(38, 28, 73));
        jPanel27.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        tblKhachHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Mã KH", "Tên KH", "SĐT", "Email", "Giới tính", "Địa chỉ"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblKhachHang.setColorBackgoundHead(new java.awt.Color(102, 102, 102));
        tblKhachHang.setColorBordeFilas(new java.awt.Color(153, 153, 153));
        tblKhachHang.setColorBordeHead(new java.awt.Color(153, 153, 153));
        tblKhachHang.setColorFilasBackgound1(new java.awt.Color(204, 204, 204));
        tblKhachHang.setColorFilasForeground1(new java.awt.Color(0, 0, 0));
        tblKhachHang.setColorFilasForeground2(new java.awt.Color(0, 0, 0));
        tblKhachHang.setColorSelBackgound(new java.awt.Color(38, 28, 73));
        tblKhachHang.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tblKhachHang.setFuenteFilas(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tblKhachHang.setFuenteFilasSelect(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        tblKhachHang.setFuenteHead(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        tblKhachHang.setRowHeight(40);
        tblKhachHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblKhachHangMouseClicked(evt);
            }
        });
        scrollPaneWin114.setViewportView(tblKhachHang);
        if (tblKhachHang.getColumnModel().getColumnCount() > 0) {
            tblKhachHang.getColumnModel().getColumn(0).setResizable(false);
            tblKhachHang.getColumnModel().getColumn(1).setResizable(false);
            tblKhachHang.getColumnModel().getColumn(2).setResizable(false);
            tblKhachHang.getColumnModel().getColumn(3).setResizable(false);
            tblKhachHang.getColumnModel().getColumn(4).setResizable(false);
            tblKhachHang.getColumnModel().getColumn(5).setResizable(false);
            tblKhachHang.getColumnModel().getColumn(5).setHeaderValue("Địa chỉ");
        }

        btnChonKhachHang.setForeground(new java.awt.Color(0, 0, 0));
        btnChonKhachHang.setText("Chọn");
        btnChonKhachHang.setColor1(new java.awt.Color(255, 255, 255));
        btnChonKhachHang.setColor2(new java.awt.Color(102, 102, 102));
        btnChonKhachHang.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnChonKhachHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChonKhachHangActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel27Layout = new javax.swing.GroupLayout(jPanel27);
        jPanel27.setLayout(jPanel27Layout);
        jPanel27Layout.setHorizontalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addGap(325, 325, 325)
                .addComponent(btnChonKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel27Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(scrollPaneWin114, javax.swing.GroupLayout.PREFERRED_SIZE, 745, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(730, 730, 730))
        );
        jPanel27Layout.setVerticalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scrollPaneWin114, javax.swing.GroupLayout.DEFAULT_SIZE, 473, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(btnChonKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9))
        );

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Tìm kiếm");

        txtTimkiem.setForeground(new java.awt.Color(0, 0, 0));
        txtTimkiem.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        btnTimKiemKhachHang.setForeground(new java.awt.Color(0, 0, 0));
        btnTimKiemKhachHang.setText("Tìm kiếm");
        btnTimKiemKhachHang.setColor1(new java.awt.Color(255, 255, 255));
        btnTimKiemKhachHang.setColor2(new java.awt.Color(102, 102, 102));
        btnTimKiemKhachHang.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnTimKiemKhachHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiemKhachHangActionPerformed(evt);
            }
        });

        btnLoadKhachHang.setForeground(new java.awt.Color(0, 0, 0));
        btnLoadKhachHang.setText("Load");
        btnLoadKhachHang.setColor1(new java.awt.Color(255, 255, 255));
        btnLoadKhachHang.setColor2(new java.awt.Color(102, 102, 102));
        btnLoadKhachHang.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnLoadKhachHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoadKhachHangActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel26Layout = new javax.swing.GroupLayout(jPanel26);
        jPanel26.setLayout(jPanel26Layout);
        jPanel26Layout.setHorizontalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel26Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel27, javax.swing.GroupLayout.PREFERRED_SIZE, 764, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel26Layout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addComponent(jLabel16)
                        .addGap(18, 18, 18)
                        .addComponent(txtTimkiem, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnTimKiemKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnLoadKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        jPanel26Layout.setVerticalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel26Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(txtTimkiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTimKiemKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLoadKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(64, Short.MAX_VALUE))
        );

        tabbedPaneCustom2.addTab("Danh sách khách hàng", jPanel20);

        jPanel34.setBackground(new java.awt.Color(38, 28, 73));

        jPanel35.setBackground(new java.awt.Color(38, 28, 73));
        jPanel35.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thiết lập thuộc tính", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 16), new java.awt.Color(255, 255, 255))); // NOI18N

        jPanel36.setBackground(new java.awt.Color(38, 28, 73));
        jPanel36.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel37.setBackground(new java.awt.Color(38, 28, 73));

        jLabel27.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(255, 255, 255));
        jLabel27.setText("Tên khách hàng");

        txtTenKH.setForeground(new java.awt.Color(0, 0, 0));
        txtTenKH.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel28.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(255, 255, 255));
        jLabel28.setText("Email");

        txtEmail.setForeground(new java.awt.Color(0, 0, 0));
        txtEmail.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel29.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(255, 255, 255));
        jLabel29.setText("Số điện thoại");

        txtsdt.setForeground(new java.awt.Color(0, 0, 0));
        txtsdt.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel30.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(255, 255, 255));
        jLabel30.setText("Ngày sinh");

        txtNgaysinh.setForeground(new java.awt.Color(0, 0, 0));
        txtNgaysinh.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel31.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(255, 255, 255));
        jLabel31.setText("Giới tính");

        rdoNam.setBackground(new java.awt.Color(38, 28, 73));
        btnGioiTinh.add(rdoNam);
        rdoNam.setForeground(new java.awt.Color(255, 255, 255));
        rdoNam.setText("Nam");
        rdoNam.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        rdoNu.setBackground(new java.awt.Color(38, 28, 73));
        btnGioiTinh.add(rdoNu);
        rdoNu.setForeground(new java.awt.Color(255, 255, 255));
        rdoNu.setText("Nữ");
        rdoNu.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel32.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(255, 255, 255));
        jLabel32.setText("Địa chỉ");

        txtdiachi.setForeground(new java.awt.Color(0, 0, 0));
        txtdiachi.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        btnThemKhachHang.setForeground(new java.awt.Color(0, 0, 0));
        btnThemKhachHang.setText("Thêm");
        btnThemKhachHang.setColor1(new java.awt.Color(255, 255, 255));
        btnThemKhachHang.setColor2(new java.awt.Color(102, 102, 102));
        btnThemKhachHang.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnThemKhachHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemKhachHangActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel37Layout = new javax.swing.GroupLayout(jPanel37);
        jPanel37.setLayout(jPanel37Layout);
        jPanel37Layout.setHorizontalGroup(
            jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel37Layout.createSequentialGroup()
                .addContainerGap(180, Short.MAX_VALUE)
                .addGroup(jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel37Layout.createSequentialGroup()
                        .addComponent(jLabel31)
                        .addGap(74, 74, 74)
                        .addComponent(rdoNam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(49, 49, 49)
                        .addComponent(rdoNu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(jPanel37Layout.createSequentialGroup()
                            .addComponent(jLabel32)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtdiachi, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel37Layout.createSequentialGroup()
                            .addComponent(jLabel30)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtNgaysinh, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel37Layout.createSequentialGroup()
                            .addComponent(jLabel29)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtsdt, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel37Layout.createSequentialGroup()
                            .addComponent(jLabel28)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel37Layout.createSequentialGroup()
                            .addComponent(jLabel27)
                            .addGap(18, 18, 18)
                            .addComponent(txtTenKH, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel37Layout.createSequentialGroup()
                        .addGap(147, 147, 147)
                        .addComponent(btnThemKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(165, 165, 165))
        );
        jPanel37Layout.setVerticalGroup(
            jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel37Layout.createSequentialGroup()
                .addContainerGap(72, Short.MAX_VALUE)
                .addGroup(jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTenKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtsdt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNgaysinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rdoNam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rdoNu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtdiachi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addComponent(btnThemKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(88, 88, 88))
        );

        javax.swing.GroupLayout jPanel36Layout = new javax.swing.GroupLayout(jPanel36);
        jPanel36.setLayout(jPanel36Layout);
        jPanel36Layout.setHorizontalGroup(
            jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel36Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel37, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel36Layout.setVerticalGroup(
            jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel36Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel37, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel35Layout = new javax.swing.GroupLayout(jPanel35);
        jPanel35.setLayout(jPanel35Layout);
        jPanel35Layout.setHorizontalGroup(
            jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel35Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel36, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel35Layout.setVerticalGroup(
            jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel35Layout.createSequentialGroup()
                .addComponent(jPanel36, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel34Layout = new javax.swing.GroupLayout(jPanel34);
        jPanel34.setLayout(jPanel34Layout);
        jPanel34Layout.setHorizontalGroup(
            jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel35, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel34Layout.setVerticalGroup(
            jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel34Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel35, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tabbedPaneCustom2.addTab("Thêm mới khách hàng", jPanel34);

        jPanel17.add(tabbedPaneCustom2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 810, 740));

        javax.swing.GroupLayout jDialogKhachHangLayout = new javax.swing.GroupLayout(jDialogKhachHang.getContentPane());
        jDialogKhachHang.getContentPane().setLayout(jDialogKhachHangLayout);
        jDialogKhachHangLayout.setHorizontalGroup(
            jDialogKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, 850, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jDialogKhachHangLayout.setVerticalGroup(
            jDialogKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialogKhachHangLayout.createSequentialGroup()
                .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, 834, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel29.setBackground(new java.awt.Color(38, 28, 73));

        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("Nhập số lượng");

        btnXacNhanSoLuong.setForeground(new java.awt.Color(0, 0, 0));
        btnXacNhanSoLuong.setText("Xác nhận");
        btnXacNhanSoLuong.setColor1(new java.awt.Color(255, 255, 255));
        btnXacNhanSoLuong.setColor2(new java.awt.Color(102, 102, 102));
        btnXacNhanSoLuong.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnXacNhanSoLuong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXacNhanSoLuongActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel29Layout = new javax.swing.GroupLayout(jPanel29);
        jPanel29.setLayout(jPanel29Layout);
        jPanel29Layout.setHorizontalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel29Layout.createSequentialGroup()
                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel29Layout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addComponent(jLabel18)
                        .addGap(21, 21, 21)
                        .addComponent(txtNhapSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel29Layout.createSequentialGroup()
                        .addGap(114, 114, 114)
                        .addComponent(btnXacNhanSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(52, Short.MAX_VALUE))
        );
        jPanel29Layout.setVerticalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel29Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(txtNhapSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addComponent(btnXacNhanSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(49, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jDialogNhapSoLuongLayout = new javax.swing.GroupLayout(jDialogNhapSoLuong.getContentPane());
        jDialogNhapSoLuong.getContentPane().setLayout(jDialogNhapSoLuongLayout);
        jDialogNhapSoLuongLayout.setHorizontalGroup(
            jDialogNhapSoLuongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jDialogNhapSoLuongLayout.setVerticalGroup(
            jDialogNhapSoLuongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel30.setBackground(new java.awt.Color(38, 28, 73));

        jLabel19.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("Nhập số lượng");

        btnChangeSoLuong.setForeground(new java.awt.Color(0, 0, 0));
        btnChangeSoLuong.setText("Xác nhận");
        btnChangeSoLuong.setColor1(new java.awt.Color(255, 255, 255));
        btnChangeSoLuong.setColor2(new java.awt.Color(102, 102, 102));
        btnChangeSoLuong.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnChangeSoLuong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChangeSoLuongActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel30Layout = new javax.swing.GroupLayout(jPanel30);
        jPanel30.setLayout(jPanel30Layout);
        jPanel30Layout.setHorizontalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel30Layout.createSequentialGroup()
                .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel30Layout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addComponent(jLabel19)
                        .addGap(21, 21, 21)
                        .addComponent(txtChangeSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel30Layout.createSequentialGroup()
                        .addGap(114, 114, 114)
                        .addComponent(btnChangeSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(79, Short.MAX_VALUE))
        );
        jPanel30Layout.setVerticalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel30Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(txtChangeSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addComponent(btnChangeSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(61, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jDialogChangeSoLuongLayout = new javax.swing.GroupLayout(jDialogChangeSoLuong.getContentPane());
        jDialogChangeSoLuong.getContentPane().setLayout(jDialogChangeSoLuongLayout);
        jDialogChangeSoLuongLayout.setHorizontalGroup(
            jDialogChangeSoLuongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel30, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jDialogChangeSoLuongLayout.setVerticalGroup(
            jDialogChangeSoLuongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel30, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel31.setBackground(new java.awt.Color(38, 28, 73));

        jLabel20.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("Nhập số lượng");

        btnNhapSoLuongWebcam.setForeground(new java.awt.Color(0, 0, 0));
        btnNhapSoLuongWebcam.setText("Xác nhận");
        btnNhapSoLuongWebcam.setColor1(new java.awt.Color(255, 255, 255));
        btnNhapSoLuongWebcam.setColor2(new java.awt.Color(102, 102, 102));
        btnNhapSoLuongWebcam.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnNhapSoLuongWebcam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNhapSoLuongWebcamActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel31Layout = new javax.swing.GroupLayout(jPanel31);
        jPanel31.setLayout(jPanel31Layout);
        jPanel31Layout.setHorizontalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel31Layout.createSequentialGroup()
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel31Layout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addComponent(jLabel20)
                        .addGap(21, 21, 21)
                        .addComponent(txtNhapSoLuongWebcam, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel31Layout.createSequentialGroup()
                        .addGap(114, 114, 114)
                        .addComponent(btnNhapSoLuongWebcam, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(52, Short.MAX_VALUE))
        );
        jPanel31Layout.setVerticalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel31Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(txtNhapSoLuongWebcam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addComponent(btnNhapSoLuongWebcam, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(49, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jDialogNhapSoLuongWebcamLayout = new javax.swing.GroupLayout(jDialogNhapSoLuongWebcam.getContentPane());
        jDialogNhapSoLuongWebcam.getContentPane().setLayout(jDialogNhapSoLuongWebcamLayout);
        jDialogNhapSoLuongWebcamLayout.setHorizontalGroup(
            jDialogNhapSoLuongWebcamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jDialogNhapSoLuongWebcamLayout.setVerticalGroup(
            jDialogNhapSoLuongWebcamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel21.setBackground(new java.awt.Color(38, 28, 73));
        jPanel21.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tabbedPaneCustom3.setBackground(new java.awt.Color(38, 28, 73));
        tabbedPaneCustom3.setForeground(new java.awt.Color(255, 255, 255));
        tabbedPaneCustom3.setSelectedColor(new java.awt.Color(51, 0, 102));
        tabbedPaneCustom3.setUnselectedColor(new java.awt.Color(38, 32, 78));

        jPanel22.setBackground(new java.awt.Color(38, 28, 73));

        jPanel28.setBackground(new java.awt.Color(38, 28, 73));
        jPanel28.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh sách voucher", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 16), new java.awt.Color(255, 255, 255))); // NOI18N

        jPanel32.setBackground(new java.awt.Color(38, 28, 73));
        jPanel32.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        tblVoucher.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Mã", "Tên", "Loại", "Giá trị giảm", "Số lượng", "Giá trị hóa đơn"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblVoucher.setColorBackgoundHead(new java.awt.Color(102, 102, 102));
        tblVoucher.setColorBordeFilas(new java.awt.Color(153, 153, 153));
        tblVoucher.setColorBordeHead(new java.awt.Color(153, 153, 153));
        tblVoucher.setColorFilasBackgound1(new java.awt.Color(204, 204, 204));
        tblVoucher.setColorFilasForeground1(new java.awt.Color(0, 0, 0));
        tblVoucher.setColorFilasForeground2(new java.awt.Color(0, 0, 0));
        tblVoucher.setColorSelBackgound(new java.awt.Color(38, 28, 73));
        tblVoucher.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tblVoucher.setFuenteFilas(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tblVoucher.setFuenteFilasSelect(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        tblVoucher.setFuenteHead(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        tblVoucher.setRowHeight(40);
        tblVoucher.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblVoucherMouseClicked(evt);
            }
        });
        scrollPaneWin115.setViewportView(tblVoucher);
        if (tblVoucher.getColumnModel().getColumnCount() > 0) {
            tblVoucher.getColumnModel().getColumn(0).setResizable(false);
            tblVoucher.getColumnModel().getColumn(1).setResizable(false);
            tblVoucher.getColumnModel().getColumn(2).setResizable(false);
            tblVoucher.getColumnModel().getColumn(3).setResizable(false);
            tblVoucher.getColumnModel().getColumn(4).setResizable(false);
            tblVoucher.getColumnModel().getColumn(5).setResizable(false);
        }

        btnChonVoucher.setForeground(new java.awt.Color(0, 0, 0));
        btnChonVoucher.setText("Chọn");
        btnChonVoucher.setColor1(new java.awt.Color(255, 255, 255));
        btnChonVoucher.setColor2(new java.awt.Color(102, 102, 102));
        btnChonVoucher.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnChonVoucher.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChonVoucherActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel32Layout = new javax.swing.GroupLayout(jPanel32);
        jPanel32.setLayout(jPanel32Layout);
        jPanel32Layout.setHorizontalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel32Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(scrollPaneWin115, javax.swing.GroupLayout.PREFERRED_SIZE, 745, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(730, 730, 730))
            .addGroup(jPanel32Layout.createSequentialGroup()
                .addGap(313, 313, 313)
                .addComponent(btnChonVoucher, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel32Layout.setVerticalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel32Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scrollPaneWin115, javax.swing.GroupLayout.DEFAULT_SIZE, 473, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnChonVoucher, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
        );

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("Tìm kiếm");

        txtTimkiem1.setForeground(new java.awt.Color(0, 0, 0));
        txtTimkiem1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        btnTimKiemKhachHang1.setForeground(new java.awt.Color(0, 0, 0));
        btnTimKiemKhachHang1.setText("Tìm kiếm");
        btnTimKiemKhachHang1.setColor1(new java.awt.Color(255, 255, 255));
        btnTimKiemKhachHang1.setColor2(new java.awt.Color(102, 102, 102));
        btnTimKiemKhachHang1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnTimKiemKhachHang1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiemKhachHang1ActionPerformed(evt);
            }
        });

        btnLoadKhachHang1.setForeground(new java.awt.Color(0, 0, 0));
        btnLoadKhachHang1.setText("Load");
        btnLoadKhachHang1.setColor1(new java.awt.Color(255, 255, 255));
        btnLoadKhachHang1.setColor2(new java.awt.Color(102, 102, 102));
        btnLoadKhachHang1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnLoadKhachHang1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoadKhachHang1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel28Layout = new javax.swing.GroupLayout(jPanel28);
        jPanel28.setLayout(jPanel28Layout);
        jPanel28Layout.setHorizontalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel28Layout.createSequentialGroup()
                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel28Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel32, javax.swing.GroupLayout.PREFERRED_SIZE, 764, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel28Layout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addComponent(jLabel17)
                        .addGap(18, 18, 18)
                        .addComponent(txtTimkiem1, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnTimKiemKhachHang1, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnLoadKhachHang1, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        jPanel28Layout.setVerticalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel28Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(txtTimkiem1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTimKiemKhachHang1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLoadKhachHang1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel32, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(64, Short.MAX_VALUE))
        );

        tabbedPaneCustom3.addTab("Danh sách voucher", jPanel22);

        jPanel21.add(tabbedPaneCustom3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 810, 740));

        javax.swing.GroupLayout jDialogVoucherLayout = new javax.swing.GroupLayout(jDialogVoucher.getContentPane());
        jDialogVoucher.getContentPane().setLayout(jDialogVoucherLayout);
        jDialogVoucherLayout.setHorizontalGroup(
            jDialogVoucherLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, 850, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jDialogVoucherLayout.setVerticalGroup(
            jDialogVoucherLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialogVoucherLayout.createSequentialGroup()
                .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, 834, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(38, 28, 73));
        jPanel1.setPreferredSize(new java.awt.Dimension(1330, 790));

        jPanel4.setBackground(new java.awt.Color(38, 28, 73));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Hóa đơn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 16), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel4.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N

        jPanel10.setBackground(new java.awt.Color(38, 28, 73));
        jPanel10.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        tblHoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã HĐ", "Ngày tạo", "Nhân viên", "Khách hàng", "Trạng thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblHoaDon.setColorBackgoundHead(new java.awt.Color(102, 102, 102));
        tblHoaDon.setColorBordeFilas(new java.awt.Color(153, 153, 153));
        tblHoaDon.setColorBordeHead(new java.awt.Color(153, 153, 153));
        tblHoaDon.setColorFilasBackgound1(new java.awt.Color(204, 204, 204));
        tblHoaDon.setColorFilasForeground1(new java.awt.Color(0, 0, 0));
        tblHoaDon.setColorFilasForeground2(new java.awt.Color(0, 0, 0));
        tblHoaDon.setColorSelBackgound(new java.awt.Color(38, 28, 73));
        tblHoaDon.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tblHoaDon.setFuenteFilas(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tblHoaDon.setFuenteFilasSelect(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        tblHoaDon.setFuenteHead(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        tblHoaDon.setRowHeight(30);
        tblHoaDon.getTableHeader().setReorderingAllowed(false);
        tblHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHoaDonMouseClicked(evt);
            }
        });
        scrollPaneWin113.setViewportView(tblHoaDon);
        if (tblHoaDon.getColumnModel().getColumnCount() > 0) {
            tblHoaDon.getColumnModel().getColumn(0).setResizable(false);
            tblHoaDon.getColumnModel().getColumn(1).setResizable(false);
            tblHoaDon.getColumnModel().getColumn(2).setResizable(false);
            tblHoaDon.getColumnModel().getColumn(3).setResizable(false);
            tblHoaDon.getColumnModel().getColumn(4).setResizable(false);
        }

        btnTaoHoaDon.setForeground(new java.awt.Color(0, 0, 0));
        btnTaoHoaDon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/add.png"))); // NOI18N
        btnTaoHoaDon.setColor1(new java.awt.Color(255, 255, 255));
        btnTaoHoaDon.setColor2(new java.awt.Color(102, 102, 102));
        btnTaoHoaDon.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnTaoHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTaoHoaDonActionPerformed(evt);
            }
        });

        btnXoaAllHoaDon.setForeground(new java.awt.Color(0, 0, 0));
        btnXoaAllHoaDon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/delete.png"))); // NOI18N
        btnXoaAllHoaDon.setColor1(new java.awt.Color(255, 255, 255));
        btnXoaAllHoaDon.setColor2(new java.awt.Color(102, 102, 102));
        btnXoaAllHoaDon.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnXoaAllHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaAllHoaDonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scrollPaneWin113, javax.swing.GroupLayout.PREFERRED_SIZE, 581, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnXoaAllHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTaoHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(16, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(scrollPaneWin113, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(btnTaoHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnXoaAllHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(233, 233, 233))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel5.setBackground(new java.awt.Color(38, 28, 73));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh sách sản phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 16), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel5.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N

        jPanel8.setBackground(new java.awt.Color(38, 28, 73));
        jPanel8.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        tblSanPhamChiTiet.setAutoCreateRowSorter(true);
        tblSanPhamChiTiet.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã SPCT", "Tên SP", "Số lượng", "Giá bán", "Màu sắc", "Kích cỡ", "Chất liệu", "Cổ áo", "Chiều dài tay"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, true, true, true
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
        tblSanPhamChiTiet.setRowHeight(30);
        tblSanPhamChiTiet.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSanPhamChiTietMouseClicked(evt);
            }
        });
        scrollPaneWin112.setViewportView(tblSanPhamChiTiet);
        if (tblSanPhamChiTiet.getColumnModel().getColumnCount() > 0) {
            tblSanPhamChiTiet.getColumnModel().getColumn(0).setResizable(false);
            tblSanPhamChiTiet.getColumnModel().getColumn(1).setResizable(false);
            tblSanPhamChiTiet.getColumnModel().getColumn(2).setResizable(false);
            tblSanPhamChiTiet.getColumnModel().getColumn(3).setResizable(false);
            tblSanPhamChiTiet.getColumnModel().getColumn(4).setResizable(false);
            tblSanPhamChiTiet.getColumnModel().getColumn(5).setResizable(false);
        }

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scrollPaneWin112, javax.swing.GroupLayout.DEFAULT_SIZE, 877, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scrollPaneWin112, javax.swing.GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Tìm kiếm");

        txtTimKiemSanPham.setForeground(new java.awt.Color(0, 0, 0));
        txtTimKiemSanPham.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        btnShowSanPhamChiTiet.setForeground(new java.awt.Color(0, 0, 0));
        btnShowSanPhamChiTiet.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/eye.png"))); // NOI18N
        btnShowSanPhamChiTiet.setColor1(new java.awt.Color(255, 255, 255));
        btnShowSanPhamChiTiet.setColor2(new java.awt.Color(102, 102, 102));
        btnShowSanPhamChiTiet.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnShowSanPhamChiTiet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnShowSanPhamChiTietActionPerformed(evt);
            }
        });

        jLabel34.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(255, 255, 255));
        jLabel34.setText("KC");

        jLabel35.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(255, 255, 255));
        jLabel35.setText("MS");

        jLabel36.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(255, 255, 255));
        jLabel36.setText("CL");

        jLabel37.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(255, 255, 255));
        jLabel37.setText("CA");

        jLabel38.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(255, 255, 255));
        jLabel38.setText("CDT");

        cbbKichCoBanHang.setBackground(new java.awt.Color(204, 204, 204));
        cbbKichCoBanHang.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        cbbKichCoBanHang.setForeground(new java.awt.Color(255, 255, 255));
        cbbKichCoBanHang.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        cbbMauSacBanHang.setBackground(new java.awt.Color(204, 204, 204));
        cbbMauSacBanHang.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        cbbMauSacBanHang.setForeground(new java.awt.Color(255, 255, 255));
        cbbMauSacBanHang.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        cbbChatLieuBanHang.setBackground(new java.awt.Color(204, 204, 204));
        cbbChatLieuBanHang.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        cbbChatLieuBanHang.setForeground(new java.awt.Color(255, 255, 255));
        cbbChatLieuBanHang.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        cbbCoAoBanHang.setBackground(new java.awt.Color(204, 204, 204));
        cbbCoAoBanHang.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        cbbCoAoBanHang.setForeground(new java.awt.Color(255, 255, 255));
        cbbCoAoBanHang.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        cbbChieuDaiTayBanHang.setBackground(new java.awt.Color(204, 204, 204));
        cbbChieuDaiTayBanHang.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        cbbChieuDaiTayBanHang.setForeground(new java.awt.Color(255, 255, 255));
        cbbChieuDaiTayBanHang.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTimKiemSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel34)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbbKichCoBanHang, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel35)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbbMauSacBanHang, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel36)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbbChatLieuBanHang, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel37)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbbCoAoBanHang, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel38)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbbChieuDaiTayBanHang, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnShowSanPhamChiTiet, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtTimKiemSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel10)
                        .addComponent(jLabel34)
                        .addComponent(jLabel35)
                        .addComponent(jLabel36)
                        .addComponent(jLabel37)
                        .addComponent(jLabel38)
                        .addComponent(cbbKichCoBanHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cbbMauSacBanHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cbbChatLieuBanHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cbbCoAoBanHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cbbChieuDaiTayBanHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnShowSanPhamChiTiet, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel7.setBackground(new java.awt.Color(38, 28, 73));
        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Giỏ hàng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 16), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel7.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N

        jPanel12.setBackground(new java.awt.Color(38, 28, 73));
        jPanel12.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        tblHoaDonChiTiet.setAutoCreateRowSorter(true);
        tblHoaDonChiTiet.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã SP", "Tên SP", "Số lượng", "Giá bán", "Thành tiền"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblHoaDonChiTiet.setColorBackgoundHead(new java.awt.Color(102, 102, 102));
        tblHoaDonChiTiet.setColorBordeFilas(new java.awt.Color(153, 153, 153));
        tblHoaDonChiTiet.setColorBordeHead(new java.awt.Color(153, 153, 153));
        tblHoaDonChiTiet.setColorFilasBackgound1(new java.awt.Color(204, 204, 204));
        tblHoaDonChiTiet.setColorFilasForeground1(new java.awt.Color(0, 0, 0));
        tblHoaDonChiTiet.setColorFilasForeground2(new java.awt.Color(0, 0, 0));
        tblHoaDonChiTiet.setColorSelBackgound(new java.awt.Color(38, 28, 73));
        tblHoaDonChiTiet.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tblHoaDonChiTiet.setFuenteFilas(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tblHoaDonChiTiet.setFuenteFilasSelect(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        tblHoaDonChiTiet.setFuenteHead(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        tblHoaDonChiTiet.setRowHeight(30);
        tblHoaDonChiTiet.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHoaDonChiTietMouseClicked(evt);
            }
        });
        scrollPaneWin111.setViewportView(tblHoaDonChiTiet);
        if (tblHoaDonChiTiet.getColumnModel().getColumnCount() > 0) {
            tblHoaDonChiTiet.getColumnModel().getColumn(0).setResizable(false);
            tblHoaDonChiTiet.getColumnModel().getColumn(1).setResizable(false);
            tblHoaDonChiTiet.getColumnModel().getColumn(2).setResizable(false);
            tblHoaDonChiTiet.getColumnModel().getColumn(3).setResizable(false);
            tblHoaDonChiTiet.getColumnModel().getColumn(4).setResizable(false);
        }

        btnXoaAllHoaDonChiTiet.setForeground(new java.awt.Color(0, 0, 0));
        btnXoaAllHoaDonChiTiet.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/delete.png"))); // NOI18N
        btnXoaAllHoaDonChiTiet.setColor1(new java.awt.Color(255, 255, 255));
        btnXoaAllHoaDonChiTiet.setColor2(new java.awt.Color(102, 102, 102));
        btnXoaAllHoaDonChiTiet.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnXoaAllHoaDonChiTiet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaAllHoaDonChiTietActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scrollPaneWin111, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnXoaAllHoaDonChiTiet, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scrollPaneWin111, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(btnXoaAllHoaDonChiTiet, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel19.setBackground(new java.awt.Color(38, 28, 73));
        jPanel19.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Hóa đơn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 16), new java.awt.Color(255, 255, 255))); // NOI18N

        tabbedPaneCustom1.setBackground(new java.awt.Color(38, 28, 73));
        tabbedPaneCustom1.setForeground(new java.awt.Color(255, 255, 255));
        tabbedPaneCustom1.setSelectedColor(new java.awt.Color(51, 0, 102));
        tabbedPaneCustom1.setUnselectedColor(new java.awt.Color(38, 32, 78));

        jPanel2.setBackground(new java.awt.Color(38, 28, 73));

        jPanel3.setBackground(new java.awt.Color(38, 28, 73));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin khách hàng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 16), new java.awt.Color(255, 255, 255))); // NOI18N

        jPanel9.setBackground(new java.awt.Color(38, 28, 73));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Mã khách hàng");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Tên khách hàng");

        txtMaKhachHang.setBackground(new java.awt.Color(204, 204, 204));
        txtMaKhachHang.setForeground(new java.awt.Color(0, 0, 0));
        txtMaKhachHang.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        txtTenKhachHang.setBackground(new java.awt.Color(204, 204, 204));
        txtTenKhachHang.setForeground(new java.awt.Color(0, 0, 0));
        txtTenKhachHang.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        btnChonNhanVien.setForeground(new java.awt.Color(0, 0, 0));
        btnChonNhanVien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/select.png"))); // NOI18N
        btnChonNhanVien.setColor1(new java.awt.Color(255, 255, 255));
        btnChonNhanVien.setColor2(new java.awt.Color(102, 102, 102));
        btnChonNhanVien.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnChonNhanVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChonNhanVienActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtMaKhachHang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtTenKhachHang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnChonNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMaKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 11, Short.MAX_VALUE))
                    .addComponent(txtTenKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnChonNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel13.setBackground(new java.awt.Color(38, 28, 73));
        jPanel13.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin hóa đơn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 16), new java.awt.Color(255, 255, 255))); // NOI18N

        jPanel15.setBackground(new java.awt.Color(38, 28, 73));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Mã hóa đơn");

        txtMaHoaDon.setForeground(new java.awt.Color(0, 0, 0));
        txtMaHoaDon.setEnabled(false);
        txtMaHoaDon.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Tên nhân viên");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Tổng tiền");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Tiền thanh toán");

        txtTenNhanVien.setForeground(new java.awt.Color(0, 0, 0));
        txtTenNhanVien.setEnabled(false);
        txtTenNhanVien.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        txtTongTien.setForeground(new java.awt.Color(0, 0, 0));
        txtTongTien.setEnabled(false);
        txtTongTien.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        btnPhieuGiamGia.setForeground(new java.awt.Color(0, 0, 0));
        btnPhieuGiamGia.setText("Phiếu giảm giá");
        btnPhieuGiamGia.setColor1(new java.awt.Color(255, 255, 255));
        btnPhieuGiamGia.setColor2(new java.awt.Color(102, 102, 102));
        btnPhieuGiamGia.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnPhieuGiamGia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPhieuGiamGiaActionPerformed(evt);
            }
        });

        txtTienThanhToan.setForeground(new java.awt.Color(0, 0, 0));
        txtTienThanhToan.setEnabled(false);
        txtTienThanhToan.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("HT thanh toán");

        cbbHinhThucThanhToan.setBackground(new java.awt.Color(204, 204, 204));
        cbbHinhThucThanhToan.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        cbbHinhThucThanhToan.setForeground(new java.awt.Color(255, 255, 255));
        cbbHinhThucThanhToan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tiền mặt", "Chuyển khoản", "Kết hợp" }));
        cbbHinhThucThanhToan.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cbbHinhThucThanhToan.setPreferredSize(new java.awt.Dimension(150, 28));
        cbbHinhThucThanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbHinhThucThanhToanActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Tiền khách đưa");

        txtTienKhachDua.setForeground(new java.awt.Color(0, 0, 0));
        txtTienKhachDua.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Tiền khách CK");

        txtTienKhachChuyenKhoan.setForeground(new java.awt.Color(0, 0, 0));
        txtTienKhachChuyenKhoan.setEnabled(false);
        txtTienKhachChuyenKhoan.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Tiền thừa");

        txtTienThua.setForeground(new java.awt.Color(0, 0, 0));
        txtTienThua.setEnabled(false);
        txtTienThua.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        btnThanhToan.setForeground(new java.awt.Color(0, 0, 0));
        btnThanhToan.setText("Thanh toán");
        btnThanhToan.setColor1(new java.awt.Color(255, 255, 255));
        btnThanhToan.setColor2(new java.awt.Color(102, 102, 102));
        btnThanhToan.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnThanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThanhToanActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(jLabel9)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTenNhanVien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtTongTien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtMaHoaDon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtTienThanhToan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(txtTienThua, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtTienKhachChuyenKhoan, javax.swing.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
                            .addGroup(jPanel15Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(cbbHinhThucThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtTienKhachDua, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(btnPhieuGiamGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel15Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnPhieuGiamGia, btnThanhToan});

        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMaHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTenNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTienThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbbHinhThucThanhToan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTienKhachDua, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTienKhachChuyenKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTienThua, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnPhieuGiamGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(162, 162, 162))
        );

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, 424, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, 321, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        tabbedPaneCustom1.addTab("", jPanel2);

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabbedPaneCustom1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tabbedPaneCustom1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel6.setBackground(new java.awt.Color(38, 28, 73));
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Quét mã sản phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 16), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel6.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel11.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnWebcam.setBackground(new java.awt.Color(38, 28, 73));
        pnWebcam.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pnWebcam.setLayout(new javax.swing.OverlayLayout(pnWebcam));
        jPanel11.add(pnWebcam, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 215, 180));

        jPanel6.add(jPanel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 215, 180));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 683, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(63, 63, 63))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(60, Short.MAX_VALUE))
        );

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1330, 790));
    }// </editor-fold>//GEN-END:initComponents

    private void btnChonNhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChonNhanVienActionPerformed
        jDialogKhachHang.setVisible(true);
        jDialogKhachHang.setSize(863, 760);
        jDialogKhachHang.setLocation(450, 60);
    }//GEN-LAST:event_btnChonNhanVienActionPerformed

    private void btnPhieuGiamGiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPhieuGiamGiaActionPerformed
        jDialogVoucher.setVisible(true);
        showDataVoucher(Double.parseDouble(txtTongTien.getText()));
        jDialogVoucher.setSize(863, 760);
        jDialogVoucher.setLocation(450, 60);
    }//GEN-LAST:event_btnPhieuGiamGiaActionPerformed

    private void btnThanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThanhToanActionPerformed
        int confirm = JOptionPane.showConfirmDialog(this, "Xác nhận thanh toán!", "Thanh toán!", JOptionPane.YES_NO_OPTION);
        if (confirm == 0) {
            int index = tblHoaDon.getSelectedRow();
            if (index < 0) {
                JOptionPane.showMessageDialog(this, "Mời chọn hóa đơn!");
                return;
            }
            try {
                String tongTienStr = txtTienThanhToan.getText();
                String tienKhachDuaStr = txtTienKhachDua.getText();
                String tienKhachChuyenKhoanStr = txtTienKhachChuyenKhoan.getText();

                if (!tongTienStr.isEmpty()) {
                    double tongTien = Double.parseDouble(tongTienStr);

                    if (cbbHinhThucThanhToan.getSelectedItem().equals("Tiền mặt")) {
                        if (!tienKhachDuaStr.isEmpty()) {
                            double tienKhachDua = Double.parseDouble(tienKhachDuaStr);

                            if (tienKhachDua >= tongTien) {
                                JOptionPane.showMessageDialog(this, hoaDonService.updateTrangThaiHoaDon(txtMaHoaDon.getText()));
                                hoaDonService.updateHinhThucThanhToanHoaDon(txtMaHoaDon.getText(), "TienMat");
                                if (selectedVoucher != null) {
                                    hoaDonService.updateVoucherForHoaDon(txtMaHoaDon.getText(), selectedVoucher.getId(), Double.parseDouble(txtTienThanhToan.getText()));
                                    voucherService.updateSoLuongVoucher(selectedVoucher.getId());
                                    showDataVoucher(Double.parseDouble(txtTongTien.getText()));
                                }
                                int confirmInHoaDon = JOptionPane.showConfirmDialog(this, "Xác nhận in hóa đơn?", "In hóa đơn!", JOptionPane.YES_NO_OPTION);
                                if (confirmInHoaDon == 0) {
                                    try {

                                        HoaDonBackup hoaDonBackup = hoaDonService.findHoaDonByMa((String) tblHoaDon.getValueAt(index, 0));
                                        Connection conn = DBConnect.getConnection();
                                        String path = "src\\invoice\\Invoice.jrxml";
                                        JasperReport jr = JasperCompileManager.compileReport(path);
                                        Map<String, Object> para = new HashMap<String, Object>();
                                        para.put("idHoaDon", hoaDonBackup.getId());
                                        JasperPrint jp = JasperFillManager.fillReport(jr, para, conn);
                                        JasperViewer.viewReport(jp);
                                        conn.close();
                                    } catch (Exception ex) {
                                        JOptionPane.showMessageDialog(this, ex);
                                    }
                                }
                            } else {
                                JOptionPane.showMessageDialog(this, "Tiền khách đưa không đủ để thanh toán!");
                            }
                        } else {
                            JOptionPane.showMessageDialog(this, "Vui lòng nhập tiền khách đưa!");
                        }
                    } else if (cbbHinhThucThanhToan.getSelectedItem().equals("Chuyển khoản")) {
                        double tienKhachChuyenKhoan = Double.parseDouble(tienKhachChuyenKhoanStr);
                        if (tienKhachChuyenKhoan == tongTien) {
                            JOptionPane.showMessageDialog(this, hoaDonService.updateTrangThaiHoaDon(txtMaHoaDon.getText()));
                            hoaDonService.updateHinhThucThanhToanHoaDon(txtMaHoaDon.getText(), "ChuyenKhoan");
                            if (selectedVoucher != null) {
                                hoaDonService.updateVoucherForHoaDon(txtMaHoaDon.getText(), selectedVoucher.getId(), Double.parseDouble(txtTienThanhToan.getText()));
                                voucherService.updateSoLuongVoucher(selectedVoucher.getId());

                            }
                            int confirmInHoaDon = JOptionPane.showConfirmDialog(this, "Xác nhận in hóa đơn?", "In hóa đơn!", JOptionPane.YES_NO_OPTION);
                            if (confirmInHoaDon == 0) {
                                try {
                                    HoaDonBackup hoaDonBackup = hoaDonService.findHoaDonByMa((String) tblHoaDon.getValueAt(index, 0));
                                    Connection conn = DBConnect.getConnection();
                                    String path = "src\\invoice\\Invoice.jrxml";
                                    JasperReport jr = JasperCompileManager.compileReport(path);
                                    Map<String, Object> para = new HashMap<String, Object>();
                                    para.put("idHoaDon", hoaDonBackup.getId());
                                    JasperPrint jp = JasperFillManager.fillReport(jr, para, conn);
                                    JasperViewer.viewReport(jp);
                                    conn.close();
                                } catch (Exception ex) {
                                    JOptionPane.showMessageDialog(this, ex);
                                }
                            }
                        } else {
                            JOptionPane.showMessageDialog(this, "Tiền khách chuyển khoản không đủ để thanh toán!");
                        }
                    } else {
                        if (!tienKhachDuaStr.isEmpty()) {
                            double tienKhachDua = Double.parseDouble(tienKhachDuaStr);
                            double tienKhachChuyenKhoan = Double.parseDouble(tienKhachChuyenKhoanStr);

                            if (tienKhachDua + tienKhachChuyenKhoan == tongTien) {
                                JOptionPane.showMessageDialog(this, hoaDonService.updateTrangThaiHoaDon(txtMaHoaDon.getText()));
                                hoaDonService.updateHinhThucThanhToanHoaDon(txtMaHoaDon.getText(), "KetHop");
                                if (selectedVoucher != null) {
                                    hoaDonService.updateVoucherForHoaDon(txtMaHoaDon.getText(), selectedVoucher.getId(), Double.parseDouble(txtTienThanhToan.getText()));
                                    voucherService.updateSoLuongVoucher(selectedVoucher.getId());
                                }
                                int confirmInHoaDon = JOptionPane.showConfirmDialog(this, "Xác nhận in hóa đơn?", "In hóa đơn!", JOptionPane.YES_NO_OPTION);
                                if (confirmInHoaDon == 0) {
                                    try {
                                        HoaDonBackup hoaDonBackup = hoaDonService.findHoaDonByMa((String) tblHoaDon.getValueAt(index, 0));
                                        Connection conn = DBConnect.getConnection();
                                        String path = "src\\invoice\\Invoice.jrxml";
                                        JasperReport jr = JasperCompileManager.compileReport(path);
                                        Map<String, Object> para = new HashMap<String, Object>();
                                        para.put("idHoaDon", hoaDonBackup.getId());
                                        JasperPrint jp = JasperFillManager.fillReport(jr, para, conn);
                                        JasperViewer.viewReport(jp);
                                        conn.close();
                                    } catch (Exception ex) {
                                        JOptionPane.showMessageDialog(this, ex);
                                    }
                                }
                            } else {
                                JOptionPane.showMessageDialog(this, "Tổng tiền không đủ để thanh toán!");
                            }
                        } else {
                            JOptionPane.showMessageDialog(this, "Vui lòng nhập tiền khách đưa!");
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Vui lòng nhập tổng tiền!");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Nhập số không hợp lệ!");
            }
        }
        resetTien();
        showDataHoaDonChiTiet(0);
        showDataHoaDonChuaThanhToan();
    }//GEN-LAST:event_btnThanhToanActionPerformed

    private void tblKhachHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblKhachHangMouseClicked
        int index = tblKhachHang.getSelectedRow();
        selectedKhachHang = khachHangService.getAllKhachHang().get(index);
    }//GEN-LAST:event_tblKhachHangMouseClicked

    private void btnChonKhachHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChonKhachHangActionPerformed
        txtMaKhachHang.setText(selectedKhachHang.getMaKh());
        txtTenKhachHang.setText(selectedKhachHang.getTenKh());
        hoaDonService.updateKhachHangForHoaDon(txtMaHoaDon.getText(), selectedKhachHang.getId());
        showDataHoaDonChuaThanhToan();
        jDialogKhachHang.setVisible(false);
    }//GEN-LAST:event_btnChonKhachHangActionPerformed

    private void btnThemKhachHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemKhachHangActionPerformed
        ArrayList<QlKhachHang> listKH = khachHangService.selectAll();
//        for (QlKhachHang qlkh : listKH) {
//            if (qlkh.getSdt().equals(txtsdt.getText())) {
//                JOptionPane.showMessageDialog(this, "Trùng SĐT");
//                return;
//                
//            }
//        }

        if (validateFormKhachHang()) {
            return;
        }
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date d = sdf.parse(txtNgaysinh.getText());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Ngày nhập vào không đúng định dạng");
            txtNgaysinh.requestFocus();
            return;
        }
        try {
            QlKhachHang qlKhachHang = getForm();
            if (khachHangService.insert(qlKhachHang) != null) {
                JOptionPane.showMessageDialog(this, "Thêm khách hàng thành công");
                clearFormKhachHang();
            } else {
                JOptionPane.showMessageDialog(this, "Thêm khách hàng Thất bại");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        loadlistKhachHang(khachHangService.selectAll());
    }//GEN-LAST:event_btnThemKhachHangActionPerformed

    private void btnTaoHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaoHoaDonActionPerformed
        if (tblHoaDon.getRowCount() >= 5) {
            JOptionPane.showMessageDialog(this, "Vượt quá 5 lần tạo hóa đơn!");
            return;
        }
        JOptionPane.showMessageDialog(this, hoaDonService.addHoaDon(addHoaDon()));
        showDataHoaDonChuaThanhToan();
    }//GEN-LAST:event_btnTaoHoaDonActionPerformed

    private void btnXacNhanSoLuongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXacNhanSoLuongActionPerformed
        jDialogNhapSoLuong.setVisible(false);
        int indexSanPham = tblSanPhamChiTiet.getSelectedRow();
        int indexHoaDon = tblHoaDon.getSelectedRow();
        if (indexHoaDon < 0) {
            JOptionPane.showMessageDialog(this, "Mời chọn hóa đơn!");
            resetTien();
        } else {
            SanPhamChiTiet sanPhamChiTiet = sanPhamChiTietService.findSanPhamChiTietByMa((String) tblSanPhamChiTiet.getValueAt(indexSanPham, 0));
            HoaDonBackup hoaDonBackup = hoaDonService.findHoaDonByMa((String) tblHoaDon.getValueAt(indexHoaDon, 0));
            HoaDonChiTietBackup hoaDonChiTietBackup = new HoaDonChiTietBackup();
            hoaDonChiTietBackup.setIdHoaDon(hoaDonBackup.getId());
            hoaDonChiTietBackup.setIdSanPhamChiTiet(sanPhamChiTiet.getId());
            String soLuong = txtNhapSoLuong.getText();
            if (isCheckEmpty(soLuong)) {
                JOptionPane.showMessageDialog(this, "Số lượng không được để trống!");
                return;
            }
            if (!isPositiveNumber(soLuong)) {
                JOptionPane.showMessageDialog(this, "Số lượng không đúng định dạng!");
                return;
            }
            if (Integer.parseInt(soLuong) > sanPhamChiTietService.checkSoLuong(sanPhamChiTiet.getMa())) {
                JOptionPane.showMessageDialog(this, "Số lượng không đủ để thêm vào giỏ hàng!");
                return;
            }
            hoaDonChiTietBackup.setSoLuong(Integer.parseInt(soLuong));
            hoaDonChiTietBackup.setGiaTien(sanPhamChiTiet.getGiaBan());
            hoaDonChiTietBackup.setThanhTien(Integer.parseInt(txtNhapSoLuong.getText()) * sanPhamChiTiet.getGiaBan());
            hoaDonService.addHoaDonChiTiet(hoaDonChiTietBackup);
            sanPhamChiTietService.reduceSoLuong(sanPhamChiTiet.getMa(), Integer.parseInt(txtNhapSoLuong.getText()));
            hoaDonService.updateTongTien(hoaDonBackup.getId());
            txtMaHoaDon.setText((String) tblHoaDon.getValueAt(indexHoaDon, 0));
            txtTenNhanVien.setText((String) tblHoaDon.getValueAt(indexHoaDon, 2));
            txtTongTien.setText(hoaDonBackup.getTongTien() + "");
            showDataHoaDonChiTiet(hoaDonBackup.getId());
            showDataSanPhamChiTiet();
            txtNhapSoLuong.setText("");
            resetTien();
        }
    }//GEN-LAST:event_btnXacNhanSoLuongActionPerformed

    private void tblSanPhamChiTietMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSanPhamChiTietMouseClicked
        int index = tblSanPhamChiTiet.getSelectedRow();
        if (index != -1 && evt.getClickCount() == 2) {
            jDialogNhapSoLuong.setVisible(true);
            jDialogNhapSoLuong.setSize(415, 240);
            jDialogNhapSoLuong.setLocation(800, 350);
        }
    }//GEN-LAST:event_tblSanPhamChiTietMouseClicked

    private void tblHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoaDonMouseClicked
        int index = tblHoaDon.getSelectedRow();
        HoaDonBackup hoaDonBackup = hoaDonService.findHoaDonByMa((String) tblHoaDon.getValueAt(index, 0));
        showDataHoaDonChiTiet(hoaDonBackup.getId());

        txtMaHoaDon.setText(hoaDonBackup.getMaHoaDon());
        KhachHang khachHang = khachHangService.findKhachHangById(hoaDonBackup.getIdKhachHang());
        txtMaKhachHang.setText(khachHang.getMaKh());
        txtTenKhachHang.setText(khachHang.getTenKh());
        System.out.println(khachHang.getMaKh());
        System.out.println(khachHang.getTenKh());
        txtTenNhanVien.setText((String) tblHoaDon.getValueAt(index, 2));
        txtTongTien.setText(hoaDonBackup.getTongTien() + "");
        txtTienThanhToan.setText(hoaDonBackup.getTongTienGiam() + "");
    }//GEN-LAST:event_tblHoaDonMouseClicked

    private void btnTimKiemKhachHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemKhachHangActionPerformed
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date date = null;
        String input = txtTimkiem.getText();

        ArrayList<QlKhachHang> listKhachHang = new ArrayList<>();
        ArrayList<QlKhachHang> lstByMa = new ArrayList<>();
        ArrayList<QlKhachHang> lstByName = new ArrayList<>();

        lstByMa = khachHangService.getAllKhByMa0(input);
        listKhachHang.addAll(lstByMa);
        lstByName = khachHangService.getAllKhByNameKM0(input);
        listKhachHang.addAll(lstByName);
        if (listKhachHang.size() == 0) {
            loadlistKhachHang(listKhachHang);
            JOptionPane.showMessageDialog(this, "Không tìm thấy Khách hàng");
        } else {

            if (lstByMa.size() != 0) {
                loadlistKhachHang(khachHangService.getAllKhByMa0(input));
            }
            if (lstByName.size() != 0) {
                loadlistKhachHang(khachHangService.getAllKhByNameKM0(input));
            }

        }
    }//GEN-LAST:event_btnTimKiemKhachHangActionPerformed

    private void btnLoadKhachHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoadKhachHangActionPerformed
//        loadlistKhachHang(khachHangService.getAllKhachHang());
        showDataKhachHang();
    }//GEN-LAST:event_btnLoadKhachHangActionPerformed

    private void btnChangeSoLuongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChangeSoLuongActionPerformed
        int indexHoaDon = tblHoaDon.getSelectedRow();
        int indexSanPham = tblHoaDonChiTiet.getSelectedRow();
        String maHoaDon = (String) tblHoaDon.getValueAt(indexHoaDon, 0);
        String maSanPham = (String) tblHoaDonChiTiet.getValueAt(indexSanPham, 0);

        HoaDonChiTietBackup hoaDonChiTietBackup = hoaDonService.findHDCTByMaSPCTAndMaHD(maSanPham, maHoaDon);
        HoaDonBackup hoaDonBackup = hoaDonService.findHoaDonByMa(maHoaDon);
        SanPhamChiTiet sanPhamChiTiet = sanPhamChiTietService.findSanPhamChiTietByMa(maSanPham);
        String newSoLuong = txtChangeSoLuong.getText();
        if (isCheckEmpty(newSoLuong)) {
            JOptionPane.showMessageDialog(this, "Số lượng không được để trống!");
            return;
        }
        if (Integer.parseInt(newSoLuong) < 0) {
            JOptionPane.showMessageDialog(this, "Số lượng không đúng định dạng!");
            return;
        }
        if (sanPhamChiTiet != null && hoaDonBackup != null) {
            if (Integer.parseInt(newSoLuong) > hoaDonService.getSoLuongByMaSPCTAndIdHDCT(maSanPham, hoaDonChiTietBackup.getId())) {
                JOptionPane.showMessageDialog(this, "Số lượng sản phẩm trong cửa hàng không đủ!");
                return;
            }
            if (Integer.parseInt(newSoLuong) == 0) {
                hoaDonService.updateSoLuongForSanPhamAndGioHang(hoaDonBackup.getId(), sanPhamChiTiet.getId(), Integer.parseInt(newSoLuong));
                hoaDonService.deleteHDCTWhereSLEqualZero(hoaDonChiTietBackup.getId());
            }
            hoaDonService.updateSoLuongForSanPhamAndGioHang(hoaDonBackup.getId(), sanPhamChiTiet.getId(), Integer.parseInt(newSoLuong));
            showDataHoaDonChiTiet(hoaDonBackup.getId());
            showDataSanPhamChiTiet();
            jDialogChangeSoLuong.setVisible(false);
        }
    }//GEN-LAST:event_btnChangeSoLuongActionPerformed

    private void tblHoaDonChiTietMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoaDonChiTietMouseClicked
        int index = tblHoaDonChiTiet.getSelectedRow();
        if (index != -1 && evt.getClickCount() == 2) {
            jDialogChangeSoLuong.setVisible(true);
            jDialogChangeSoLuong.setSize(415, 240);
            jDialogChangeSoLuong.setLocation(800, 350);
        }
    }//GEN-LAST:event_tblHoaDonChiTietMouseClicked

    private void btnNhapSoLuongWebcamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNhapSoLuongWebcamActionPerformed
        jDialogNhapSoLuongWebcam.setVisible(false);
        int indexHoaDon = tblHoaDon.getSelectedRow();
        if (indexHoaDon < 0) {
            JOptionPane.showMessageDialog(this, "Mời chọn hóa đơn!");
            jDialogKhachHang.setVisible(false);
        } else {
            SanPhamChiTiet sanPhamChiTiet = sanPhamChiTietService.findSanPhamChiTietByMa(resultWebcam.getText());
            HoaDonBackup hoaDonBackup = hoaDonService.findHoaDonByMa((String) tblHoaDon.getValueAt(indexHoaDon, 0));
            HoaDonChiTietBackup hoaDonChiTietBackup = new HoaDonChiTietBackup();
            hoaDonChiTietBackup.setIdHoaDon(hoaDonBackup.getId());
            hoaDonChiTietBackup.setIdSanPhamChiTiet(sanPhamChiTiet.getId());
            String soLuong = txtNhapSoLuongWebcam.getText();
            if (isCheckEmpty(soLuong)) {
                JOptionPane.showMessageDialog(this, "Số lượng không được để trống!");
                return;
            }
            if (!isPositiveNumber(soLuong)) {
                JOptionPane.showMessageDialog(this, "Số lượng không đúng định dạng!");
                return;
            }
            if (Integer.parseInt(soLuong) > sanPhamChiTietService.checkSoLuong(resultWebcam.getText())) {
                JOptionPane.showMessageDialog(this, "Số lượng không đủ để thêm vào giỏ hàng!");
                return;
            }
            hoaDonChiTietBackup.setSoLuong(Integer.parseInt(soLuong));
            hoaDonChiTietBackup.setGiaTien(sanPhamChiTiet.getGiaBan());
            hoaDonChiTietBackup.setThanhTien(Integer.parseInt(soLuong) * sanPhamChiTiet.getGiaBan());
            hoaDonService.addHoaDonChiTiet(hoaDonChiTietBackup);
            sanPhamChiTietService.reduceSoLuong(sanPhamChiTiet.getMa(), Integer.parseInt(soLuong));
            hoaDonService.updateTongTien(hoaDonBackup.getId());
            txtMaHoaDon.setText((String) tblHoaDon.getValueAt(indexHoaDon, 0));
            txtTenNhanVien.setText((String) tblHoaDon.getValueAt(indexHoaDon, 2));
            txtTongTien.setText(hoaDonBackup.getTongTien() + "");
            showDataHoaDonChiTiet(hoaDonBackup.getId());
            showDataSanPhamChiTiet();
        }
    }//GEN-LAST:event_btnNhapSoLuongWebcamActionPerformed

    private void cbbHinhThucThanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbHinhThucThanhToanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbHinhThucThanhToanActionPerformed

    private void tblVoucherMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblVoucherMouseClicked
        int index = tblVoucher.getSelectedRow();
        selectedVoucher = voucherService.getAllByTongTien(Double.parseDouble(txtTongTien.getText())).get(index);
    }//GEN-LAST:event_tblVoucherMouseClicked

    private void btnChonVoucherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChonVoucherActionPerformed
        double tongTien = Double.parseDouble(txtTongTien.getText());
        if (selectedVoucher == null) {
//            btnChonVoucher.disable();
            JOptionPane.showMessageDialog(this, "Mời chọn voucher!");
            return;
        }
        if (selectedVoucher.isLoaiGiamGia() == false) {
            double tienThanhToanVND = tongTien - selectedVoucher.getGiaTriGiam();
            txtTienThanhToan.setText(String.valueOf(tienThanhToanVND));
        } else if (selectedVoucher.isLoaiGiamGia() == true) {
            double tienGiam = (tongTien * selectedVoucher.getGiaTriGiam()) / 100;
            double tienThanhToanPercent = tongTien - tienGiam;
            txtTienThanhToan.setText(String.valueOf(tienThanhToanPercent));
        }
    }//GEN-LAST:event_btnChonVoucherActionPerformed

    private void btnTimKiemKhachHang1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemKhachHang1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnTimKiemKhachHang1ActionPerformed

    private void btnLoadKhachHang1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoadKhachHang1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnLoadKhachHang1ActionPerformed

    private void btnXoaAllHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaAllHoaDonActionPerformed
        int confirm = JOptionPane.showConfirmDialog(this, "Xác nhận xóa tất cả hóa đơn chưa thanh toán!", "Xóa!", JOptionPane.YES_NO_OPTION);
        if (confirm == 0) {
            hoaDonService.deleteHoaDon();
            showDataHoaDonChuaThanhToan();
            resetTien();
            showDataHoaDonChiTiet(0);
            showDataSanPhamChiTiet();
        }
    }//GEN-LAST:event_btnXoaAllHoaDonActionPerformed

    private void btnXoaAllHoaDonChiTietActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaAllHoaDonChiTietActionPerformed
        int index = tblHoaDon.getSelectedRow();
        if (index < 0) {
            JOptionPane.showMessageDialog(this, "Mời chọn hóa đơn!");
            return;
        }
        int confirm = JOptionPane.showConfirmDialog(this, "Xác nhận xóa sản phẩm trong giỏ hàng!", "Xóa!", JOptionPane.YES_NO_OPTION);
        if (confirm == 0) {
            HoaDonBackup hoaDonBackup = hoaDonService.findHoaDonByMa((String) tblHoaDon.getValueAt(index, 0));
            hoaDonService.deleteHDCTByIdHoaDon(hoaDonBackup.getId());
            hoaDonService.updateTongTien(hoaDonBackup.getId());
            showDataHoaDonChuaThanhToan();
            resetTien();
            showDataHoaDonChiTiet(hoaDonBackup.getId());
            showDataSanPhamChiTiet();
        }
    }//GEN-LAST:event_btnXoaAllHoaDonChiTietActionPerformed

    private void btnShowSanPhamChiTietActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnShowSanPhamChiTietActionPerformed
        showDataSanPhamChiTiet();
    }//GEN-LAST:event_btnShowSanPhamChiTietActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private app.view.swing.ButtonGradient btnChangeSoLuong;
    private app.view.swing.ButtonGradient btnChonKhachHang;
    private app.view.swing.ButtonGradient btnChonNhanVien;
    private app.view.swing.ButtonGradient btnChonVoucher;
    private javax.swing.ButtonGroup btnGioiTinh;
    private app.view.swing.ButtonGradient btnLoadKhachHang;
    private app.view.swing.ButtonGradient btnLoadKhachHang1;
    private app.view.swing.ButtonGradient btnNhapSoLuongWebcam;
    private app.view.swing.ButtonGradient btnPhieuGiamGia;
    private app.view.swing.ButtonGradient btnShowSanPhamChiTiet;
    private app.view.swing.ButtonGradient btnTaoHoaDon;
    private app.view.swing.ButtonGradient btnThanhToan;
    private app.view.swing.ButtonGradient btnThemKhachHang;
    private app.view.swing.ButtonGradient btnTimKiemKhachHang;
    private app.view.swing.ButtonGradient btnTimKiemKhachHang1;
    private app.view.swing.ButtonGradient btnXacNhanSoLuong;
    private app.view.swing.ButtonGradient btnXoaAllHoaDon;
    private app.view.swing.ButtonGradient btnXoaAllHoaDonChiTiet;
    private app.view.swing.ComboBoxSuggestion cbbChatLieuBanHang;
    private app.view.swing.ComboBoxSuggestion cbbChieuDaiTayBanHang;
    private app.view.swing.ComboBoxSuggestion cbbCoAoBanHang;
    private app.view.swing.ComboBoxSuggestion cbbHinhThucThanhToan;
    private app.view.swing.ComboBoxSuggestion cbbKichCoBanHang;
    private app.view.swing.ComboBoxSuggestion cbbMauSacBanHang;
    private javax.swing.JDialog jDialogChangeSoLuong;
    private javax.swing.JDialog jDialogKhachHang;
    private javax.swing.JDialog jDialogNhapSoLuong;
    private javax.swing.JDialog jDialogNhapSoLuongWebcam;
    private javax.swing.JDialog jDialogVoucher;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel30;
    private javax.swing.JPanel jPanel31;
    private javax.swing.JPanel jPanel32;
    private javax.swing.JPanel jPanel34;
    private javax.swing.JPanel jPanel35;
    private javax.swing.JPanel jPanel36;
    private javax.swing.JPanel jPanel37;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPanel pnWebcam;
    private radio_button.RadioButtonCustom rdoNam;
    private radio_button.RadioButtonCustom rdoNu;
    private custome_ui.swing.ScrollPaneWin11 scrollPaneWin111;
    private custome_ui.swing.ScrollPaneWin11 scrollPaneWin112;
    private custome_ui.swing.ScrollPaneWin11 scrollPaneWin113;
    private custome_ui.swing.ScrollPaneWin11 scrollPaneWin114;
    private custome_ui.swing.ScrollPaneWin11 scrollPaneWin115;
    private app.view.swing.TabbedPaneCustom tabbedPaneCustom1;
    private app.view.swing.TabbedPaneCustom tabbedPaneCustom2;
    private app.view.swing.TabbedPaneCustom tabbedPaneCustom3;
    private rojeru_san.complementos.RSTableMetro tblHoaDon;
    private rojeru_san.complementos.RSTableMetro tblHoaDonChiTiet;
    private rojeru_san.complementos.RSTableMetro tblKhachHang;
    private rojeru_san.complementos.RSTableMetro tblSanPhamChiTiet;
    private rojeru_san.complementos.RSTableMetro tblVoucher;
    private textfield.TextField2 txtChangeSoLuong;
    private textfield.TextField2 txtEmail;
    private textfield.TextField2 txtMaHoaDon;
    private textfield.TextField2 txtMaKhachHang;
    private textfield.TextField2 txtNgaysinh;
    private textfield.TextField2 txtNhapSoLuong;
    private textfield.TextField2 txtNhapSoLuongWebcam;
    private textfield.TextField2 txtTenKH;
    private textfield.TextField2 txtTenKhachHang;
    private textfield.TextField2 txtTenNhanVien;
    private textfield.TextField2 txtTienKhachChuyenKhoan;
    private textfield.TextField2 txtTienKhachDua;
    private textfield.TextField2 txtTienThanhToan;
    private textfield.TextField2 txtTienThua;
    private textfield.TextField2 txtTimKiemSanPham;
    private textfield.TextField2 txtTimkiem;
    private textfield.TextField2 txtTimkiem1;
    private textfield.TextField2 txtTongTien;
    private textfield.TextField2 txtdiachi;
    private textfield.TextField2 txtsdt;
    // End of variables declaration//GEN-END:variables
}
