package ltd.scau.entity.dao.sports;

import ltd.scau.base.dao.impl.base.dao.BaseDao;
import ltd.scau.entity.sports.Student;

/**
 * 接口声明了一系列操作 Student Entity 的方法
 */
public interface StudentDao extends BaseDao<Student> {

    /**
     * 根据学号查找对应学生实例
     * @param stuId 学号
     * @return 如果查找成功则返回一个学生实例
     */
    Student findByStudentId(String stuId);

}
