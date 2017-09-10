package ltd.scau.entity.dao;

import ltd.scau.base.dao.impl.base.dao.BaseDao;
import ltd.scau.entity.Exam;

import java.util.List;

public interface ExamDao extends BaseDao<Exam> {

    List<Exam> findByStudentId(String stuId);
}
