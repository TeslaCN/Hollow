package ltd.scau.entity.dao.sports.impl;

import ltd.scau.base.dao.impl.BaseDaoHibernate5;
import ltd.scau.entity.dao.sports.RecordDao;
import ltd.scau.entity.sports.Record;

import java.util.List;

public class RecordDaoHibernate5 extends BaseDaoHibernate5<Record> implements RecordDao {

    @Override
    public List<Record> findByStudentId(String stuId) {
        return find("select r from Record r where r.stuId = " + stuId);
    }
}
