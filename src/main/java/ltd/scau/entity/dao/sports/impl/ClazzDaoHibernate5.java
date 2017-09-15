package ltd.scau.entity.dao.sports.impl;

import ltd.scau.base.dao.impl.BaseDaoHibernate5;
import ltd.scau.entity.dao.sports.ClazzDao;
import ltd.scau.entity.sports.Clazz;
import ltd.scau.entity.sports.Clazz_;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class ClazzDaoHibernate5 extends BaseDaoHibernate5<Clazz> implements ClazzDao {

    @Override
    public Clazz findByNum(String num) {
        EntityManager entityManager = getEntityManagerFactory().createEntityManager();
        try {
            CriteriaBuilder builder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Clazz> criteria = builder.createQuery(Clazz.class);
            Root<Clazz> root = criteria.from(Clazz.class);
            criteria.select(root);
            criteria.where(builder.equal(root.get(Clazz_.num), num));
            List<Clazz> c = entityManager.createQuery(criteria).getResultList();
            if (c != null && c.size() == 1) {
                return c.get(0);
            }
        } finally {
            entityManager.close();
        }
        return null;
    }
}
