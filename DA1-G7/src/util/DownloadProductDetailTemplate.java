/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.util;

import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DownloadProductDetailTemplate {

    public static boolean ImportExcel() {
        boolean check = false;
        try {
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("Quản Lý Sản Phẩm");
            int rowNum = 0;
            Row firstRow = sheet.createRow(rowNum++);
            Cell firstCell1 = firstRow.createCell(0);
            Cell firstCell2 = firstRow.createCell(1);
            Cell firstCell3 = firstRow.createCell(2);
            Cell firstCell4 = firstRow.createCell(3);
            Cell firstCell5 = firstRow.createCell(4);
            Cell firstCell6 = firstRow.createCell(5);
            Cell firstCell7 = firstRow.createCell(6);
            Cell firstCell8 = firstRow.createCell(7);
            Cell firstCell9 = firstRow.createCell(8);
            Cell firstCell10 = firstRow.createCell(9);

            firstCell1.setCellValue("STT");
            firstCell2.setCellValue("Sản phẩm");
            firstCell3.setCellValue("Số lượng");
            firstCell4.setCellValue("Giá bán");
            firstCell5.setCellValue("Màu sắc");
            firstCell6.setCellValue("Kích cỡ");
            firstCell7.setCellValue("Chất liệu");
            firstCell8.setCellValue("Cổ áo");
            firstCell9.setCellValue("Chiều dài tay");
            firstCell10.setCellValue("Xuất xứ");

            try {
                String pathFile = "D:" + "\\" + "QuanLySanPhamTemplate.xlsx";
                File file = new File(pathFile);
                FileOutputStream outputStream = new FileOutputStream(pathFile);
                workbook.write(outputStream);
                workbook.close();
                if (!Desktop.isDesktopSupported()) {
                    return check;
                }
                Desktop desktop = Desktop.getDesktop();
                if (file.exists()) {
                    desktop.open(file);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            check = true;
        } catch (Exception e) {
        }
        return check;
    }
}
