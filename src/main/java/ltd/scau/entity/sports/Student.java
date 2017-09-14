package ltd.scau.entity.sports;

import ltd.scau.entity.type.GenderType;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "students")
public class Student {

    @Column(unique = true, nullable = false)
    private int id;

    @Column(name = "stu_id", nullable = false)
    @Id
    private String stuId;

    @Column(name = "stu_name")
    private String stuName;

    @Enumerated(value = EnumType.ORDINAL)
    private GenderType gender;

    private long birthday;

    @Column(name = "barcode_url")
    private String barcodeUrl;

    @ManyToOne(targetEntity = Clazz.class)
    @JoinColumn(name = "class_id", referencedColumnName = "class_id")
    private Clazz clazz;

    @OneToMany(targetEntity = Record.class, cascade = CascadeType.ALL, mappedBy = "student")
    private List<Record> records;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Student student = (Student) o;

        return id == student.id;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", stuId='" + stuId + '\'' +
                ", stuName='" + stuName + '\'' +
                ", gender=" + gender +
                ", birthday=" + birthday +
                ", barcodeUrl='" + barcodeUrl + '\'' +
                ", clazz=" + clazz +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStuId() {
        return stuId;
    }

    public void setStuId(String stuId) {
        this.stuId = stuId;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public GenderType getGender() {
        return gender;
    }

    public void setGender(GenderType gender) {
        this.gender = gender;
    }

    public long getBirthday() {
        return birthday;
    }

    public void setBirthday(long birthday) {
        this.birthday = birthday;
    }

    public String getBarcodeUrl() {
        return barcodeUrl;
    }

    public void setBarcodeUrl(String barcodeUrl) {
        this.barcodeUrl = barcodeUrl;
    }

    public Clazz getClazz() {
        return clazz;
    }

    public void setClazz(Clazz clazz) {
        this.clazz = clazz;
    }

    public List<Record> getRecords() {
        return records;
    }

    public void setRecords(List<Record> records) {
        this.records = records;
    }
}
