package ltd.scau.utils.listener;

import ltd.scau.event.MessageEvent;
import ltd.scau.utils.MessageList;
import org.springframework.context.ApplicationListener;

public class MessageListener implements ApplicationListener<MessageEvent> {

    private MessageList messageList;

    public MessageList getMessageList() {
        return messageList;
    }

    public void setMessageList(MessageList messageList) {
        this.messageList = messageList;
    }

    @Override
    public void onApplicationEvent(MessageEvent event) {
        messageList.updateMessageList();
    }
}
