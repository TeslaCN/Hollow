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

import java.util.Date;
import java.util.UUID;

@ParentPackage("hollow")
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
                    @EmailValidator(type = ValidatorType.SIMPLE, fieldName = "user.account", key = "userAccountInvalid")
            },
            stringLengthFields = {
                    @StringLengthFieldValidator(type = ValidatorType.SIMPLE, fieldName = "user.password", trim = false, minLength = "8", maxLength = "255", key = "userPasswordLength"),
                    @StringLengthFieldValidator(type = ValidatorType.SIMPLE, fieldName = "user.nickname", minLength = "1", maxLength = "32", key = "userNicknameLength")
            },
            requiredStrings = {
                    @RequiredStringValidator(type = ValidatorType.SIMPLE, fieldName = "user.account", key = "userAccountRequired"),
                    @RequiredStringValidator(type = ValidatorType.SIMPLE, fieldName = "user.password", key = "userPasswordRequired"),
                    @RequiredStringValidator(type = ValidatorType.SIMPLE, fieldName = "user.nickname", key = "userNicknameRequired")
            }
    )
    @Override
    @Action(results = {
            @Result(type = "json", params = {"includeProperties", "message"}),
            @Result(type = "json", name = "input", params = {"includeProperties", "message"})})
    public String execute() throws Exception {
        ActionContext ctx = ActionContext.getContext();
        if (ctx.getSession().get("user") != null) {
            return "homepage";
        }
        if (user == null || user.getAccount() == null || user.getPassword() == null) {
            return INPUT;
        }
        if (userDao.findUserByAccount(user.getAccount()) != null) {
            setMessage(getText("userExist"));
            return INPUT;
        }
        user.setDate(getDate());
        user.setTime(System.currentTimeMillis());
        user.setLevel(UserLevel.NOTVALIDATE);
        user.setUuid(UUID.randomUUID().toString());
        userDao.save(user);
        setMessage(SUCCESS);
        ctx.getSession().put("user", user);
        return SUCCESS;
    }

    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
