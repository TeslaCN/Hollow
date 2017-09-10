package ltd.scau.entity.dao;

import ltd.scau.base.dao.impl.base.dao.BaseDao;
import ltd.scau.entity.Grade;

import java.util.Collection;
import java.util.List;

public interface GradeDao extends BaseDao<Grade> {

    List<Grade> findByStudentId(String stuId);
}
