package ltd.scau.utils;

import ltd.scau.entity.Message;
import ltd.scau.entity.dao.MessageDao;

import java.util.Collections;
import java.util.List;

public class MessageList {

    private List<Message> messages;

    private MessageDao messageDao;

    public List<Message> getMessages() {
        return messages;
    }

    public List<Message> getMessages(int pageNo, int pageSize) {
        int size = getMessages().size();
        int from = (pageNo - 1) * pageSize;
        int to = pageNo * pageSize;
        from = from >= 0 ? from : 0;
        to = to > 0 ? to : pageSize;
        to = to < size ? to : size;
        from = from < to ? from : to;
        return getMessages().subList(from, to);
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public MessageDao getMessageDao() {
        return messageDao;
    }

    public void setMessageDao(MessageDao messageDao) {
        this.messageDao = messageDao;
    }

    public void updateMessageList() {
        List<Message> m = getMessageDao().findAll(Message.class);
        Collections.reverse(m);
        setMessages(m);
    }
}
