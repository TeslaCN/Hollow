package ltd.scau.entity.dao.impl;

import ltd.scau.base.dao.impl.BaseDaoHibernate5;
import ltd.scau.entity.Exam;
import ltd.scau.entity.dao.ExamDao;

import java.util.List;

public class ExamDaoHibernate5 extends BaseDaoHibernate5<Exam> implements ExamDao {

    @Override
    public List<Exam> findByStudentId(String stuId) {
        return find("select e from Exam e where e.stuId = " + stuId);
    }
}
