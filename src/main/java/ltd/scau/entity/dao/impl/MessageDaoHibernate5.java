package ltd.scau.entity.dao.impl;

import ltd.scau.base.dao.impl.BaseDaoHibernate5;
import ltd.scau.entity.Message;
import ltd.scau.entity.dao.MessageDao;
import ltd.scau.entity.type.MessageAvailable;

import java.util.List;

public class MessageDaoHibernate5 extends BaseDaoHibernate5<Message> implements MessageDao {

    @Override
    public List<Message> findAllVisibleMessages() {
        Message message = new Message();
        message.setAvailable(MessageAvailable.VISIBLE);
        return getHibernateTemplate().findByExample(message);
    }

    @Override
    public List<Message> searchByKeyWords(String keyWords) {
        List<Message> messages = findAll(Message.class);
        for (Message m : messages) {
            if (m.getContent().contains(keyWords)) {
                messages.remove(m);
            }
        }
        return messages;
    }
}
