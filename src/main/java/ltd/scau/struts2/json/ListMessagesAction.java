package ltd.scau.struts2.json;

import com.opensymphony.xwork2.ActionSupport;
import ltd.scau.entity.Message;
import ltd.scau.utils.MessageList;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.json.annotations.JSON;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 这个 Action 返回 JSON 格式的数据，主要用于 homepage 的列表渲染
 * includeProperties 用正则表达式匹配JSON内所需要的数据
 */
@ParentPackage(value = "json-default")
@Result(type = "json", params = {
//        "excludeNullProperties", "true",
        "includeProperties",
        "messages\\[\\d+\\]\\.(id|content|date|time|comments|favors|imagePath)," +
                "messages\\[\\d+\\]\\.user\\.(id|nickname|gender|icon)," +
                "messages\\[\\d+\\]\\.comments\\[\\d+\\]\\.(id|content|time)," +
                "messages\\[\\d+\\]\\.comments\\[\\d+\\]\\.user\\.(id|gender|nickname)",
})
public class ListMessagesAction extends ActionSupport implements ServletResponseAware {

    private MessageList messageList;

    private List<Message> messages;

    private int pageNo;

    private int pageSize;

    @Override
    public String execute() throws Exception {
        response.addHeader("Access-Control-Allow-Origin", "*");
        setMessages(messageList.getMessages(pageNo, pageSize));
        return SUCCESS;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
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

    @JSON(serialize = false)
    public MessageList getMessageList() {
        return messageList;
    }

    public void setMessageList(MessageList messageList) {
        this.messageList = messageList;
    }

    private HttpServletResponse response;

    @Override
    public void setServletResponse(HttpServletResponse httpServletResponse) {
        this.response = httpServletResponse;
    }
}
