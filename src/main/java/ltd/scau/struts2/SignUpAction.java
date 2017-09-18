package ltd.scau.struts2;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.conversion.annotations.Conversion;
import com.opensymphony.xwork2.conversion.annotations.TypeConversion;
import com.opensymphony.xwork2.validator.annotations.*;
import ltd.scau.entity.User;
import ltd.scau.entity.dao.UserDao;
import ltd.scau.entity.type.UserLevel;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.ServletResponseAware;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.UUID;

@ParentPackage("hollow-default")
@Conversion(conversions = {@TypeConversion(key = "user.gender", converter = "ltd.scau.struts2.converter.GenderTypeConverter")})
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

    @Validations(
            emails = {
                    @EmailValidator(type = ValidatorType.SIMPLE, fieldName = "user.account", key = "user.account.invalid")
            },
            stringLengthFields = {
                    @StringLengthFieldValidator(type = ValidatorType.SIMPLE, fieldName = "user.password", trim = false, minLength = "8", maxLength = "255", key = "user.password.length"),
                    @StringLengthFieldValidator(type = ValidatorType.SIMPLE, fieldName = "user.nickname", minLength = "1", maxLength = "32", key = "user.nickname.length")
            },
            requiredStrings = {
                    @RequiredStringValidator(type = ValidatorType.SIMPLE, fieldName = "user.account", key = "user.account.required"),
                    @RequiredStringValidator(type = ValidatorType.SIMPLE, fieldName = "user.password", key = "user.password.required"),
                    @RequiredStringValidator(type = ValidatorType.SIMPLE, fieldName = "user.nickname", key = "user.nickname.required")
            }
    )
    @Override
    @Action(results = {@Result(name = "input", location = "sign-up.jsp")})
    public String execute() throws Exception {
        ActionContext ctx = ActionContext.getContext();
        if (ctx.getSession().get("user") != null) {
            return "homepage";
        }
        if (user == null || user.getAccount() == null || user.getPassword() == null) {
            return INPUT;
        }
        if (userDao.findUserByAccount(user.getAccount()) != null) {
            return INPUT;
        }
        user.setDate(getDate());
        user.setTime(System.currentTimeMillis());
        user.setLevel(UserLevel.NOTVALIDATE);
        user.setUuid(UUID.randomUUID().toString());
        userDao.save(user);
        return LOGIN;
    }
}
