package ltd.scau.struts2;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.conversion.annotations.Conversion;
import com.opensymphony.xwork2.conversion.annotations.TypeConversion;
import ltd.scau.entity.User;
import ltd.scau.entity.dao.UserDao;
import ltd.scau.entity.type.UserLevel;
import org.apache.struts2.convention.annotation.ParentPackage;


//@Conversion(conversions = {@TypeConversion(key = "uuid", converter = "ltd.scau.struts2.converter.UUIDConverter")})
@ParentPackage("hollow-default")
public class ValidateUserAction extends ActionSupport {

    private UserDao userDao;

    private String uuid;

    @Override
    public String execute() throws Exception {
        if (getUuid() == null) return INPUT;

        User user = userDao.findUserByUUID(getUuid());
        if (user != null && user.getLevel().equals(UserLevel.NOTVALIDATE)) {
            user.setLevel(UserLevel.ORDINARY);
            userDao.update(user);
            ActionContext ctx = ActionContext.getContext();
            if (((User) ctx.getSession().get("user")).getId().equals(user.getId())) {
                ctx.getSession().replace("user", user);
            }
            return SUCCESS;
        }
        return "homepage";
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
