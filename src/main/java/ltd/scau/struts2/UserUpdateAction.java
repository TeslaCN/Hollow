package ltd.scau.struts2;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.conversion.annotations.Conversion;
import com.opensymphony.xwork2.conversion.annotations.TypeConversion;
import ltd.scau.aspect.annotations.Ordinary;
import ltd.scau.entity.User;
import ltd.scau.entity.dao.UserDao;
import ltd.scau.entity.type.GenderType;
import ltd.scau.event.MessageEvent;
import ltd.scau.utils.storage.StorageClient;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;

@ParentPackage("hollow")
@Conversion(conversions = {@TypeConversion(key = "gender", converter = "ltd.scau.struts2.converter.GenderTypeConverter")})
public class UserUpdateAction extends ActionSupport implements ServletRequestAware, ServletResponseAware, ApplicationContextAware {

    private User user;

    private UserDao userDao;

    private String message;

    private String nickname;

    private GenderType gender;

    @Override
    @Ordinary
    @Action(results = {@Result(type = "redirect", location = "/user-profile")})
    public String execute() throws Exception {
        ActionContext ctx = ActionContext.getContext();

        User user = (User) ctx.getSession().get("user");
        user = getUserDao().findUserByAccount(user.getAccount());
        if (getGender() != null) {
            user.setGender(getGender());
        }
        if (getNickname()!= null && getNickname().length() > 0 &&getNickname().length() < 32) {
            user.setNickname(getNickname());
        }
        getUserDao().update(user);
        ctx.getSession().replace("user", user);
        return SUCCESS;
    }

    private String oldPassword;

    private String newPassword;

    private BCryptPasswordEncoder passwordEncoder;

    @Action(value = "modify-password", results = {
            @Result(type = "json", params = {"includeProperties", "message"}),
            @Result(type = "json", name = "error", params = {"includeProperties", "message"})
    })
    @Ordinary
    public String modifyPassword() throws Exception {
        ActionContext ctx = ActionContext.getContext();
        User user = (User) ctx.getSession().get("user");

        user = getUserDao().findUserByAccount(user.getAccount());
        if (!getPasswordEncoder().matches(getOldPassword(), user.getPassword())) {
            setMessage(getText("userPasswordError"));
            return ERROR;
        } else if (getNewPassword().length() < 8) {
            setMessage(getText("userPasswordLength"));
            return ERROR;
        } else {
            user.setPassword(getPasswordEncoder().encode(getNewPassword()));
            getUserDao().update(user);
            setMessage(SUCCESS);
            ctx.getSession().remove("user");
            return SUCCESS;
        }
    }


    @Action(value = "refresh-user")
    @Ordinary
    public String refresh() throws Exception {
        String referer = request.getHeader("referer");

        ActionContext ctx = ActionContext.getContext();
        User user = (User) ctx.getSession().get("user");
        user = getUserDao().findUserByAccount(user.getAccount());
        ctx.getSession().replace("user", user);

        response.sendRedirect(referer);
        return NONE;
    }

    private File icon;

    private String iconContentType;

    private String iconFileName;

    private StorageClient storageClient;

    private String savePath;

    private String bucketName;

    @Action(value = "upload-icon", params = {"savePath", "icons/", "bucketName", "tesla-cn"}, results = {@Result(type = "redirect", location = "/")})
    @Ordinary
    public String uploadIcon() throws Exception {
        if (getIcon() != null) {

            ActionContext ctx = ActionContext.getContext();
            User user = (User) ctx.getSession().get("user");

            user = getUserDao().findUserByAccount(user.getAccount());

            String[] filename = getIconFileName().split("\\.");
            //filename: 'email' + 'suffix'
            String img = user.getAccount() + "." + filename[filename.length - 1];
            try (FileInputStream in = new FileInputStream(getIcon())) {
                if (!getStorageClient().put(in, img, getBucketName())) {
                    return ERROR;
                }
            }
            user.setIcon(img);
            getUserDao().update(user);
            ctx.getSession().replace("user", user);
            applicationContext.publishEvent(new MessageEvent("User updated"));
        }
        return SUCCESS;
    }

    private HttpServletResponse response;

    private HttpServletRequest request;

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void setServletRequest(HttpServletRequest request) {
        this.request = request;
    }

    @Override
    public void setServletResponse(HttpServletResponse response) {
        this.response = response;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public File getIcon() {
        return icon;
    }

    public void setIcon(File icon) {
        this.icon = icon;
    }

    public String getIconContentType() {
        return iconContentType;
    }

    public void setIconContentType(String iconContentType) {
        this.iconContentType = iconContentType;
    }

    public String getIconFileName() {
        return iconFileName;
    }

    public void setIconFileName(String iconFileName) {
        this.iconFileName = iconFileName;
    }

    public StorageClient getStorageClient() {
        return storageClient;
    }

    public void setStorageClient(StorageClient storageClient) {
        this.storageClient = storageClient;
    }

    public String getSavePath() {
        return savePath;
    }

    public void setSavePath(String savePath) {
        this.savePath = savePath;
    }

    public String getBucketName() {
        return bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public GenderType getGender() {
        return gender;
    }

    public void setGender(GenderType gender) {
        this.gender = gender;
    }

    public BCryptPasswordEncoder getPasswordEncoder() {
        return passwordEncoder;
    }

    public void setPasswordEncoder(BCryptPasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }
}
