package ltd.scau.entity.dao.sports;

import ltd.scau.base.dao.impl.base.dao.BaseDao;
import ltd.scau.entity.sports.Record;

import java.util.List;

public interface RecordDao extends BaseDao<Record> {

    List<Record> findByStudentId(String stuId);
}
