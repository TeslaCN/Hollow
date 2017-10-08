package ltd.scau.utils;

import ltd.scau.entity.Message;
import ltd.scau.entity.User;
import ltd.scau.entity.dao.MessageDao;
import ltd.scau.entity.type.MessageStatus;

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
        List<Message> messages = getMessageDao().findAllVisibleMessages();
        Collections.sort(messages);
        Collections.reverse(messages);
        for (Message m : messages) {
            if (m.getStatus().equals(MessageStatus.ANONYMOUS)) {
                m.setUser(anonymous[m.getUser().getGender().ordinal()]);
            }
        }
        setMessages(messages);
    }

    private User[] anonymous;

    public User[] getAnonymous() {
        return anonymous;
    }

    public void setAnonymous(User[] anonymous) {
        this.anonymous = anonymous;
    }
}
