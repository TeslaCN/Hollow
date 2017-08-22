package ltd.scau.struts2;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import ltd.scau.entity.Comment;
import ltd.scau.entity.Message;
import ltd.scau.entity.User;
import ltd.scau.entity.dao.MessageDao;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.ServletResponseAware;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

public class DetailAction extends ActionSupport implements ServletResponseAware{

    private MessageDao messageDao;

    private Long id;

    private Message message;

    @Override
    public String execute() throws Exception {
        setMessage(messageDao.get(Message.class, id));
        if (getMessage() != null) {
            return SUCCESS;
        }
        return ERROR;
    }

    private Comment comment;

    @Action(value = "comment", results = {@Result(name = "login", location = "sign-in.jsp")})
    public String comment() throws IOException {
        ActionContext ctx = ActionContext.getContext();
        User user = (User) ctx.getSession().get("user");
        if (user == null) {
            return LOGIN;
        }
        comment.setUser(user);
        comment.setTime(System.currentTimeMillis());
        Message msg = messageDao.get(Message.class, id);
        msg.getComments().add(comment);
        messageDao.save(msg);
        response.sendRedirect("detail?id=" + getId().longValue());
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
}
