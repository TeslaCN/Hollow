package ltd.scau.entity.dao.sports.impl;

import ltd.scau.base.dao.impl.BaseDaoHibernate5;
import ltd.scau.entity.dao.sports.StudentDao;
import ltd.scau.entity.sports.Student;

import java.util.List;

public class StudentDaoHibernate5 extends BaseDaoHibernate5<Student> implements StudentDao {

    @Override
    public Student findByStudentId(String stuId) {
        List<Student> s = find("select stu from Student stu where stu.stuId = " + stuId);
        if (s != null && s.size() == 1) {
            return s.get(0);
        }
        return null;
    }
}
