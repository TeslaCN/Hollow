package ltd.scau.struts2;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import ltd.scau.aspect.annotations.Ordinary;
import ltd.scau.entity.Comment;
import ltd.scau.entity.Message;
import ltd.scau.entity.User;
import ltd.scau.entity.dao.CommentDao;
import ltd.scau.entity.dao.MessageDao;
import ltd.scau.entity.type.MessageAvailable;
import ltd.scau.entity.type.MessageStatus;
import ltd.scau.entity.type.UserLevel;
import ltd.scau.event.MessageEvent;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * Action 主要负责处理 Message 的相关操作，例如评论、点赞等。
 */
@ParentPackage("hollow")
public class DetailAction extends ActionSupport implements ServletResponseAware, ApplicationContextAware, ServletRequestAware {

    private MessageDao messageDao;

    private Long id;

    private Message message;

    private Date date;

    /**
     * 根据传入的参数，在一个request中返回 id 所对应的 Message 的详细内容
     * @return
     * @throws Exception
     */
    @Override
    @Action(results = {@Result(name = "error", type = "redirect", location = "/")})
    public String execute() throws Exception {
        Message message = getMessageDao().get(Message.class, id);
        if (message == null || message.getAvailable().equals(MessageAvailable.INVISIBLE)) return ERROR;
        if (message.getStatus().equals(MessageStatus.ANONYMOUS)) {
            User user = new User();
            user.setId(0l);
            user.setGender(message.getUser().getGender());
            message.setUser(user);
        }
        setMessage(message);
        return SUCCESS;
    }

    private CommentDao commentDao;

    private Comment comment;

    /**
     * 根据传入的 comment 参数，设置对应 Message 的评论
     * @return
     * @throws Exception
     */
    @Action(value = "comment")
    @Ordinary
    public String comment() throws Exception {
        ActionContext ctx = ActionContext.getContext();
        User user = (User) ctx.getSession().get("user");
        getComment().setUser(user);
        getComment().setTime(System.currentTimeMillis());
        getComment().setDate(getDate());
        getComment().setStatus(MessageStatus.ONYMOUS);
        getComment().setAvailable(MessageAvailable.VISIBLE);
        Message msg = messageDao.get(Message.class, id);
        getComment().setMessage(msg);
        msg.getComments().add(comment);
        getMessageDao().update(msg);
        applicationContext.publishEvent(new MessageEvent(comment));
        response.sendRedirect("detail?id=" + msg.getId().longValue());
        return NONE;
    }

    private String result;

    /**
     * 点赞操作
     * @return
     * @throws Exception
     */
    @Action(value = "like", results = {@Result(type = "json", params = {"includeProperties", "result"})})
    @Ordinary
    public String like() throws Exception {
        ActionContext ctx = ActionContext.getContext();
        User user = (User) ctx.getSession().get("user");
        Message msg = getMessageDao().get(Message.class, getId());
        msg.getFavors().add(user);
        getMessageDao().update(msg);
        applicationContext.publishEvent(new MessageEvent(msg));
        setResult(SUCCESS);
        return NONE;
    }

    /**
     * 信息删除操作
     * @return
     * @throws Exception
     */
    @Action(value = "delete-message", results = {@Result(type = "redirect", location = "/")})
    @Ordinary
    public String deleteMessage() throws Exception {
        ActionContext ctx = ActionContext.getContext();
        User user = (User) ctx.getSession().get("user");
        Message message = getMessageDao().get(Message.class, id);
        if (message.getUser().equals(user) || user.getLevel().equals(UserLevel.ADMINISTRATOR)) {
            message.setAvailable(MessageAvailable.INVISIBLE);
            getMessageDao().update(message);
        }
        applicationContext.publishEvent(new MessageEvent(message));
        String referer = request.getHeader("referer");
        response.sendRedirect(referer);
        return NONE;
    }

    @Action(value = "delete-comment")
    @Ordinary
    public String deleteComment() throws Exception {
        ActionContext ctx = ActionContext.getContext();
        User user = (User) ctx.getSession().get("user");
        Comment comment = getCommentDao().get(Comment.class, id);
        if (comment.getUser().equals(user) || user.getLevel().equals(UserLevel.ADMINISTRATOR)) {
            comment.setAvailable(MessageAvailable.INVISIBLE);
            getCommentDao().update(comment);
        }
        String referer = request.getHeader("referer");
        response.sendRedirect(referer);
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

    private HttpServletRequest request;

    @Override
    public void setServletRequest(HttpServletRequest request) {
        this.request = request;
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

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
