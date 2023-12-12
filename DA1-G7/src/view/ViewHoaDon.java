/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;

import DomainModels.QLHoaDon;
import DomainModels.QLHoaDonChiTiet;
import com.lowagie.text.Cell;
import com.lowagie.text.Row;
import java.awt.FileDialog;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.plaf.FileChooserUI;
import javax.swing.table.DefaultTableModel;
import model.HoaDonBackup;
import model.SanPham;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import service.hoadonservice.IHoaDonServices;
import service.hoadonservice.impl.HoaDonService;
import util.DBConnect;
import util.Uhelper;
import java.sql.Connection;

/**
 *
 * @author bcuon
 */
public class ViewHoaDon extends javax.swing.JPanel {

    private HoaDonService serv = new HoaDonService();

    public ViewHoaDon() {
        initComponents();
        loadToTable();
        loadToTableCT();
    }

    private void loadToTable() {
        DefaultTableModel model = (DefaultTableModel) this.tableHoaDon.getModel();
        model.setRowCount(0);
        String tt = null;
        int index = 0;
        for (QLHoaDon hd : this.serv.selectAll()) {
            index++;
            if (hd.getTrangthai() == 1) {
                tt = " Đã thanh toán";
            } else {
                tt = " Chưa thanh toán";

            }
            System.out.println(index);
            model.addRow(new Object[]{
                index, hd.getmaHoaDon(), hd.gettenKhacHang(), hd.gettenNhanVien(), hd.getGiaTriVoucher(),
                hd.getTongTien(), hd.getTongTienGiam(), hd.getNgayTao(), hd.getGhiChu(),
                tt
//                    hd.getTrangthai()
            });
        }
    }

    private void loadToTableCT() {
        DefaultTableModel model = (DefaultTableModel) this.tableChiTiet.getModel();
        model.setRowCount(0);
        int index = 0;
        for (QLHoaDonChiTiet hd : this.serv.selectAllCT()) {
            index++;
            model.addRow(new Object[]{
                index, hd.getMaHoaDon(), hd.getTenSp(), hd.getSoLuong(),
                hd.getGiaTien(), hd.getThanhTien(), hd.getNgayTao()
            });
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        scrollPaneWin111 = new custome_ui.swing.ScrollPaneWin11();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        tblhoadonchitiet = new app.view.swing.TabbedPaneCustom();
        scrollPaneWin112 = new custome_ui.swing.ScrollPaneWin11();
        tableHoaDon = new rojeru_san.complementos.RSTableMetro();
        scrollPaneWin114 = new custome_ui.swing.ScrollPaneWin11();
        tableChiTiet = new rojeru_san.complementos.RSTableMetro();
        cboTrangThai = new app.view.swing.ComboBoxSuggestion();
        btnTimKiem1 = new app.view.swing.ButtonGradient();
        btnLoc = new app.view.swing.ButtonGradient();
        btnXuatExcel = new app.view.swing.ButtonGradient();
        btnloadLai = new app.view.swing.ButtonGradient();
        jButton4 = new app.view.swing.ButtonGradient();
        txtIDHoaDon = new textfield.TextField2();
        DateKetThuc = new com.toedter.calendar.JDateChooser();
        DatebatDau = new com.toedter.calendar.JDateChooser();
        jLabel6 = new javax.swing.JLabel();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(38, 28, 73));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Mã hóa đơn");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Lọc Theo ngày");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Ngày Bắt Đầu");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Ngày Kết Thúc");

        tblhoadonchitiet.setBackground(new java.awt.Color(38, 28, 73));
        tblhoadonchitiet.setForeground(new java.awt.Color(255, 255, 255));
        tblhoadonchitiet.setSelectedColor(new java.awt.Color(51, 0, 102));
        tblhoadonchitiet.setUnselectedColor(new java.awt.Color(38, 32, 78));

        tableHoaDon.setModel(new javax.swing.table.DefaultTableModel(
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
                "STT", "Mã Hóa Đơn", "Tên Khách Hàng", "Tên Nhân Viên", "Giá Trị  Voucher", "Tổng Tiền", "Tổng Tiền Giảm", "Ngày Tạo", "Ghi Chú", "Trạng Thái"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, true, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableHoaDon.setColorBackgoundHead(new java.awt.Color(102, 102, 102));
        tableHoaDon.setColorBordeFilas(new java.awt.Color(153, 153, 153));
        tableHoaDon.setColorBordeHead(new java.awt.Color(153, 153, 153));
        tableHoaDon.setColorFilasBackgound1(new java.awt.Color(204, 204, 204));
        tableHoaDon.setColorFilasForeground1(new java.awt.Color(0, 0, 0));
        tableHoaDon.setColorFilasForeground2(new java.awt.Color(0, 0, 0));
        tableHoaDon.setColorSelBackgound(new java.awt.Color(38, 28, 73));
        tableHoaDon.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tableHoaDon.setFuenteFilas(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tableHoaDon.setFuenteFilasSelect(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        tableHoaDon.setFuenteHead(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        tableHoaDon.setRowHeight(40);
        tableHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableHoaDonMouseClicked(evt);
            }
        });
        scrollPaneWin112.setViewportView(tableHoaDon);

        tblhoadonchitiet.addTab("Bảng Hóa Đơn", scrollPaneWin112);

        tableChiTiet.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã Hóa Đơn", "Tên Sản Phẩm", "Số Lượng", "Giá Tiền", "Thành Tiền", "Ngày Tạo"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableChiTiet.setColorBackgoundHead(new java.awt.Color(102, 102, 102));
        tableChiTiet.setColorBordeFilas(new java.awt.Color(153, 153, 153));
        tableChiTiet.setColorBordeHead(new java.awt.Color(153, 153, 153));
        tableChiTiet.setColorFilasBackgound1(new java.awt.Color(204, 204, 204));
        tableChiTiet.setColorFilasForeground1(new java.awt.Color(0, 0, 0));
        tableChiTiet.setColorFilasForeground2(new java.awt.Color(0, 0, 0));
        tableChiTiet.setColorSelBackgound(new java.awt.Color(38, 28, 73));
        tableChiTiet.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tableChiTiet.setFuenteFilas(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tableChiTiet.setFuenteFilasSelect(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        tableChiTiet.setFuenteHead(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        tableChiTiet.setRowHeight(40);
        tableChiTiet.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableChiTietMouseClicked(evt);
            }
        });
        scrollPaneWin114.setViewportView(tableChiTiet);

        tblhoadonchitiet.addTab("Bảng Chi Tiết Hóa Đơn", scrollPaneWin114);

        cboTrangThai.setBackground(new java.awt.Color(204, 204, 204));
        cboTrangThai.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        cboTrangThai.setForeground(new java.awt.Color(255, 255, 255));
        cboTrangThai.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Đã Thanh Toán", "Chưa Thanh Toán" }));
        cboTrangThai.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        btnTimKiem1.setForeground(new java.awt.Color(0, 0, 0));
        btnTimKiem1.setText("Tìm Kiếm");
        btnTimKiem1.setColor1(new java.awt.Color(255, 255, 255));
        btnTimKiem1.setColor2(new java.awt.Color(102, 102, 102));
        btnTimKiem1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnTimKiem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiem1ActionPerformed(evt);
            }
        });

        btnLoc.setForeground(new java.awt.Color(0, 0, 0));
        btnLoc.setText("Lọc");
        btnLoc.setColor1(new java.awt.Color(255, 255, 255));
        btnLoc.setColor2(new java.awt.Color(102, 102, 102));
        btnLoc.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnLoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLocActionPerformed(evt);
            }
        });

        btnXuatExcel.setForeground(new java.awt.Color(0, 0, 0));
        btnXuatExcel.setText("Xuất Excel");
        btnXuatExcel.setColor1(new java.awt.Color(255, 255, 255));
        btnXuatExcel.setColor2(new java.awt.Color(102, 102, 102));
        btnXuatExcel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnXuatExcel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXuatExcelActionPerformed(evt);
            }
        });

        btnloadLai.setForeground(new java.awt.Color(0, 0, 0));
        btnloadLai.setText("load Lại");
        btnloadLai.setColor1(new java.awt.Color(255, 255, 255));
        btnloadLai.setColor2(new java.awt.Color(102, 102, 102));
        btnloadLai.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnloadLai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnloadLaiActionPerformed(evt);
            }
        });

        jButton4.setForeground(new java.awt.Color(0, 0, 0));
        jButton4.setText("Xuất hóa Đơn");
        jButton4.setColor1(new java.awt.Color(255, 255, 255));
        jButton4.setColor2(new java.awt.Color(102, 102, 102));
        jButton4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        txtIDHoaDon.setForeground(new java.awt.Color(0, 0, 0));
        txtIDHoaDon.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtIDHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIDHoaDonActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Trạng Thái");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addComponent(jLabel1))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(txtIDHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(52, 52, 52)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(cboTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(34, 34, 34)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btnXuatExcel, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(56, 56, 56)
                                .addComponent(btnloadLai, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btnTimKiem1, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(52, 52, 52)
                                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(37, 37, 37)
                                .addComponent(btnLoc, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel4))
                                .addGap(34, 34, 34)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(DatebatDau, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE)
                                    .addComponent(DateKetThuc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(tblhoadonchitiet, javax.swing.GroupLayout.PREFERRED_SIZE, 1306, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(8, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel3)
                        .addGap(21, 21, 21)
                        .addComponent(DatebatDau, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(DateKetThuc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(38, 38, 38))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel1)
                                        .addComponent(jLabel6)
                                        .addComponent(btnTimKiem1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                        .addGap(8, 8, 8)
                                        .addComponent(btnLoc, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtIDHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cboTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnXuatExcel, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnloadLai, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel5)))
                        .addGap(32, 32, 32)))
                .addComponent(tblhoadonchitiet, javax.swing.GroupLayout.PREFERRED_SIZE, 611, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(225, 225, 225))
        );

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1320, 920));
    }// </editor-fold>//GEN-END:initComponents
    private void timKiem() {
        String ma = this.txtIDHoaDon.getText();
        if (Uhelper.checkNull(txtIDHoaDon, "không để trống mã Hóa Đơn")) {
            return;
        } else {
            String tt = cboTrangThai.getSelectedItem().toString();
            int tt1;
            if (tt.equals("Chưa Thanh Toán")) {
                tt1 = 0;
                System.out.println(tt1 + " lấy trạng thái thành công");

            } else {
                tt1 = 1;
            }
            this.timKiemHoaDo(ma, tt1);
            this.timkiemHoaDonChiTiet(ma);
            // TODO add your handling code here:

        }
    }

    private void timKiemHoaDo(String ma, int tt) {
        DefaultTableModel model = (DefaultTableModel) this.tableHoaDon.getModel();
        model.setRowCount(0);
        int index = 0;
        for (QLHoaDon hd : this.serv.selectloc(ma, tt)) {
            String tt1;
            index++;
            if (hd.getTrangthai() == 1) {
                tt1 = " Đã thanh toán";
            } else {
                tt1 = " Chưa thanh toán";

            }
            model.addRow(new Object[]{
                index, hd.getmaHoaDon(), hd.gettenKhacHang(), hd.gettenNhanVien(), hd.getGiaTriVoucher(),
                hd.getTongTien(), hd.getTongTienGiam(), hd.getNgayTao(), hd.getGhiChu(),
                tt
            });
        }
    }

    private void timkiemHoaDonChiTiet(String ma) {
        DefaultTableModel model1 = (DefaultTableModel) this.tableChiTiet.getModel();
        model1.setRowCount(0);
        int index = 0;
        for (QLHoaDonChiTiet hd : this.serv.selectlocCT(ma)) {
            index++;
            model1.addRow(new Object[]{
                index, hd.getMaHoaDon(), hd.getTenSp(), hd.getSoLuong(),
                hd.getGiaTien(), hd.getThanhTien(), hd.getNgayTao()
            });
        }
    }

    private void loc() {

        Date ngayBatDau = this.DatebatDau.getDate();
        Date ngayKetThuc = this.DateKetThuc.getDate();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        // Ép kiểu và chỉ lấy phần ngày (ngày, tháng, năm)
        String ngayBatDauString = dateFormat.format(ngayBatDau);
        String ngayKetThucString = dateFormat.format(ngayKetThuc);
        System.out.println(ngayBatDauString + " okey");
        DefaultTableModel model = (DefaultTableModel) this.tableHoaDon.getModel();
        model.setRowCount(0);
        int index = 0;
        ArrayList<QLHoaDon> list = this.serv.selectlocSupper(ngayBatDauString, ngayKetThucString);
        for (QLHoaDon hd : list) {
            index++;
            String tt1;

            if (hd.getTrangthai() == 1) {
                tt1 = " Đã thanh toán";
            } else {
                tt1 = " Chưa thanh toán";

            }

            model.addRow(new Object[]{
                index, hd.getmaHoaDon(), hd.gettenKhacHang(), hd.gettenNhanVien(), hd.getGiaTriVoucher(),
                hd.getTongTien(), hd.getTongTienGiam(), hd.getNgayTao(), hd.getGhiChu(),
                tt1
            });
        }

        DefaultTableModel model1 = (DefaultTableModel) this.tableChiTiet.getModel();
        model1.setRowCount(0);
        for (QLHoaDonChiTiet hd : this.serv.selectlocSupperCT(ngayBatDauString, ngayKetThucString)) {
            model1.addRow(new Object[]{
                hd.getId(), hd.getMaHoaDon(), hd.getTenSp(), hd.getSoLuong(),
                hd.getGiaTien(), hd.getThanhTien(), hd.getNgayTao()
            });
        }
    }

    private void exportToExcel(JTable table, String filePath) {
        try (Workbook workbook = new XSSFWorkbook(); FileOutputStream fileOut = new FileOutputStream(filePath + ".xlsx")) {
            XSSFSheet sheet = (XSSFSheet) workbook.createSheet("HoaDonData");
            // Header row
            XSSFRow headerRow = sheet.createRow(0);
            for (int i = 0; i < table.getColumnCount(); i++) {
                XSSFCell cell = headerRow.createCell(i);
                cell.setCellValue(table.getColumnName(i));
            }
            for (int i = 0; i < table.getRowCount(); i++) {
                XSSFRow row = sheet.createRow(i + 1);
                for (int j = 0; j < table.getColumnCount(); j++) {
                    XSSFCell cell = row.createCell(j);
                    Object value = table.getValueAt(i, j);
                    cell.setCellValue(value != null ? value.toString() : "");
                }
            }
            // Write the workbook to a file
            workbook.write(fileOut);
            System.out.println("file Excel đã dược tạo!");

        } catch (FileNotFoundException e) {
            System.err.println("không thể tìm thấy file: " + filePath);
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("Lỗi trong quá trình nhập file: " + filePath);
            e.printStackTrace();
        }
    }

    private void loadLai() {
        loadToTable();
        loadToTableCT();
    }

    private void xuatExcel() {
        JFileChooser pathFilExcel = new JFileChooser();
        int userSelection = pathFilExcel.showSaveDialog(null);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            String filePath = pathFilExcel.getSelectedFile().getAbsolutePath();
            exportToExcel(tableHoaDon, filePath);
        }
        JOptionPane.showMessageDialog(this, "Xuất file thành công");
    }

    private void xuatHoaDon() {
        int row = this.tableChiTiet.getSelectedRow();
        String id = this.tableChiTiet.getValueAt(row, 1).toString();
        String ma1 = (String) this.tableChiTiet.getValueAt(row, 1);
        int tt1;
        String tt = this.tableChiTiet.getValueAt(row, 9).toString();
        if (tt.equals("Chưa thanh toán")) {
            tt1 = 0;
        } else {
            tt1 = 1;
        }
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn Hóa Đơn cần in");
            return;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("mm:HH dd-MM");
        String noiDung = """
                                                  Hóa đơn chi tiết
                          "Áo Thun G_7 Store - tầng 1 CT2c2 CC Quốc Hội \n"
                         """ + "\nThời Gian  :" + sdf.format(new Date())
                + "\nTên SP                          " + "Số Lượng                                " + "Thành tiền     " //                    + "hotline : 0987654321\n"
                //                    + "STK....3692659.... (Họ và Ten)\n"
                //                    + "                                         Hóa Đơn Bán hàng\n"
                //                + "Số Hóa Đơn : " + id
                //                    + "Người Bán  :"
                ;
        var lst = this.serv.selectlocCT(id);
        for (QLHoaDonChiTiet hd : lst) {
            noiDung = noiDung + "\n" + hd.getTenSp() + "                  " + hd.getSoLuong() + "                                  " + hd.getThanhTien() + "\n"
                    + "";
//            tongTien = tongTien.add(hd.getThanhTien());

        }
        String noiDung1 = null;
        for (QLHoaDon hd : this.serv.selectloc(ma1, tt1)) {

            noiDung1
                    = "\n----------------------------------------------------------------------------------------------------------------------------------------------------------"
                    + "\nTổng tiền                :" + hd.getGiaTriVoucher()
                    + "\nTienGiamGiam             :" + hd.getTongTienGiam()
                    + "\nTiền Cần Thanh toán      :" + hd.getTongTien();

        }
//        BigDecimal tienct = tongTien.subtract(tienGiamGiam);

        util.Print.printText(noiDung + noiDung1);
    }
    private void tableHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableHoaDonMouseClicked
        int row = this.tableHoaDon.getSelectedRow();
//        mã hóa đơn
        String maHoaDon = this.tableHoaDon.getValueAt(row, 1).toString();
        this.timkiemHoaDonChiTiet(maHoaDon);

        this.tblhoadonchitiet.setSelectedIndex(1);
        // TODO add your handling code here:
    }//GEN-LAST:event_tableHoaDonMouseClicked

    private void tableChiTietMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableChiTietMouseClicked
//        int index = tableChiTiet.getSelectedRow();
//        int limit = 10;
//        int offset = (currentPage - 1) * limit + index;
//        if (index >= 0) {
//            SanPham selectedSanPham = sanPhamService.getAll(offset, 1).get(0);
//            fillDataSanPham(selectedSanPham);
//        }
    }//GEN-LAST:event_tableChiTietMouseClicked

    private void btnTimKiem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiem1ActionPerformed
        this.timKiem();        // TODO add your handling code here:
    }//GEN-LAST:event_btnTimKiem1ActionPerformed

    private void btnLocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLocActionPerformed
        this.loc();        // TODO add your handling code here:
    }//GEN-LAST:event_btnLocActionPerformed

    private void btnXuatExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXuatExcelActionPerformed
        this.xuatExcel();        // TODO add your handling code here:
    }//GEN-LAST:event_btnXuatExcelActionPerformed

    private void btnloadLaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnloadLaiActionPerformed
        this.loadLai();
    }//GEN-LAST:event_btnloadLaiActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        int confirmInHoaDon = JOptionPane.showConfirmDialog(this, "Xác nhận in hóa đơn?", "In hóa đơn!", JOptionPane.YES_NO_OPTION);
        if (confirmInHoaDon == 0) {
            try {
                int index = tableHoaDon.getSelectedRow();
                HoaDonBackup hoaDonBackup = serv.findHoaDonByMa((String) tableHoaDon.getValueAt(index, 1));
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
    }//GEN-LAST:event_jButton4ActionPerformed

    private void txtIDHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIDHoaDonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIDHoaDonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser DateKetThuc;
    private com.toedter.calendar.JDateChooser DatebatDau;
    private app.view.swing.ButtonGradient btnLoc;
    private app.view.swing.ButtonGradient btnTimKiem1;
    private app.view.swing.ButtonGradient btnXuatExcel;
    private app.view.swing.ButtonGradient btnloadLai;
    private app.view.swing.ComboBoxSuggestion cboTrangThai;
    private app.view.swing.ButtonGradient jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private custome_ui.swing.ScrollPaneWin11 scrollPaneWin111;
    private custome_ui.swing.ScrollPaneWin11 scrollPaneWin112;
    private custome_ui.swing.ScrollPaneWin11 scrollPaneWin114;
    private rojeru_san.complementos.RSTableMetro tableChiTiet;
    private rojeru_san.complementos.RSTableMetro tableHoaDon;
    private app.view.swing.TabbedPaneCustom tblhoadonchitiet;
    private textfield.TextField2 txtIDHoaDon;
    // End of variables declaration//GEN-END:variables
}
