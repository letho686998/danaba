/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package repository.ThongkeRepository;

import DomainModels.QLHoaDon;
import DomainModels.QLThongKe;
import java.util.ArrayList;
import java.util.Date;
import model.ThongKe;
import model.ThongkeBanHang;

/**
 *
 * @author ledun
 */
public interface IThongRepository {

    public ArrayList<ThongKe> selectAllSanPhamBanchay();

    public ArrayList<ThongKe> selectAllSanPhamTon();

    public ArrayList<ThongkeBanHang> selectcBanHang();
  public ArrayList<ThongkeBanHang> selectcBanHangbyten(String ma);
    public ArrayList<ThongkeBanHang> SeleccLocBanHang(Date bd, Date kt);
}
