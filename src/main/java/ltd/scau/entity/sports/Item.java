package ltd.scau.entity.sports;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "items")
public class Item implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "item_id")
    private int itemId;

    private String name;

    private String unit;

    @Column(name = "update_time")
    private long updateTime;

    @Column(name = "best_score")
    private double bestScore;

    @Column(name = "value")
    private double value;

    @Column(name = "score_grade")
    private String scoreGrade;

    @Column(name = "stu_id")
    private String stuId;

    @ManyToOne(targetEntity = Record.class)
    @JoinColumn(name = "record_id", referencedColumnName = "record_id")
    private Record record;

    @Column(name = "exam_id")
    private int examId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }

    public double getBestScore() {
        return bestScore;
    }

    public void setBestScore(double bestScore) {
        this.bestScore = bestScore;
    }

    public String getScoreGrade() {
        return scoreGrade;
    }

    public void setScoreGrade(String scoreGrade) {
        this.scoreGrade = scoreGrade;
    }


    public int getExamId() {
        return examId;
    }

    public void setExamId(int examId) {
        this.examId = examId;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public Record getRecord() {
        return record;
    }

    public void setRecord(Record record) {
        this.record = record;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Item item = (Item) o;

        if (id != item.id) return false;
        if (itemId != item.itemId) return false;
        if (examId != item.examId) return false;
        return stuId.equals(item.stuId);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + itemId;
        result = 31 * result + stuId.hashCode();
        result = 31 * result + examId;
        return result;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", itemId=" + itemId +
                ", name='" + name + '\'' +
                ", unit='" + unit + '\'' +
                ", updateTime=" + updateTime +
                ", bestScore=" + bestScore +
                ", value=" + value +
                ", scoreGrade='" + scoreGrade + '\'' +
                ", stuId='" + stuId + '\'' +
                ", examId=" + examId +
                '}';
    }

    public String getStuId() {
        return stuId;
    }

    public void setStuId(String stuId) {
        this.stuId = stuId;
    }
}
