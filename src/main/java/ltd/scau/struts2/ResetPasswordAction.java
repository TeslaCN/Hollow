package ltd.scau.struts2;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.validator.annotations.StringLengthFieldValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;
import ltd.scau.entity.User;
import ltd.scau.entity.dao.UserDao;
import org.apache.commons.mail.HtmlEmail;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import java.util.UUID;

@ParentPackage("hollow-json")
@Results({
        @Result(type = "json", params = {"includeProperties", "message, account"}),
        @Result(name = "error", type = "json", params = {"includeProperties", "message, account"}),
})
public class ResetPasswordAction extends ActionSupport {

    private HtmlEmail htmlEmail;

    private String account;

    private UserDao userDao;

    private String message;

    private String uuid;

    private String newPassword;

    @Override
    @Validations(stringLengthFields = {@StringLengthFieldValidator(key = "newPassword", minLength = "8", maxLength = "255", trim = false)})
    public String execute() throws Exception {
        if (getNewPassword() == null || getNewPassword().equals(""))  {
            return INPUT;
        }
        if (getUuid() == null || getUuid().trim().equals("")) {
            setMessage("参数不正确");
            return ERROR;
        }
        User user = getUserDao().findUserByUUID(getUuid());
        if (user == null) {
            setMessage("参数不正确");
            return ERROR;
        }
        setAccount(user.getAccount());
        user.setPassword(getNewPassword());
        user.setUuid(UUID.randomUUID().toString());
        getUserDao().update(user);
        setMessage(SUCCESS);
        return SUCCESS;
    }

    @Action(value = "send-reset")
    public String sendReset() throws Exception {
        if (getAccount() == null || getAccount().trim().equals("")) {
            setMessage("请输入账号");
            return ERROR;
        }
        User user = getUserDao().findUserByAccount(getAccount());
        if (user == null) {
            setMessage("用户可能不存在");
            return ERROR;
        }
        getHtmlEmail().addTo(getAccount());
        String text = getText("forgetPassword_text", new String[]{user.getNickname()});
        String html = String.format("<p>%s</p><br><br>" +
                "<a style=\"color: red;\" href=\"https://scau.ltd/reset-password?account=%s&uuid=%s\">%s</a>", text, getAccount(), user.getUuid(), getText("clickme"));
        String subject = String.format("scau.ltd %s", getText("resetPassword"));
        getHtmlEmail().setSubject(subject);
        getHtmlEmail().setHtmlMsg(html);
        getHtmlEmail().send();
        setMessage(SUCCESS);
        return SUCCESS;
    }

    public HtmlEmail getHtmlEmail() {
        return htmlEmail;
    }

    public void setHtmlEmail(HtmlEmail htmlEmail) {
        this.htmlEmail = htmlEmail;
    }

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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
