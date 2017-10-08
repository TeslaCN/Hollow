package ltd.scau.event;

import org.springframework.context.ApplicationEvent;

/**
 * 观察者模式
 */
public class MessageEvent extends ApplicationEvent {

    public MessageEvent(Object source) {
        super(source);
    }
}
