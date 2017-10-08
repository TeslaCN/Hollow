package ltd.scau.struts2;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

@ParentPackage("hollow")
public class SignOutAction extends ActionSupport {

    @Override
    public String execute() throws Exception {
        ActionContext ctx = ActionContext.getContext();
        ctx.getSession().remove("user");
        return "homepage";
    }
}
