package util;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

/**
 *
 * @author cuongwf
 */
public class TinhTien extends Thread implements Runnable {

    private JTextField tienThanhToan;
    private JTextField tienKhachDua;
    private JTextField tienThua;
    private JTextField tienKhachChuyenKhoan;
    private JComboBox<String> cbbHinhThucThanhToan;

    public TinhTien(JTextField tienThanhToan, JTextField tienKhachDua, JTextField tienThua, JTextField tienKhachChuyenKhoan, JComboBox<String> cbbHinhThucThanhToan) {
        this.tienThanhToan = tienThanhToan;
        this.tienKhachDua = tienKhachDua;
        this.tienThua = tienThua;
        this.tienKhachChuyenKhoan = tienKhachChuyenKhoan;
        this.cbbHinhThucThanhToan = cbbHinhThucThanhToan;
    }

    @Override
    public void run() {
        while (true) {
            try {
                if (!tienThanhToan.getText().isEmpty()) {
                    double tienThanhToanValue = Double.parseDouble(tienThanhToan.getText());

                    SwingUtilities.invokeLater(() -> {
                        String hinhThucThanhToan = (String) cbbHinhThucThanhToan.getSelectedItem();

                        if ("Tiền mặt".equals(hinhThucThanhToan)) {
                            handleTienMat(tienThanhToanValue);
                        } else if ("Chuyển khoản".equals(hinhThucThanhToan)) {
                            handleChuyenKhoan(tienThanhToanValue);
                        } else if ("Kết hợp".equals(hinhThucThanhToan)) {
                            handleKetHop(tienThanhToanValue);
                        }
                    });
                }
                Thread.sleep(100);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    private void handleTienMat(double tienThanhToanValue) {
        try {
            if (!tienKhachDua.getText().isEmpty()) {
                double tienKhachDuaValue = Double.parseDouble(tienKhachDua.getText());
                double tienThuaValue = tienKhachDuaValue - tienThanhToanValue;

                SwingUtilities.invokeLater(() -> {
                    tienThua.setText(String.format("%.2f", tienThuaValue));
                    tienKhachChuyenKhoan.setText("");
                });
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void handleChuyenKhoan(double tienThanhToanValue) {
        SwingUtilities.invokeLater(() -> {
            tienKhachDua.setText("");
            tienKhachChuyenKhoan.setText(String.format("%.2f", tienThanhToanValue));
            tienThua.setText("");
        });
    }

    private void handleKetHop(double tienThanhToanValue) {
        try {
            if (!tienKhachDua.getText().isEmpty()) {
                double tienKhachDuaValue = Double.parseDouble(tienKhachDua.getText());
                double tienKhachChuyenKhoanValue = tienThanhToanValue - tienKhachDuaValue;

                SwingUtilities.invokeLater(() -> {
                    tienKhachChuyenKhoan.setText(String.format("%.2f", tienKhachChuyenKhoanValue));
                    tienThua.setText("");
                });
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
