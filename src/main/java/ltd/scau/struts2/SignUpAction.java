package ltd.scau.struts2;

import com.opensymphony.xwork2.ActionSupport;
import ltd.scau.entity.User;
import ltd.scau.entity.dao.UserDao;

import java.util.Date;

public class SignUpAction extends ActionSupport {

    private UserDao userDao;

    private User user;

    private Date date;

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String execute() throws Exception {
        if (user == null || user.getAccount() == null || user.getPassword() == null) {
            return INPUT;
        }
        if (userDao.findUserByAccount(user.getAccount()) != null) {
            addActionError(getText("user.exist"));
            return INPUT;
        }
        userDao.save(user);
        return SUCCESS;
    }
}
