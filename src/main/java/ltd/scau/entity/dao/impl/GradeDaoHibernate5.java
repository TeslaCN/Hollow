package ltd.scau.entity.dao.impl;

import ltd.scau.base.dao.impl.BaseDaoHibernate5;
import ltd.scau.entity.Grade;
import ltd.scau.entity.dao.GradeDao;

import java.util.Collection;
import java.util.List;

public class GradeDaoHibernate5 extends BaseDaoHibernate5<Grade> implements GradeDao {

    @Override
    public List<Grade> findByStudentId(String stuId) {
        return find("select g from Grade g where g.stuId = " + stuId);
    }
}
