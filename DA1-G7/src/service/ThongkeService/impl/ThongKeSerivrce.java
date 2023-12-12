/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.ThongkeService.impl;

import DomainModels.QLThongKe;
import DomainModels.QLThongkeBanHang;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JPanel;
import model.ThongKe;
import model.ThongkeBanHang;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.AxisLabelLocation;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import repository.ThongkeRepository.impl.ThongKeRepository;
import service.ThongkeService.IThongKeService;

/**
 *
 * @author ledun
 */
public class ThongKeSerivrce implements IThongKeService {

    ArrayList<QLThongKe> list = new ArrayList<>();
    ArrayList<QLThongkeBanHang> listBanHang = new ArrayList<>();
    ThongKeRepository repo = new ThongKeRepository();

    @Override
    public ArrayList<QLThongKe> selectAllSanPhamBanchay() {
        var lst = repo.selectAllSanPhamBanchay();
        list.clear();
        for (ThongKe tk : lst) {
            list.add(new QLThongKe(tk.getMa(), tk.getTen(), tk.getSoLuong(), tk.getKichThuoc(), tk.getGiaBan(),
                    tk.getTongTien(), tk.getTrangThai()));
        }
        return list;
    }

    @Override
    public ArrayList<QLThongKe> selectAllSanPhamTon() {
        var lst = repo.selectAllSanPhamTon();
        list.clear();
        for (ThongKe tk : lst) {
            list.add(new QLThongKe(tk.getMa(), tk.getTen(), tk.getSoLuong(), tk.getKichThuoc(), tk.getGiaBan(),
                    tk.getTongTien(), tk.getTrangThai()));
        }
        return list;
    }

    @Override
    public ArrayList<QLThongkeBanHang> selectcBanHang() {
        var lst = repo.selectcBanHang();
        listBanHang.clear();
        for (ThongkeBanHang tk : lst) {
            listBanHang.add(new QLThongkeBanHang(tk.getMa(), tk.getTen(), tk.getKichthuoc(),
                    tk.getSoLuong(), tk.getTongTien(), tk.getNgayTao()));
        }
        return listBanHang;
    }

    @Override
    public ArrayList<QLThongkeBanHang> SeleccLocBanHang(Date bd, Date kt) {
        var lst = repo.SeleccLocBanHang(bd, kt);
        listBanHang.clear();
        for (ThongkeBanHang tk : lst) {
            listBanHang.add(new QLThongkeBanHang(tk.getMa(), tk.getTen(), tk.getKichthuoc(),
                    tk.getSoLuong(), tk.getTongTien(), tk.getNgayTao()));
        }
        return listBanHang;
    }

    @Override
    public ArrayList<QLThongkeBanHang> selectcBanHangByTimkiem(String Ma) {
        var lst = repo.selectcBanHangbyten(Ma);
        listBanHang.clear();
        for (ThongkeBanHang tk : lst) {
            listBanHang.add(new QLThongkeBanHang(tk.getMa(), tk.getTen(), tk.getKichthuoc(),
                    tk.getSoLuong(), tk.getTongTien(), tk.getNgayTao()));
        }
        return listBanHang;
    }

    public ArrayList<QLThongkeBanHang> selectcBanHangByTimkiemMa(String Ma, String size) {
        var lst = repo.selectcBanHangbyma(Ma, size);
        listBanHang.clear();
        for (ThongkeBanHang tk : lst) {
            listBanHang.add(new QLThongkeBanHang(tk.getMa(), tk.getTen(), tk.getKichthuoc(),
                    tk.getSoLuong(), tk.getTongTien(), tk.getNgayTao()));
        }
        return listBanHang;
    }

//    public void setDatetoChart(JPanel jpnItem,ArrayList<QLThongkeBanHang> list) {     
//        if (list != null) {
//            DefaultCategoryDataset dataset = new DefaultCategoryDataset();
//            for (QLThongkeBanHang tk : list) {
//               dataset.addValue(tk.getSoLuong(), "ÁO thun bán ra", tk.getTen());            
//            }
//            JFreeChart chart = ChartFactory.createBarChart("Thống Kê số lượng áo thun bán",
//                    "Loại áo", "Số Lượng", dataset);
//            ChartPanel chartPanel  = new ChartPanel(chart);
//            chartPanel.setPreferredSize(new Dimension(jpnItem.getWidth(),450));
//            jpnItem.removeAll();
//            jpnItem.setLayout(new CardLayout());
//            jpnItem.add(chartPanel);
//            jpnItem.validate();
//            jpnItem.repaint();
//        }
//
//    }
    public void setDatetoChart(JPanel jpnItem, ArrayList<QLThongkeBanHang> list) {
        if (list != null) {
            DefaultCategoryDataset dataset = createDataset(list);
            JFreeChart chart = createChart(dataset);

            ChartPanel chartPanel = new ChartPanel(chart);
            chartPanel.setPreferredSize(new Dimension(jpnItem.getWidth(), 682));

            jpnItem.removeAll();
            jpnItem.setLayout(new CardLayout());
            jpnItem.add(chartPanel);
            jpnItem.validate();
            jpnItem.repaint();
        }
    }

    private DefaultCategoryDataset createDataset(ArrayList<QLThongkeBanHang> list) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (QLThongkeBanHang tk : list) {
            dataset.addValue(tk.getSoLuong(), tk.getKichthuoc(), tk.getTen());
        }
        return dataset;
    }

    private JFreeChart createChart(CategoryDataset dataset) {
        JFreeChart chart = ChartFactory.createBarChart(
                "Thống Kê số lượng áo thun bán",
                "Loại áo",
                "Số Lượng",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

        CategoryPlot plot = (CategoryPlot) chart.getPlot();
        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
//        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        rangeAxis.setVerticalTickLabels(true);
        CategoryAxis domainAxis = plot.getDomainAxis();
        domainAxis.setCategoryLabelPositions(CategoryLabelPositions.STANDARD);

        // Add item labels to display quantity on top of each bar
        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
        renderer.setBaseItemLabelsVisible(true);

        return chart;
    }


}
