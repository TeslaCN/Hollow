package ltd.scau.entity.dao.impl;

import ltd.scau.base.dao.impl.BaseDaoHibernate5;
import ltd.scau.entity.Grade;
import ltd.scau.entity.Grade_;
import ltd.scau.entity.dao.GradeDao;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Collection;
import java.util.List;

public class GradeDaoHibernate5 extends BaseDaoHibernate5<Grade> implements GradeDao {

    @Override
    public List<Grade> findByStudentId(String stuId) {
        EntityManager entityManager = getEntityManagerFactory().createEntityManager();
        try {
            CriteriaBuilder builder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Grade> criteria = builder.createQuery(Grade.class);
            Root<Grade> root = criteria.from(Grade.class);
            criteria.select(root);
            criteria.where(builder.equal(root.get(Grade_.stuId), stuId));
            List<Grade> grades = entityManager.createQuery(criteria).getResultList();
            return grades;
        } finally {

            entityManager.close();
        }
    }
}
