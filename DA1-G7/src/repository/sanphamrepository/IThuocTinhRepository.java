package repository.sanphamrepository;

import java.util.List;

/**
 *
 * @author cuongwf
 */
public interface IThuocTinhRepository<T> {

    List<T> getAll();
    
    List<T> getAllExport();

    boolean add(T entity);

    boolean update(T entity);

    boolean isThuocTinhExists(String tenThuocTinh);

    T findByTen(String ten);

}
