/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.swing.JOptionPane;
import model.TaiKhoan;
import service.taikhoanservice.impl.QLtaiKhoan;
import util.Uhelper;

/**
 *
 * @author cuongwf
 */
public class ViewDangNhap extends javax.swing.JFrame {

    QLtaiKhoan serv = new QLtaiKhoan();

    public ViewDangNhap() {
        initComponents();
        this.setLocationRelativeTo(null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtQuenpass = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtTaiKhoan = new javax.swing.JTextField();
        btnhuy = new app.view.swing.ButtonGradient();
        btnDangnhap = new app.view.swing.ButtonGradient();
        txtMatKhau = new javax.swing.JPasswordField();
        lblhienan = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(38, 28, 73));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(38, 32, 78));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 50, -1, -1));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Mật khẩu");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 210, 80, 30));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Tên tài khoản");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 160, 100, 30));

        txtQuenpass.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        txtQuenpass.setForeground(new java.awt.Color(255, 255, 255));
        txtQuenpass.setText("Quên mật Khẩu");
        txtQuenpass.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtQuenpassMouseClicked(evt);
            }
        });
        jPanel1.add(txtQuenpass, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 240, 110, 40));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Đăng nhập");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 80, 130, 40));

        txtTaiKhoan.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtTaiKhoan.setForeground(new java.awt.Color(128, 128, 128));
        txtTaiKhoan.setText(" ");
        txtTaiKhoan.setBorder(null);
        txtTaiKhoan.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtTaiKhoanFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtTaiKhoanFocusLost(evt);
            }
        });
        txtTaiKhoan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtTaiKhoanMouseClicked(evt);
            }
        });
        txtTaiKhoan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTaiKhoanActionPerformed(evt);
            }
        });
        jPanel1.add(txtTaiKhoan, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 160, 170, 30));

        btnhuy.setForeground(new java.awt.Color(0, 0, 0));
        btnhuy.setText("Hủy");
        btnhuy.setColor1(new java.awt.Color(255, 255, 255));
        btnhuy.setColor2(new java.awt.Color(102, 102, 102));
        btnhuy.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnhuy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnhuyActionPerformed(evt);
            }
        });
        jPanel1.add(btnhuy, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 300, 90, -1));

        btnDangnhap.setForeground(new java.awt.Color(0, 0, 0));
        btnDangnhap.setText("Đăng Nhập");
        btnDangnhap.setColor1(new java.awt.Color(255, 255, 255));
        btnDangnhap.setColor2(new java.awt.Color(102, 102, 102));
        btnDangnhap.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnDangnhap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDangnhapActionPerformed(evt);
            }
        });
        jPanel1.add(btnDangnhap, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 300, -1, -1));

        txtMatKhau.setToolTipText("");
        txtMatKhau.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMatKhauActionPerformed(evt);
            }
        });
        jPanel1.add(txtMatKhau, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 210, 170, 30));

        lblhienan.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        lblhienan.setForeground(new java.awt.Color(255, 255, 255));
        lblhienan.setText("hiện");
        lblhienan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblhienanMouseClicked(evt);
            }
        });
        jPanel1.add(lblhienan, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 220, -1, -1));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 379, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 431, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtTaiKhoanFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtTaiKhoanFocusGained

    }//GEN-LAST:event_txtTaiKhoanFocusGained

    private void txtTaiKhoanFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtTaiKhoanFocusLost

    }//GEN-LAST:event_txtTaiKhoanFocusLost

    private void txtTaiKhoanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtTaiKhoanMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTaiKhoanMouseClicked

    private void txtTaiKhoanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTaiKhoanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTaiKhoanActionPerformed

    private void btnhuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnhuyActionPerformed

    }//GEN-LAST:event_btnhuyActionPerformed

    private void btnDangnhapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDangnhapActionPerformed
        dangNhap(this.txtTaiKhoan.getText(), this.txtMatKhau.getText());
    }//GEN-LAST:event_btnDangnhapActionPerformed

    private void txtMatKhauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMatKhauActionPerformed

    }//GEN-LAST:event_txtMatKhauActionPerformed

    private void lblhienanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblhienanMouseClicked
        if (lblhienan.getText().equals("Hiện")) {
            txtMatKhau.setEchoChar((char) 0);
            lblhienan.setText("Ẩn");
        } else {
            txtMatKhau.setEchoChar('*');
            lblhienan.setText("Hiện");
        }  
    }//GEN-LAST:event_lblhienanMouseClicked

    private void txtQuenpassMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtQuenpassMouseClicked
            QuenMatkhau quen = new QuenMatkhau();
            quen.setVisible(true);
            this.dispose();

    }//GEN-LAST:event_txtQuenpassMouseClicked
   
     public static String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedPassword = md.digest(password.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for (byte b : hashedPassword) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }
     
    private void dangNhap(String username, String pas) {
        String user = username;
        String pass = pas;
        if (user.trim().equals("")) {
            JOptionPane.showMessageDialog(this, "không được để tài Khoản trống");
            return;
        } else if (pass.trim().equals("")) {
            JOptionPane.showMessageDialog(this, "không được để mật khẩu trống");
            return;
        }

        if (user.trim().contains("@")) {
            if (Uhelper.checkEmail(txtTaiKhoan, "Email Không đúng")) {
                return;
            } else {
                TaiKhoan acc = new TaiKhoan();
                acc = this.serv.selectByDangNhap(user.trim());
                String hashedPassword = hashPassword(pass);
                if (acc != null) {
                    if (this.serv.selectByDangNhap(user.trim()).getPassWord().equals(hashedPassword)) {
                        JOptionPane.showMessageDialog(this, "đăng Nhập thành công");
                        String role = acc.getRole();
                        String tenNV = acc.getUserName();
                        String idNhanvien = String.valueOf(acc.getIdNV());
                        MainJFrame framemain = new MainJFrame(tenNV,role, idNhanvien, pass, user);
                        framemain.setVisible(true);
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(this, "Mật Khẩu sai");
                        return;
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "đăng Nhập thất bại");
                    return;
                }
            }

        } else {
            if (Uhelper.checkSDT(txtTaiKhoan, "Số điện thoại Không đúng")) {
                return;
            } else {
                TaiKhoan acc = new TaiKhoan();
                acc = this.serv.selectByDangNhap(user.trim());
                String hashedPassword = hashPassword(pass);
                if (acc != null) {
                    if (acc.getSdt().equals(user.trim()) && acc.getPassWord().equals(hashedPassword)) {
                        JOptionPane.showMessageDialog(this, "đăng Nhập thành công");
                        String role = acc.getRole();
                        String tenNV = acc.getUserName();
                        String idNhanvien = String.valueOf(acc.getIdNV());
                        MainJFrame framemain = new MainJFrame(tenNV,role, idNhanvien,pass,user);
                        framemain.setVisible(true);
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(this, "Mật Khẩu sai");
                        return;
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "đăng Nhập thất bại");
                    return;
                }
            }
        }
    }


    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ViewDangNhap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewDangNhap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewDangNhap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewDangNhap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ViewDangNhap().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private app.view.swing.ButtonGradient btnDangnhap;
    private app.view.swing.ButtonGradient btnhuy;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel lblhienan;
    private javax.swing.JPasswordField txtMatKhau;
    private javax.swing.JLabel txtQuenpass;
    private javax.swing.JTextField txtTaiKhoan;
    // End of variables declaration//GEN-END:variables
}
