package ltd.scau.struts2.json;

import com.opensymphony.xwork2.ActionSupport;
import ltd.scau.entity.Comment;
import ltd.scau.entity.Message;
import ltd.scau.entity.dao.MessageDao;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

import java.util.List;

/**
 * 这个Action返回JSON格式的数据，主要用于 detail 页面的评论列表渲染
 * includeProperties 用正则表达式匹配JSON内所需要的数据
 */
@ParentPackage("json-default")
@Result(type = "json", params = {"includeProperties",
        "comments\\[\\d+\\]\\.(id|content|time|status)," +
                "comments\\[\\d+\\]\\.user\\.(id|gender|nickname)"})
public class ListCommentsAction extends ActionSupport {

    private Long id;

    private int pageNo;

    private int pageSize;

    private MessageDao messageDao;

    private List<Comment> comments;

    @Override
    public String execute() throws Exception {
        Message msg = getMessageDao().get(Message.class, getId());
        List<Comment> l = msg.getComments();
        int size = l.size();
        int from = (pageNo - 1) * pageSize;
        int to = pageNo * pageSize;
        from = from >= 0 ? from : 0;
        to = to > 0 ? to : pageSize;
        to = to < size ? to : size;
        from = from < to ? from : to;
        setComments(l.subList(from, to));
        return SUCCESS;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MessageDao getMessageDao() {
        return messageDao;
    }

    public void setMessageDao(MessageDao messageDao) {
        this.messageDao = messageDao;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
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
