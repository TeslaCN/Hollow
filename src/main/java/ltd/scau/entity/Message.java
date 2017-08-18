package ltd.scau.entity;

import ltd.scau.entity.type.MessageStatus;
import org.hibernate.annotations.TypeDef;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "messages")
public class Message implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "msg_id")
    private Long id;

    @ManyToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    private User user;

    @Column(name = "msg_date")
    private Date date;

    @Column(name = "msg_time")
    private Long time;

    @Column(name = "msg_content")
    @Lob
    private String content;

    @ManyToMany(targetEntity = Comment.class, fetch = FetchType.EAGER)
    @JoinTable
    private List<Comment> comments;

    @Column(name = "image")
    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] image;

    @Column(name = "msg_status", columnDefinition = "INT default 1")
    @Enumerated(value = EnumType.ORDINAL)
    private MessageStatus status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", user=" + user +
                ", date=" + date +
                ", time=" + time +
                ", content='" + content + '\'' +
                ", status=" + status +
                '}';
    }

    public Long getTime() {
        return time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Message message = (Message) o;

        return id.equals(message.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public MessageStatus getStatus() {
        return status;
    }

    public void setStatus(MessageStatus status) {
        this.status = status;
    }
}
