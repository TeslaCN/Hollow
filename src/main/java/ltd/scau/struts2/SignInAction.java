package ltd.scau.struts2;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import ltd.scau.entity.User;
import ltd.scau.entity.dao.UserDao;
import ltd.scau.entity.type.UserLevel;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@ParentPackage("hollow-json")
@Results({
        @Result(type = "json", params = {"includeProperties", "message, account"}),
        @Result(name = "login", type = "json", params = {"includeProperties", "message, account"})
})
public class SignInAction extends ActionSupport {

    private UserDao userDao;

    private String account;

    private String password;

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String execute() throws Exception {
        ActionContext ctx = ActionContext.getContext();
        if (ctx.getSession().get("user") != null) {
            return "homepage";
        }
        if (getAccount() == null || getPassword() == null) {
            return LOGIN;
        }
        User user = userDao.findUserByAccount(getAccount());
        if (user == null) {
            setMessage(getText("user.notExist"));
            return LOGIN;
        } else if (!user.getPassword().equals(getPassword())) {
            setMessage(getText("user.password.error"));
            return LOGIN;
        }
        ctx.getSession().put("user", user);
        setMessage("success");
        return SUCCESS;
    }

}
