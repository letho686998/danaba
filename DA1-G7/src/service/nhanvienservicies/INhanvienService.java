/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service.nhanvienservicies;

import DomainModels.QLCtNhanVien;
import DomainModels.QLNhanVien;
import java.util.ArrayList;
import model.NhanVien;

/**
 *
 * @author ledun
 */
public interface INhanvienService {

    public ArrayList<QLNhanVien> getList();

    public ArrayList<QLCtNhanVien> getListct();

    public void addNhanvien(QLNhanVien nv);

    public void setNhanvien(QLNhanVien nv);

    public void removeNhanvien(String nv);

    public void addNhanvien1(QLCtNhanVien nv);

    public void setNhanvien1(QLCtNhanVien nv);

    public void removeNhanvien1(String nv);
}
