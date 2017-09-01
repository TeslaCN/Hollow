package ltd.scau.struts2;

import com.aliyun.oss.OSSClient;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import ltd.scau.entity.Message;
import ltd.scau.entity.User;
import ltd.scau.entity.dao.MessageDao;
import ltd.scau.entity.dao.UserDao;
import ltd.scau.event.MessageEvent;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class PublishAction extends ActionSupport implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    private MessageDao messageDao;

    private UserDao userDao;

    private Message message;

    private File image;

    private String imageContentType;

    private String imageFileName;

    private String savePath;

    private OSSClient ossClient;

    private String bucketName;

    @Override
    @Action(results = {@Result(name = "login", location = "sign-in.jsp")}, params = {"savePath", "images/", "bucketName", "tesla-cn"})
    public String execute() throws Exception {
        ActionContext ctx = ActionContext.getContext();
        User user = (User) ctx.getSession().get("user");
        if (user == null) {
            //user = userDao.get(User.class, 1L);
            return LOGIN;
        }
        long millis = System.currentTimeMillis();
        message.setUser(user);
        message.setTime(millis);

        if (getImage() != null) {
            String[] filename = getImageFileName().split("\\.");
            // filename: 'currentMillis + user_id.(png|jpg|gif)'
            String img = savePath + millis + "_" + user.getId() + "." + filename[filename.length - 1];
            try (FileInputStream in = new FileInputStream(getImage());) {
                getOssClient().putObject(getBucketName(), img, in);
                message.setImagePath(img);
            }
        }

        messageDao.save(message);
        System.out.println(message);
        applicationContext.publishEvent(new MessageEvent(message));
        return SUCCESS;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public MessageDao getMessageDao() {
        return messageDao;
    }

    public void setMessageDao(MessageDao messageDao) {
        this.messageDao = messageDao;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public File getImage() {
        return image;
    }

    public void setImage(File image) {
        this.image = image;
    }

    public String getImageContentType() {
        return imageContentType;
    }

    public void setImageContentType(String imageContentType) {
        this.imageContentType = imageContentType;
    }

    public String getImageFileName() {
        return imageFileName;
    }

    public void setImageFileName(String imageFileName) {
        this.imageFileName = imageFileName;
    }

    public String getSavePath() {
        return ServletActionContext.getServletContext().getRealPath(savePath);
    }

    public void setSavePath(String savePath) {
        this.savePath = savePath;
    }

    public OSSClient getOssClient() {
        return ossClient;
    }

    public void setOssClient(OSSClient ossClient) {
        this.ossClient = ossClient;
    }

    public String getBucketName() {
        return bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }
}
