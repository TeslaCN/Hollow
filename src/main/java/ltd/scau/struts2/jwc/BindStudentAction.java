package ltd.scau.struts2.jwc;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import ltd.scau.aspect.annotations.Ordinary;
import ltd.scau.entity.User;
import ltd.scau.entity.dao.UserDao;
import ltd.scau.entity.type.UserLevel;
import ltd.scau.utils.jwc.StudentLoader;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

@ParentPackage("hollow-default")
public class BindStudentAction extends ActionSupport {

    private String id;

    private String password;

    private UserDao userDao;

    private StudentLoader studentLoader;

    private String error;

    @Override
    @Ordinary
    @Action(results = {@Result(type = "redirect", location = "/jwc/student")})
    public String execute() throws Exception {
        if (this.id == null || this.password == null) return INPUT;
        getStudentLoader().setId(getId());
        getStudentLoader().setPassword(getPassword());
        if (!getStudentLoader().login()) {
            setError(getStudentLoader().getError());
            return ERROR;
        }

        ActionContext ctx = ActionContext.getContext();
        User user = userDao.findUserByAccount(((User) ctx.getSession().get("user")).getAccount());
        user.setStuId(getId());
        user.setStuPassword(getPassword());
        user.setName(getStudentLoader().getName());
        user.setLevel(UserLevel.STUDENT);
        userDao.update(user);
        ctx.getSession().replace("user", user);
        return SUCCESS;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public StudentLoader getStudentLoader() {
        return studentLoader;
    }

    public void setStudentLoader(StudentLoader studentLoader) {
        this.studentLoader = studentLoader;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
