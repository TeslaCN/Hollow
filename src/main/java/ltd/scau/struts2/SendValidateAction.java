package ltd.scau.struts2;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import ltd.scau.entity.User;
import org.apache.commons.mail.HtmlEmail;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

@ParentPackage("hollow-default")
public class SendValidateAction extends ActionSupport implements ApplicationContextAware {

    private HtmlEmail htmlEmail;

    @Override
    public String execute() throws Exception {
        ActionContext ctx = ActionContext.getContext();
        User user = (User) ctx.getSession().get("user");
        if (user == null) return LOGIN;
        getHtmlEmail().addTo(user.getAccount(), user.getNickname());
        String text = getText("email.validate.description", new String[]{user.getNickname()});
        String html = String.format("<p>%s</p><br><br><a href=\"http://scau.ltd:8080/validate-user?uuid=%s\">%s</a>", text, user.getUuid().toString(), getText("email.validate.clickme"));
        String subject = String.format("scau.ltd %s", getText("user.validate"));
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
