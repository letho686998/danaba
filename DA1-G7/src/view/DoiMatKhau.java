/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import model.TaiKhoan;

import service.taikhoanservice.QLtaiKhoanService;
import javax.swing.JOptionPane;
import service.taikhoanservice.impl.QLtaiKhoan;
import util.Uhelper;

/**
 *
 * @author ledun
 */
public class DoiMatKhau extends javax.swing.JFrame {

    private QLtaiKhoanService serv = new QLtaiKhoan();
    private String email;
    private String pass;
    public DoiMatKhau(String email, String newpas) {
        initComponents();
        this.email = email;
        this.pass = newpas;
        this.txtUserName.setText(email);
        this.txtPassWordHientai.setText(newpas);
    }

    private DoiMatKhau() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        txtUserName1 = new javax.swing.JTextField();
        txtUserName = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtPassWordMoi = new javax.swing.JPasswordField();
        txtPassWordHientai = new javax.swing.JPasswordField();
        lblhienan = new javax.swing.JLabel();
        lblhienan1 = new javax.swing.JLabel();
        txtPassWordMoi2 = new javax.swing.JPasswordField();
        lblhienan2 = new javax.swing.JLabel();
        btnDoiMatKhau = new app.view.swing.ButtonGradient();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(38, 28, 73));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(38, 32, 78));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
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
        jPanel2.add(txtUserName, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 90, 130, 30));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Nhập Lại Mật Khẩu Mới");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 240, -1, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Mật Khẩu Mới");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 190, -1, 20));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Mật Khẩu");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 150, -1, -1));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Đổi Mật Khẩu");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 10, 250, 50));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Tài Khoản");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 100, -1, -1));

        txtPassWordMoi.setToolTipText("");
        txtPassWordMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPassWordMoiActionPerformed(evt);
            }
        });
        jPanel2.add(txtPassWordMoi, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 190, 130, 30));

        txtPassWordHientai.setToolTipText("");
        jPanel2.add(txtPassWordHientai, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 140, 130, 30));

        lblhienan.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        lblhienan.setForeground(new java.awt.Color(255, 255, 255));
        lblhienan.setText("hiện");
        lblhienan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblhienanMouseClicked(evt);
            }
        });
        jPanel2.add(lblhienan, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 150, -1, -1));

        lblhienan1.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        lblhienan1.setForeground(new java.awt.Color(255, 255, 255));
        lblhienan1.setText("hiện");
        lblhienan1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblhienan1MouseClicked(evt);
            }
        });
        jPanel2.add(lblhienan1, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 200, -1, -1));

        txtPassWordMoi2.setToolTipText("");
        txtPassWordMoi2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPassWordMoi2ActionPerformed(evt);
            }
        });
        jPanel2.add(txtPassWordMoi2, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 240, 130, 30));

        lblhienan2.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        lblhienan2.setForeground(new java.awt.Color(255, 255, 255));
        lblhienan2.setText("hiện");
        lblhienan2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblhienan2MouseClicked(evt);
            }
        });
        jPanel2.add(lblhienan2, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 250, -1, -1));

        btnDoiMatKhau.setForeground(new java.awt.Color(0, 0, 0));
        btnDoiMatKhau.setText("Đổi Mật Khẩu");
        btnDoiMatKhau.setColor1(new java.awt.Color(255, 255, 255));
        btnDoiMatKhau.setColor2(new java.awt.Color(102, 102, 102));
        btnDoiMatKhau.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnDoiMatKhau.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDoiMatKhauActionPerformed(evt);
            }
        });
        jPanel2.add(btnDoiMatKhau, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 340, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 455, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 455, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtUserName1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUserName1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUserName1ActionPerformed
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
    private void txtPassWordMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPassWordMoiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPassWordMoiActionPerformed

    private void lblhienanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblhienanMouseClicked
        if (lblhienan.getText().equals("hiện")) {
            txtPassWordHientai.setEchoChar((char) 0);
            lblhienan.setText("Ẩn");
        } else {
            txtPassWordHientai.setEchoChar('*');
            lblhienan.setText("Hiện");
        }

        // TODO add your handling code here:
    }//GEN-LAST:event_lblhienanMouseClicked

    private void lblhienan1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblhienan1MouseClicked
        if (lblhienan1.getText().equals("hiện")) {
            txtPassWordMoi.setEchoChar((char) 0);
            lblhienan1.setText("Ẩn");
        } else {
            txtPassWordMoi.setEchoChar('*');
            lblhienan1.setText("Hiện");
        }        // TODO add your handling code here:
    }//GEN-LAST:event_lblhienan1MouseClicked

    private void txtPassWordMoi2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPassWordMoi2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPassWordMoi2ActionPerformed

    private void lblhienan2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblhienan2MouseClicked
        if (lblhienan2.getText().equals("hiện")) {
            txtPassWordMoi2.setEchoChar((char) 0);
            lblhienan2.setText("Ẩn");
        } else {
            txtPassWordMoi2.setEchoChar('*');
            lblhienan2.setText("Hiện");
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_lblhienan2MouseClicked

    private void btnDoiMatKhauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDoiMatKhauActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnDoiMatKhauActionPerformed

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
            java.util.logging.Logger.getLogger(DoiMatKhau.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DoiMatKhau.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DoiMatKhau.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DoiMatKhau.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DoiMatKhau().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private app.view.swing.ButtonGradient btnDoiMatKhau;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel lblhienan;
    private javax.swing.JLabel lblhienan1;
    private javax.swing.JLabel lblhienan2;
    private javax.swing.JPasswordField txtPassWordHientai;
    private javax.swing.JPasswordField txtPassWordMoi;
    private javax.swing.JPasswordField txtPassWordMoi2;
    private javax.swing.JTextField txtUserName;
    private javax.swing.JTextField txtUserName1;
    // End of variables declaration//GEN-END:variables
}
