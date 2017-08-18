package ltd.scau.struts2.json;

import com.opensymphony.xwork2.ActionSupport;
import ltd.scau.entity.Message;
import ltd.scau.utils.MessageList;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.json.annotations.JSON;

import java.util.List;

@ParentPackage(value = "json-default")
@Result(type = "json", params = {
//        "excludeNullProperties", "true",
        "includeProperties", "messages\\[\\d+\\]\\.(id|content|date|time),messages\\[\\d+\\]\\.user\\.(id|nickname|gender)",
})
public class ListMessagesAction extends ActionSupport{

    private MessageList messageList;

    private List<Message> messages;

    private int pageNo;

    private int pageSize;

    @Override
    public String execute() throws Exception {
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
}
