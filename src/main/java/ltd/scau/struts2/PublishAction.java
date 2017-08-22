package ltd.scau.struts2;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import ltd.scau.entity.Message;
import ltd.scau.entity.User;
import ltd.scau.entity.dao.MessageDao;
import ltd.scau.entity.dao.UserDao;
import ltd.scau.event.MessageEvent;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEvent;

public class PublishAction extends ActionSupport implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    private MessageDao messageDao;

    private UserDao userDao;

    private Message message;

    @Override
    public String execute() throws Exception {
        ActionContext ctx = ActionContext.getContext();
        User user = (User) ctx.getSession().get("user");
        if (user == null) user = userDao.get(User.class, 1L);
        message.setUser(user);
        message.setTime(System.currentTimeMillis());
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

}
