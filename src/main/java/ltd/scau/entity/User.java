package ltd.scau.entity;

import ltd.scau.entity.type.GenderType;
import ltd.scau.entity.type.UserLevel;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User implements Serializable {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_account")
    private String account;

    @Column(name = "user_password")
    private String password;

    @Column(name = "user_nick")
    private String nickname;

    @Column(name = "user_gender", columnDefinition = "INT default 0")
    @Enumerated(value = EnumType.ORDINAL)
    private GenderType gender;

    @OneToMany(targetEntity = Message.class, mappedBy = "user", fetch = FetchType.EAGER)
    private List<Message> messages;

    @ManyToMany(targetEntity = Message.class, fetch = FetchType.EAGER)
    @JoinTable(name = "favors", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "msg_id", referencedColumnName = "msg_id"))
    private Set<Message> favors;

    @Column(name = "sign_up_date")
    private Date date;

    @Column(name = "sign_up_time")
    private Long time;

    @Column(name = "user_remark")
    private String remark;

    @Column(name = "user_icon")
    @Lob
    private byte[] icon;

    @Column(name = "stu_id")
    private String stuId;

    @Column(name = "stu_password")
    private String stuPassword;

    @Enumerated(value = EnumType.ORDINAL)
    @Column(name = "user_level", columnDefinition = "INT default 0")
    private UserLevel level;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public byte[] getIcon() {
        return icon;
    }

    public void setIcon(byte[] icon) {
        this.icon = icon;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (!id.equals(user.id)) return false;
        return account != null ? account.equals(user.account) : user.account == null;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Long getTime() {
        return time;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", account='" + account + '\'' +
                ", password='" + password + '\'' +
                ", nickname='" + nickname + '\'' +
                ", gender=" + gender +
                ", date=" + date +
                ", time=" + time +
                ", remark='" + remark + '\'' +
                ", level=" + level +
                '}';
    }

    public GenderType getGender() {
        return gender;
    }

    public void setGender(GenderType gender) {
        this.gender = gender;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public UserLevel getLevel() {
        return level;
    }

    public void setLevel(UserLevel level) {
        this.level = level;
    }

    public String getStuId() {
        return stuId;
    }

    public void setStuId(String stuId) {
        this.stuId = stuId;
    }

    public String getStuPassword() {
        return stuPassword;
    }

    public void setStuPassword(String stuPassword) {
        this.stuPassword = stuPassword;
    }

    public Set<Message> getFavors() {
        return favors;
    }

    public void setFavors(Set<Message> favors) {
        this.favors = favors;
    }
}
