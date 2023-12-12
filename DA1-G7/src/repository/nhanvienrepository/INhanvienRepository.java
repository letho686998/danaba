/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package repository.nhanvienrepository;

import DomainModels.QLCtNhanVien;
import DomainModels.QLNhanVien;
import java.util.ArrayList;
import model.CtNhanVien;
import model.HoaDon;
import model.NhanVien;

/**
 *
 * @author ledun
 */
public interface INhanvienRepository {

    public ArrayList<NhanVien> getList();

    public ArrayList<CtNhanVien> getList2();

    public void addNhanvien(QLNhanVien nv);

    public void setNhanvien(QLNhanVien nv);

    public void removeNhanvien(String nv);

    public ArrayList<NhanVien> selectBySql(String sql, Object... args);

    public ArrayList<CtNhanVien> selectBySql1(String sql, Object... args);

    public void addNhanvien1(QLCtNhanVien nv);

    public void setNhanvien1(QLCtNhanVien nv);

    public void removeNhanvien1(String nv);
}
