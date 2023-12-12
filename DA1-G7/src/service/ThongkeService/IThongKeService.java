/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service.ThongkeService;

import DomainModels.QLHoaDon;
import DomainModels.QLThongKe;
import DomainModels.QLThongkeBanHang;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author ledun
 */
public interface IThongKeService {

    public ArrayList<QLThongKe> selectAllSanPhamBanchay();

    public ArrayList<QLThongKe> selectAllSanPhamTon();

    public ArrayList<QLThongkeBanHang> selectcBanHang();
     public ArrayList<QLThongkeBanHang> selectcBanHangByTimkiem(String Ma);

    public ArrayList<QLThongkeBanHang> SeleccLocBanHang(Date bd, Date kt);
}
