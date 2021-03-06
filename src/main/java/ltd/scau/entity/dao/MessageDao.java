package ltd.scau.entity.dao;

import ltd.scau.base.dao.impl.base.dao.BaseDao;
import ltd.scau.entity.Message;
import ltd.scau.entity.User;

import java.util.List;

public interface MessageDao extends BaseDao<Message> {

    List<Message> searchByKeyWords(String keyWords);

    List<Message> findAllVisibleMessages();

    List<Message> getMessagesByUser(User user);
}
