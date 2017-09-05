package ltd.scau.aspect;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import ltd.scau.entity.User;
import ltd.scau.entity.type.UserLevel;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

@Aspect
public class AuthorityCheck {

    @Around(value = "execution(@ltd.scau.aspect.annotations.Ordinary * *())")
    public Object ordinaryRequired(ProceedingJoinPoint jp) throws Throwable {
        ActionContext ctx = ActionContext.getContext();
        User u = (User) ctx.getSession().get("user");
        System.out.println(u);
        if (u == null) {
            return Action.LOGIN;
        } else if (u.getLevel() == null || u.getLevel().equals(UserLevel.NOTVALIDATE)) {
            return "validate";
        }
        return jp.proceed();
    }
}
