package ltd.scau.entity.dao.impl;

import ltd.scau.base.dao.impl.BaseDaoHibernate5;
import ltd.scau.entity.User;
import ltd.scau.entity.dao.UserDao;

import java.util.List;

public class UserDaoHibernate5 extends BaseDaoHibernate5<User> implements UserDao {

    @Override
    public User findUserByAccount(String account) {
        if (account == null) return null;
        User user = new User();
        user.setAccount(account);
        List<User> users = getHibernateTemplate().findByExample(user);
        if (users != null && users.size() == 1) return users.get(0);
        return null;
    }

}
