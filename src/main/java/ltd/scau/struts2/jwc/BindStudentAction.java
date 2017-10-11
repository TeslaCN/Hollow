package ltd.scau.struts2.jwc;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.validator.annotations.Validations;
import ltd.scau.aspect.annotations.Ordinary;
import ltd.scau.entity.User;
import ltd.scau.entity.dao.UserDao;
import ltd.scau.entity.dao.sports.StudentDao;
import ltd.scau.entity.sports.Student;
import ltd.scau.entity.type.UserLevel;
import ltd.scau.utils.jwc.StudentLoader;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

import java.util.Calendar;
import java.util.Date;

/**
 * Action 负责用户绑定教务账号的相关操作
 */
@ParentPackage("hollow")
public class BindStudentAction extends ActionSupport {

    private String id;

    private String password;

    private UserDao userDao;

    private StudentLoader studentLoader;

    private String error;


    /**
     * 通过参数传入学生账号密码，并尝试登陆，如果成功则 学生账号 绑定到对应的 User
     *
     * @return
     * @throws Exception
     */
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

    private StudentDao studentDao;

    private String year;

    private String month;

    private String day;

    private String stuName;

    private String stuId;

    private String message;

    @Action(value = "bind-by-birth", results = {@Result(type = "dispatcher", location = "student/sport.jsp")})
    public String bindByBirth() throws Exception {
        Student student = getStudentDao().findByStudentId(getStuId());
        if (student == null) {
            setMessage("学生信息不存在，可能是学生数据不在体育管理系统中");
            return SUCCESS;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(student.getBirthday());
        if (calendar.get(Calendar.YEAR) == Integer.parseInt(getYear())
                && calendar.get(Calendar.MONTH) + 1 == Integer.parseInt(getMonth())
                && calendar.get(Calendar.DAY_OF_MONTH) == Integer.parseInt(getDay())
                && student.getStuName().trim().equals(getStuName())
                && student.getStuId().trim().equals(getStuId())) {
            ActionContext ctx = ActionContext.getContext();
            ctx.getSession().put("stuId", getStuId());
        }
        else {
            setMessage("学生信息有误");
        }
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

    public StudentDao getStudentDao() {
        return studentDao;
    }

    public void setStudentDao(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public String getStuId() {
        return stuId;
    }

    public void setStuId(String stuId) {
        this.stuId = stuId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
