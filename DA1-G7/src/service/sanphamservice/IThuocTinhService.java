package service.sanphamservice;

import java.util.List;

/**
 *
 * @author cuongwf
 */
public interface IThuocTinhService<T> {

    List<T> getAll();
    
    List<T> getAllExport();

    String add(T entity);

    boolean isThuocTinhExists(String tenThuocTinh);

    String update(T entity);
}
