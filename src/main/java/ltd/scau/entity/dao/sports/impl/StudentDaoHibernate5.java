package ltd.scau.entity.dao.sports.impl;

import ltd.scau.base.dao.impl.BaseDaoHibernate5;
import ltd.scau.entity.dao.sports.StudentDao;
import ltd.scau.entity.sports.Student;
import ltd.scau.entity.sports.Student_;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class StudentDaoHibernate5 extends BaseDaoHibernate5<Student> implements StudentDao {

    @Override
    public Student findByStudentId(String stuId) {
        EntityManager entityManager = getEntityManagerFactory().createEntityManager();
        try {
            CriteriaBuilder builder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Student> criteria = builder.createQuery(Student.class);
            Root<Student> root = criteria.from(Student.class);
            criteria.select(root);
            criteria.where(builder.equal(root.get(Student_.stuId), stuId));
            List<Student> s = entityManager.createQuery(criteria).getResultList();
            if (s != null && s.size() == 1) {
                return s.get(0);
            }
        } finally {
            entityManager.close();
        }
        return null;
    }
}
