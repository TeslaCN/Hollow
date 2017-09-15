package ltd.scau.entity.dao.sports;

import ltd.scau.base.dao.impl.base.dao.BaseDao;
import ltd.scau.entity.sports.Clazz;

public interface ClazzDao extends BaseDao<Clazz> {

    Clazz findByNum(String num);
}
