/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.nhanvienservicies;

import DomainModels.QLCtNhanVien;
import DomainModels.QLNhanVien;
import java.lang.reflect.Array;
import java.util.ArrayList;
import model.ChucVu;
import model.CtNhanVien;
import model.NhanVien;
import repository.nhanvienrepository.NhanVienReponsitory;

/**
 *
 * @author ledun
 */
public class NhanVienService implements INhanvienService {
    
    NhanVienReponsitory repo = new NhanVienReponsitory();
    ArrayList<QLNhanVien> listNhanvien = new ArrayList<>();
    ArrayList<QLCtNhanVien> listNhanvien1 = new ArrayList<>();
    ArrayList<ChucVu> listChuVu = new ArrayList<>();
    @Override
    public ArrayList<QLNhanVien> getList() {
        
        var list = repo.getList();
        listNhanvien.clear();
        for (NhanVien nv : list) {
            listNhanvien.add(new QLNhanVien(nv.getId(),
                    nv.getMaNv(),
                    nv.getTenNv(),
                    nv.getSdt(),
                    nv.getChucVu(),
                    nv.getNgayTao(),
                    nv.getGioiTinh(),
                    nv.getNgaySua(),
                    nv.getTrangThai()));
        }
        return listNhanvien;
    }
    
    @Override
    public void addNhanvien(QLNhanVien nv) {
        repo.addNhanvien(nv);
        
    }
    public ArrayList<ChucVu> getChuCvu(){
     var list = repo.getChuvu();
        for (ChucVu cv : list) {
            listChuVu.add(new ChucVu(cv.getId(),cv.getMa(),cv.getTen()));
        }
        return listChuVu;
    }
    @Override
    public void removeNhanvien(String nv) {
        repo.removeNhanvien(nv);
    }
    
    @Override
    public ArrayList<QLCtNhanVien> getListct() {
        var list = repo.getList2();
        listNhanvien1.clear();
        for (CtNhanVien nv : list) {
            listNhanvien1.add(new QLCtNhanVien(nv.getId(), nv.getMa(), nv.getEmail(), nv.getSdt(), nv.getNgaySinh(), nv.getDiaChi(),nv.getPasss()));
        }
        return listNhanvien1;
    }
    
    @Override
    public void addNhanvien1(QLCtNhanVien nv) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    @Override
    public void setNhanvien1(QLCtNhanVien nv) {
   repo.setNhanvien1(nv);
    }
    
    @Override
    public void removeNhanvien1(String nv) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    @Override
    
    public void setNhanvien(QLNhanVien nv) {
        
        repo.setNhanvien(nv);
    }
    
}
