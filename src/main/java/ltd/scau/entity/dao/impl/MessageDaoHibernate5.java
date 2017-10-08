package ltd.scau.entity.dao.impl;

import ltd.scau.base.dao.impl.BaseDaoHibernate5;
import ltd.scau.entity.Message;
import ltd.scau.entity.Message_;
import ltd.scau.entity.dao.MessageDao;
import ltd.scau.entity.type.MessageAvailable;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class MessageDaoHibernate5 extends BaseDaoHibernate5<Message> implements MessageDao {

    @Override
    public List<Message> findAllVisibleMessages() {
        EntityManager entityManager = null;
        try {
            entityManager = getEntityManagerFactory().createEntityManager();
            CriteriaBuilder builder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Message> criteria = builder.createQuery(Message.class);
            Root<Message> root = criteria.from(Message.class);
            criteria.select(root);
            criteria.where(builder.equal(root.get(Message_.available), MessageAvailable.VISIBLE));
            return entityManager.createQuery(criteria).getResultList();
        } finally {
            entityManager.close();
        }
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
