package ltd.scau.struts2.json;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import ltd.scau.aspect.annotations.Ordinary;
import ltd.scau.entity.Message;
import ltd.scau.entity.User;
import ltd.scau.entity.dao.MessageDao;
import ltd.scau.entity.dao.UserDao;
import ltd.scau.entity.type.MessageAvailable;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@ParentPackage("hollow")
@Result(type = "json", params = {
        "includeProperties",
        "messages\\[\\d+\\]\\.(id|content|date|time|comments|favors|imagePath)," +
                "messages\\[\\d+\\]\\.user\\.(id|nickname|gender|icon)," +
                "messages\\[\\d+\\]\\.comments\\[\\d+\\]\\.(id|content|time)," +
                "messages\\[\\d+\\]\\.comments\\[\\d+\\]\\.user\\.(id|gender|nickname)," +
                "messageCount, pageNo, pageSize",
})
public class MyPublishedAction extends ActionSupport {

    private MessageDao messageDao;

    private UserDao userDao;

    private List<Message> messages;

    private int messageCount;

    private int pageNo;

    private int pageSize;

    @Ordinary
    @Override
    public String execute() throws Exception {
        ActionContext ctx = ActionContext.getContext();
        User user = (User) ctx.getSession().get("user");
//        List<Message> messages = getMessageDao().getMessagesByUser(user);
        List<Message> messages = new ArrayList<>();
//        messages.addAll(getUserDao().findUserByAccount(user.getAccount()).getMessages());
//        Collections.sort(messages, Comparator.comparingLong(Message::getId).reversed());
        for (Message m : getUserDao().findUserByAccount(user.getAccount()).getMessages()) {
            if (m.getAvailable().equals(MessageAvailable.VISIBLE)) {
                messages.add(m);
            }
        }
        Collections.reverse(messages);
        pageNo = pageNo < 1 ? 1 : pageNo;
        pageSize = pageSize < 1 ? 10 : pageSize;
        setMessages(getMessages(messages, pageNo, pageSize));
        setMessageCount(messages.size());
        return SUCCESS;
    }

    public List<Message> getMessages(List<Message> messages, int pageNo, int pageSize) {
        int size = messages.size();
        int from = (pageNo - 1) * pageSize;
        int to = pageNo * pageSize;
        from = from >= 0 ? from : 0;
        to = to > 0 ? to : pageSize;
        to = to < size ? to : size;
        from = from < to ? from : to;
        return messages.subList(from, to);
    }

    public MessageDao getMessageDao() {
        return messageDao;
    }

    public void setMessageDao(MessageDao messageDao) {
        this.messageDao = messageDao;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public int getMessageCount() {
        return messageCount;
    }

    public void setMessageCount(int messageCount) {
        this.messageCount = messageCount;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
