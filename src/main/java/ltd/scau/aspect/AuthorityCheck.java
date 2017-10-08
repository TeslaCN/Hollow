package ltd.scau.aspect;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import ltd.scau.entity.User;
import ltd.scau.entity.type.UserLevel;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

/**
 * 此类用于检查权限，ltd.scau.aspect.annotations 中的 Annotation 标记了操作所需要的权限
 */
@Aspect
public class AuthorityCheck {

    /**
     * 切入点为所有拥有 @Oridinary annotation 的方法，用于检查用户是否有权限执行指定的 Action
     * @param jp
     * @return 返回 Action 的 Result
     * @throws Throwable
     */
    @Around(value = "execution(@ltd.scau.aspect.annotations.Ordinary * *())")
    public Object ordinaryRequired(ProceedingJoinPoint jp) throws Throwable {
        ActionContext ctx = ActionContext.getContext();
        User u = (User) ctx.getSession().get("user");
        Log log = LogFactory.getLog(AuthorityCheck.class);
        log.info("ordinary: " + u);
        if (u == null) {
            return Action.LOGIN;
        } else if (u.getLevel() == null || u.getLevel().equals(UserLevel.NOTVALIDATE)) {
            return "validate";
        }
        return jp.proceed();
    }

    /**
     * 切入点为所有拥有 @Student annotation 的方法，用于检查用户是否有权限执行指定的 Action
     * @param jp
     * @return 返回 Action 的 Result
     * @throws Throwable
     */
    @Around(value = "execution(@ltd.scau.aspect.annotations.Student * *())")
    public Object studentRequired(ProceedingJoinPoint jp) throws Throwable {
        ActionContext ctx = ActionContext.getContext();
        User u = (User) ctx.getSession().get("user");
        Log log = LogFactory.getLog(AuthorityCheck.class);
        log.info("student: " + u);
        if (u == null) return Action.LOGIN;
        else if (u.getLevel() == null || !u.getLevel().equals(UserLevel.STUDENT)) {
            return "student_auth";
        }
        return jp.proceed();
    }

    /**
     *
     * 切入点为所有拥有 @Administrator annotation 的方法，用于检查用户是否有权限执行指定的 Action
     * @param jp
     * @return
     * @throws Throwable
     */
    @Around(value = "execution(@ltd.scau.aspect.annotations.Administrator * *())")
    public Object administratorRequired(ProceedingJoinPoint jp) throws Throwable {
        ActionContext ctx = ActionContext.getContext();
        User user = (User) ctx.getSession().get("user");
        Log log = LogFactory.getLog(AuthorityCheck.class);
        log.info("administrator: " + user);
        if (user == null) return Action.LOGIN;
        else if (user.getLevel() == null || !user.getLevel().equals(UserLevel.ADMINISTRATOR)) {
            return Action.NONE;
        }
        return jp.proceed();
    }
}
