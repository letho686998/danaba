/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import util.DBConnect;
import java.sql.*;

public class Xhelp {

    public static ResultSet excuteQuery(String sql, Object... args) {
        //args : Là tham số tưỳ chọn
        Connection connection = null;
        ResultSet resultSet = null;
        //thực thi câu truy vấn có tham số dùng pre
        PreparedStatement preparedStatement = null;
        try {
            connection = DBConnect.getConnection();
            preparedStatement = connection.prepareStatement(sql);//truy vấn
            //gán tham số trong mảng Object
            for (int i = 0; i < args.length; i++) {
                preparedStatement.setObject(i+1, args[i]);
                
            }
            //thuẹc hiện truy vấn
            resultSet=preparedStatement.executeQuery();
            
        } catch (Exception e) {
        }
        return resultSet;

    }
    
    public static Integer excuteUpdate(String sql, Object... args) {
        //args : Là tham số tưỳ chọn
        //trả về số dòng ảnh hưởng
        Connection connection = null;
        Integer rowAffec = 0;
        //thực thi câu truy vấn có tham số dùng pre
        PreparedStatement preparedStatement = null;
        try {
            connection = DBConnect.getConnection();
            preparedStatement = connection.prepareStatement(sql);//truy vấn
            //gán tham số trong mảng Object
            for (int i = 0; i < args.length; i++) {
                preparedStatement.setObject(i+1, args[i]);
                
            }
            //thuẹc hiện truy vấn
            rowAffec=preparedStatement.executeUpdate();
            
        } catch (Exception e) {
        }
        return rowAffec;

    }
}
