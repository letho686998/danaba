/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;

import service.ThongkeService.impl.ThongKeSerivrce;
import javax.swing.table.DefaultTableModel;
import service.ThongkeService.impl.ThongKeSerivrce;
import javax.swing.table.DefaultTableModel;
import DomainModels.QLThongKe;
import DomainModels.QLThongKe;
import DomainModels.QLThongkeBanHang;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;

public class ViewThongKe extends javax.swing.JPanel {

    private ThongKeSerivrce serv = new ThongKeSerivrce();

    public ViewThongKe() {
        initComponents();
        LoadTablekho();
        LoadTablebanhang();
        ArrayList<QLThongkeBanHang> dataList = serv.selectcBanHang();
        serv.setDatetoChart(JpanelViewThongke, dataList);
    }

    private void LoadTableTon() {
        DefaultTableModel model = (DefaultTableModel) this.tableKho.getModel();
        model.setRowCount(0);
        int index = 0;
        String tt;
        for (QLThongKe hd : this.serv.selectAllSanPhamTon()) {

            if (hd.getTrangThai() == 1) {
                tt = "Đang bán";
            } else {
                tt = " Ngừng bán";
            }
            index++;
            model.addRow(new Object[]{
                index, hd.getMa(), hd.getTen(), hd.getKichThuoc(), hd.getSoLuong(), tt
            });
        }
    }

    private void LoadTablekho() {
        DefaultTableModel model = (DefaultTableModel) this.tableKho.getModel();
        model.setRowCount(0);
        int index = 0;
        String tt;
        for (QLThongKe hd : this.serv.selectAllSanPhamBanchay()) {

            if (hd.getTrangThai() == 1) {
                tt = "Ngừng bán";
            } else {
                tt = " Đang bán";
            }
            index++;
            model.addRow(new Object[]{
                index, hd.getMa(), hd.getTen(), hd.getKichThuoc(), hd.getSoLuong(), tt
            });
        }
    }

    private void LoadTablebanhang() {
        DefaultTableModel model = (DefaultTableModel) this.tableBanHang.getModel();
        model.setRowCount(0);
        int index = 0;
        Double tongTien = 0.0;
        for (QLThongkeBanHang tk : this.serv.selectcBanHang()) {
            tongTien += tk.getTongTien();
            index++;
            model.addRow(new Object[]{
                index, tk.getMa(), tk.getTen(), tk.getKichthuoc(), tk.getSoLuong(), tk.getTongTien(), tk.getNgayTao()
            });
            System.out.println(tk.getTongTien());
        }
        this.lblDoanhthu.setText(String.valueOf(tongTien) + " VND");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        tabbedPaneCustom2 = new app.view.swing.TabbedPaneCustom();
        jPanel4 = new javax.swing.JPanel();
        scrollPaneWin116 = new custome_ui.swing.ScrollPaneWin11();
        tableKho = new rojeru_san.complementos.RSTableMetro();
        buttonGradient3 = new app.view.swing.ButtonGradient();
        cbbtrangthaikho = new app.view.swing.ComboBoxSuggestion();
        jLabel6 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        scrollPaneWin114 = new custome_ui.swing.ScrollPaneWin11();
        tableBanHang = new rojeru_san.complementos.RSTableMetro();
        jLabel7 = new javax.swing.JLabel();
        lblDoanhthulb1 = new javax.swing.JLabel();
        lblDoanhthu = new javax.swing.JLabel();
        btnLoc1 = new app.view.swing.ButtonGradient();
        btnLoadlai = new app.view.swing.ButtonGradient();
        btnLoc2 = new app.view.swing.ButtonGradient();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jdNgayKt = new com.toedter.calendar.JDateChooser();
        jdNgaybd = new com.toedter.calendar.JDateChooser();
        txtTenSanPham = new textfield.TextField2();
        btnLoadlai1 = new app.view.swing.ButtonGradient();
        jPanel2 = new javax.swing.JPanel();
        JpanelViewThongke = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(38, 28, 73));

        tabbedPaneCustom2.setBackground(new java.awt.Color(38, 28, 73));
        tabbedPaneCustom2.setForeground(new java.awt.Color(255, 255, 255));
        tabbedPaneCustom2.setSelectedColor(new java.awt.Color(51, 0, 102));
        tabbedPaneCustom2.setUnselectedColor(new java.awt.Color(51, 0, 102));

        jPanel4.setBackground(new java.awt.Color(38, 28, 73));

        tableKho.setAutoCreateRowSorter(true);
        tableKho.setModel(new javax.swing.table.DefaultTableModel(
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
                "STT", "Mã Sản Phẩm", "Tên Sản Phẩm", "Size", "Số Lượng còn lại", "Trạng Thái"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableKho.setColorBackgoundHead(new java.awt.Color(102, 102, 102));
        tableKho.setColorBordeFilas(new java.awt.Color(153, 153, 153));
        tableKho.setColorBordeHead(new java.awt.Color(153, 153, 153));
        tableKho.setColorFilasBackgound1(new java.awt.Color(204, 204, 204));
        tableKho.setColorFilasForeground1(new java.awt.Color(0, 0, 0));
        tableKho.setColorFilasForeground2(new java.awt.Color(0, 0, 0));
        tableKho.setColorSelBackgound(new java.awt.Color(38, 28, 73));
        tableKho.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tableKho.setFuenteFilas(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tableKho.setFuenteFilasSelect(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        tableKho.setFuenteHead(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        tableKho.setRowHeight(30);
        tableKho.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableKhoMouseClicked(evt);
            }
        });
        scrollPaneWin116.setViewportView(tableKho);

        buttonGradient3.setForeground(new java.awt.Color(0, 0, 0));
        buttonGradient3.setText("Load lại");
        buttonGradient3.setColor1(new java.awt.Color(255, 255, 255));
        buttonGradient3.setColor2(new java.awt.Color(102, 102, 102));
        buttonGradient3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        buttonGradient3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonGradient3ActionPerformed(evt);
            }
        });

        cbbtrangthaikho.setBackground(new java.awt.Color(38, 28, 73));
        cbbtrangthaikho.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        cbbtrangthaikho.setForeground(new java.awt.Color(255, 255, 255));
        cbbtrangthaikho.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Bán chạy", "Tồn" }));
        cbbtrangthaikho.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cbbtrangthaikho.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbtrangthaikhoItemStateChanged(evt);
            }
        });
        cbbtrangthaikho.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbtrangthaikhoActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Kiểm tra Kho");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(cbbtrangthaikho, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(buttonGradient3, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(976, Short.MAX_VALUE))
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(scrollPaneWin116, javax.swing.GroupLayout.PREFERRED_SIZE, 1301, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addGap(22, 22, 22)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbbtrangthaikho, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonGradient3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(721, Short.MAX_VALUE))
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addGap(104, 104, 104)
                    .addComponent(scrollPaneWin116, javax.swing.GroupLayout.PREFERRED_SIZE, 611, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(104, Short.MAX_VALUE)))
        );

        tabbedPaneCustom2.addTab("Thông Kê Kho", jPanel4);

        jPanel5.setBackground(new java.awt.Color(38, 28, 73));

        tableBanHang.setAutoCreateRowSorter(true);
        tableBanHang.setModel(new javax.swing.table.DefaultTableModel(
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
                "STT", "Mã Sản Phẩm", "Tên Sản Phẩm", "Size", "Số Lượng", "Tổng Tiền", "Ngày bán"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, true, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableBanHang.setColorBackgoundHead(new java.awt.Color(102, 102, 102));
        tableBanHang.setColorBordeFilas(new java.awt.Color(153, 153, 153));
        tableBanHang.setColorBordeHead(new java.awt.Color(153, 153, 153));
        tableBanHang.setColorFilasBackgound1(new java.awt.Color(204, 204, 204));
        tableBanHang.setColorFilasForeground1(new java.awt.Color(0, 0, 0));
        tableBanHang.setColorFilasForeground2(new java.awt.Color(0, 0, 0));
        tableBanHang.setColorSelBackgound(new java.awt.Color(38, 28, 73));
        tableBanHang.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tableBanHang.setFuenteFilas(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tableBanHang.setFuenteFilasSelect(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        tableBanHang.setFuenteHead(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        tableBanHang.setRowHeight(30);
        tableBanHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableBanHangMouseClicked(evt);
            }
        });
        scrollPaneWin114.setViewportView(tableBanHang);

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Nhập Tên");

        lblDoanhthulb1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblDoanhthulb1.setForeground(new java.awt.Color(255, 255, 255));
        lblDoanhthulb1.setText("Doanh Thu: ");

        lblDoanhthu.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblDoanhthu.setForeground(new java.awt.Color(255, 255, 255));
        lblDoanhthu.setText("......");

        btnLoc1.setForeground(new java.awt.Color(0, 0, 0));
        btnLoc1.setText("Tìm Kiếm");
        btnLoc1.setColor1(new java.awt.Color(255, 255, 255));
        btnLoc1.setColor2(new java.awt.Color(102, 102, 102));
        btnLoc1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnLoc1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoc1ActionPerformed(evt);
            }
        });

        btnLoadlai.setForeground(new java.awt.Color(0, 0, 0));
        btnLoadlai.setText("Load lại");
        btnLoadlai.setColor1(new java.awt.Color(255, 255, 255));
        btnLoadlai.setColor2(new java.awt.Color(102, 102, 102));
        btnLoadlai.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnLoadlai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoadlaiActionPerformed(evt);
            }
        });

        btnLoc2.setForeground(new java.awt.Color(0, 0, 0));
        btnLoc2.setText("Lọc");
        btnLoc2.setColor1(new java.awt.Color(255, 255, 255));
        btnLoc2.setColor2(new java.awt.Color(102, 102, 102));
        btnLoc2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnLoc2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoc2ActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Ngày bắt đầu");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Ngày Kết Thúc");

        txtTenSanPham.setForeground(new java.awt.Color(0, 0, 0));
        txtTenSanPham.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        btnLoadlai1.setForeground(new java.awt.Color(0, 0, 0));
        btnLoadlai1.setText("Biều Đồ");
        btnLoadlai1.setColor1(new java.awt.Color(255, 255, 255));
        btnLoadlai1.setColor2(new java.awt.Color(102, 102, 102));
        btnLoadlai1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnLoadlai1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoadlai1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblDoanhthulb1)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTenSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDoanhthu, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnLoc1, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLoadlai, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(99, 99, 99)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnLoadlai1, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLoc2, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 83, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(jLabel8))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jdNgaybd, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jdNgayKt, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(215, Short.MAX_VALUE))
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel5Layout.createSequentialGroup()
                    .addGap(5, 5, 5)
                    .addComponent(scrollPaneWin114, javax.swing.GroupLayout.PREFERRED_SIZE, 1315, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(12, Short.MAX_VALUE)))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jdNgaybd, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jdNgayKt, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(btnLoc1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnLoc2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTenSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblDoanhthulb1)
                            .addComponent(lblDoanhthu)
                            .addComponent(btnLoadlai, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnLoadlai1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(851, Short.MAX_VALUE))
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel5Layout.createSequentialGroup()
                    .addGap(161, 161, 161)
                    .addComponent(scrollPaneWin114, javax.swing.GroupLayout.PREFERRED_SIZE, 590, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(224, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1332, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 981, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 6, Short.MAX_VALUE)))
        );

        tabbedPaneCustom2.addTab("thống Kê Bán Hàng", jPanel3);

        jPanel2.setBackground(new java.awt.Color(38, 28, 73));
        jPanel2.setForeground(new java.awt.Color(38, 28, 73));

        javax.swing.GroupLayout JpanelViewThongkeLayout = new javax.swing.GroupLayout(JpanelViewThongke);
        JpanelViewThongke.setLayout(JpanelViewThongkeLayout);
        JpanelViewThongkeLayout.setHorizontalGroup(
            JpanelViewThongkeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1307, Short.MAX_VALUE)
        );
        JpanelViewThongkeLayout.setVerticalGroup(
            JpanelViewThongkeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 682, Short.MAX_VALUE)
        );

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("biểu đồ bán Hàng");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(JpanelViewThongke, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel10)
                .addGap(18, 18, 18)
                .addComponent(JpanelViewThongke, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(84, Short.MAX_VALUE))
        );

        tabbedPaneCustom2.addTab("Thống kê biểu đồ", jPanel2);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tabbedPaneCustom2, javax.swing.GroupLayout.PREFERRED_SIZE, 1318, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(26, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tabbedPaneCustom2, javax.swing.GroupLayout.PREFERRED_SIZE, 856, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(128, Short.MAX_VALUE))
        );

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1350, 990));
    }// </editor-fold>//GEN-END:initComponents

    private void tableKhoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableKhoMouseClicked
        int row = this.tableKho.getSelectedRow();
        String ma = this.tableKho.getValueAt(row, 1).toString();
        String s = this.tableKho.getValueAt(row, 3).toString();
        DefaultTableModel model = (DefaultTableModel) this.tableBanHang.getModel();
        Double tongTien = 0.0;
        model.setRowCount(0);
        int index = 0;
        for (QLThongkeBanHang tk : this.serv.selectcBanHangByTimkiemMa(ma, s)) {
            index++;
            tongTien += tk.getTongTien();
            Double tongtien = 0.0;
            index++;
            model.addRow(new Object[]{
                index, tk.getMa(), tk.getTen(), tk.getKichthuoc(), tk.getSoLuong(), tk.getTongTien(), tk.getNgayTao()
            });
        }
        this.lblDoanhthu.setText(String.valueOf(tongTien) + " VND");
        this.tabbedPaneCustom2.setSelectedIndex(1);
    }//GEN-LAST:event_tableKhoMouseClicked

    private void buttonGradient3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonGradient3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonGradient3ActionPerformed

    private void cbbtrangthaikhoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbtrangthaikhoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbtrangthaikhoActionPerformed

    private void tableBanHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableBanHangMouseClicked

        // TODO add your handling code here:
    }//GEN-LAST:event_tableBanHangMouseClicked

    private void btnLoc1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoc1ActionPerformed
        DefaultTableModel model = (DefaultTableModel) this.tableBanHang.getModel();
        model.setRowCount(0);
        int index = 0;
        Double tongtien = 0.0;
        for (QLThongkeBanHang tk : this.serv.selectcBanHangByTimkiem(this.txtTenSanPham.getText())) {
            index++;
            tongtien += tk.getTongTien();
            model.addRow(new Object[]{
                index, tk.getMa(), tk.getTen(), tk.getKichthuoc(), tk.getSoLuong(), tk.getTongTien()

            });

        }
        this.lblDoanhthu.setText(String.valueOf(tongtien) + " VND");
        ArrayList<QLThongkeBanHang> dataList = this.serv.selectcBanHangByTimkiem(this.txtTenSanPham.getText());
        serv.setDatetoChart(JpanelViewThongke, dataList);
        // TODO add your handling code here:
    }//GEN-LAST:event_btnLoc1ActionPerformed

    private void btnLoadlaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoadlaiActionPerformed
        LoadTablebanhang();
        LoadTablekho();
        ArrayList<QLThongkeBanHang> dataList = serv.selectcBanHang();
        serv.setDatetoChart(JpanelViewThongke, dataList);
        // TODO add your handling code here:
    }//GEN-LAST:event_btnLoadlaiActionPerformed
// thống kê biểu đồ

    private void btnLoc2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoc2ActionPerformed
        int index = 0;
        Date ngaybd = this.jdNgaybd.getDate();
        Date ngaykt = this.jdNgayKt.getDate();
        DefaultTableModel model = (DefaultTableModel) this.tableBanHang.getModel();
        model.setRowCount(0);
        ArrayList<QLThongkeBanHang> list = this.serv.SeleccLocBanHang(ngaybd, ngaykt);
        double tongTien = 0.0;
        for (QLThongkeBanHang tk : list) {
            index++;
            tongTien = tongTien + tk.getTongTien();
            model.addRow(new Object[]{
                index, tk.getMa(), tk.getTen(), tk.getKichthuoc(), tk.getSoLuong(), tk.getTongTien()
            });
        }
        this.lblDoanhthu.setText(String.valueOf(tongTien) + " VND");
        ArrayList<QLThongkeBanHang> dataList = this.serv.SeleccLocBanHang(ngaybd, ngaykt);
        serv.setDatetoChart(JpanelViewThongke, dataList);
    }//GEN-LAST:event_btnLoc2ActionPerformed

    private void cbbtrangthaikhoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbtrangthaikhoItemStateChanged
        if (cbbtrangthaikho.getSelectedIndex() == 0) {
            LoadTablekho();
        } else {
            LoadTableTon();
        }
    }//GEN-LAST:event_cbbtrangthaikhoItemStateChanged

    private void btnLoadlai1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoadlai1ActionPerformed
        this.tabbedPaneCustom2.setSelectedIndex(2);
    }//GEN-LAST:event_btnLoadlai1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel JpanelViewThongke;
    private app.view.swing.ButtonGradient btnLoadlai;
    private app.view.swing.ButtonGradient btnLoadlai1;
    private app.view.swing.ButtonGradient btnLoc1;
    private app.view.swing.ButtonGradient btnLoc2;
    private app.view.swing.ButtonGradient buttonGradient3;
    private app.view.swing.ComboBoxSuggestion cbbtrangthaikho;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private com.toedter.calendar.JDateChooser jdNgayKt;
    private com.toedter.calendar.JDateChooser jdNgaybd;
    private javax.swing.JLabel lblDoanhthu;
    private javax.swing.JLabel lblDoanhthulb1;
    private custome_ui.swing.ScrollPaneWin11 scrollPaneWin114;
    private custome_ui.swing.ScrollPaneWin11 scrollPaneWin116;
    private app.view.swing.TabbedPaneCustom tabbedPaneCustom2;
    private rojeru_san.complementos.RSTableMetro tableBanHang;
    private rojeru_san.complementos.RSTableMetro tableKho;
    private textfield.TextField2 txtTenSanPham;
    // End of variables declaration//GEN-END:variables
}
