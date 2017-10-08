package ltd.scau.struts2;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import ltd.scau.entity.User;
import ltd.scau.entity.type.UserLevel;
import org.apache.commons.mail.HtmlEmail;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * 此 Action 发送验证邮件 到 用户的注册邮箱
 */
@ParentPackage("hollow")
public class SendValidateAction extends ActionSupport implements ApplicationContextAware {

    private HtmlEmail htmlEmail;

    @Override
    public String execute() throws Exception {
        ActionContext ctx = ActionContext.getContext();
        User user = (User) ctx.getSession().get("user");
        if (user == null || !user.getLevel().equals(UserLevel.NOTVALIDATE)) return LOGIN;
        getHtmlEmail().addTo(user.getAccount(), user.getNickname());
        String text = getText("validateDescription", new String[]{user.getNickname(), user.getAccount()});
        String html = String.format("<p>%s</p><br><br><a href=\"https://scau.ltd/validate-user?uuid=%s\">%s</a>", text, user.getUuid(), getText("validateClickme"));
        String subject = String.format("scau.ltd %s", getText("userValidate"));
        getHtmlEmail().setSubject(subject);
        getHtmlEmail().setHtmlMsg(html);
        getHtmlEmail().send();
        return SUCCESS;
    }

    public HtmlEmail getHtmlEmail() {
        return htmlEmail;
    }

    public void setHtmlEmail(HtmlEmail htmlEmail) {
        this.htmlEmail = htmlEmail;
    }

    private ApplicationContext ctx;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ctx = applicationContext;
    }
}
