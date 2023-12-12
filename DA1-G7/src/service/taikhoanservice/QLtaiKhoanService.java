/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service.taikhoanservice;

import model.TaiKhoan;
import java.util.ArrayList;
import model.TaiKhoan;

/**
 *
 * @author ledun
 */
public interface QLtaiKhoanService {

    public TaiKhoan selectByDangNhap(String Id);

    public void DoiPass(TaiKhoan tk);
    

   public void resestPW(String user,String matkhau);
}
