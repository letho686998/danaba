/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.swing.JOptionPane;
import model.TaiKhoan;
import service.taikhoanservice.QLtaiKhoanService;
import service.taikhoanservice.impl.QLtaiKhoan;
import util.EmailSender;
import util.Uhelper;
import static view.ViewDangNhap.hashPassword;

/**
 *
 * @author ledun
 */
public class QuenMatkhau extends javax.swing.JFrame {

    private QLtaiKhoanService serv = new QLtaiKhoan();

    public QuenMatkhau() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        txtUserName1 = new javax.swing.JTextField();
        txtTaiKhoan = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        btnDangKi = new app.view.swing.ButtonGradient();
        jLabel11 = new javax.swing.JLabel();
        btnDangKi1 = new app.view.swing.ButtonGradient();
        jLabel12 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(38, 28, 73));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(38, 32, 78));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel2.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 50, -1, -1));

        jLabel5.setText("Tài khoản");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, -30, 80, -1));

        jButton1.setText("Thêm");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, -50, 100, -1));

        txtUserName1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUserName1ActionPerformed(evt);
            }
        });
        jPanel2.add(txtUserName1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, -30, 130, -1));
        jPanel2.add(txtTaiKhoan, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 90, 130, 30));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Quên Mật Khẩu");
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 10, 250, 50));

        btnDangKi.setForeground(new java.awt.Color(0, 0, 0));
        btnDangKi.setText("Đăng Nhập");
        btnDangKi.setColor1(new java.awt.Color(255, 255, 255));
        btnDangKi.setColor2(new java.awt.Color(102, 102, 102));
        btnDangKi.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnDangKi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDangKiActionPerformed(evt);
            }
        });
        jPanel2.add(btnDangKi, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 160, 120, -1));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Hãy nhập Email để nhận mã  xác thực");
        jPanel2.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 130, -1, -1));

        btnDangKi1.setForeground(new java.awt.Color(0, 0, 0));
        btnDangKi1.setText("Nhận Mã");
        btnDangKi1.setColor1(new java.awt.Color(255, 255, 255));
        btnDangKi1.setColor2(new java.awt.Color(102, 102, 102));
        btnDangKi1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnDangKi1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDangKi1ActionPerformed(evt);
            }
        });
        jPanel2.add(btnDangKi1, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 160, 120, -1));

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Emai");
        jPanel2.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 100, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 441, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtUserName1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUserName1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUserName1ActionPerformed


    private void btnDangKiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDangKiActionPerformed
        new ViewDangNhap().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnDangKiActionPerformed
    private void doipass(String username) {
        String user = username;
        if (user.trim().equals("")) {
            JOptionPane.showMessageDialog(this, "không được để tài Khoản trống");
            return;
        }
        if (user.trim().contains("@")) {
            if (Uhelper.checkEmail(txtTaiKhoan, "Email Không đúng định dạng")) {
                return;
            } else {
                TaiKhoan acc = new TaiKhoan();
                String emaicheck = this.serv.selectByDangNhap(user.trim()).getEmail();
                if (acc != null) {
                    if (this.serv.selectByDangNhap(user.trim()).getEmail().equals(username)) {
                        if (emaicheck != null && emaicheck.equals(user)) {
                            String code = EmailSender.sendEmail(user.trim(), "dunglvph30063@fpt.edu.vn", "qluf pibd mklo asnk");

                            if (code != null) {
                                JOptionPane.showMessageDialog(this, "Gửi email xác nhận thành công.");
                                new NhapMaBaoMat(EmailSender.getSentCode(), user).setVisible(true);
                                this.dispose();
                            } else {
                                JOptionPane.showMessageDialog(this, "Gửi email xác nhận thất bại. Vui lòng thử lại sau.");
                                return;
                            }
                        } else {
                            JOptionPane.showMessageDialog(this, "Nhập sai email bạn đã đăng kí");
                            return;
                        }
                    }
                }
            }
        }
    }


    private void btnDangKi1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDangKi1ActionPerformed
        doipass(this.txtTaiKhoan.getText());
    }//GEN-LAST:event_btnDangKi1ActionPerformed

    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(QuenMatkhau.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QuenMatkhau.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QuenMatkhau.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QuenMatkhau.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new QuenMatkhau().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private app.view.swing.ButtonGradient btnDangKi;
    private app.view.swing.ButtonGradient btnDangKi1;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JTextField txtTaiKhoan;
    private javax.swing.JTextField txtUserName1;
    // End of variables declaration//GEN-END:variables
}
