package ltd.scau.struts2.api;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.commons.mail.HtmlEmail;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

@ParentPackage("json-default")
@Result(type = "json", params = {"includeProperties", ""})
public class EmailAction extends ActionSupport {

    private String toAddress;

    private String toName;

    private String title;

    private String textContent;

    private String htmlContent;

    private HtmlEmail email;

    @Override
    public String execute() throws Exception {
        email.addTo(getToAddress(), getToName());
        email.setHtmlMsg(getHtmlContent());
        email.setTextMsg(getTextContent());
        email.send();
        return SUCCESS;
    }

    public String getToAddress() {
        return toAddress;
    }

    public void setToAddress(String toAddress) {
        this.toAddress = toAddress;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTextContent() {
        return textContent;
    }

    public void setTextContent(String textContent) {
        this.textContent = textContent;
    }

    public HtmlEmail getEmail() {
        return email;
    }

    public void setEmail(HtmlEmail email) {
        this.email = email;
    }

    public String getHtmlContent() {
        return htmlContent;
    }

    public void setHtmlContent(String htmlContent) {
        this.htmlContent = htmlContent;
    }

    public String getToName() {
        return toName;
    }

    public void setToName(String toName) {
        this.toName = toName;
    }
}
