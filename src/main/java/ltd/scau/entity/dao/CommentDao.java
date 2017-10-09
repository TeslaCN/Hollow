package ltd.scau.entity.dao;

import ltd.scau.base.dao.impl.base.dao.BaseDao;
import ltd.scau.entity.Comment;
import ltd.scau.entity.User;

import java.util.List;

public interface CommentDao extends BaseDao<Comment>{

    List<Comment> getCommentsByUser(User user);
}
