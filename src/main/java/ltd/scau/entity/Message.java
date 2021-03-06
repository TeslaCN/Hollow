package ltd.scau.entity;

import ltd.scau.entity.type.MessageAvailable;
import ltd.scau.entity.type.MessageStatus;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "messages")
public class Message implements Serializable, Comparable<Message> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "msg_id")
    private Long id;

    @ManyToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    private User user;

    @Column(name = "msg_date")
    private Date date;

    @Column(name = "msg_time", nullable = false)
    private Long time;

    @Column(name = "msg_content")
    @Lob
    private String content;

    @OneToMany(cascade = CascadeType.ALL, targetEntity = Comment.class, fetch = FetchType.EAGER, mappedBy = "message")
    private List<Comment> comments;

    @ManyToMany(cascade = CascadeType.ALL, targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinTable(name = "favors", joinColumns = @JoinColumn(name = "msg_id", referencedColumnName = "msg_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "user_id"))
    private Set<User> favors;

    @Column(name = "image_path")
    private String imagePath;

    @Column(name = "msg_available", columnDefinition = "INT default 1")
    @Enumerated(value = EnumType.ORDINAL)
    private MessageAvailable available;

    @Column(name = "msg_status", columnDefinition = "INT default 0")
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

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", user=" + user +
                ", date=" + date +
                ", time=" + time +
                ", content='" + content + '\'' +
                ", available=" + available +
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

    public MessageAvailable getAvailable() {
        return available;
    }

    public void setAvailable(MessageAvailable available) {
        this.available = available;
    }

    public Set<User> getFavors() {
        return favors;
    }

    public void setFavors(Set<User> favors) {
        this.favors = favors;
    }

    public MessageStatus getStatus() {
        return status;
    }

    public void setStatus(MessageStatus status) {
        this.status = status;
    }

    @Override
    public int compareTo(Message o) {
        if (getTime() > o.getTime()) return 1;
        else if (getTime() == o.getTime()) return 0;
        else return -1;
    }
}
