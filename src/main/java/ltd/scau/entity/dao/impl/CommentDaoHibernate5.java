package ltd.scau.entity.dao.impl;

import ltd.scau.base.dao.impl.BaseDaoHibernate5;
import ltd.scau.entity.Comment;
import ltd.scau.entity.Comment_;
import ltd.scau.entity.User;
import ltd.scau.entity.dao.CommentDao;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class CommentDaoHibernate5 extends BaseDaoHibernate5<Comment> implements CommentDao {

    @Override
    public List<Comment> getCommentsByUser(User user) {
        EntityManager entityManager = null;
        try {
            entityManager = getEntityManagerFactory().createEntityManager();
            CriteriaBuilder builder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Comment> criteria = builder.createQuery(Comment.class);
            Root<Comment> root = criteria.from(Comment.class);
            criteria.select(root);
            criteria.where(builder.equal(root.get(Comment_.user), user));
            return entityManager.createQuery(criteria).getResultList();
        } finally {
            entityManager.close();
        }
    }
}
