/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author cuongwf
 */
public class XGenerateQRCode {

    public static void generateQRcode(String data, String path, String charset, Map map, int h, int w) throws WriterException, IOException {
        BitMatrix matrix = new MultiFormatWriter().encode(new String(data.getBytes(charset), charset), BarcodeFormat.QR_CODE, w, h);
        MatrixToImageWriter.writeToFile(matrix, path.substring(path.lastIndexOf('.') + 1), new File(path));
    }

    public static boolean doGenerate(String maSPCT, String tenSanPham) {
        try {
            String path = "src\\qrcode\\" + maSPCT + "-QR" + ".png";
            String charset = "UTF-8";
            Map<EncodeHintType, ErrorCorrectionLevel> hashMap = new HashMap<>();
            hashMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
            generateQRcode(maSPCT, path, charset, hashMap, 200, 200);
            return true;
        } catch (WriterException | IOException e) {
            return false;
        }

    }

    public static boolean doGenerateQR(String maSPCT, String tenSanPham, String selectedDirectory) {
        try {
            String fileName = maSPCT + "-QR.png";
            String path = selectedDirectory + File.separator + fileName;
            String charset = "UTF-8";
            Map<EncodeHintType, ErrorCorrectionLevel> hashMap = new HashMap<>();
            hashMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
            generateQRcode(maSPCT, path, charset, hashMap, 200, 200);
            return true;
        } catch (WriterException | IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
