package ltd.scau.entity.dao;

import ltd.scau.base.dao.impl.base.dao.BaseDao;
import ltd.scau.entity.User;

public interface UserDao extends BaseDao<User>{

    User findUserByAccount(String account);

}
