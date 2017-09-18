package ltd.scau.entity.dao.sports.impl;

import ltd.scau.base.dao.impl.BaseDaoHibernate5;
import ltd.scau.entity.dao.sports.ItemDao;
import ltd.scau.entity.sports.Item;
import ltd.scau.entity.sports.Item_;
import ltd.scau.entity.type.OrderType;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class ItemDaoHibernate5 extends BaseDaoHibernate5<Item> implements ItemDao {

    @Override
    public int rank(int examId, int itemId, double value, OrderType order) {
        EntityManager entityManager = null;
        try {
            entityManager = getEntityManagerFactory().createEntityManager();
            CriteriaBuilder builder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Item> criteriaQuery = builder.createQuery(Item.class);
            Root<Item> root = criteriaQuery.from(Item.class);
            criteriaQuery.select(root);
            switch (order) {
                case ASCEND:
                    criteriaQuery.where(
                            builder.and(
                                    builder.lt(root.get(Item_.value), value),
                                    builder.equal(root.get(Item_.examId), examId),
                                    builder.equal(root.get(Item_.itemId), itemId)
                            )
                    );
                    break;
                case DESCEND:
                    criteriaQuery.where(
                            builder.and(
                                    builder.gt(root.get(Item_.value), value),
                                    builder.equal(root.get(Item_.examId), examId),
                                    builder.equal(root.get(Item_.itemId), itemId)
                            )
                    );
                    break;
            }
            return entityManager.createQuery(criteriaQuery).getResultList().size();
        } finally {
            entityManager.close();
        }
    }

}
