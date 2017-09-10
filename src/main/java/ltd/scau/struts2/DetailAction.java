package ltd.scau.struts2;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import ltd.scau.aspect.annotations.Ordinary;
import ltd.scau.entity.Comment;
import ltd.scau.entity.Message;
import ltd.scau.entity.User;
import ltd.scau.entity.dao.CommentDao;
import ltd.scau.entity.dao.MessageDao;
import ltd.scau.event.MessageEvent;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@ParentPackage("hollow-default")
public class DetailAction extends ActionSupport implements ServletResponseAware, ApplicationContextAware {

    private MessageDao messageDao;

    private Long id;

    private Message message;

    private Date date;

    @Override
    public String execute() throws Exception {
        setMessage(messageDao.get(Message.class, id));
        if (getMessage() != null) {
            return SUCCESS;
        }
        return ERROR;
    }

    private CommentDao commentDao;

    private Comment comment;

    @Action(value = "comment")
    @Ordinary
    public String comment() throws Exception {
        ActionContext ctx = ActionContext.getContext();
        User user = (User) ctx.getSession().get("user");
        getComment().setUser(user);
        getComment().setTime(System.currentTimeMillis());
        getComment().setDate(getDate());
        Message msg = messageDao.get(Message.class, id);
        getComment().setMessage(msg);
        msg.getComments().add(comment);
        getMessageDao().update(msg);
        applicationContext.publishEvent(new MessageEvent(comment));
        response.sendRedirect("detail?id=" + msg.getId().longValue());
        return NONE;
    }

    @Action(value = "like")
    @Ordinary
    public String like() throws Exception {
        ActionContext ctx = ActionContext.getContext();
        User user = (User) ctx.getSession().get("user");
        Message msg = getMessageDao().get(Message.class, getId());
        msg.getFavors().add(user);
        getMessageDao().update(msg);
        return NONE;
    }

    public MessageDao getMessageDao() {
        return messageDao;
    }

    public void setMessageDao(MessageDao messageDao) {
        this.messageDao = messageDao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    private HttpServletResponse response;
    @Override
    public void setServletResponse(HttpServletResponse httpServletResponse) {
        response = httpServletResponse;
    }

    public CommentDao getCommentDao() {
        return commentDao;
    }

    public void setCommentDao(CommentDao commentDao) {
        this.commentDao = commentDao;
    }

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
