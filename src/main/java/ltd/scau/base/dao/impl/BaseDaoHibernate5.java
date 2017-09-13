package ltd.scau.base.dao.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import ltd.scau.base.dao.impl.base.dao.BaseDao;
import ltd.scau.entity.Grade;
import org.hibernate.query.Query;
import org.springframework.orm.hibernate5.HibernateTemplate;

public class BaseDaoHibernate5<T> implements BaseDao<T> {

    private HibernateTemplate hibernateTemplate;

    @Override
    public T get(Class<T> entityClass, Serializable id) {
        return getHibernateTemplate().get(entityClass, id);
    }

    @Override
    public Serializable save(T entity) {
        return getHibernateTemplate().save(entity);
    }

    @Override
    public void update(T entity) {
        getHibernateTemplate().saveOrUpdate(entity);
    }

    @Override
    public void delete(T entity) {
        getHibernateTemplate().delete(entity);
    }

    @Override
    public void delete(Class<T> entityClass, Serializable id) {
        getHibernateTemplate().delete(get(entityClass, id));
    }

    @Override
    public List<T> findAll(Class<T> entityClass) {
        return getHibernateTemplate().loadAll(entityClass);
    }

    @Override
    public List<T> findAllByPage(Class<T> entityClass, int pageNo, int pageSize) {
        return getHibernateTemplate().loadAll(entityClass).subList((pageNo - 1) * pageSize, pageNo * pageSize);
    }

    @Override
    public long count(Class<T> entityClass) {
        return getHibernateTemplate().loadAll(entityClass).size();
    }

    @Override
    public List<T> find(String hql) {
        return (List<T>) getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql).list();
    }

    @Override
    public List<T> find(String hql, Object... params) {
        Query<T> query = getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);
        for (int i = 0; i < params.length; i++) {
            query.setParameter(i, params[i]);
        }
        return query.list();
    }

    public List<T> findByPage(String hql, int pageNo, int pageSize) {
        return (List<T>) getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql).setFirstResult((pageNo - 1) * pageSize).setMaxResults(pageSize).list();
    }

    public List<T> findByPage(String hql, int pageNo, int pageSize, Object... params) {
        Query<T> query = getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);
        for (int i = 0; i < params.length; i++) {
            query.setParameter(i, params[i]);
        }
        return query.setFirstResult((pageNo - 1) * pageSize).setMaxResults(pageSize).list();
    }

    @Override
    public void saveOrUpdateAll(Collection<?> collections) {
        for (Object g : collections) {
            getHibernateTemplate().saveOrUpdate(g);
        }
    }

    public HibernateTemplate getHibernateTemplate() {
        return hibernateTemplate;
    }

    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }

    @Override
    public T merge(T entity) {
        return getHibernateTemplate().merge(entity);
    }
}
