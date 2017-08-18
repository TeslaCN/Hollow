package ltd.scau.struts2;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import ltd.scau.entity.Message;
import ltd.scau.entity.dao.MessageDao;

public class DetailAction extends ActionSupport {

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
}
