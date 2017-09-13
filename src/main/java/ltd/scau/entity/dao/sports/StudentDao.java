package ltd.scau.entity.dao.sports;

import ltd.scau.base.dao.impl.base.dao.BaseDao;
import ltd.scau.entity.sports.Student;

public interface StudentDao extends BaseDao<Student> {

    Student findByStudentId(String stuId);

}
