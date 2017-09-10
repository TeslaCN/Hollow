package ltd.scau.base.dao.impl.base.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

public interface BaseDao<T> {

    T get(Class<T> entityClass, Serializable id);

    Serializable save(T entity);

    void update(T entity);

    void delete(T entity);

    void delete(Class<T> entityClass, Serializable id);

    List<T> find(String hql);

    List<T> find(String hql, Object... params);

    List<T> findByPage(String hql, int pageNo, int pageSize);

    List<T> findByPage(String hql, int pageNo, int pageSize, Object... params);

    List<T> findAll(Class<T> entityClass);

    List<T> findAllByPage(Class<T> entityClass, int pageNo, int pageSize);

    long count(Class<T> entityClass);

    void saveOrUpdateAll(Collection<?> c);
}
