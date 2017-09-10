package ltd.scau.entity;

import javax.persistence.*;

@Entity
@Table(name = "exams")
public class Exam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, name = "stu_id")
    private String stuId;

    private String code;

    private String title;

    @Column(name = "stu_name")
    private String stuName;

    private String time;

    private String locale;

    private String form;

    private String seat;

    private String zone;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getStuId() {
        return stuId;
    }

    public void setStuId(String stuId) {
        this.stuId = stuId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public String getForm() {
        return form;
    }

    public void setForm(String form) {
        this.form = form;
    }

    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    @Override
    public String toString() {
        return "Exam{" +
                "id=" + id +
                ", stuId='" + stuId + '\'' +
                ", code='" + code + '\'' +
                ", title='" + title + '\'' +
                ", stuName='" + stuName + '\'' +
                ", time='" + time + '\'' +
                ", locale='" + locale + '\'' +
                ", form='" + form + '\'' +
                ", seat='" + seat + '\'' +
                ", zone='" + zone + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Exam exam = (Exam) o;

        if (!stuId.equals(exam.stuId)) return false;
        if (!code.equals(exam.code)) return false;
        if (!title.equals(exam.title)) return false;
        return stuName.equals(exam.stuName);
    }

    public boolean detailEquals(Object o) {
        if (!equals(o)) return false;

        Exam exam = (Exam) o;

        if (!time.equals(exam.time)) return false;
        if (!locale.equals(exam.locale)) return false;
        if (!form.equals(exam.form)) return false;
        if (!seat.equals(exam.seat)) return false;
        return zone.equals(exam.zone);
    }

    @Override
    public int hashCode() {
        int result = stuId.hashCode();
        result = 31 * result + code.hashCode();
        result = 31 * result + title.hashCode();
        result = 31 * result + stuName.hashCode();
        return result;
    }
}
