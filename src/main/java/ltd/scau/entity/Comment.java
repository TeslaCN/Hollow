package ltd.scau.entity;

import ltd.scau.entity.type.MessageAvailable;
import ltd.scau.entity.type.MessageStatus;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "comments")
public class Comment implements Serializable, Comparable<Comment> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    @Column(name = "content")
    @Lob
    private String content;

    @ManyToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    private User user;

    @ManyToOne(targetEntity = Message.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "msg_id", referencedColumnName = "msg_id", nullable = false)
    private Message message;

    @Column(name = "status")
    private MessageStatus status;

    @Column(name = "comment_available", columnDefinition = "INT default 1")
    @Enumerated(value = EnumType.ORDINAL)
    private MessageAvailable available;

    @Column(name = "time", nullable = false)
    private Long time;

    @Column(name = "date")
    private Date date;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Comment comment = (Comment) o;

        return id.equals(comment.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", user=" + user +
                ", status=" + status +
                ", time=" + time +
                ", date=" + date +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public MessageStatus getStatus() {
        return status;
    }

    public void setStatus(MessageStatus status) {
        this.status = status;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public MessageAvailable getAvailable() {
        return available;
    }

    public void setAvailable(MessageAvailable available) {
        this.available = available;
    }

    @Override
    public int compareTo(Comment o) {
        if (getTime() > o.getTime()) return 1;
        else if (getTime() < o.getTime()) return -1;
        else return 0;
    }
}
