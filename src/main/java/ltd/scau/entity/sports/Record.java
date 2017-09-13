package ltd.scau.entity.sports;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "records")
public class Record implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "record_id")
    private Integer id;

    @ManyToOne(targetEntity = Student.class)
    @JoinColumn(name = "stu_id", referencedColumnName = "stu_id")
    private Student student;

    @OneToMany(targetEntity = Item.class, cascade = CascadeType.ALL, mappedBy = "record")
    private List<Item> items;

    @Column(name = "total_score")
    private double totalScore;

    @Column(name = "exam_id")
    private Integer examId;

    private String text;

    private String message;

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public double getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(double totalScore) {
        this.totalScore = totalScore;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getExamId() {
        return examId;
    }

    public void setExamId(Integer examId) {
        this.examId = examId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Record record = (Record) o;

        if (!id.equals(record.id)) return false;
        if (!student.equals(record.student)) return false;
        return examId.equals(record.examId);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + student.hashCode();
        result = 31 * result + examId.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Record{" +
                "id=" + id +
                ", student=" + student.getStuId() +
                ", totalScore=" + totalScore +
                ", examId=" + examId +
                ", text='" + text + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
