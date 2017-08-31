package ltd.scau.struts2;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import ltd.scau.entity.User;
import ltd.scau.entity.dao.UserDao;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

public class UserProfileAction extends ActionSupport{

    private UserDao userDao;

    private User user;

    @Override
    @Action(results = {@Result(name = "login", location = "sign-in.jsp")})
    public String execute() throws Exception {
        ActionContext ctx = ActionContext.getContext();
        setUser((User) ctx.getSession().get("user"));
        if (getUser() == null) {
            return LOGIN;
        }
        return SUCCESS;
    }

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
}
